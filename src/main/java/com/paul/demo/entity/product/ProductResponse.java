package com.paul.demo.entity.product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ProductResponse {
    private String id;
    @NotEmpty
    private String name;

    @Min(0)
    private int price;

    private String creator;

    public ProductResponse() {

    }

    public ProductResponse(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
