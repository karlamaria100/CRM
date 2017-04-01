package View;

import Model.Company;
import Model.Product;
import View.MainUI;
import sun.applet.Main;

import java.util.ArrayList;

/**
 * Created by Rares on 4/1/2017.
 */
public class Controller {

    ArrayList<Company> listCompanies = new ArrayList<>();
    ArrayList<Product> listProducts = new ArrayList<>();
    MainUI userInterface = new MainUI(this);

    public void addCompany(String nameCompany){

        Company c = new Company(nameCompany);
        listCompanies.add(c);
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

    public ArrayList<Company> getListCompanies(){
        return listCompanies;
    }

    public ArrayList<Product> getListProducts(){return listProducts;}


}
