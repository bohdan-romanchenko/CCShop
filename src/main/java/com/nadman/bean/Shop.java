package com.nadman.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bohdan Romanchenko (nadman)
 * Date : 6/16/16.
 */
@Entity
@Table(name = "CCS_SHOP")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SHOP")
    @Getter @Setter private Long id;

    @Column(name = "ADDRESS_SHOP")
    @Getter @Setter private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    @Getter @Setter private List<Item> items;

    @Column(name = "NAME_SHOP", nullable = false)
    @Getter @Setter private String nameShop;

    @Column(name = "NAME_OWNER_SHOP", nullable = false)
    @Getter @Setter private String nameOwner;

    public Shop(String address, List<Item> items, String nameShop, String nameOwner){
        this.address = address;
        this.items = items;
        this.nameShop = nameShop;
        this.nameOwner = nameOwner;
    }

    public Shop() {
    }
}