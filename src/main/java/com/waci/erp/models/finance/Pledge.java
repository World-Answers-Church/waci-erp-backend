package com.waci.erp.models.finance;

import com.waci.erp.models.prayers.Member;
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
public class Pledge extends BaseEntity {

    @Column(name = "amount")
    private float amount;

    @Column(name = "amount_paid")
    private float amountPaid;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private PledgeStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_pledged")
    private LocalDateTime datePledged;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_cleared")
    private LocalDateTime dateCleared;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_cancelled")
    private LocalDateTime dateCancelled;
}
