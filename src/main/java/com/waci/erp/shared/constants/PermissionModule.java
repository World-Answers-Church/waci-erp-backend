package com.waci.erp.shared.constants;

public enum PermissionModule {
    LOOKUPS("Lookup values"),
    USERS("System Users"),
    MEMBERS("Church Members"),
    PRAYER_REQUESTS("Prayer Requests"),
    TESTIMONIES("Testimonies"),
    PROPHECIES("Prophecies");

    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    PermissionModule(String displayName) {
        this.displayName = displayName;
    }
}
