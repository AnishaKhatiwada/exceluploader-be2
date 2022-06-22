package com.excelreader.practice.repo;

import com.excelreader.practice.entity.ProductManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductManager, Integer> {

}
