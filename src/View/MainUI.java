package View;

import Model.Client;
import Model.Product;
import Model.ProductTable;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainUI {

    private Controller control;
    private MainUI acesta = this;
    private JFrame jframe;
    private JPanel clientList;
    private JPanel productList;
    JTable table;


    MainUI(Controller control){

        this.control = control;
        startWindow();

    }

    private void startWindow(){
        //BASE WINDOW FOR LOGIN
        jframe = new JFrame("CRM");
        jframe.setPreferredSize(new Dimension(800,500));
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setMinimumSize(new Dimension(800, 350));

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

        JPanel newClient = new JPanel();
        JButton newClientPersonButton = new JButton("Adaugati Persoana Fizica");
        newClientPersonButton.setEnabled(false);
        newClientPersonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewClientUI newWindow = new NewClientUI(1,control,acesta);
            }
        });
        newClient.add(newClientPersonButton);

        JButton newClientCompanyButton = new JButton("Adaugati Persoana Juridica");
        newClientCompanyButton.setEnabled(false);
        newClientCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewClientUI newWindow = new NewClientUI(2, control,acesta);
            }
        });
        newClient.add(newClientCompanyButton);

        JButton exportClientsButton = new JButton("Exportati lista de clienti");
        exportClientsButton.setEnabled(false);
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
                newClientCompanyButton.setEnabled(true);
                newClientPersonButton.setEnabled(true);
                exportClientsButton.setEnabled(true);
                importClientsButton.setEnabled(false);
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

        table.repaint();
       // productList();

    }

    private JPanel clientEntry(Client c){
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

        table = new JTable(control.getProductTable());
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        //PUT EVERYTHING TOGETHER

        panel.add(newProduct,BorderLayout.PAGE_START);
        panel.add(testTable().getTableHeader(),BorderLayout.PAGE_START);
        panel.add(testTable(),BorderLayout.CENTER);

        return panel;
    }

    private JTable testTable(){
        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };

        JTable table = new JTable(data, columnNames);
        return table;
    }
    /*
    public JPanel productList(){

        productList = new JPanel();

        table = new JTable(control.getProductTable());
        table.setFillsViewportHeight(true);

        productList.add(new JScrollPane(table));
        productList.setLayout(new GridLayout(1,1));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        return productList;
    }
    */
}
