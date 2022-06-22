package com.excelreader.practice.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ProductManager {
    @Id
    private Integer id;
    private String name;
    private String address;
    private Integer salary;
    private Long phoneNumber;
}
