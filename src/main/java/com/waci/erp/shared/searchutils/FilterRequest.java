package com.waci.erp.shared.searchutils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilterRequest implements Serializable {

    private static final long serialVersionUID = 6293344849078612450L;

    private String key;

    private Operator operator;

    private FieldType fieldType;

    private transient Object value;

    private transient Object valueTo;

    private transient List<Object> values;

    public FilterRequest(String key, Operator operator, FieldType fieldType, Object value) {
        this.key = key;
        this.operator = operator;
        this.fieldType = fieldType;
        this.value = value;
    }

    public FilterRequest(String key, Operator operator, FieldType fieldType, List<Object> values) {
        this.key = key;
        this.operator = operator;
        this.fieldType = fieldType;
        this.values = values;
    }


}
