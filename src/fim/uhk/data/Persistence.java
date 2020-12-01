package fim.uhk.data;

public interface Persistence {


    void ulozitCSV(ShoppingCart shoppingCart);

    void nacistCSV();

    void ulozitXML(ShoppingCart shoppingCart);

    void nacistXML();



}
