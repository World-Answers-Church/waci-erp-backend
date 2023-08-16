package com.waci.erp.models.finance;

import com.waci.erp.models.prayers.LookupType;

public enum FundraisingPlanTypes {
    OPEN(1,"Open"),
    FIXED_VALUE(2,"Fixed Value"),
    FIXED_RECURRING(3,"Reccurring and Fixed");

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
        return null;
    }

}
