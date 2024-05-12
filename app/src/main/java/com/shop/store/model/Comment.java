package com.shop.store.model;

public class Comment {
    private String id;
    private String username;
    private int productId;
    private String image;
    private String comment;
    private String date;
    private Integer idUser;

    public Comment() {
    }

    public Comment(String id, String username, int productId, String image, String comment, String date, Integer idUser) {
        this.id = id;
        this.username = username;
        this.productId = productId;
        this.image = image;
        this.comment = comment;
        this.date = date;
        this.idUser = idUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
