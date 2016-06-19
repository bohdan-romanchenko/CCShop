package com.nadman.dao;

import com.nadman.bean.Item;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Bohdan Romanchenko (nadman)
 * Date : 6/16/16.
 */
public class ItemDao {

    @Autowired
    SessionFactory sessionFactory;

    public Long createItem(Item item){
        return (Long) sessionFactory.getCurrentSession().save(item);
    }

    public void deleteItem(Item item){
        sessionFactory.getCurrentSession().delete(item);
    }
}