package com.waci.erp.dtos;

import com.waci.erp.models.LookupType;
import com.waci.erp.models.LookupValue;
import com.waci.erp.models.Member;
import com.waci.erp.shared.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LookupValueDTO{
private long id;
    private LookupType type;
    private String typeName;
    private String value;
    private String description;
    private String imageUrl;

    public LookupValueDTO(LookupType type, String value) {
        this.type = type;
        this.value = value;
    }

    public LookupValue toDBModel(){
        return new ModelMapper().map(this,LookupValue.class);
    }



}
