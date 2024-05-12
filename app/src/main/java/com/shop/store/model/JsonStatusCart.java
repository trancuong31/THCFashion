package com.shop.store.model;

public class JsonStatusCart {
    private String status;
    private String color;
    private String size;

    public JsonStatusCart() {
    }

    public JsonStatusCart(String status, String color, String size) {
        this.status = status;
        this.color = color;
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
