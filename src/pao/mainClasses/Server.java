package pao.mainClasses;

import pao.Model.Company;
import pao.Model.Customer;
import pao.Model.Factura;
import pao.Model.Product;
import pao.controllers.Controller;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Karla on 10.05.2017.
 */
public class Server {

    private boolean running;

    public void start(){

        //Controller.getInstance().addClients();
        if (Controller.getInstance().acceptConnection()){
            running = true;

            while(running && !Controller.getInstance().clientDisconected()){
                //TODO editare clienti
                //TODO raport produs
                String message = Controller.getInstance().read();
                System.out.println(message);
                if(Controller.getInstance().wantsClientList(message) && !Controller.getInstance().clientDisconected()){
                    Controller.getInstance().sendsClientList();
                }
                else if(Controller.getInstance().wantsToAddCustomer(message) && !Controller.getInstance().clientDisconected()){
                    Customer customer = Controller.getInstance().readCustomer();
                    Controller.getInstance().addCustomer(customer);
                }
                else if(Controller.getInstance().wantsToAddCompany(message) && !Controller.getInstance().clientDisconected()){
                    Company company = Controller.getInstance().readCompany();
                    Controller.getInstance().addCompany(company);
                }
                else if(Controller.getInstance().wantsProductList(message) && !Controller.getInstance().clientDisconected()){
                    Controller.getInstance().sendsProductList();
                }
                else if(Controller.getInstance().wantsToAddProduct(message) && !Controller.getInstance().clientDisconected()){
                    Product product = Controller.getInstance().readProduct();
                    Controller.getInstance().addProduct(product);
                }
                else if(Controller.getInstance().wantsRaportForPerson(message) && !Controller.getInstance().clientDisconected()){
                    int client = Controller.getInstance().readInt();
                    Controller.getInstance().getRaport(client);
                }
                else if(Controller.getInstance().existsProduct(message) && !Controller.getInstance().clientDisconected()){
                    String product = Controller.getInstance().read();
                    System.out.println(product);
                    Controller.getInstance().hasProduct(product);
                }
                else if(Controller.getInstance().wantsToAddFactura(message) && !Controller.getInstance().clientDisconected()){
                    Factura factura = Controller.getInstance().readFactura();
                    int idClient = Controller.getInstance().readInt();
                    Controller.getInstance().addFactura(factura, idClient);
                }
                else if(Controller.getInstance().wantsToEditProducts(message) && !Controller.getInstance().clientDisconected()){
                    int idProduct = Controller.getInstance().readInt();
                    double stock = Controller.getInstance().readDouble();
                    double pret = Controller.getInstance().readDouble();
                    System.out.println(stock);
                    System.out.println(pret);
                    Controller.getInstance().editProduct(idProduct, stock, pret);
                }
                else if (Controller.getInstance().wantsToDelete(message) && !Controller.getInstance().clientDisconected()){
                    int idProdus = Controller.getInstance().readInt();
                    Controller.getInstance().deleteProduct(idProdus);
                }
                else if (Controller.getInstance().wantsToEditClient(message) && !Controller.getInstance().clientDisconected()){
                    int id = Controller.getInstance().readInt();
                    if(Company.isCompany(id)){
                        String name = Controller.getInstance().read();
                        Controller.getInstance().editCompany(id, name);
                    }
                    else if(Customer.isCustomer(id)){
                        String name = Controller.getInstance().read();
                        String surname = Controller.getInstance().read();
                        Controller.getInstance().editCustomer(id, name, surname);
                    }
                }
                else if(Controller.getInstance().wantsProductRaport(message) && !Controller.getInstance().clientDisconected()){
                    int product = Controller.getInstance().readInt();
                    Controller.getInstance().getRaportProduct(product);
                }
            }
        }
    }



}
