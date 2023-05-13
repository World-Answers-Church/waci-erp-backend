package com.waci.erp.models.finance;

import com.waci.erp.models.prayers.LookupValue;
import com.waci.erp.models.prayers.Member;
import com.waci.erp.shared.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "fundraising_causes")
public class FundraisingCause extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "category_id")
    private LookupValue category;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "image_url")
    private String imageUrl;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fundraising_plan_type")
    private FundraisingPlanTypes fundraisingPlanType;

    @Column(name = "minimum_contribution")
    private float minimumContribution ;

    @Column(name = "periodic_contribution_name")
    private float periodicContributionAmount ;

    @Column(name = "target_amount")
    private float targetAmount ;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "reccuring_payment_frequency")
    private ReccuringPaymentFrequency reccuringPaymentFrequency; ;

}
