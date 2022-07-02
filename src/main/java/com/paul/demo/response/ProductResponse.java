package com.paul.demo.response;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ProductResponse {
    private String id;
    @NotEmpty
    private String name;

    @Min(0)
    private int price;

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
}
