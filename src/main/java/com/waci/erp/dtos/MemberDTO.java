package com.waci.erp.dtos;

import com.waci.erp.models.prayers.Member;
import com.waci.erp.shared.api.BaseDTO;
import lombok.Data;

@Data
public class MemberDTO extends BaseDTO {
    private String fullName;
    private String firstName;
    private String lastName;
    private String salutationName;
    private long salutationId;
    private String genderName;
    private long genderId;
    private String middleName;
    private String physicalAddress;
    private String phoneNumber;
    private String emailAddress;
    private int yearJoined;
    private String occupationName;
    private long occupationId;
    private String nin;
    private String imageUrl;

    public static MemberDTO fromModel(Member model){
        MemberDTO dto= new MemberDTO();
        dto.setFirstName(model.getFirstName());
        dto.setFullName(model.getFullName());
        dto.setLastName(model.getLastName());
        dto.setEmailAddress(model.getEmailAddress());
        dto.setImageUrl(model.getImageUrl());
        dto.setYearJoined(model.getYearJoined());
        dto.setNin(model.getNin());

        dto.setPhoneNumber(model.getPhoneNumber());
        dto.setPhysicalAddress(model.getPhysicalAddress());
        dto.setEmailAddress(model.getEmailAddress());

        if(model.getOccupation()!=null) {
            dto.setOccupationId(model.getOccupation().getId());
            dto.setOccupationName(model.getOccupation().getValue());
        }
        if(model.getSalutation()!=null) {
            dto.setSalutationId(model.getSalutation().getId());
            dto.setSalutationName(model.getSalutation().getValue());
        }
        if(model.getGender()!=null) {
            dto.setGenderId(model.getGender().getId());
            dto.setGenderName(model.getGender().getUiName());
        }

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
