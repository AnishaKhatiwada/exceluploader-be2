package com.excelreader.practice.helper;

import com.excelreader.practice.entity.ProductManager;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {
    //checks coming file is excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        //compairing contetnttype of excel
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
    }

    //converts excel to list of productmanagers
    public static List<ProductManager> convertExcelToListOfProductManager(InputStream is) {
        List<ProductManager> list = new ArrayList<>();
        try {
            //this workbook has excelsheet
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            //sheet use garna
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            //from sheet to row
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                //excel table ko row ko first ko header ignore garne
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                //cid is cell id
                int cid = 0;

                //create object of productmanager to store data
                ProductManager p = new ProductManager();

                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    //cell id zero
                    switch (cid) {
                        case 0:
                            //double numericCellValue = cell.getNumericCellValue();
                            p.setId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            p.setAddress(cell.getStringCellValue());
                            break;
                        case 2:
                            p.setName(cell.getStringCellValue());
                            break;
                        case 3:
                            p.setPhoneNumber((long) cell.getNumericCellValue());
                            break;
                        case 4:
                            p.setSalary((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
