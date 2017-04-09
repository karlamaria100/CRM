package View;

import Model.Client;
import Model.Company;
import Model.Product;

import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by rares on 31-Mar-17.
 */
public class MainUI {

    Controller control;
    MainUI acesta = this;
    JFrame jframe;


    public MainUI(Controller control){

        this.control = control;
        startWindow();

    }

    private void startWindow(){
        //BASE WINDOW FOR LOGIN
        jframe = new JFrame("CRM");
        jframe.setPreferredSize(new Dimension(800,500));
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //jframe.setLocation(dim.width/2-jframe.getSize().width/2, dim.height/2-jframe.getSize().height/2);

        JTabbedPane jtp = new JTabbedPane();
        jtp.addTab("Clienti", clientsPane());
        jtp.addTab("Produse", productsPane());

        jframe.add(jtp);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    private JPanel clientsPane(){
        JPanel panel = new JPanel(new BorderLayout());


        //ADD CLIENT

        JPanel newClient = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton newClientPersonButton = new JButton("Adaugati Persoana Fizica");
        newClientPersonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewClientUI newWindow = new NewClientUI(1,control,acesta);
            }
        });
        newClient.add(newClientPersonButton);

        JButton newClientCompanyButton = new JButton("Adaugati Persoana Juridica");
        newClientCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewClientUI newWindow = new NewClientUI(2, control,acesta);
            }
        });
        newClient.add(newClientCompanyButton);

        JButton exportClientsButton = new JButton("Exportati lista de clienti");
        exportClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.exportClientList();
            }
        });
        newClient.add(exportClientsButton);

        JButton importClientsButton = new JButton("Importati lista de clienti");
        importClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.importClientsList();
            }
        });
        newClient.add(importClientsButton);



        //CLIENT LIST

        JScrollPane clientScrollList = new JScrollPane(clientList());
        clientScrollList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        clientScrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        clientScrollList.setViewportBorder(new LineBorder(Color.BLACK));

        //PUT EVERYTHING TOGETHER

        panel.add(newClient,BorderLayout.PAGE_START);
        //panel.add(newClientPerson,BorderLayout.PAGE_START);

        panel.add(clientScrollList,BorderLayout.CENTER);

        return panel;

    }

    JPanel clientList;
    JPanel productList;

    private JPanel clientList(){

        clientList = new JPanel();
        BoxLayout boxlayout = new BoxLayout(clientList, BoxLayout.Y_AXIS);
        clientList.setLayout(boxlayout);
        return clientList;
    }

    public void refreshCL(){
        ArrayList<Client> listClients = control.getListCompanies();
        clientList.removeAll();
        for(int i = 0; i < listClients.size(); i++){
            clientList.add(clientEntry(listClients.get(i)));
        }
        clientList.revalidate();
    }

    public void refreshPL(){
        ArrayList<Product> listProducts = control.getListProducts();
        productList.removeAll();
        for(int i = 0; i < listProducts.size(); i++){
            productList.add(productEntry(listProducts.get(i)));
        }
        productList.revalidate();
    }

    private JPanel clientEntry(Client c){
        //JPanel client = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel client = new JPanel(new GridBagLayout());

        JLabel clientName = new JLabel(c.getName());
        JLabel clientID = new JLabel(Integer.toString(c.getId()));

        JLabel clientNumberFacturi = new JLabel(String.valueOf(c.getNumberFacturi()));


        JButton newFacturaButton = new JButton("Factura Noua");
        newFacturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FacturaView fv = new FacturaView(c,control);
                jframe.revalidate();
            }
        });
        JButton newRaportButton = new JButton("Raport");
        newRaportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RaportUI r = new RaportUI(c);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.1;
        client.add(clientID,gbc);
        gbc.weightx = 0.3;
        client.add(clientName,gbc);
        gbc.weightx = 0.1;
        client.add(clientNumberFacturi,gbc);
        gbc.weightx = 0.3;
        client.add(newFacturaButton,gbc);
        client.add(newRaportButton,gbc);

        return client;
    }

    private JPanel productsPane(){

        JPanel panel = new JPanel(new BorderLayout());


        //ADD PRODUCT

        JPanel newProduct = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton newProductButton = new JButton("Adaugati un produs nou");
        newProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ProductUI prod = new ProductUI(control,acesta);
            }
        });
        newProduct.add(newProductButton);

        JButton exportProductListButton = new JButton("Exportati lista de produse");
        exportProductListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.exportProductList();
            }
        });

        JButton importProductListButton = new JButton("Importati lista de produse");
        importProductListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.importProductList();
            }
        });

        newProduct.add(newProductButton);
        newProduct.add(exportProductListButton);
        newProduct.add(importProductListButton);



        //PRODUCT LIST

        JScrollPane productScrollList = new JScrollPane(productList());
        productScrollList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        productScrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        productScrollList.setViewportBorder(new LineBorder(Color.BLACK));





        //PUT EVERYTHING TOGETHER

        panel.add(newProduct,BorderLayout.PAGE_START);
        panel.add(productScrollList,BorderLayout.CENTER);

        return panel;
    }

    private JPanel productList(){

        productList = new JPanel();
        BoxLayout boxlayout = new BoxLayout(productList, BoxLayout.Y_AXIS);
        productList.setLayout(boxlayout);

        return productList;
    }

    private JPanel productEntry(Product p){
        JPanel product = new JPanel();
        product.setBackground(Color.pink);
        product.setBorder(BorderFactory.createLineBorder(Color.black,4,true));

        JLabel productNameLabel = new JLabel("Produs:");
        JLabel productName = new JLabel(p.getName());

        JLabel productQuantity = new JLabel("Stoc:");
        JLabel productStocs = new JLabel(String.valueOf(p.getQuantity()));

        JLabel productPrice = new JLabel("Pret:");
        JLabel productFinalPrice = new JLabel (String.valueOf(p.getPrice()));

        product.add(productNameLabel);
        product.add(productName);
        product.add(productQuantity);
        product.add(productStocs);
        product.add(productPrice);
        product.add(productFinalPrice);

        return product;
    }

}
