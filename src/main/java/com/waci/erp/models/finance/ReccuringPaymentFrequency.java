package com.waci.erp.models.finance;

public enum ReccuringPaymentFrequency {
    WEEKLY(1,"WEEKLY"),
    BI_WEEKLY(2,"BI_WEEKLY"),
    MONTHLY(3,"MONTHLY"),
    YEARLY(4,"YEARLY"),;
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
