package com.waci.erp.shared.constants;

public enum PermissionConstant {
    CREATE_USERS(1,"Create/Update Users",PermissionModule.USERS),
    CREATE_ROLE(2,"Create/Update Roles",PermissionModule.USERS),
    CREATE_MEMBERS(3,"Create/Update Church Members",PermissionModule.MEMBERS),
    CREATE_PRAYER_REQUESTS(4,"Create/Update Prayer Requests",PermissionModule.PRAYER_REQUESTS),
    CREATE_PROPHECIES(5,"Create/Update Prophecies",PermissionModule.PRAYER_REQUESTS),
    CREATE_TESTIMONIES(6,"Create/Update Testimonies",PermissionModule.TESTIMONIES),


    ;
    private String name;
    private long id;
    private PermissionModule permissionModule;

    PermissionConstant(long id,String name, PermissionModule permissionModule) {
        this.id=id;
        this.name = name;
        this.permissionModule = permissionModule;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public PermissionModule getPermissionModule() {
        return permissionModule;
    }

    public static PermissionConstant getById(long id){
        for(PermissionConstant permissionConstant:PermissionConstant.values()){
            if(permissionConstant.id==id){
                return permissionConstant;
            }
        }
        return null;
    }
}
