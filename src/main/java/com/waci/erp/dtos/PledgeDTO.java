package com.waci.erp.dtos;

import com.waci.erp.models.finance.Pledge;
import com.waci.erp.models.finance.PledgeStatus;
import com.waci.erp.models.prayers.Member;
import com.waci.erp.models.prayers.Prophecy;
import com.waci.erp.shared.api.BaseDTO;
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
public class PledgeDTO extends BaseDTO {
    private float amount;
    private float amountPaid;
    private String memberName;
    private long memberId;
    private String fundraisingCauseName;
    private long fundraisingCauseId;
    private String statusName;
    private long statusId;
    private LocalDateTime datePledged;
    private LocalDateTime dateCleared;
    private String cancellationReason;
    private LocalDateTime dateCancelled;

    public PledgeDTO fromModel(Pledge model){
        this.setAmount(model.getAmount());
        this.setAmountPaid(model.getAmountPaid());
        this.setDatePledged(model.getDatePledged());
        this.setDateCleared(model.getDateCleared());
        this.setDateCancelled(model.getDateCancelled());
        this.setCancellationReason(model.getCancellationReason());
        this.setMemberId(model.getMember().getId());
        this.setMemberName(model.getMember().getFullName());
        this.setFundraisingCauseId(model.getFundraisingCause().getId());
        this.setFundraisingCauseName(model.getFundraisingCause().getName());

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
