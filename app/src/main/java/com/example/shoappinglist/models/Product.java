package com.example.shoappinglist.models;

public class Product {

    private Long id;
    private String name;
    private String note;
    private int price;

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product(Long id, String name, String note, int price){
        this.note = note;
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public String getNote() {
        return note;
    }
}
