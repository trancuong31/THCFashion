package com.shop.store.model;

public class DiscountStatus {
    private String ok;
    private String startTime;
    private String endTime;

    public DiscountStatus() {
    }

    public DiscountStatus(String ok, String startTime, String endTime) {
        this.ok = ok;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
