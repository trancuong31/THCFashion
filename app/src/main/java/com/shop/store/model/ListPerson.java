package com.shop.store.model;

public class ListPerson {
    private String email;
    private String img;

    public ListPerson() {
    }

    public ListPerson(String email, String img) {
        this.email = email;
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
