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
@Table(name = "pledges")
public class Pledge extends OrganisationBaseEntity {

    @Column(name = "amount")
    private float amount;

    @Column(name = "amount_paid")
    private float amountPaid;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "fundraising_cause")
    private FundraisingCause fundraisingCause;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private PledgeStatus status;

    
    @Column(name = "date_pledged")
    private LocalDateTime datePledged;

    
    @Column(name = "date_cleared")
    private LocalDateTime dateCleared;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

    
    @Column(name = "date_cancelled")
    private LocalDateTime dateCancelled;
}
