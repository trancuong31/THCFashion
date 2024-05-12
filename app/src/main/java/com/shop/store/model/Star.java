package com.shop.store.model;

public class Star {
    private String id;
    private int idProduct;
    private int numberStar;

    public Star(String id, int idProduct, int numberStar) {
        this.id = id;
        this.idProduct = idProduct;
        this.numberStar = numberStar;
    }

    public Star() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getNumberStar() {
        return numberStar;
    }

    public void setNumberStar(int numberStar) {
        this.numberStar = numberStar;
    }
}
