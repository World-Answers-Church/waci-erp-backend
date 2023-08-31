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
@Table(name = "organisations")
public class Organisation extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "code")
    private String code;
    @ManyToOne
    @JoinColumn(name = "category")
    private LookupValue category;
    @Column(name = "website")
    private String website;
    @Column(name = "physical_address")
    private String physicalAddress;
    @Column(name = "primary_phone_number")
    private String primaryPhoneNumber;
    @Column(name = "other_phone_number")
    private String otherPhoneNumber;
    @Column(name = "logo_url")
    private String logoUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "settings_id")
    private OrganisationSetting settings;


}
