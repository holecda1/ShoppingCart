package fim.uhk.gui;

import fim.uhk.data.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainFrame extends JFrame implements ActionListener {

    private Persistence persistenceManager = new PersistenceManager("storage.csv");
    JCheckBoxMenuItem chbConfirm = new JCheckBoxMenuItem("Dotazat se pred smazanim zaznamu",true);


    JButton btnInputAdd;
    JTextField txtInputName;
    JTextField txtInputPricePerPiece;
    JSpinner spInputPieces;
    JLabel lblTotalPrice;
    JTable table;
    MainFrame mainFrame = this;
    ShoppingCart shoppingCart;
    ShoppingCartTableModel model;






    public MainFrame(int width, int height) {
        super("PRO2 - Shopping cart");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



        shoppingCart = new ShoppingCart();
        model = new ShoppingCartTableModel();
        model.setShoppingCart(shoppingCart);


        initGui();
        updateFooter();

    }

    public void initGui() {
        JPanel panelMain = new JPanel(new BorderLayout());

        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelTable = new JPanel(new BorderLayout());
        JPanel panelFooter = new JPanel(new BorderLayout());

        createMenuBar();

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


        table = new JTable();
        table.setModel(model);
        panelTable.add(new JScrollPane(table));

        lblTotalPrice = new JLabel();
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
            clearInput();
            boolean isDuplicated = false;
            for(ShoppingCartItem item : shoppingCart.getItems()){
                if(newItem.getCenaZaKus()==item.getCenaZaKus() && newItem.getNazev().equalsIgnoreCase(item.getNazev())){
                    item.setKusy(item.getKusy() + newItem.getKusy());
                    isDuplicated = true;
                    break;
                }
            }
            if(!isDuplicated) {
                shoppingCart.addItem(newItem);
            }
            updateFooter();

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
           if(txtInputName.getText().isBlank()){
               JOptionPane.showMessageDialog(this,"Musite zadat jmeno polozky", "CHYBA", JOptionPane.ERROR_MESSAGE );
               valid = false;
           }


        return  valid;
    }
    private void updateFooter(){
        lblTotalPrice.setText("Celková cena: " + String.format("%.2f", shoppingCart.getTotalPrice()));
    }
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Soubor");
        fileMenu.add(new AbstractAction("Nový nákupní seznam") {
            @Override
            public void actionPerformed(ActionEvent e) {

                novySeznam();
            }
        });
        fileMenu.add(new AbstractAction("Otevřít") {
            @Override
            public void actionPerformed(ActionEvent e) {
                nacist();
            }
        });
        fileMenu.add(new AbstractAction("Uložit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ulozit();
            }
        });
        fileMenu.add(new AbstractAction("Uložit Jako") {
            @Override
            public void actionPerformed(ActionEvent e) {

                ulozitJako();
            }
        });
        menuBar.add(fileMenu);

        JMenu aboutMenu = new JMenu("O programu");

        menuBar.add(aboutMenu);

        setJMenuBar(menuBar);
    }
    private void novySeznam(){
        if (!chbConfirm.isSelected() || JOptionPane.showConfirmDialog(this,"Neuložená data budou ztracena, přejete si přesto vytvořit nový seznam??","Upozornění",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION)
                 shoppingCart.clear();
                 model.refresh();
                 clearInput();
    }

    private void ulozit() {
        try {
            persistenceManager.ulozitCSV(shoppingCart);
            System.out.println("Data byla ulozena\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void nacist(){
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream fis = new FileInputStream(fc.getSelectedFile());
                persistenceManager.nacistCSV();
                model.fireTableDataChanged();
                fis.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Soubor nelze otevrit" + "!",
                        "Chyba",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void ulozitJako(){
        JFileChooser fc = new JFileChooser();
        try {
            if (fc.getName().endsWith(".csv")) {
                persistenceManager.ulozitCSV(shoppingCart);
            } else if (fc.getName().endsWith(".xml")) {
                persistenceManager.ulozitXML(shoppingCart);
            } else {
                JOptionPane.showMessageDialog(this, "špatně zadaná přípona", "CHYBA", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
    }

    private void saveFileCsv(){
            try {
                persistenceManager.ulozitCSV(shoppingCart);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

        private void loadFileXmlSax(){
            try {
                CharArrayWriter content = new CharArrayWriter();

                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                parser.parse(new File("src/fim/uhk/shoppingCart.xml"), new DefaultHandler(){
                    @Override
                    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                        super.startElement(uri, localName, qName, attributes);
                        content.reset();

                    }
                    @Override
                    public void endElement(String uri, String localName, String qName) throws SAXException {
                        System.out.println(qName + ":" + content.toString());
                    }

                    @Override
                    public void characters(char[] ch, int start, int length) throws SAXException {
                        super.characters(ch, start, length);
                        content.write(ch, start, length);
                    }
                });

                System.out.println(content);


            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        private void loadFileXmlDom(){
        try {
        DocumentBuilder builder = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder();
            Document document = builder.parse(new File("src/fim/uhk/shoppingCart.xml"));

            Node root = document.getFirstChild();
            if(root.hasAttributes()){
                NodeList list = root.getChildNodes();
                for(int i = 0; i < list.getLength(); i++){
                    Node newNode = list.item(i);
                }
            }

        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e) {
        e.printStackTrace();
        } catch (IOException e){
        e.printStackTrace();
    }
  }

  private void clearInput() {
        txtInputName.setText("");
        txtInputPricePerPiece.setText("");
        lblTotalPrice.setText("");
    }


}
