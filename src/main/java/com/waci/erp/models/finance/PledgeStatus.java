package com.waci.erp.models.finance;

public enum PledgeStatus {
    OPEN(0),
    COMPLETED(1),
    CANCELLED(2);
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    PledgeStatus(int id) {
        this.id = id;
    }

    public static PledgeStatus getById(long id) {
        for (PledgeStatus pledgeStatus : PledgeStatus.values()) {
            if (pledgeStatus.id == id) {
                return pledgeStatus;
            }
        }
        return null;
    }
}
