package com.nadman.dao;

import com.nadman.bean.Shop;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Bohdan Romanchenko (nadman)
 * Date : 6/16/16.
 */
public class ShopDao {

    @Autowired
    SessionFactory sessionFactory;

    public Long createShop(Shop shop){
        return (Long) sessionFactory.getCurrentSession().save(shop);
    }

    public void deleteShop(Shop shop){
        sessionFactory.getCurrentSession().delete(shop);
    }

    public List<Shop> getAllShops(){
        return sessionFactory.getCurrentSession().createCriteria(Shop.class).list();
    }
}
