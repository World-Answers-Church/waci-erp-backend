package com.waci.erp.models;

import com.waci.erp.shared.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "salutation")
    private Salutation salutation;
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
    @Column(name = "occupation")
    private String occupation;
    @Column(name = "nin")
    private String nin;
    @Column(name = "image_url")
    private String imageUrl;


}
