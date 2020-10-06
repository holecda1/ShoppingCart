package fim.uhk;

public class ShoppingCartItem {
    private String nazev;
    private Double cenaZaKus;
    private Double kusy;

    public ShoppingCartItem(String nazev, Double cenaZaKus, Double kusy) {
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

    public Double getCenaZaKus() {
        return cenaZaKus;
    }

    public void setCenaZaKus(Double cenaZaKus) {
        this.cenaZaKus = cenaZaKus;
    }

    public Double getKusy() {
        return kusy;
    }

    public void setKusy(Double kusy) {
        this.kusy = kusy;
    }
}
