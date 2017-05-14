package pao.View;

import pao.Model.*;
import pao.Network.ConnectionController;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Controller {
    private MainUI userInterface = new MainUI(this);

    public void addCustomer(String nameCustomer, String surnameCustomer){
        Customer c = new Customer(nameCustomer,surnameCustomer, 0);
        ConnectionController.getInstance().sendCustomer(c);
        importClientsList();
    }

    public void addProduct(String nameProduct, String quantityProduct, String priceProduct){
        Product p = new Product(nameProduct, Double.parseDouble(quantityProduct), Double.parseDouble(priceProduct), 0);
        ConnectionController.getInstance().sendProduct(p);
        importProductList();
    }

    public void addCompany(String nameCompany){

        Company c = new Company(nameCompany,0);
        ConnectionController.getInstance().sendCompany(c);
        importClientsList();
    }

    public void refreshClientList(ArrayList<Client> c ){
        userInterface.refreshCL(c);
    }

    public void refreshProductList(ArrayList<Product> p) {userInterface.refreshPL();}

    public void importProductList(){
        refreshProductList(ConnectionController.getInstance().requestProductList());
    }

    public void importClientsList(){
        refreshClientList(ConnectionController.getInstance().requestClientList());
    }

    public double enoughProduct(String nameProduct, String quantityProduct){

        if(getProduct(nameProduct).getQuantity() < Double.parseDouble(quantityProduct)){
            return getProduct(nameProduct).getQuantity();
        }
        return -1.0;
    }

    public Product getProduct(String nameProduct){
        return ConnectionController.getInstance().requestProduct(nameProduct);
    }

    public void removeProduct(Product p){
        ConnectionController.getInstance().deleteProduct(p.getName());
    }

    public void updateStocks(){
        // TODO STUFF
        userInterface.refreshPL();
    }

}
