package com.waci.erp.dtos;

import com.waci.erp.models.finance.FundraisingCause;
import com.waci.erp.shared.api.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FundraisingCauseDTO extends BaseDTO {
    private String categoryName;
    private int categoryId;
    private String name;
    private String description;
    private String imageUrl;
    private String fundraisingPlanTypeName;
    private int fundraisingPlanTypeId;
    private float minimumContribution ;
    private float fixedOneTimeContribution ;
    private float targetAmount ;
    private float periodicContributionAmount;
    private String reccuringPaymentFrequencyName;
    private int reccuringPaymentFrequencyId;

    public static FundraisingCauseDTO fromModel(FundraisingCause model){
        FundraisingCauseDTO dto=    new FundraisingCauseDTO();
        dto.setName(model.getName());
        if(model.getCategory()!=null) {
            dto.setCategoryId((int) model.getCategory().getId());
            dto.setCategoryName(model.getCategory().getValue());
        }
        dto.setImageUrl(model.getImageUrl());
        if(model.getFundraisingPlanType()!=null) {
            dto.setFundraisingPlanTypeId((int) model.getFundraisingPlanType().getId());
            dto.setFundraisingPlanTypeName(model.getFundraisingPlanType().getUiName());
        }
        dto.setMinimumContribution(model.getMinimumContribution());
        dto.setPeriodicContributionAmount(model.getPeriodicContributionAmount());
        if(model.getReccuringPaymentFrequency()!=null) {
            dto.setReccuringPaymentFrequencyId(model.getReccuringPaymentFrequency().getId());
            dto.setReccuringPaymentFrequencyName(model.getReccuringPaymentFrequency().getUiName());
        }
        dto.setImageUrl(model.getImageUrl());

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
