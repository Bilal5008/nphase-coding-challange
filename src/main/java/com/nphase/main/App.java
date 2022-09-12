package com.nphase.main;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import com.nphase.service.ShoppingCartService;
import java.util.*;  
public class App {
    public static void main(String[] args) throws Exception {
        final ArrayList newCart  = new ArrayList<Product>();
        String nameValue =null;
        String categoryValue =null;
        int pricePerUnit = 0;
        int quantityForProduct =0;
        Scanner sc= new Scanner(System.in); 
        boolean takeInput = true;
        int discountedPrice = 0;


        
        while (takeInput) {
       
            System.out.print("Enter name of product- ");
            nameValue = sc.nextLine();
            while(nameValue != null && !nameValue.matches("[a-zA-Z]+")){
                System.out.println("Please enter a name in Alpha only!");
                nameValue = sc.nextLine();
            }
            System.out.print("Enter price per Unit- ");
            pricePerUnit = sc.nextInt();
            System.out.print("Enter quantity for product- ");
            quantityForProduct = sc.nextInt();
            System.out.print("Enter category for product- ");
            categoryValue = sc.next();
             newCart.add(new Product(nameValue, BigDecimal.valueOf(pricePerUnit), quantityForProduct, categoryValue));
            System.out.println("Exit? (y exits)");
            String input = sc.next();
            if (input.equals("y")) {
                takeInput =false;
                break;
            }
        }
        System.out.print("Enter discount percentage- ");
        discountedPrice = sc.nextInt();

        ShoppingCart cart = new ShoppingCart(newCart);

        ShoppingCartService shoppingCart = new ShoppingCartService();
        System.out.println("Get Calculate Value" +shoppingCart.calculateTotalPrice(cart));
        System.out.println("Get Discounted Value" +shoppingCart.discountedPrice(cart,discountedPrice));
        System.out.println("Get Discounted Value for same valeu" +shoppingCart.discountedPriceWithCategory(cart,discountedPrice));
       

    }
}
