package com.shop.store.model;

public class JsonDataDetails {
    private String details;
    private String color;
    private String size;

    public JsonDataDetails() {
    }

    public JsonDataDetails(String details, String color, String size) {
        this.details = details;
        this.color = color;
        this.size = size;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
