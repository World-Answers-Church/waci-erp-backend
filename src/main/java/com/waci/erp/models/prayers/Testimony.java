package com.waci.erp.models.prayers;

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
@Table(name = "testimonies")
public class Testimony extends OrganisationBaseEntity {

    @ManyToOne
    @JoinColumn(name = "type_id")
    private LookupValue type;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "prayer_request_id")
    private PrayerRequest prayerRequest;
    @Column(name = "details")
    private String details;
    @Column(name = "image_url")
    private String imageUrl;


}
