package pao.View;

import pao.Model.*;
import pao.Network.ConnectionController;

import java.util.ArrayList;

public class Controller {
    private MainUI userInterface = new MainUI(this);

    public Controller(){
        ConnectionController.getInstance();
    }

    public void addCustomer(String nameCustomer, String surnameCustomer){
        Customer c = new Customer(nameCustomer,surnameCustomer, 0);
        ConnectionController.getInstance().sendCustomer(c);
        importClientsList();
    }

    public void addProduct(String nameProduct, String quantityProduct, String priceProduct, boolean service){
        Product p = new Product(nameProduct, Double.parseDouble(quantityProduct), Double.parseDouble(priceProduct), 0, service);
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

    public void removeProduct(int id){
        ConnectionController.getInstance().removeProduct(id);
        userInterface.refreshPL();
    }

    public void modifyProduct(int id, double stock, double price){
        ConnectionController.getInstance().modifyProduct(id, stock, price);
        userInterface.refreshPL();
    }

    public void requestRaportClient(Client c){
        RaportClient ri = new RaportClient(ConnectionController.getInstance().requestFacturaList(c), c.getFullName());
    }

    public boolean checkProductExistance(String nameProduct){
        if(ConnectionController.getInstance().requestProduct(nameProduct) == null){
            return true;
        }
        return false;
    }

    public void editClient(Client client){
        if(Company.isCompany(client.getId())) {
            ClientEditUI ci = new ClientEditUI(2, this, userInterface, client);
        }
        else {
            ClientEditUI ci = new ClientEditUI(1, this, userInterface, client);
        }

    }
    public void editClient(int id, String name){
        ConnectionController.getInstance().editClient(id, name);
        importClientsList();
    }
    public void editClient(int id, String name, String surname){
        ConnectionController.getInstance().editClient(id, name, surname);
        importClientsList();
    }

    public void raportProduct(int id, String nameProduct){
        RaportProdus rp = new RaportProdus(ConnectionController.getInstance().requestProductRaport(id), nameProduct);
        userInterface.refreshPL();
    }

}
