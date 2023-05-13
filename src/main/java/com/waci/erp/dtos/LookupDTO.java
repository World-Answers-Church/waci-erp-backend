package com.waci.erp.dtos;

import com.waci.erp.shared.api.BaseDTO;
import lombok.Data;

@Data
public class LookupDTO extends BaseDTO {
    public long id;
    public String name;

    public LookupDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }


}
