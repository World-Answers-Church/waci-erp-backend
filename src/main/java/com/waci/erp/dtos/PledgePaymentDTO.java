package com.waci.erp.dtos;

import com.waci.erp.models.finance.Pledge;
import com.waci.erp.models.finance.PledgePayment;
import com.waci.erp.shared.api.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PledgePaymentDTO extends BaseDTO {
    private float amount;
    private String memberName;
    private long memberId;
    private String pledgeName;
    private long pledgeId;
    private LocalDateTime datePaid;

    public PledgePaymentDTO fromModel(PledgePayment model){
        this.setAmount(model.getAmount());
        this.setPledgeId(model.getPledge().getId());
        this.setPledgeName(model.getPledge().getMember().getFullName());
        this.setDatePaid(model.getDatePaid());
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
        return this;
    }
}
