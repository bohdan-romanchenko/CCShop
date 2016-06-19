package com.nadman.services;

import com.nadman.bean.Item;
import com.nadman.bean.Shop;
import com.nadman.dao.ShopDao;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bohdan Romanchenko (nadman)
 * Date : 6/16/16.
 */
@Transactional
public class ShopService {

    @Setter ShopDao shopDao;

    public Shop createShop(Shop Shop){
        Long id = shopDao.createShop(Shop);
        System.out.println("    Shop " + id + " : " + Shop.getNameShop() + " - created");
        return Shop;
    }

    public void deleteShop(Shop Shop){
        shopDao.deleteShop(Shop);
        System.out.println("    Shop " + Shop.getNameShop() + " - deleted");
    }

    public void printNamesOfShops(){
        ArrayList<Shop> listShops = (ArrayList<Shop>) shopDao.getAllShops();
        System.out.println("    ---------------------");
        if (listShops.size() > 0){
            for (Shop shop : listShops)
                System.out.println("    - " + shop.getNameShop());
        } else {
            System.out.println("    There are no shops yet.");
            System.out.print("    Press q to go to previous menu : ");
        }
    }

    public Shop getShopByName(String name){
        ArrayList<Shop> listShops = (ArrayList<Shop>) shopDao.getAllShops();
        for (Shop shop : listShops)
            if (shop.getNameShop().equals(name))
                return shop;
        return null;
    }

    public void printNamesOfItems(Shop shop){
        System.out.println("      ---------------------");
        List<Item> listItems = shop.getItems();
        for (Item item : listItems)
            if (item.getAmount() > 0)
                System.out.println("      - " + item.getNameItem() + " : " + item.getPrice() + " dollars. "
                        + item.getAmount() + " left");
    }

    public Item getItemByName(String name, Shop shop){
        List<Item> listItems = shop.getItems();
        for (Item item : listItems)
            if (item.getNameItem().equals(name))
                return item;
        return null;
    }

}
