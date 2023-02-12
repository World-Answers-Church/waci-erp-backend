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
@Table(name = "prayer_requests")
public class PrayerRequest extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "type_id")
    private LookupValue type;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(name = "details")
    private String details;
    @Column(name = "image_url")
    private String imageUrl;


}
