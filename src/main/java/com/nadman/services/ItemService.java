package com.nadman.services;

import com.nadman.bean.Item;
import com.nadman.dao.ItemDao;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bohdan Romanchenko (nadman)
 * Date : 6/16/16.
 */
@Transactional
public class ItemService {

    @Setter ItemDao itemDao;

    public Item createItem(Item item){
        Long id = itemDao.createItem(item);
        System.out.println("      Item " + id + " : " + item.getNameItem() + " - created");
        return item;
    }

    public void deleteItem(Item item){
        itemDao.deleteItem(item);
        System.out.println("      Item " + item.getNameItem() + " - deleted");
    }
}
