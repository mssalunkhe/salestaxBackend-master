package com.examle.salestax;


import com.example.salestax.model.Product;
import com.example.salestax.model.ProductType;
import com.example.salestax.service.ShoppingCartService;
import com.example.salestax.service.TaxService;
import org.junit.jupiter.api.Test;

import java.io.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import static com.example.salestax.utility.FormatUtility.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testClass {
    BufferedReader reader;
    TaxService taxService;


    public testClass(String fileName) {

        try {
            reader = new BufferedReader(new FileReader(new File(fileName)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        taxService = new TaxService();


    }

    public String readNextTestLine() {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return line;
    }

    public Product prepareProduct(String line) throws Exception {
        String[] units = line.split(",");
        ProductType type;
        Boolean isImported = Boolean.FALSE;
        Product product = null;
        try {
            switch (units[1]) {
                case "BOOK":
                    type = ProductType.BOOK;
                    break;
                case "MEDICINE":
                    type = ProductType.MEDICINE;
                    break;
                case "FOOD":
                    type = ProductType.FOOD;
                    break;
                default:
                    type = ProductType.OTHER;
            }
            switch (units[2]) {
                case "Boolean.FALSE":
                    isImported = Boolean.FALSE;
                    break;
                case "Boolean.TRUE":
                    isImported = Boolean.TRUE;
            }
            Double price = Double.parseDouble(units[3]);
            Double salesTax = Double.parseDouble(units[4]);
            Double shelfPrice = Double.parseDouble(units[5]);

            product = new Product(units[0], price, type, isImported);

            checkSalesTaxAndShelfPrice(product, salesTax, shelfPrice);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array out of bound exception");
        }
        return product;
    }

    @Test
    private Boolean checkSalesTaxAndShelfPrice(Product product, Double salesTax, Double shelfPrice) {
        double calculatedSalesTax = (taxService.getTaxFor(product));
        double calculatedShelfPrice = product.getOriginalPrice() + calculatedSalesTax;
        assertEquals(salesTax, calculatedSalesTax, 0.01);
        assertEquals(shelfPrice, calculatedShelfPrice, 0.01);
        return Boolean.TRUE;
    }

    @Test
    public void checkNextReceipt() throws Exception {
        double totalSalesTax = 0;
        double totalBillAmount = 0;
        ShoppingCartService shoppingCartService= new ShoppingCartService();

        Product product;
        String line = this.readNextTestLine();

       try {
            while (Boolean.TRUE) {

                String[] units = line.split(",");
                if (units[0].equals("Total")) {
                    totalSalesTax=shoppingCartService.getTotalTax();
                    totalBillAmount=shoppingCartService.getTotalBillAmount();
                    assertEquals(Double.parseDouble((units[4])), totalSalesTax, 0.01);
                    assertEquals(Double.parseDouble(units[5]), totalBillAmount, 0.01);
                    return;
                } else {

                    product = this.prepareProduct(line);
                    double salesTax = (taxService.getTaxFor(product));
                    double shelfPrice = product.getOriginalPrice() + salesTax;
                    shoppingCartService.addProduct(product,1);
                    line = this.readNextTestLine();
                }
            }
       } catch (NullPointerException e) {
            System.out.println("Input file not properly formatted. ");
        }
    }


    public static void main(String[] argv) throws Exception {
        testClass test = new testClass("test/input.csv");

        ProductType.setImportDutyRate(5.0);

        ProductType book = ProductType.BOOK;
        ProductType food = ProductType.FOOD;
        ProductType medicine = ProductType.MEDICINE;
        ProductType other = ProductType.OTHER;

        book.setTaxRate(0.0);
        food.setTaxRate(0.0);
        medicine.setTaxRate(0.0);
        other.setTaxRate(10.0);

        String line = test.readNextTestLine();
        while (line != null) {
            test.checkNextReceipt();
            line = test.readNextTestLine();
        }


    }
}


