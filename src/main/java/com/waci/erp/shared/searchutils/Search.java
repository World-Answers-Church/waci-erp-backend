package com.waci.erp.shared.searchutils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Search implements Serializable {

    private static final long serialVersionUID = 8514625832019794838L;

    private List<Filter> filters;

    private List<Sort> sorts;

    private Integer page;

    private Integer size;

    public List<Filter> getFilters() {
        if (Objects.isNull(this.filters)) return new ArrayList<>();
        return this.filters;
    }

    public Search addFilter(Filter filterRequest) {
        if (Objects.isNull(this.filters)){
            this.filters= new ArrayList<>();
        };
        this.filters.add(filterRequest);
        return this;
    }

    public Search addFilterEqual(FieldType fieldType, String key , Object value) {
        if (Objects.isNull(this.filters)){
            this.filters= new ArrayList<>();
        };
        this.filters.add(new Filter(key,Operator.EQUAL,fieldType,value));
        return this;
    }

    public Search addFilterLike(FieldType fieldType, String key , Object value) {
        if (Objects.isNull(this.filters)){
            this.filters= new ArrayList<>();
        };
        if(value!=null) {
            this.filters.add(new Filter(key, Operator.LIKE, fieldType, value));
        }
        return this;
    }

    public Search addFilterLike(FieldType fieldType, String[] keys , Object value) {
        if (Objects.isNull(this.filters)){
            this.filters= new ArrayList<>();
        };
        if(value!=null) {
            this.filters.add( new Filter(keys,Operator.LIKE,fieldType,value));
        }
        return this;
    }

    public Search addFilterNotEqual(FieldType fieldType, String key , Object value) {
        if (Objects.isNull(this.filters)){
            this.filters= new ArrayList<>();
        };
        this.filters.add(new Filter(key,Operator.NOT_EQUAL,fieldType,value));
        return this;
    }

    public Search addFilterBetween(FieldType fieldType, String key , Object fromValue, Object toValue) {
        if (Objects.isNull(this.filters)){
            this.filters= new ArrayList<>();
        };
        this.filters.add(new Filter(key,Operator.NOT_EQUAL,fieldType,fromValue,toValue));
        return this;
    }

    public Search addSortDescending(String key) {
        if (Objects.isNull(this.sorts)){
            this.sorts= new ArrayList<>();
        };
        this.sorts.add(new Sort(key,SortDirection.DESC));
        return this;
    }

    public Search addSortAscending(String key) {
        if (Objects.isNull(this.sorts)){
            this.sorts= new ArrayList<>();
        };
        this.sorts.add(new Sort(key,SortDirection.ASC));
        return this;
    }

    public List<Sort> getSorts() {
        if (Objects.isNull(this.sorts)) return new ArrayList<>();
        return this.sorts;
    }

}
