package com.mashibing.family_service_platform.poitest;

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
