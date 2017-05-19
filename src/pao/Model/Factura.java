package pao.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rares on 06-Apr-17.
 */
public class Factura implements Serializable{

    private int id = 0;

    transient private static final int minID = 3000;
    transient private static final int maxID = 4000;

    ArrayList<Product> listaProduse = new ArrayList<Product>();

    public ArrayList<Product> getListaProduse() {
        return listaProduse;
    }

    public int getNumberProducts(){
        return listaProduse.size();
    }

    public void add(String nameProduct, double quantityProduct, double priceProduct, int id, boolean service){
        listaProduse.add(new Product(nameProduct, quantityProduct, priceProduct, id, service));
        System.out.println(toString());

    }

    public static int generateID(){
        int range = (maxID - minID) + 1;
        return (int)(Math.random() * range) + minID;
    }

    public void add(String nameProduct, double priceProduct, int id){
        listaProduse.add(new Product(nameProduct, priceProduct, id));
        System.out.println(toString());

    }

    @Override
    public String toString() {
        return "Factura{" +
                "listaProduse=" + listaProduse +
                '}';
    }

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public double getTotalFactura(){
        double total = 0;
        for(int i = 0; i<listaProduse.size();i++){
            total = total + (listaProduse.get(i).getPrice() * listaProduse.get(i).getQuantity());
        }
        return total;
    }
}
