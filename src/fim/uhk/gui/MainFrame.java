package fim.uhk.gui;

import fim.uhk.data.ShoppingCart;
import fim.uhk.data.ShoppingCartItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.KeyException;
import java.text.ParseException;
import java.util.InputMismatchException;

public class MainFrame extends JFrame implements ActionListener {

    JButton btnInputAdd;
    JTextField txtInputName;
    JTextField txtInputPricePerPiece;
  //  JTextField txtInputPieces;
    JSpinner spInputPieces;
    JLabel lblTotalPrice;
    MainFrame mainFrame = this;
    ShoppingCartTableModel model;



    ShoppingCart shoppingCart;

    public MainFrame(int width, int height) {
        super("PRO2 - Shopping cart");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        shoppingCart = new ShoppingCart();
        model = new ShoppingCartTableModel();
        model.setShoppingCart(shoppingCart);

        initGui();

    }

    public void initGui() {
        JPanel panelMain = new JPanel(new BorderLayout());

        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelTable = new JPanel(new BorderLayout());
        JPanel panelFooter = new JPanel(new BorderLayout());

        JLabel lblInputName = new JLabel("Název:");
        txtInputName = new JTextField("", 10);

        JLabel lblInputPricePerPiece = new JLabel("Cena/kus");
        txtInputPricePerPiece = new JTextField("", 5);


        JLabel lblInputPieces = new JLabel("Počet kusů:");
        //txtInputPieces = new JTextField("", 5);
        spInputPieces = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));

        btnInputAdd = new JButton("Přijat");
        btnInputAdd.addActionListener(this);


        panelInput.add(lblInputName);
        panelInput.add(txtInputName);
        panelInput.add(lblInputPricePerPiece);
        panelInput.add(txtInputPricePerPiece);
        panelInput.add(lblInputPieces);
        panelInput.add(spInputPieces);
        panelInput.add(btnInputAdd);


        JTable table = new JTable();
        table.setModel(model);
        panelTable.add(new JScrollPane(table));

        lblTotalPrice = new JLabel("Celková cena: ");
        panelFooter.add(lblTotalPrice, BorderLayout.WEST);


        panelMain.add(panelInput, BorderLayout.NORTH);
        panelMain.add(panelTable, BorderLayout.CENTER);
        panelMain.add(panelFooter, BorderLayout.SOUTH);

        add(panelMain);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnInputAdd && validace()) {
            ShoppingCartItem newItem = new ShoppingCartItem(txtInputName.getText(), Double.parseDouble(txtInputPricePerPiece.getText()), (int) spInputPieces.getValue());

            for(ShoppingCartItem item : shoppingCart.getItems()){
                if(newItem.getNazev().equals(item.getNazev()) && newItem.getNazev().equalsIgnoreCase(item.getNazev())){

                }else {

                }
            }
            shoppingCart.addItem(newItem);

            lblTotalPrice.setText("Celková cena: " + shoppingCart.getTotalPrice() + " KČ.");
            model.fireTableDataChanged();
            JOptionPane.showMessageDialog(mainFrame, "Položka přidána", "Úspěch", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private boolean validace(){
        boolean valid = true;
        try {
            double cenaZaKus = Double.parseDouble(txtInputPricePerPiece.getText());
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "chybny format cisla", "CHYBA", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }
           if(txtInputName.getText().equals("")){
               JOptionPane.showMessageDialog(this,"Musite zadat jmeno polozky", "CHYBA", JOptionPane.ERROR_MESSAGE );
               valid = false;
           }

        return  valid;
    }

  }





//