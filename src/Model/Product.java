package Model;

import java.io.Serializable;

/**
 * Created by Rares on 4/1/2017.
 */
public class Product implements Serializable{

    String name;
    double quantity;
    double price;

    public Product(String name, double quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(){
        this.name = "Mere";
        this.price = 12.00;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
