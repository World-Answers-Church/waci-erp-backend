package com.waci.erp.models.finance;

import com.waci.erp.models.prayers.Member;
import com.waci.erp.models.prayers.OrganisationBaseEntity;
import com.waci.erp.shared.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "pledge_payment")
public class PledgePayment extends OrganisationBaseEntity {

    @Column(name = "amount")
    private float amount;

    @ManyToOne
    @JoinColumn(name = "pledge_id")
    private Pledge pledge;



    @Column(name = "date_paid")
    private LocalDateTime datePaid;

}
