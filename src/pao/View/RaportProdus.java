package pao.View;

import pao.Model.Sale;
import pao.Model.Sale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by rares on 18-May-17.
 */
public class RaportProdus {
    private JFrame jframe;
    ArrayList<Sale> saleList;
    String nameProduct = " ";

    RaportProdus(ArrayList<Sale> saleList, String nameProduct){
        this.saleList = saleList;
        this.nameProduct = nameProduct;
        startWindow();
    }

    private void startWindow() {
        jframe = new JFrame("Raport Produs");
        jframe.setPreferredSize(new Dimension(400, 300));
        jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jframe.add(raportView());
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    private JPanel raportView(){
        JPanel form = new JPanel(new BorderLayout());

        JPanel productNamePane = new JPanel();
        productNamePane.setLayout(new FlowLayout(FlowLayout.LEFT));
        productNamePane.setBorder(new EmptyBorder(20, 20, 5, 0));

        JLabel nameLabel = new JLabel("Numele Produsului:" + nameProduct);
        productNamePane.add(nameLabel);

        form.add(productNamePane,BorderLayout.NORTH);


        JScrollPane productScrollList = new JScrollPane(saleTable());
        productScrollList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        productScrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        productScrollList.setViewportBorder(new LineBorder(Color.BLACK));

        form.add(productScrollList,BorderLayout.CENTER);

        return form;
    }

    private JPanel saleTable(){

        JPanel salelist = new JPanel();
        BoxLayout boxlayout = new BoxLayout(salelist, BoxLayout.Y_AXIS);
        salelist.setLayout(boxlayout);
        for(int i = 0; i < saleList.size(); i++){
            JPanel saleEntry = new JPanel(new FlowLayout(FlowLayout.LEFT));
            saleEntry.add(new JLabel(String.valueOf(saleList.get(i).getBuyer())));
            saleEntry.add(new JLabel(String.valueOf(saleList.get(i).getPret())));
            saleEntry.add(new JLabel(String.valueOf(saleList.get(i).getStock())));
            salelist.add(saleEntry);
        }

        return salelist;

    }
    /*
    private JPanel saleEntry(Sale sale){
        JPanel salePane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        for(int i = 0 ; i < saleList; i++){
            JLabel nameProduct = new JLabel(sale.getListaProduse().get(i).getName());
            JLabel quantityProduct = new JLabel(String.valueOf(sale.getListaProduse().get(i).getQuantity()));
            JLabel priceProduct = new JLabel(String.valueOf(sale.getListaProduse().get(i).getPrice()));
            JLabel priceProductTotal = new JLabel(String.valueOf(sale.getListaProduse().get(i).getQuantity() * sale.getListaProduse().get(i).getPrice()));

            salePane.add(nameProduct,gbc);
            gbc.gridx++;
            gbc.weightx = 0.2;
            salePane.add(quantityProduct,gbc);
            gbc.gridx++;
            gbc.weightx = 0.2;
            salePane.add(priceProduct,gbc);
            gbc.gridx++;
            gbc.weightx = 0.2;
            salePane.add(priceProductTotal,gbc);
            gbc.gridx = 0;
            gbc.gridy++;

        }
        gbc.gridx = 3;
        JLabel totalSale = new JLabel(String.valueOf(sale.getTotalSale()));
        salePane.add(totalSale,gbc);

        gbc.gridx = 2;
        JLabel totalText = new JLabel("Total = ");
        salePane.add(totalText,gbc);

        return salePane;

    }
    */
}
