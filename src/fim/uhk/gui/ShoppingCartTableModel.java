package fim.uhk.gui;

import fim.uhk.data.ShoppingCart;
import fim.uhk.data.ShoppingCartItem;


import javax.swing.table.AbstractTableModel;

public class ShoppingCartTableModel extends AbstractTableModel {

   ShoppingCart shoppingCart;



    @Override
    public int getRowCount() {
        return shoppingCart.getItems().size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ShoppingCartItem item = shoppingCart.getItems().get(rowIndex);
        switch (columnIndex){
            case 0:
                return item.getNazev();
            case 1:
                return item.getCenaZaKus();
            case 2:
                return item.getKusy();
            case 3:
                return item.getPrice();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Název";
            case 1:
                return "Cena/kus";
            case 2:
                return "Počet kusů";
            case 3:
                return "Cena celkem" ;
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
            case 3:
                return Double.class;
            case 2:
                return Integer.class;
            default:
                return null;
        }
    }



    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void refresh(){
        fireTableDataChanged();
    }
}
