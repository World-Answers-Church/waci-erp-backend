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
    private String typeName;
    private int typeId;
    private String value;
    private String description;
    private String imageUrl;

    public LookupValueDTO(LookupType type, String value) {
        this.typeId = type.getId();
        this.typeName = type.getUiName();
        this.value = value;
    }

    public static LookupValueDTO fromModel(LookupValue model){
        LookupValueDTO dto= new LookupValueDTO();
        dto.setValue(model.getValue());
        dto.setTypeName(model.getType().getUiName());
        dto.setDescription(model.getDescription());
        dto.setImageUrl(model.getImageUrl());
        dto.setTypeId(model.getType().getId());

        dto.setId(model.getId());
        dto.setRecordStatus(model.getRecordStatus().name());
        dto.setCreatedById(model.getCreatedById());
        dto.setCreatedByUsername(model.getCreatedByUsername());
        dto.setChangedById(model.getChangedById());
        dto.setChangedByUserName(model.getChangedByUsername());
        dto.setDateCreated(model.getDateCreated());
        dto.setDateChanged(model.getDateChanged());
        dto.setOrganisationCode(model.getOrganisationCode());
        return dto;
    }



}
