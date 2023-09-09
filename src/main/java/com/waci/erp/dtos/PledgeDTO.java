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

    public static PledgeDTO fromModel(Pledge model){
        PledgeDTO dto= new PledgeDTO();
        dto.setAmount(model.getAmount());
        dto.setAmountPaid(model.getAmountPaid());
        dto.setDatePledged(model.getDatePledged());
        dto.setDateCleared(model.getDateCleared());
        dto.setDateCancelled(model.getDateCancelled());
        dto.setCancellationReason(model.getCancellationReason());
        dto.setMemberId(model.getMember().getId());
        dto.setMemberName(model.getMember().getFullName());
        dto.setStatusId(model.getStatus().getId());
        dto.setStatusName(model.getStatus().name());
        dto.setFundraisingCauseId(model.getFundraisingCause().getId());
        dto.setFundraisingCauseName(model.getFundraisingCause().getName());

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
