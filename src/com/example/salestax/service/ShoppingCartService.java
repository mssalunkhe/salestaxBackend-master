package com.example.salestax.service;


import com.example.salestax.model.Product;
import com.example.salestax.model.ProductType;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartService {
    private Map<Product, Integer> shoppingCart = new HashMap<>();
    TaxService taxService = new TaxService();

    public void addProduct(Product product, int quantity) {
        shoppingCart.put(product, shoppingCart.get(product)==null?quantity:shoppingCart.get(product) + quantity);
    }

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public double getTotalTax() {
        double totalTax = 0.0;
        for (Product product : shoppingCart.keySet()) {
            double tax;
            tax = (taxService.getTaxFor(product)) * shoppingCart.get(product);
            totalTax += tax;
        }
        return totalTax;
    }

    public double getTotalBillAmount() {
        double totalBillAmount = 0.0;
        for (Product product : shoppingCart.keySet()) {
            double shelfPriceOfProduct = (product.getOriginalPrice() + taxService.getTaxFor(product));
            totalBillAmount += shelfPriceOfProduct * shoppingCart.get(product);
        }
        return totalBillAmount;

    }
}
