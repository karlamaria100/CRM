package pao.Network;

import pao.Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class ConnectionController {

    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    private static ConnectionController instance;

    public static ConnectionController getInstance(){
        if(instance == null)
            instance = new ConnectionController();
        return instance;
    }

    public ConnectionController() {
        try {
            socket = new Socket("127.0.0.1", 9998);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.flush();
            objectOutputStream.writeObject("Hello");
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject("Aici Rares");
            System.out.print(objectInputStream.readObject());

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Client> requestClientList(){
        ArrayList<Client> arr = null;
        try {
            objectOutputStream.writeObject("REQUEST CLIENT LIST");
            arr = (ArrayList<Client>) objectInputStream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return arr;
    }

    public ArrayList<Product> requestProductList(){
        ArrayList<Product> arr = null;
        try {
            objectOutputStream.writeObject("REQUEST PRODUCT LIST");
            arr = (ArrayList<Product>) objectInputStream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return arr;
    }

    public Product requestProduct(String nameProduct){
        Product r = null;
        try {
            System.out.println("ajunge");
            objectOutputStream.writeObject("REQUEST PRODUCT");
            objectOutputStream.writeObject(nameProduct);
            r = (Product) objectInputStream.readObject();
            if(r!=null) { System.out.println(r.getName()); }
            if(r == null) {
                System.out.println("e gol");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return r;
    }

    public ArrayList<Factura> requestFacturaList(Client c){
        ArrayList<Factura> arr = null;
        try{
            objectOutputStream.writeObject("REQUEST CLIENT RAPORT");
            objectOutputStream.writeObject(c.getId());
            arr = (ArrayList<Factura>) objectInputStream.readObject();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException cl){
            cl.printStackTrace();
        }
        return arr;
    }

    public void deleteProduct(String nameProduct){
        try {
            objectOutputStream.writeObject("DELETE PRODUCT ");
            objectOutputStream.writeObject(nameProduct);
            System.out.println(objectInputStream.read());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendProduct(Product product){
        try{
            objectOutputStream.writeObject("ADD NEW PRODUCT");
            objectOutputStream.writeObject(product);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendCustomer(Customer customer){
        try {
            objectOutputStream.writeObject("ADD NEW CUSTOMER");
            objectOutputStream.writeObject(customer);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendCompany(Company company){
        try{
            objectOutputStream.writeObject("ADD NEW COMPANY");
            objectOutputStream.writeObject(company);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendFactura(Factura factura, int idClient){
        try{
            objectOutputStream.writeObject("ADD NEW FACTURA");
            objectOutputStream.writeObject(factura);
            objectOutputStream.writeObject(idClient);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void modifyProduct(int id, double stock, double price){
        try{
            objectOutputStream.writeObject("MODIFY PRODUCT");
            objectOutputStream.writeObject(id);
            objectOutputStream.writeObject(stock);
            objectOutputStream.writeObject(price);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void removeProduct(int id){
        try{
            System.out.println("Intra");
            objectOutputStream.writeObject("REMOVE PRODUCT");
            objectOutputStream.flush();
            objectOutputStream.writeObject(id);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editClient(int id, String name){
        try{
            objectOutputStream.writeObject("EDIT CLIENT");
            objectOutputStream.writeObject(id);
            objectOutputStream.writeObject(name);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void editClient(int id, String name, String surname){
        try{
            objectOutputStream.writeObject("EDIT CLIENT");
            objectOutputStream.writeObject(id);
            objectOutputStream.writeObject(name);
            objectOutputStream.writeObject(surname);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Sale> requestProductRaport(int id){
        ArrayList<Sale> arr = null;
        try{
            objectOutputStream.writeObject("RAPORT PRODUCT");
            objectOutputStream.writeObject(id);
            arr = (ArrayList<Sale>) objectInputStream.readObject();
            //TODO ARRAY CARE PRIMESTE RAPORTUL
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return arr;
    }

}
