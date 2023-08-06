package com.waci.erp.shared.constants;

import java.util.Arrays;

/**
 *
 * @author RayGdhrt
 */
public enum Gender {
    MALE(0,"Male"),
    FEMALE(1,"Female");

    private String uiName;
    private int id;

    Gender(int id,String name) {
        this.uiName = name;
        this.id=id;
    }

    public String getUiName() {
        return uiName;
    }

    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

    public int getId() {
        return id;
    }

    public static Gender fromId(int id){
        return Arrays.stream(Gender.values()).filter(gender -> gender.id == id).findFirst().orElse(null);
    }


}
