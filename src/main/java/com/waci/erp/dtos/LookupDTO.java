package com.waci.erp.dtos;

import com.waci.erp.shared.api.BaseDTO;
import lombok.Data;

@Data
public class LookupDTO extends BaseDTO {
    public long id;
    public String name;
    public String value;
    public String typeName;
    public String typeId;

    public LookupDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }


}
