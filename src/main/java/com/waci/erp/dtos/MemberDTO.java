package com.waci.erp.dtos;

import com.waci.erp.models.Member;
import com.waci.erp.models.Salutation;
import com.waci.erp.shared.api.BaseDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class MemberDTO extends BaseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private Salutation salutation;
    private String middleName;
    private String physicalAddress;
    private String phoneNumber;
    private String emailAddress;
    private int yearJoined;
    private String occupation;
    private String nin;
    private String imageUrl;

    public static MemberDTO fromModel(Member model){
        MemberDTO dto= new MemberDTO();
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEmailAddress(model.getEmailAddress());
        dto.setImageUrl(model.getImageUrl());
        dto.setNin(model.getNin());
        dto.setOccupation(model.getOccupation());
        dto.setPhoneNumber(model.getPhoneNumber());
        dto.setEmailAddress(model.getEmailAddress());


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
