package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javax.lang.model.util.ElementScanner14;

public class ShoppingCartService {

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public Double discountedPrice(ShoppingCart shoppingCart, int discountedPrice) {
        Double value = 0.0;
        for (int i = 0; i < shoppingCart.getProducts().size(); i++) {
            if (shoppingCart.getProducts().get(i).getQuantity() > 3) {
                value = +shoppingCart.getProducts().get(i).getPricePerUnit()
                        .multiply(BigDecimal.valueOf(shoppingCart.getProducts().get(i).getQuantity())).doubleValue()
                        - ((shoppingCart.getProducts().get(i).getPricePerUnit()
                                .multiply(BigDecimal.valueOf(shoppingCart.getProducts().get(i).getQuantity()))
                                .doubleValue()) / discountedPrice);

            } else {
                value = +shoppingCart.getProducts().get(i).getPricePerUnit()
                        .multiply(BigDecimal.valueOf(shoppingCart.getProducts().get(i).getQuantity())).doubleValue();
            }

        }
        return value;

    }

    public Double discountedPriceWithCategory(ShoppingCart shoppingCart, int discountedPrice) {
        ArrayList<Product> newArray = new ArrayList<Product>();
        int totalQuantity = 0;
        Double value = 0.0;
        Product newProduct =null;

        for (int i = 0; i < shoppingCart.getProducts().size(); i++) {
            for (int j = i + 1; j < shoppingCart.getProducts().size(); j++) {
                if (shoppingCart.getProducts().get(i).getCategory()
                        .equalsIgnoreCase(shoppingCart.getProducts().get(j).getCategory())) {
                    totalQuantity += shoppingCart.getProducts().get(i).getQuantity();
                     newProduct = new Product(shoppingCart.getProducts().get(i).getName(),
                            shoppingCart.getProducts().get(i).getPricePerUnit(), totalQuantity,
                            shoppingCart.getProducts().get(i).getCategory());
                            newArray.add(newProduct);

                } else {
                     newProduct = new Product(shoppingCart.getProducts().get(i).getName(),
                            shoppingCart.getProducts().get(i).getPricePerUnit(),
                            shoppingCart.getProducts().get(i).getQuantity(),
                            shoppingCart.getProducts().get(i).getCategory());
                            newArray.add(newProduct);
                }
            }
         
            totalQuantity = 0;
        }

        for (int i = 0; i < newArray.size(); i++) {
            if (newArray.get(i).getQuantity() > 3) {
                value = +newArray.get(i).getPricePerUnit()
                        .multiply(BigDecimal.valueOf(newArray.get(i).getQuantity())).doubleValue()
                        - ((newArray.get(i).getPricePerUnit()
                                .multiply(BigDecimal.valueOf(newArray.get(i).getQuantity()))
                                .doubleValue()) / discountedPrice);

            } else {
                value = +newArray.get(i).getPricePerUnit()
                        .multiply(BigDecimal.valueOf(newArray.get(i).getQuantity())).doubleValue();
            }

        }

        return value;

    }
}
