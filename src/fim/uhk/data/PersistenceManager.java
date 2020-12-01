package fim.uhk.data;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;

public class PersistenceManager extends Component implements Persistence {
    private String filename;

    public PersistenceManager(String fileName) {
        this.filename = fileName;
    }

    public PersistenceManager() {
    }

    @Override
    public void ulozitCSV(ShoppingCart shoppingCart) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(filename))) {
            for (ShoppingCartItem item : shoppingCart.getItems()) {
                out.println(item.getNazev() + ";" + item.getCenaZaKus() + ";" + item.getKusy());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nacistCSV() {
        ShoppingCart shp = new ShoppingCart();

        try (BufferedReader inp = new BufferedReader(new FileReader(filename))) {
            String radek;

            while ((radek = inp.readLine()) != null) {
                String[] pole = radek.split(";");
                double cenaZaKus = NumberFormat.getInstance().parse(pole[1]).doubleValue();
                double kusy = NumberFormat.getInstance().parse(pole[2]).doubleValue();
                shp.addItem(new ShoppingCartItem(pole[0], cenaZaKus, kusy));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void ulozitXML(ShoppingCart shoppingCart) {

    }

    @Override
    public void nacistXML() {
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
}






