package com.excelreader.practice.service;

import com.excelreader.practice.entity.ProductManager;
import com.excelreader.practice.helper.Helper;
import com.excelreader.practice.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductManagerService {

    @Autowired
    private ProductRepo productRepo;

    public void save(MultipartFile file) {
        try {

            //all the data will be saved automatically
            List<ProductManager> productManager = Helper.convertExcelToListOfProductManager(file.getInputStream());
            this.productRepo.saveAll(productManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //for getting
    public List<ProductManager> getAllProductManagers() {
        return this.productRepo.findAll();
    }

}
