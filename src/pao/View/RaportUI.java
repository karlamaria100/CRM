package pao.View;

import pao.Model.Client;
import pao.Model.Factura;
import pao.Network.ConnectionController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;


public class RaportUI {
    private JFrame jframe;
    private Client client;
    private JPanel facturalist;

    RaportUI(Client c){
        this.client = c;
        startWindow();
    }

    private void startWindow() {
        jframe = new JFrame("Client Nou");
        jframe.setPreferredSize(new Dimension(400, 300));
        jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jframe.add(raportView());
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    private JPanel raportView(){
        JPanel form = new JPanel(new BorderLayout());

        JPanel clientNamePane = new JPanel();
        clientNamePane.setLayout(new FlowLayout(FlowLayout.LEFT));
        clientNamePane.setBorder(new EmptyBorder(20, 20, 5, 0));

        JLabel nameClient = new JLabel("Numele Clientului:" + client.getFullName());


        clientNamePane.add(nameClient);

        form.add(clientNamePane,BorderLayout.NORTH);


        JScrollPane clientScrollList = new JScrollPane(facturaList());
        clientScrollList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        clientScrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        clientScrollList.setViewportBorder(new LineBorder(Color.BLACK));

        form.add(clientScrollList,BorderLayout.CENTER);

        return form;
    }

    private JPanel facturaList(){

        facturalist = new JPanel();
        BoxLayout boxlayout = new BoxLayout(facturalist, BoxLayout.Y_AXIS);
        facturalist.setLayout(boxlayout);
        for(int i = 0; i < ConnectionController.getInstance().requestFacturaList(client).size(); i++){
            facturalist.add(facturaEntry(ConnectionController.getInstance().requestFacturaList(client).get(i)));
        }

        return facturalist;

    }

    private JPanel facturaEntry(Factura factura){
        JPanel facturaPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;

        JLabel facutraLabel = new JLabel("Factura nr. " + factura.getID());
        facturaPane.add(facutraLabel,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        for(int i = 0 ; i < factura.getNumberProducts(); i++){
            JLabel nameProduct = new JLabel(factura.getListaProduse().get(i).getName());
            JLabel quantityProduct = new JLabel(String.valueOf(factura.getListaProduse().get(i).getQuantity()));
            JLabel priceProduct = new JLabel(String.valueOf(factura.getListaProduse().get(i).getPrice()));
            JLabel priceProductTotal = new JLabel(String.valueOf(factura.getListaProduse().get(i).getQuantity() * factura.getListaProduse().get(i).getPrice()));

            facturaPane.add(nameProduct,gbc);
            gbc.gridx++;
            gbc.weightx = 0.2;
            facturaPane.add(quantityProduct,gbc);
            gbc.gridx++;
            gbc.weightx = 0.2;
            facturaPane.add(priceProduct,gbc);
            gbc.gridx++;
            gbc.weightx = 0.2;
            facturaPane.add(priceProductTotal,gbc);
            gbc.gridx = 0;
            gbc.gridy++;

        }
        gbc.gridx = 3;
        JLabel totalFactura = new JLabel(String.valueOf(factura.getTotalFactura()));
        facturaPane.add(totalFactura,gbc);

        gbc.gridx = 2;
        JLabel totalText = new JLabel("Total = ");
        facturaPane.add(totalText,gbc);



        return facturaPane;

    }
}
