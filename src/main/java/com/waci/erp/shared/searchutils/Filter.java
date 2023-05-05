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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Filter implements Serializable {

    private static final long serialVersionUID = 6293344849078612450L;
private List<Filter> orFilters;

public List<Filter> addOrFilter(Filter filter){
    if(this.orFilters==null){
        this.orFilters= new ArrayList<>();
    }
    this.orFilters.add(filter);
    return this.orFilters;
}
    private String key;
    private String[] keys;

    private Operator operator;

    private FieldType fieldType;

    private transient Object value;

    private transient Object valueTo;

    private transient List<Object> values;

    public Filter(String key, Operator operator, FieldType fieldType, Object value) {
        this.key = key;
        this.operator = operator;
        this.fieldType = fieldType;
        this.value = value;
    }

    public Filter(String key, Operator operator, FieldType fieldType, List<Object> values) {
        this.key = key;
        this.operator = operator;
        this.fieldType = fieldType;
        this.values = values;
    }

    public Filter(String key, Operator operator, FieldType fieldType, Object value, Object tovalue) {
        this.key = key;
        this.operator = operator;
        this.fieldType = fieldType;
        this.value = value;
        this.valueTo = tovalue;
    }

    public Filter(String[] keys, Operator operator, FieldType fieldType, Object value) {
        this.keys = keys;
        this.operator = operator;
        this.fieldType = fieldType;
        this.value = value;
    }
}
