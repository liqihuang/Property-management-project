package com.mashibing.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Integer count;
}
