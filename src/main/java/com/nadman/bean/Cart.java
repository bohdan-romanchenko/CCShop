package com.nadman.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Created by Bohdan Romanchenko (nadman)
 * Date : 6/16/16.
 */
public class Cart {

    @Getter @Setter private ArrayList<Item> items;
    @Getter @Setter private Double totalPrice;

    public void addItemToCart(Item item){
        this.items.add(item);
        this.totalPrice += item.getPrice();
        item.setAmount(item.getAmount() - 1);
        System.out.println("      * " + item.getNameItem() + " added to your cart. ");
        System.out.println("      * Total price of your cart : " + this.totalPrice);
    }

    public void buyEverythingInCart(){
        for (Item item : this.items)
            System.out.println("   ~   You bought " + item.getNameItem());
        System.out.println("Total price is " + this.totalPrice + " dollars.");
        this.items.clear();
        this.totalPrice = 0d;
    }

    public void showAllItemsInCart(){
        for (Item item : this.items)
            System.out.println("   ~   " + item.getNameItem() + " - " + item.getPrice() + " dollars.");
        System.out.println("   ~   Total : " + this.totalPrice);
    }

    public Cart(){
        this.items = new ArrayList<Item>();
        this.totalPrice = 0d;
    }

}
