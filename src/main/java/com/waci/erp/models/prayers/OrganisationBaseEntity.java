package com.waci.erp.models.prayers;

import com.waci.erp.shared.constants.Gender;
import com.waci.erp.shared.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@MappedSuperclass
public class OrganisationBaseEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "organisation")
    private Organisation organisation;


}
