package pao.View;

import pao.Model.Client;
import pao.Model.Product;
import pao.Network.ConnectionController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
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
    DefaultTableModel dtm;

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

        JButton importClientsButton = new JButton("Refresh Clientlist");
        importClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.importClientsList();
                newClientCompanyButton.setEnabled(true);
                newClientPersonButton.setEnabled(true);
                //exportClientsButton.setEnabled(true);
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

    public void refreshCL(ArrayList<Client> listClients){
        //ArrayList<Client> listClients = control.getListCompanies();
        clientList.removeAll();
        for(int i = 0; i < listClients.size(); i++){
            clientList.add(clientEntry(listClients.get(i)));
        }
        clientList.revalidate();
    }

    public void refreshPL(){
        if (dtm.getRowCount() > 0) {
            for (int i = dtm.getRowCount() - 1; i > -1; i--) {
                dtm.removeRow(i);
            }
        }
        for(int i = 0; i < ConnectionController.getInstance().requestProductList().size(); i++){
            productEntry(ConnectionController.getInstance().requestProductList().get(i));
        }
    }

    private JPanel clientEntry(Client c){
        JPanel client = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel clientName = new JLabel(c.getFullName());
        clientName.setPreferredSize(new Dimension(110,20));

        JLabel clientID = new JLabel(Integer.toString(c.getId()));
        clientID.setPreferredSize(new Dimension(50, 20));

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
                control.requestRaportClient(c);
            }
        });
        JButton newEditButton = new JButton("Edit");
        newEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                control.editClient(c);
            }
        });

        client.add(clientID);
        client.add(clientName);
        client.add(newFacturaButton);
        client.add(newRaportButton);
        client.add(newEditButton);

        return client;
    }

    private JPanel productsPane(){

        JPanel panel = new JPanel(new BorderLayout());

        //ADD PRODUCT

        JPanel optionPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton newProductButton = new JButton("Adaugati un produs nou");
        newProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ProductUI prod = new ProductUI(control,acesta);
            }
        });
        optionPane.add(newProductButton);

        JButton importProductListButton = new JButton("Importati lista de produse");
        importProductListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.importProductList();
            }
        });

        optionPane.add(newProductButton);
        optionPane.add(importProductListButton);

        //PRODUCT LIST

        table = productTable();

        //PUT EVERYTHING TOGETHER

        panel.add(optionPane,BorderLayout.PAGE_START);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(table.getTableHeader(),BorderLayout.PAGE_START);
        tablePanel.add(table,BorderLayout.CENTER);

        panel.add(tablePanel,BorderLayout.CENTER);

        return panel;
    }

    private JTable productTable(){
        JTable table = new JTable();
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        dtm = new ProductsTableModel(0, 0);
        String header[] = new String[] {"Nr. Crt", "ID Produs", "Nume Produs", "Stoc", "Pret Unitate", "Editare", "Stergere", "Raport"};
        dtm.setColumnIdentifiers(header);
        table.setModel(dtm);


        table.getColumn("Editare").setCellRenderer(new ButtonRenderer());
        table.getColumn("Editare").setCellEditor(new ButtonEditor(new JCheckBox(), this, "edit"));

        table.getColumn("Stergere").setCellRenderer(new ButtonRenderer());
        table.getColumn("Stergere").setCellEditor(new ButtonEditor(new JCheckBox(),this, "remove"));

        table.getColumn("Raport").setCellRenderer(new ButtonRenderer());
        table.getColumn("Raport").setCellEditor(new ButtonEditor(new JCheckBox(),this, "raport"));

        return table;
    }

    public void modifyProduct(int rowNumber){
        ChangeProduct cp = new ChangeProduct(control, (int) table.getModel().getValueAt(rowNumber, 1),
                (String) table.getModel().getValueAt(rowNumber, 2),
                (double) table.getModel().getValueAt(rowNumber, 3),
                (double) table.getModel().getValueAt(rowNumber, 4));
    }

    public void removeProduct(int rowNumber){
        control.removeProduct((int) table.getModel().getValueAt(rowNumber, 1));
    }

    public void raportProduct(int rowNumber){
        control.raportProduct((int) table.getModel().getValueAt(rowNumber, 1),
                (String) table.getModel().getValueAt(rowNumber, 2));
    }

    public void productEntry(Product p){
        Object[] o = new Object[] {dtm.getRowCount(), p.getId(), p.getName(), p.getQuantity(), p.getPrice(), "Edit", "Delete", "Raport"};
        dtm.addRow(o);
        table.revalidate();
    }
}
