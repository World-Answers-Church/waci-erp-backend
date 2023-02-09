package com.waci.erp.dtos;

import com.waci.erp.models.Member;
import com.waci.erp.models.Salutation;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class MemberDTO {
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

    public Member toMember(){
        return new ModelMapper().map(this,Member.class);
    }

}
