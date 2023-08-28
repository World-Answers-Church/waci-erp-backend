package com.waci.erp.dtos;

import com.waci.erp.models.finance.Pledge;
import com.waci.erp.models.finance.PledgePayment;
import com.waci.erp.shared.api.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

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
    private String programName;
    private long programId;
    private LocalDateTime datePaid;

    public static PledgePaymentDTO fromModel(@NonNull PledgePayment model){
        PledgePaymentDTO dto= new PledgePaymentDTO();
        dto.setAmount(model.getAmount());
        dto.setPledgeId(model.getPledge().getId());
        dto.setPledgeName(model.getPledge().getMember().getFullName());
        dto.setDatePaid(model.getDatePaid());
        dto.setMemberId(model.getPledge().getMember().getId());
        dto.setMemberName(model.getPledge().getMember().getFullName());
        dto.setProgramId(model.getPledge().getFundraisingCause().getId());
        dto.setProgramName(model.getPledge().getFundraisingCause().getName());

        dto.setId(model.getId());
        dto.setRecordStatus(model.getRecordStatus().name());
        dto.setCreatedById(model.getCreatedById());
        dto.setCreatedByUsername(model.getCreatedByUsername());
        dto.setChangedById(model.getChangedById());
        dto.setChangedByUserName(model.getChangedByUsername());
        dto.setDateCreated(model.getDateCreated());
        dto.setDateChanged(model.getDateChanged());
        return dto;
    }
}
