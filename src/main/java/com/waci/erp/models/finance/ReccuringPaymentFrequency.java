package com.waci.erp.models.finance;

public enum ReccuringPaymentFrequency {
    WEEKLY(1,"WEEKLY"),
    BI_WEEKLY(1,"BI_WEEKLY"),
    MONTHLY(1,"MONTHLY"),
    YEARLY(1,"YEARLY"),;
    private int id;
    private String uiName;
    ReccuringPaymentFrequency(int id,String name){
        this.id=id;
        this.uiName=name;
    }

    public int getId() {
        return id;
    }

    public String getUiName() {
        return uiName;
    }

    public static ReccuringPaymentFrequency getById(long id){
        for(ReccuringPaymentFrequency permissionConstant:ReccuringPaymentFrequency.values()){
            if(permissionConstant.id==id){
                return permissionConstant;
            }
        }
        return null;
    }


}
