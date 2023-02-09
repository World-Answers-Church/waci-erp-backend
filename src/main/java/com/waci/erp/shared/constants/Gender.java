package com.waci.erp.shared.constants;

/**
 *
 * @author RayGdhrt
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    RATHER_NOT_SAY("Rather not say"),
    OTHER("Other");

    private String uiName;

    Gender(String name) {
        this.uiName = name;
    }

    public String getUiName() {
        return uiName;
    }

    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

}
