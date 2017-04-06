package View;

import Model.Client;
import Model.Company;
import Model.Customer;
import Model.Product;
import View.MainUI;
import sun.applet.Main;

import java.io.*;
import java.util.ArrayList;

public class Controller {

    private ArrayList<Client> listClients = new ArrayList<>();
    private ArrayList<Product> listProducts = new ArrayList<>();
    private MainUI userInterface = new MainUI(this);

    public void addCompany(String nameCompany){

        Company c = new Company(nameCompany);
        listClients.add(c);
    }

    public void addCustomer(String nameCustomer, String surnameCustomer){
        Customer c = new Customer(nameCustomer,surnameCustomer);
        listClients.add(c);
    }

    public void addProduct(String nameProduct, String quantityProduct, String priceProduct){
        Product p = new Product(nameProduct, Double.parseDouble(quantityProduct), Double.parseDouble(priceProduct));
        listProducts.add(p);
    }

    public void refreshClientList(){
        userInterface.refreshCL();
    }

    public void refreshProductList(){
        userInterface.refreshPL();
    }

    public ArrayList<Client> getListCompanies(){
        return listClients;
    }

    public ArrayList<Product> getListProducts(){return listProducts;}

    public void importProductList(){
        ArrayList<Product> arr = null;
        try {
            FileInputStream fileIn = new FileInputStream("exportProducts.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            arr = (ArrayList<Product>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("Product class not found");
            c.printStackTrace();
        }
        for(int i = 0; i < arr.size(); i++){
            listProducts.add(arr.get(i));
        }
        refreshProductList();
    }

    public void exportProductList(){
        try {
            FileOutputStream fileOut = new FileOutputStream("exportProducts.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(listProducts);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in exportProducts.txt");
        }catch(IOException i) {

        }

    }

    public void exportClientList(){
        try {
            FileOutputStream fileOut = new FileOutputStream("exportClients.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(listClients);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in exportClients.txt");
        }catch(IOException i) {

        }
    }

    public void importClientsList(){
        ArrayList<Client> arr = null;
        try {
            FileInputStream fileIn = new FileInputStream("exportClients.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            arr = (ArrayList<Client>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("Client class not found");
            c.printStackTrace();
        }
        for(int i = 0; i < arr.size(); i++){
            listClients.add(arr.get(i));
        }
        refreshClientList();
    }

    public Product queryProduct(String nameProduct){
        System.out.print(nameProduct);
        for(int i = 0; i < listProducts.size(); i++) {
            System.out.print(listProducts.get(i).getName() + " ");
        }

        Product gasit = null;
        for(int i = 0; i < listProducts.size(); i++){
            if(listProducts.get(i).getName().equals(nameProduct)){
                System.out.print("true");
                gasit = listProducts.get(i);
            }
        }
        System.out.print(gasit);
        return gasit;
    }

    public double enoughProduct(String nameProduct, String quantityProduct){
        for(int i = 0; i < listProducts.size(); i++){
            if(listProducts.get(i).getName().equals(nameProduct)){
                if(listProducts.get(i).getQuantity() < Double.parseDouble(quantityProduct)){
                    return listProducts.get(i).getQuantity();
                }
            }
        }
        return -1.0;
    }

    public Product getProduct(int index){
        return listProducts.get(index);
    }

    public Product getProduct(String nameProduct){
        for(int i = 0; i < listProducts.size(); i++) {
            if(listProducts.get(i).getName().equals(nameProduct)){
                return listProducts.get(i);
            }
        }
        return null;
    }

}
