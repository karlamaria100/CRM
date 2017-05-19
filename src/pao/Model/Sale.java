package pao.Model;

import java.io.Serializable;

public class Sale implements Serializable{

    double stock;
    double pret;
    String buyer;

    public String getBuyer() {
        return buyer;
    }

    public Sale(double stock, double pret, String buyer){
        this.stock = stock;
        this.pret = pret;
        this.buyer = buyer;
    }

    public double getStock() {
        return stock;
    }

    public double getPret() {
        return pret;
    }
}