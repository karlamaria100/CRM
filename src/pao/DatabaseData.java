package pao;

/**
 * Created by Karla on 13.05.2017.
 */
public interface DatabaseData {

    public static final String insertCustomer = "INSERT INTO crm.persoana_fizica (`idpersoana_fizica`, `nume`, `prenume`) VALUES (?, ?, ?)";
    public static final String selectCustomers = "SELECT * FROM crm.persoana_fizica";
    public static final String insertCompany = "INSERT INTO crm.persoana_juridica (`idpersoana_juridica`, `nume`) VALUES (?, ?)";
    public static final String selectCompanys = "SELECT * FROM crm.persoana_juridica";
    public static final String insertProduct = "INSERT INTO crm.produse (`idProduse`, `nume`, `stock`, `pret`, `serviciu`)  VALUES (?, ?, ?, ?, ?)";
    public static final String selectProducts = "SELECT * FROM crm.produse";
    public static final String selectProduct = "SELECT * FROM crm.produse WHERE nume = ?";
    public static final String selectProductID = "SELECT * FROM crm.produse WHERE idProduse = ?";
    public static final String insertFactura = "INSERT INTO crm.facturi (`idFactura`, `idClient`, `pretTotal`)  VALUES (?, ?, ?)";
    public static final String insertProduseFacturi = "INSERT INTO crm.produsedinfacturi (`idFactura`, `idProdus`, `cantitate`, `pret`, `nume`, `serviciu`) VALUES (?, ?, ? ,?, ?, ?)";
    public static final String selectCustomer = "SELECT * FROM crm.persoana_fizica WHERE idpersoana_fizica = ?";
    public static final String selectCompany = "SELECT * FROM crm.persoana_juridica WHERE idpersoana_juridica = ?";
    public static final String selectFacturi = "SELECT * FROM crm.facturi WHERE idClient = ?";
    public static final String selectProduseFacturi = "SELECT * FROM crm.produsedinfacturi WHERE idFactura = ?";
    public static final String updateProduct = "UPDATE crm.produse SET stock = stock + ?, pret = ? WHERE idProduse = ?" ;
    public static final String updateCompany = "UPDATE crm.persoana_juridica SET nume = ? WHERE idpersoana_juridica = ?" ;
    public static final String updateCustomer = "UPDATE crm.persoana_fizica SET nume = ?, surname = ?  WHERE idpersoana_fizica = ?" ;
    public static final String deleteProduct = "DELETE FROM crm.produse WHERE idProduse = ?";
    public static final String insertSale = "INSERT INTO crm.sales (`idProdus`, `stock`, `pret`, `cumparator`) VALUES (?, ?, ?, ?)";
    public static final String selectSale = "SELECT * FROM crm.sales WHERE idProdus = ?";


}
