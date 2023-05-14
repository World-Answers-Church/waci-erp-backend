package com.waci.erp.dtos;

import com.waci.erp.models.prayers.LookupType;
import com.waci.erp.models.prayers.LookupValue;
import com.waci.erp.shared.api.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LookupValueDTO extends BaseDTO {
private long id;
    private LookupType type;
    private String typeName;
    private int typeId;
    private String value;
    private String description;
    private String imageUrl;

    public LookupValueDTO(LookupType type, String value) {
        this.type = type;
        this.value = value;
    }

    public LookupValueDTO fromModel(LookupValue model){
        this.setValue(model.getValue());
        this.setTypeName(model.getType().name());
        this.setDescription(model.getDescription());
        this.setImageUrl(model.getImageUrl());
        this.setTypeId(model.getType().getId());

        this.setId(model.getId());
        this.setRecordStatus(model.getRecordStatus().name());
        this.setCreatedById(model.getCreatedById());
        this.setCreatedByUsername(model.getCreatedByUsername());
        this.setChangedById(model.getChangedById());
        this.setChangedByUserName(model.getChangedByUsername());
        this.setDateCreated(model.getDateCreated());
        this.setDateChanged(model.getDateChanged());
        return this;
    }



}
