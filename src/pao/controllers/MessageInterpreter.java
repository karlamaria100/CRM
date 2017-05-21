package pao.controllers;

import pao.Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Karla on 10.05.2017.
 */
public class MessageInterpreter {


    public boolean clientDisconected;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public MessageInterpreter(ObjectInputStream inputStream, ObjectOutputStream outputStream){
        output = outputStream;
        input = inputStream;
    }

    public boolean writeObject(Object message){
        try {
            output.writeObject(message);
            System.out.print(message);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            clientDisconected = true;
        }
        return false;
    }

    public int getInt(){
        try {
            return (Integer) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            clientDisconected = true;
            return 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getDouble(){
        try {
            return (Double) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            clientDisconected = true;
            return 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getMessage(){
        try {
            return (String) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            clientDisconected = true;
            return "Reading failed";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "Reading failed";
        }
    }

    public boolean wantsClientList(String message){
        if(message.equals("REQUEST CLIENT LIST"))
            return true;
        return false;
    }

    public boolean wantsProductList(String message){
        if(message.equals("REQUEST PRODUCT LIST"))
            return true;
        return false;
    }

    public boolean wantsToAddCompany(String message) {
        if(message.equals("ADD NEW COMPANY"))
            return true;
        return false;
    }

    public boolean wantsToAddProduct(String message) {
        if(message.equals("ADD NEW PRODUCT"))
            return true;
        return false;
    }

    public boolean wantsToDelete(String message){
        if (message.equals("REMOVE PRODUCT"))
            return true;
        return false;
    }

    public boolean wantsToEditClient(String message){
        if (message.equals("EDIT CLIENT"))
            return true;
        return false;
    }

    public boolean existsProduct(String message){
        if(message.equals("REQUEST PRODUCT")){
            return true;
        }
        return false;
    }

    public boolean wantsToAddFactura(String message){
        if(message.equals("ADD NEW FACTURA")){
            return true;
        }
        return false;
    }

    public boolean wantsRaportForPerson(String message){
        if(message.equals("REQUEST CLIENT RAPORT"))
            return true;
        return false;
    }

    public boolean wantsToEditProducts(String message){
        if(message.equals("MODIFY PRODUCT"))
            return true;
        return false;
    }

    public boolean wantsProductRaport(String message){
        if(message.equals("RAPORT PRODUCT"))
            return true;
        return false;
    }

    public Customer getReadCustomer(){
        try {
            return (Customer) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            clientDisconected = true;
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Product getReadProduct(){
        try {
            return (Product) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            clientDisconected = true;
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Company getReadCompany(){
        try {
            return (Company) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            clientDisconected = true;
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Factura getReadFactura(){
        try {
            return (Factura) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            clientDisconected = true;
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean wantsToAddCustomer(String message) {
        if(message.equals("ADD NEW CUSTOMER"))
            return true;
        return false;
    }

    public boolean isClientDisconected() {
        return clientDisconected;
    }


}
