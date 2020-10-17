package fim.uhk.data;

import fim.uhk.data.ShoppingCart;
import fim.uhk.data.ShoppingCartItem;
import fim.uhk.gui.MainFrame;



import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            new MainFrame(800,600).setVisible(true);
            }
        });
     ShoppingCart shp = new ShoppingCart();

     shp.addItem(new ShoppingCartItem("das", 20, 2));
     shp.addItem(new ShoppingCartItem("hfgd", 30, 1));
     shp.addItem(new ShoppingCartItem("ewq", 40, 3));

    System.out.println(shp.getTotalPrice());


    }
}
