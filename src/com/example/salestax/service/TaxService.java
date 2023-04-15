package com.example.salestax.service;

import com.example.salestax.model.Product;
import com.example.salestax.utility.FormatUtility;

import static com.example.salestax.utility.FormatUtility.*;

public class TaxService {
    private Double taxRate = 0.0;

    public Double getTaxRateFor(Product product) {
        return product.getType().getTaxRate(product.isImported());
    }

    public Double getTaxFor(Product product) {
        Double tax = (double) 0;
        tax = product.getOriginalPrice() * getTaxRateFor(product) / 100;
        return Double.parseDouble( FormatUtility.format(tax));


    }


}
