package fim.uhk.gui;

import fim.uhk.ShoppingCartItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    JButton btnInputAdd;
    JButton btnInputRemove;
    JTextField txtInputName;
    JTextField txtInputPricePerPiece;
    JTextField txtInputPieces;


    public MainFrame(int width, int height) {
        super("PRO2 - Shopping cart");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initGui();

    }

    public void initGui() {
        JPanel panelMain = new JPanel(new BorderLayout());

        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelTable = new JPanel();
        JPanel panelFooter = new JPanel();

        JLabel lblInputName = new JLabel("Název:");
        txtInputName = new JTextField("", 15);

        JLabel lblInputPricePerPiece = new JLabel("Cena/kus");
        txtInputPricePerPiece = new JTextField("", 5);

        JLabel lblInputPieces = new JLabel("Počet kusů:");
        txtInputPieces = new JTextField("", 5);

        btnInputAdd = new JButton("Přijat");
        btnInputAdd.addActionListener(this);
        btnInputRemove = new JButton("Smazat");
        btnInputRemove.addActionListener(this);

        txtInputName.setText(String.valueOf(txtInputName));

        panelInput.add(lblInputName);
        panelInput.add(txtInputName);
        panelInput.add(lblInputPricePerPiece);
        panelInput.add(txtInputPricePerPiece);
        panelInput.add(lblInputPieces);
        panelInput.add(txtInputPieces);

        panelInput.add(btnInputAdd);


        panelMain.add(panelInput, BorderLayout.NORTH);
        panelMain.add(panelTable, BorderLayout.CENTER);
        panelMain.add(panelFooter, BorderLayout.SOUTH);

        add(panelMain);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnInputAdd) {
            System.out.println("Přidán produkt:");
        } else if (actionEvent.getSource() == btnInputRemove) {
            System.out.println("Kliknuto na smazat");
        }
    }


}
//