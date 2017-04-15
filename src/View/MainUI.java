package View;

import Model.Client;
import Model.Product;
import javafx.stage.WindowEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class MainUI {

    private Controller control;
    private MainUI acesta = this;
    private JFrame jframe;
    private JPanel clientList;
    JTable table;
    DefaultTableModel dtm;
    JButton exportClientsButton;
    JButton importClientsButton;


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

        jframe.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                String[] options = {"Exporta", "Nu exporta", "Cancel"};
                int response = JOptionPane.showOptionDialog(jframe,"Are you sure to close this window?",
                        "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,null,options, options[0]);
                if(response == 0){
                    exportClientsButton.doClick();
                }
            }
        });


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

        exportClientsButton = new JButton("Exportati lista de clienti");
        exportClientsButton.setEnabled(false);
        exportClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.exportClientList();
            }
        });
        newClient.add(exportClientsButton);

        importClientsButton = new JButton("Importati lista de clienti");
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
        if (dtm.getRowCount() > 0) {
            for (int i = dtm.getRowCount() - 1; i > -1; i--) {
                dtm.removeRow(i);
            }
        }
        for(int i = 0; i < control.getListProducts().size(); i++){
            productEntry(control.getProduct(i));
        }
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

        JPanel optionPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton newProductButton = new JButton("Adaugati un produs nou");
        newProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ProductUI prod = new ProductUI(control,acesta);
            }
        });
        optionPane.add(newProductButton);

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
                importProductListButton.setEnabled(false);
                control.importProductList();
            }
        });

        optionPane.add(newProductButton);
        optionPane.add(exportProductListButton);
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
        String header[] = new String[] {"Nr. Crt", "Nume Produs", "Stoc", "Pret Unitate", "Delete", "Editare" };
        dtm.setColumnIdentifiers(header);
        table.setModel(dtm);

        table.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        table.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(),control));

        table.getColumn("Editare").setCellRenderer(new ButtonRenderer());
        table.getColumn("Editare").setCellEditor(new ButtonEditor(new JCheckBox(),control));

        return table;
    }

    public void productEntry(Product p){
        Object[] o = new Object[] {dtm.getRowCount(), p.getName(), p.getQuantity(), p.getPrice(), "Delete", "Editeaza"};
        dtm.addRow(o);
        table.revalidate();
    }

    public void editProduct(int row){

        for(int i = 0; i < control.getListProducts().size(); i++){
            System.out.println(dtm.getValueAt( row,1 ) + control.getListProducts().get(i).getName() + control.getListProducts().get(i).getName().equals( dtm.getValueAt( row,1 ))  );
            if(control.getListProducts().get(i).getName().equals( dtm.getValueAt( row,1 )) == true) {
                ProductEditUI peui = new ProductEditUI(control.getListProducts().get(i), control);
            }
        }

    }
}
