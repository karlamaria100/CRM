package pao.controllers;

import pao.Model.*;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Karla on 10.05.2017.
 */
public class Controller {

    private DatabaseController databaseController;
    private static Controller instance;
    private ClientConnection connection;
    private MessageInterpreter messageInterpreter;

    private Controller(){
        databaseController = DatabaseController.getInstance();
        connection = new ClientConnection();
    }

    public static Controller getInstance(){
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    public boolean acceptConnection(){
        return connection.accept();
    }

    public void setUpMessage(){
        messageInterpreter = new MessageInterpreter(connection.getInputStream(), connection.getOutputStream());
    }

    public String read(){
        return messageInterpreter.getMessage();
    }

    public int readInt(){return messageInterpreter.getInt();}

    public double readDouble(){return messageInterpreter.getDouble();}

    public void write(Object object){
        if(!messageInterpreter.writeObject(object))
            System.out.println(" - Message sent failed");
        else System.out.println(" - Message sent successful ");
    }

    public ArrayList<Client> getCustomers(){
        return databaseController.getInstance().getCustomers();
    }

    public ArrayList<Product> getProducts(){
        return databaseController.getInstance().getProducts();
    }

    public boolean wantsClientList(String message){
        return messageInterpreter.wantsClientList(message);
    }

    public boolean wantsProductList(String message){
        return messageInterpreter.wantsProductList(message);
    }

    public void sendsClientList(){
        write(getCustomers());
    }

    public void sendsProductList(){
        write(getProducts());
    }

    public void addCustomer(Customer customer){
        databaseController.exportCustomerList(customer);
    }

    public void addCompany(Company company){
        databaseController.exportCompanyList(company);
    }

    public void addProduct(Product product){databaseController.exportProductList(product);}

    public void addFactura(Factura factura, int idClient){databaseController.exportFacturaList(factura, idClient);}

    public boolean clientDisconected(){
        return messageInterpreter.isClientDisconected();
    }

    public boolean wantsToAddCustomer(String message){
        return messageInterpreter.wantsToAddCustomer(message);
    }

    public boolean wantsToAddCompany(String message){
        return messageInterpreter.wantsToAddCompany(message);
    }

    public boolean wantsToAddProduct(String message){
        return messageInterpreter.wantsToAddProduct(message);
    }

    public boolean wantsToAddFactura(String message){
        return messageInterpreter.wantsToAddFactura(message);
    }

    public boolean wantsRaportForPerson(String message){
        return messageInterpreter.wantsRaportForPerson(message);
    }

    public boolean wantsToEditProducts(String message){
        return messageInterpreter.wantsToEditProducts(message);
    }

    public boolean wantsToDelete(String message){
        return messageInterpreter.wantsToDelete(message);
    }

    public boolean wantsProductRaport(String message){
        return messageInterpreter.wantsProductRaport(message);
    }

    public boolean wantsToEditClient(String message){
        return messageInterpreter.wantsToEditClient(message);
    }

    public void getRaport(int client){
        write(databaseController.getRaport(client));
    }

    public void getRaportProduct(int product){
        write(databaseController.getRaportProduct(product));
        write(databaseController.hasProduct(product));
    }

    public void editProduct(int idProduct, double stock, double pret){
        if(databaseController.editProduct(idProduct, stock, pret))
            System.out.println("EDITAREA S-A REALIZAT CU SUCCES");
        else System.out.println("EROARE LA EDITARE");
    }

    public Customer readCustomer(){
        return messageInterpreter.getReadCustomer();
    }

    public Company readCompany(){
        return messageInterpreter.getReadCompany();
    }

    public Product readProduct(){return messageInterpreter.getReadProduct();}

    public Factura readFactura(){return messageInterpreter.getReadFactura();}

    public boolean existsProduct(String message){
        return messageInterpreter.existsProduct(message);
    }

    public void hasProduct(String message){
         write(databaseController.hasProduct(message));
    }

    public void deleteProduct(int idProdus){
        databaseController.deleteProduct(idProdus);
    }

    public void editCustomer(int idClient, String name, String surname){
        databaseController.editCustomer(idClient, name, surname);
    }

    public void editCompany(int idClient, String name){
        databaseController.editCompany(idClient, name);
    }

}
