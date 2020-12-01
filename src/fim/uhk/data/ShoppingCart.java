package fim.uhk.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<ShoppingCartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<ShoppingCartItem>();
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void addItem(ShoppingCartItem newItem){

            items.add(newItem);

    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (ShoppingCartItem item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
    public void clear(){
        items.clear();
    }


    }

















