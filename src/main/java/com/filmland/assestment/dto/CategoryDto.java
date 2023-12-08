package com.filmland.assestment.dto;

import lombok.Getter;

@Getter
public class CategoryDto {

    private String name;

    private int availableContent;
    private double price;

    public CategoryDto(String name, int availableContent, double price) {
        this.name = name;
        this.availableContent = availableContent;
        this.price = price;
    }
}
