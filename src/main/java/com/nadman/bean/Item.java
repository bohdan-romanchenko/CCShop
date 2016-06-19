package com.nadman.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Bohdan Romanchenko (nadman)
 * Date : 6/16/16.
 */
@Entity
@Table(name = "CCS_ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    @Getter @Setter private Long id;

    @Column(name = "NAME_ITEM", nullable = false)
    @Getter @Setter private String nameItem;

    @Column(name = "AMOUNT_ITEM")
    @Getter @Setter private Integer amount;

    @Column(name = "PRICE_ITEM")
    @Getter @Setter private Double price;

    @ManyToOne
    @JoinColumn(name = "SHOP_ID")
    @Getter @Setter private Shop shop;

    public Item(String nameItem, Integer amount, Double price){
        this.nameItem = nameItem;
        this.amount = amount;
        this.price = price;
    }

    public Item() {
    }
}