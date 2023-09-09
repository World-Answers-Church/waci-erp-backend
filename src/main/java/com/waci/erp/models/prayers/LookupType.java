package com.waci.erp.models.prayers;

public enum LookupType {
    OTHER_TYPE(0,"Other"),
    PRAYER_REQUEST_TYPE(1,"Prayer Request Types"),
    TESTIMONY_TYPE(2,"Testimony Types"),
    SALUTATION(3,"Salutations"),
    PROPHECY_TYPES(4,"Prophecy Types"),
    OCCUPATION_TYPES(5,"Occupation Types"),
    FUNDRAISING_CAUSE_CATEGORIES(6,"Fundraising Categories"),
    ORGANISATION_CATEGORIES(7,"Organisation Categories");;

    private int id;
    private String uiName;
    LookupType(int id,String name){
        this.id=id;
        this.uiName=name;
    }

    public int getId() {
        return id;
    }

    public String getUiName() {
        return uiName;
    }

    public static LookupType getById(long id){
        for(LookupType permissionConstant:LookupType.values()){
            if(permissionConstant.id==id){
                return permissionConstant;
            }
        }
        return null;
    }
}
