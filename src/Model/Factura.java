package Model;

import oracle.jrockit.jfr.StringConstantPool;

import java.util.ArrayList;

/**
 * Created by rares on 06-Apr-17.
 */
public class Factura {

    public class ProductFactura{
        private String nameProduct;
        private String quantityProduct;
        private double price;

        ProductFactura(String nameProduct, String quantityProduct, double price){
            this.nameProduct = nameProduct;
            this.quantityProduct = quantityProduct;
            this.price = price;
        }

    }

    ArrayList<Product> listaProduse = new ArrayList<Product>();

    public ArrayList<Product> getListaProduse() {
        return listaProduse;
    }

    public int getNumberProducts(){
        return listaProduse.size();
    }

    public void add(String nameProduct, double quantityProduct, double priceProduct){
        listaProduse.add(new Product(nameProduct, quantityProduct, priceProduct));
        System.out.println(toString());

    }

    @Override
    public String toString() {
        return "Factura{" +
                "listaProduse=" + listaProduse +
                '}';
    }
}
