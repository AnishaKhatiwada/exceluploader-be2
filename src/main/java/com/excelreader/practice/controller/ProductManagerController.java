package com.excelreader.practice.controller;

import com.excelreader.practice.entity.ProductManager;
import com.excelreader.practice.helper.Helper;
import com.excelreader.practice.service.ProductManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin("http://localhost:4200/")
// @CrossOrigin("*")
public class ProductManagerController {

    @Autowired
    private ProductManagerService productManagerService;

    @PostMapping("/productManager/upload")
    //multipartfile bata file liyera @requestparam ko file ma upload hunxa
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (Helper.checkExcelFormat(file)) {
            //if file is excel then save
            this.productManagerService.save(file);
            //give message
            return ResponseEntity.ok("file is upload and data is saved to db");
            //true
        }
        //excel file haina vane
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file");

    }

    @GetMapping("/productManager")
    public List<ProductManager> getAllProductManager() {
        return this.productManagerService.getAllProductManagers();
    }
}
