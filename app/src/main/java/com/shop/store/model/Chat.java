package com.shop.store.model;

public class Chat {
    private String user;
    private String id;
    private String message;
    private String date;
    private String avatarUser;
    private String avatarAdmin;
    private String type;

    public Chat() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Chat(String user, String id, String message, String date, String avatarUser, String avatarAdmin, String type) {
        this.user = user;
        this.id = id;
        this.message = message;
        this.date = date;
        this.avatarUser = avatarUser;
        this.avatarAdmin = avatarAdmin;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvatarUser() {
        return avatarUser;
    }

    public void setAvatarUser(String avatarUser) {
        this.avatarUser = avatarUser;
    }

    public String getAvatarAdmin() {
        return avatarAdmin;
    }

    public void setAvatarAdmin(String avatarAdmin) {
        this.avatarAdmin = avatarAdmin;
    }
}
