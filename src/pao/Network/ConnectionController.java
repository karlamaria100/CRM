package pao.Network;

import pao.Model.Client;
import pao.Model.Company;
import pao.Model.Customer;
import pao.Model.Product;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by rares on 12-May-17.
 */
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
            socket = new Socket("192.168.1.137", 9998);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.flush();
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
}
