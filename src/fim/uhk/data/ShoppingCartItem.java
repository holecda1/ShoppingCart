package fim.uhk.data;

public class ShoppingCartItem {
    private String nazev;
    private double cenaZaKus;
    private double kusy;



    public ShoppingCartItem(String nazev, double cenaZaKus, double kusy) {
        this.nazev = nazev;
        this.cenaZaKus = cenaZaKus;
        this.kusy = kusy;
    }



    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public double getCenaZaKus() {
        return cenaZaKus;
    }

    public void setCenaZaKus(double cenaZaKus) {
        this.cenaZaKus = cenaZaKus;
    }

    public double getKusy() {
        return kusy;
    }

    public void setKusy(double kusy) {
        this.kusy = kusy;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "nazev='" + nazev + '\'' +
                ", cenaZaKus=" + cenaZaKus +
                ", kusy=" + kusy +
                '}';
    }
}
