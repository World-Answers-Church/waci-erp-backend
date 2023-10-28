package com.waci.erp.models.finance;

import com.waci.erp.models.prayers.LookupType;
import com.waci.erp.shared.exceptions.ValidationFailedException;

public enum FundraisingPlanTypes {
    OPEN(1,"Open (any amount)"),
    FIXED_VALUE(2,"Fixed Value (once)"),
    FIXED_RECURRING(3,"Recurring and Fixed (periodic payment)");

    private int id;
    private String uiName;
    FundraisingPlanTypes(int id,String name){
        this.id=id;
        this.uiName=name;
    }

    public int getId() {
        return id;
    }

    public String getUiName() {
        return uiName;
    }

    public static FundraisingPlanTypes getById(long id){
        for(FundraisingPlanTypes permissionConstant:FundraisingPlanTypes.values()){
            if(permissionConstant.id==id){
                return permissionConstant;
            }
        }
     throw new ValidationFailedException(String.format("FundraisingPlanType with id %d was not found", id));
    }

}
