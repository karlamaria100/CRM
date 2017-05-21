package pao.dao;

import com.sun.org.apache.regexp.internal.RE;
import pao.DatabaseData;
import pao.Model.*;
import pao.controllers.Controller;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Karla on 01.04.2017.
 */
public class DataBaseConnection {

    private Connection connection ;

    public DataBaseConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm?autoReconnect=true&useSSL=false","root", "pizza");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Factura> getRaport(int client){
        try (PreparedStatement statement = connection.prepareStatement(DatabaseData.selectFacturi); PreparedStatement statement1 = connection.prepareStatement(DatabaseData.selectProduseFacturi)){
            ArrayList<Factura> arrayList = new ArrayList<>();
            statement.setInt(1, client);
            ResultSet rs = statement.executeQuery();
            ResultSet rs1;
            while (rs.next()){
                Factura factura = new Factura();
                statement1.setInt(1, rs.getInt("idFactura"));
                rs1 = statement1.executeQuery();
                while (rs1.next()){
                    factura.add(rs1.getString("nume"), rs1.getDouble("cantitate"),  rs1.getDouble("pret") , rs1.getInt("idProdus"), rs1.getBoolean("serviciu"));
                }
                arrayList.add(factura);
                rs1.close();
            }

            rs.close();
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Sale> getRaportProduct(int product){
        try(PreparedStatement statement = connection.prepareStatement(DatabaseData.selectSale)) {
            statement.setInt(1,product);
            ResultSet rs = statement.executeQuery();
            ArrayList<Sale> arrayList = new ArrayList<>();
            while (rs.next()){
                Sale sale = new Sale(rs.getDouble("stock"), rs.getDouble("pret"), rs.getString("cumparator"));
                arrayList.add(sale);
            }
            rs.next();
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertCustomer(Customer customer){
        try (PreparedStatement statement = connection.prepareStatement(DatabaseData.insertCustomer)){
            statement.setInt(1, Customer.generateID());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getSurname());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCompany(Company company){
        try (PreparedStatement statement = connection.prepareStatement(DatabaseData.insertCompany)){
            statement.setInt(1, Company.generateID());
            statement.setString(2, company.getFullName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProduct(Product product){
        try (PreparedStatement statement = connection.prepareStatement(DatabaseData.insertProduct)){
            int idProduct =  product.generateID();
            statement.setInt(1,idProduct);
            statement.setString(2, product.getName());
            if(product.isServices())
                statement.setDouble(3, 1000);
            else
                statement.setDouble(3, product.getQuantity());
            statement.setDouble(4, product.getPrice());
            statement.setBoolean(5, product.isServices());
            statement.executeUpdate();
            addSale(idProduct, product.getQuantity(), product.getPrice(), "Aprovizionare");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertFactura(Factura factura, int idClient){
        try (PreparedStatement statement = connection.prepareStatement(DatabaseData.insertFactura); PreparedStatement statement1 = connection.prepareStatement(DatabaseData.insertProduseFacturi)){
            factura.setID(Factura.generateID());
            statement.setInt(1, factura.getID());
            statement.setInt(2, idClient);
            statement.setDouble(3, factura.getTotalFactura());
            statement.executeUpdate();
            for (int i = 0; i < factura.getListaProduse().size(); i++){
                Product product = factura.getListaProduse().get(i);
                statement1.setInt(1, factura.getID());
                statement1.setInt(2, product.getId());
                statement1.setDouble(3, product.getQuantity());
                statement1.setDouble(4, product.getPrice());
                statement1.setString(5, product.getName());
                statement1.setBoolean(6, product.isServices());
                statement1.executeUpdate();

                Product product1 = hasProduct(product.getName());
                editProduct(product.getId(), product1.getQuantity() - product.getQuantity(), product.getPrice());
                addSale(product.getId(), -product.getQuantity(), product.getPrice(), product.getName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSale(int idProdus, double stock, double pret, String buyer){
        try (PreparedStatement statement1 = connection.prepareStatement(DatabaseData.insertSale)) {
            statement1.setDouble(1, idProdus);
            statement1.setDouble(2, stock);
            statement1.setDouble(3, pret);
            statement1.setString(4, buyer);
            statement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Client> selectClients(){
        ArrayList<Client> arrayList = new ArrayList<>();
        selectCustomers(arrayList);
        selectCompany(arrayList);
        return arrayList;
    }

    public ArrayList<Product> selectProducts(){
        ArrayList<Product> arrayList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DatabaseData.selectProducts); ResultSet rs = statement.executeQuery()){
            while (rs.next()){
                arrayList.add(new Product(rs.getString("nume"), rs.getDouble("stock"),  rs.getDouble("pret") , rs.getInt("idProduse"), rs.getBoolean("serviciu")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void selectCustomers(ArrayList<Client> arrayList){
        try (PreparedStatement statement = connection.prepareStatement(DatabaseData.selectCustomers);
             ResultSet rs = statement.executeQuery()){
            while (rs.next()){
                arrayList.add(new Customer(rs.getString("nume"), rs.getString("prenume"), rs.getInt("idpersoana_fizica")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectCompany(ArrayList<Client> arrayList){
        try (PreparedStatement statement = connection.prepareStatement(DatabaseData.selectCompanys);
             ResultSet rs = statement.executeQuery()){
            while (rs.next()){
                arrayList.add(new Company(rs.getString("nume"), rs.getInt("idpersoana_juridica")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product hasProduct(String message){
        try (PreparedStatement statement = connection.prepareStatement(DatabaseData.selectProduct)){
            statement.setString(1, message);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                Product product = new Product(rs.getString("nume"), rs.getDouble("stock"), rs.getDouble("pret"), rs.getInt("idProduse"), rs.getBoolean("serviciu"));
                rs.close();
                return product;
            }
            rs.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product hasProduct(int message){
        try (PreparedStatement statement = connection.prepareStatement(DatabaseData.selectProductID)){
            statement.setInt(1, message);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                Product product = new Product(rs.getString("nume"), rs.getDouble("stock"), rs.getDouble("pret"), rs.getInt("idProduse"), rs.getBoolean("serviciu"));
                rs.close();
                return product;
            }
            rs.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean editProduct(int idProduct, double stock, double pret){
        try ( PreparedStatement statement = connection.prepareStatement(DatabaseData.updateProduct); PreparedStatement statement1 = connection.prepareStatement(DatabaseData.selectProductID)){
            statement1.setInt(1, idProduct);
            ResultSet rs = statement1.executeQuery();
            if(rs.next())
                if(rs.getInt("stock") == -stock && rs.getBoolean("serviciu") == true) stock = 1000;

            statement.setDouble(1, stock);
            statement.setDouble(2, pret);
            statement.setInt(3, idProduct);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteProduct(int idProdus){
        try(PreparedStatement statement = connection.prepareStatement(DatabaseData.deleteProduct)) {
            statement.setInt(1, idProdus);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editCompany(int idClient, String nume){

        try (PreparedStatement statement =  connection.prepareStatement(DatabaseData.updateCompany);){
            statement.setString(1, nume);
            statement.setInt(2, idClient);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCustomer(int idClient, String nume, String prenume){
        //TODO verificare daca e customer sau company
        try (PreparedStatement statement = connection.prepareStatement(DatabaseData.updateCustomer)){
            statement.setString(1, nume);
            statement.setString(2, prenume);
            statement.setInt(3, idClient);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
