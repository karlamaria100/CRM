package Model;

import Model.Company;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rares on 4/1/2017.
 */
public class Factura implements Serializable{

    Company c;
    ArrayList<ProductFactura> products = new ArrayList<>();
    int ID;

    Factura(Company c){

        this.c = c;

    }

    public double getTotal(){
        double total = 0;
        for(int i = 0; i < products.size(); i++){
            total = total + products.get(i).getProduct().getPrice();
        }
        return total;
    }

    class ProductFactura{
        Product product = new Product();
        double quantity;

        ProductFactura(Product p, double quantity){
            this.product = p;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }


}
