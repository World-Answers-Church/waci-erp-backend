package com.waci.erp.dtos;

import com.waci.erp.models.LookupValue;
import com.waci.erp.models.Member;
import com.waci.erp.models.PrayerRequest;
import com.waci.erp.models.Testimony;
import com.waci.erp.shared.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TestimonyDTO extends BaseEntity {
    private LookupValue type;
    private String typeName;
    private long memberId;
    private Member member;
    private PrayerRequest prayerRequest;
    private long prayerRequestId;
    private String details;
    private String imageUrl;

    public Testimony toDBObject(){
        return new ModelMapper().map(this,Testimony.class);
    }
}
