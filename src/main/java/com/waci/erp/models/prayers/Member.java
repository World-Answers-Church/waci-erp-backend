package com.waci.erp.models.prayers;

import com.waci.erp.shared.constants.Gender;
import com.waci.erp.shared.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "members")
public class Member extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "salutation_category")
    private LookupValue salutation;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "physical_address")
    private String physicalAddress;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "year_joined")
    private int yearJoined;
    @ManyToOne
    @JoinColumn(name = "occupation_category")
    private LookupValue occupation;
    @Column(name = "nin")
    private String nin;
    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender  gender;
    @Transient
    public String getFullName(){
        if(StringUtils.isNotBlank(this.middleName)) {
            return this.firstName + " " + this.lastName;
        }else {
            return this.firstName + " "+this.middleName+" " + this.lastName;
        }
    }


}
