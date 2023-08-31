package com.waci.erp.dtos;

import com.waci.erp.models.prayers.Prophecy;
import com.waci.erp.shared.api.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProphecyDTO extends BaseDTO {
    private String typeName;
    private long typeId;
    private long memberId;
    private String memberName;
    private String details;
    private String imageUrl;

    public ProphecyDTO fromModel(Prophecy model){
        this.setTypeId(model.getType().getId());
        this.setTypeName(model.getType().getValue());
        this.setDetails(model.getDetails());
        this.setImageUrl(model.getImageUrl());
        this.setMemberId(model.getMember().getId());
        this.setMemberName(model.getMember().getFullName());

        this.setId(model.getId());
        this.setRecordStatus(model.getRecordStatus().name());
        this.setCreatedById(model.getCreatedById());
        this.setCreatedByUsername(model.getCreatedByUsername());
        this.setChangedById(model.getChangedById());
        this.setChangedByUserName(model.getChangedByUsername());
        this.setDateCreated(model.getDateCreated());
        this.setDateChanged(model.getDateChanged());
        this.setOrganisationCode(model.getOrganisationCode());
        return this;
    }
}
