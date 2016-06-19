package com.nadman;

import com.nadman.bean.Cart;
import com.nadman.bean.Item;
import com.nadman.bean.Shop;
import com.nadman.services.ItemService;
import com.nadman.services.ShopService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * Created by Bohdan Romanchenko (nadman)
 * Date : 6/16/16.
 */
public class MainClass {

    private static String answer = "";
    private static Cart cart = new Cart();
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext(new String[]{"springConfig.xml"});

        ShopService shopService = (ShopService) context.getBean("shopService");
        System.out.println("Welcome!");

        while (true){
            System.out.println("Press 1 to choose shop");
            System.out.println("Press 2 to create shop");
            System.out.println("Press 3 to delete shop");
            System.out.println("Press q to finish");
            System.out.print("Your answer : ");

            Scanner read = new Scanner(System.in);
            answer = read.next();

            if (answer.equals("1")){
                chooseShop(shopService, read);
            } else if(answer.equals("2")){
                createShop(shopService, read);
            } else if(answer.equals("3")){
                deleteShop(shopService, read);
            }
            else if (answer.equals("q")){
                break;
            }
        }
    }

    private static void deleteShop(ShopService shopService, Scanner read){
        while(true){
            shopService.printNamesOfShops();
            System.out.println("    Input name of shop to delete and q to quit: ");
            answer = read.next();
            if (answer.equals("q"))
                break;
            else {
                Shop shop = shopService.getShopByName(answer);
                if (shop == null)
                    System.out.println("    There is no shop with name " + answer);
                else
                    shopService.deleteShop(shop);
            }
        }
    }

    private static void createShop(ShopService shopService, Scanner read) {
        Shop shop = new Shop();
        System.out.print("Input name of shop : ");
        shop.setNameShop(read.next());

        System.out.print("Input shop owners name : ");
        shop.setNameOwner(read.next());

        System.out.print("Input shop address : ");
        shop.setAddress(read.next());

        Shop createdShop = shopService.createShop(shop);
        System.out.println("Shop " + createdShop.getNameShop() + " successfully created!");
    }

    private static void chooseShop(ShopService shopService, Scanner read){
        while (true){
            shopService.printNamesOfShops();
            System.out.print("    Input name of shop to choose or q to quit: ");
            answer = read.next();
            if (answer.equals("q"))
                break;
            else{
                Shop shop = shopService.getShopByName(answer);
                if (shop == null){
                    System.out.println("    There is no shop with name " + answer);
                    break;
                }
                while (true){
                    shopService.printNamesOfItems(shop);
                    System.out.println("      Print name of item to buy or print create/delete to create/delete new item.");
                    System.out.println("      Print buy to buy everything in cart. Print show to show all items in cart.");
                    System.out.print("      q to quit : ");
                    answer = read.next();
                    if (answer.equals("q"))
                        break;
                    else if (answer.equals("create"))
                        createItem(read, shop);
                    else if(answer.equals("delete"))
                        deleteItem(read, shop, shopService);
                    else if(answer.equals("buy"))
                        cart.buyEverythingInCart();
                    else if(answer.equals("show"))
                        cart.showAllItemsInCart();
                    else{
                        Item item = shopService.getItemByName(answer, shop);    //catch null
                        cart.addItemToCart(item);
                    }
                }
            }
        }
    }

    private static void deleteItem(Scanner read, Shop shop, ShopService shopService) {
        while (true){
            ItemService itemService = (ItemService) context.getBean("itemService");
            shopService.printNamesOfItems(shop);
            System.out.print("      Print name of item which you want to delete and q to quit : ");
            answer = read.next();
            if (answer.equals("q")){
                break;
            } else{
                Item item = shopService.getItemByName(answer, shop);    //catch null
                if (item == null){
                    System.out.println("      There is no item with name " + answer);
                    break;
                }
                shop.getItems().remove(item);
                itemService.deleteItem(item);
            }
        }

    }

    private static Item createItem(Scanner read, Shop currentShop) {
        ItemService itemService = (ItemService) context.getBean("itemService");
        Item item = new Item();
        System.out.print("      Input name of item: ");
        answer = read.next();
        item.setNameItem(answer);

        System.out.print("      Input amount of items: ");
        answer = read.next();
        item.setAmount(Integer.valueOf(answer));

        System.out.print("      Input items price: ");
        answer = read.next();
        item.setPrice(Double.valueOf(answer));

        item.setShop(currentShop);

        itemService.createItem(item);
        currentShop.getItems().add(item);
        System.out.println("      Item " + item.getNameItem() + " in " + currentShop.getNameShop() + " shop successfully created!");

        return item;
    }
}
