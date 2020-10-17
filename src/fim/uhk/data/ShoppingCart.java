package fim.uhk.data;

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

    public void addItem(ShoppingCartItem item){

        items.add(item);
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (ShoppingCartItem item : items) {
            totalPrice += item.getCenaZaKus() * item.getKusy();
        }
        return totalPrice;
    }


}