package pao.Model;

import java.io.Serializable;

/**
 * Created by Rares on 4/1/2017.
 */
public class Product implements Serializable{

    private int id;
    private String name;
    private double quantity;
    private double price;
    transient private static final int minID = 4000;
    transient private static final int maxID = 5000;
    private boolean services;

    public static int generateID(){
        int range = (maxID - minID) + 1;
        return (int)(Math.random() * range) + minID;
    }

    public Product(String name, double quantity, double price, int id, boolean services) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.id = id;
        this.services = services;
    }

    public Product(String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isServices() {
        return services;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
