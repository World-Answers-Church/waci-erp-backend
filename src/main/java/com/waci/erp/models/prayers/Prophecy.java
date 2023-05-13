package com.waci.erp.models.prayers;

import com.waci.erp.shared.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "prophecies")
public class Prophecy extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "prophecy_type_id")
    private LookupValue type;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(name = "details")
    private String details;
    @Column(name = "image_url")
    private String imageUrl;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_fulfilled")
    private Date dateFulfilled;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_given")
    private Date dateGiven;
    @Column(name = "fulfillment_notes")
    private String fulfillmentNotes;




}
