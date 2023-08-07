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

    public FundraisingCauseDTO fromModel(FundraisingCause model){
        this.setName(model.getName());
        if(model.getCategory()!=null) {
            this.setCategoryId((int) model.getCategory().getId());
            this.setCategoryName(model.getCategory().getValue());
        }
        this.setImageUrl(model.getImageUrl());
        if(model.getFundraisingPlanType()!=null) {
            this.setFundraisingPlanTypeId((int) model.getFundraisingPlanType().getId());
            this.setFundraisingPlanTypeName(model.getFundraisingPlanType().getUiName());
        }
        this.setMinimumContribution(model.getMinimumContribution());
        this.setPeriodicContributionAmount(model.getPeriodicContributionAmount());
        if(model.getReccuringPaymentFrequency()!=null) {
            this.setReccuringPaymentFrequencyId(model.getReccuringPaymentFrequency().getId());
            this.setReccuringPaymentFrequencyName(model.getReccuringPaymentFrequency().getUiName());
        }
        this.setImageUrl(model.getImageUrl());

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
