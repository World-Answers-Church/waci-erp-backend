package com.waci.erp.dtos;

import com.waci.erp.models.prayers.Organisation;
import com.waci.erp.shared.api.BaseDTO;
import lombok.Data;

@Data
public class OrganisationDTO extends BaseDTO {
    private String name;
    private String code;
    private String emailAddress;
    private String categoryName;
    private long categoryId;
    private String website;
    private String physicalAddress;
    private String primaryPhoneNumber;
    private String otherPhoneNumber;
    private String logoUrl;
    public static OrganisationDTO fromModel(Organisation model){
        OrganisationDTO dto= new OrganisationDTO();
        dto.setName(model.getName());
        dto.setPrimaryPhoneNumber(model.getPrimaryPhoneNumber());
        dto.setOtherPhoneNumber(model.getOtherPhoneNumber());
        dto.setEmailAddress(model.getEmailAddress());
        dto.setCode(model.getCode());

        dto.setLogoUrl(model.getLogoUrl());
        dto.setPhysicalAddress(model.getPhysicalAddress());
        dto.setEmailAddress(model.getEmailAddress());

        if(model.getCategory()!=null) {
            dto.setCategoryId(model.getCategory().getId());
            dto.setCategoryName(model.getCategory().getValue());
        }


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
