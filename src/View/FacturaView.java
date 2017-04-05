package View;

import Model.Client;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rares on 4/1/2017.
 */
public class FacturaView {

    Client c;
    private JFrame jframe;
    Controller control;

    FacturaView(Client c, Controller control){
        this.c = c;
        startWindow();
        this.control = control;
    }

    private void startWindow() {
        jframe = new JFrame("Factura Noua");
        jframe.setPreferredSize(new Dimension(630, 300));
        jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        jframe.add(facturaForm());
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    private JPanel facturaForm(){
        JPanel form = new JPanel(new BorderLayout());

        JPanel clientNamePane = new JPanel();
        FlowLayout experimentLayout = new FlowLayout(FlowLayout.LEFT);
        clientNamePane.setLayout(experimentLayout);
        clientNamePane.setBorder(new EmptyBorder(20, 20, 5, 0));

        JLabel nameClient = new JLabel("Numele Clientului:");
        JLabel nameClientSet = new JLabel(c.getName());

        clientNamePane.add(nameClient);
        clientNamePane.add(nameClientSet);

        form.add(clientNamePane,BorderLayout.NORTH);


        JScrollPane clientScrollList = new JScrollPane(productList());
        clientScrollList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        clientScrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        clientScrollList.setViewportBorder(new LineBorder(Color.BLACK));

        form.add(clientScrollList,BorderLayout.CENTER);


        JPanel operationButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        operationButtons.setBorder(new EmptyBorder(5, 0, 20, 20));
        JButton saveButton = new JButton("Save");
        JButton clearButton = new JButton("Clear");

        operationButtons.add(clearButton);
        operationButtons.add(saveButton);

        form.add(operationButtons, BorderLayout.SOUTH);

        return form;
    }

    JPanel productList;
    private JPanel productList(){

        productList = new JPanel();
        BoxLayout boxlayout = new BoxLayout(productList, BoxLayout.Y_AXIS);
        productList.setLayout(boxlayout);

        productList.add(productEntry());

        return productList;
    }

    private JPanel productEntry(){
        JPanel product = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //JPanel product = new JPanel(new GridBagLayout());
        JButton addButton = null;

        JLabel productNameLabel = new JLabel("Nume Produs:");
        JTextField productNameTextField = new JTextField();

        productNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            int characterCount = 0;
            boolean done = false;

            public void behaviour(){
                if(characterCount == 0){
                    //System.out.println("Gol");
                    productList.remove(productList.getComponents()[productList.getComponents().length - 1]);
                    productList.revalidate();
                    done = false;
                }
                if(characterCount != 0 && done == false){
                    //System.out.println(characterCount);
                    done = true;
                    productList.add(productEntry());
                    productList.revalidate();

                }
                else {
                    //System.out.println(characterCount);
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                characterCount++;
                behaviour();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                characterCount--;
                behaviour();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("Change");
            }
        });
        productNameTextField.setPreferredSize(new Dimension(150,24));

        JLabel productQuantity = new JLabel("Quantity");
        JTextField productQuantityTextField = new JTextField();
        productQuantityTextField.setPreferredSize(new Dimension(50,24));

        JLabel productFinalPrice = new JLabel ("0.00 RON");

        addButton = new JButton("Adauga");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = true;
                if(productNameTextField.getText().equals("")){
                    valid = false;
                    JOptionPane.showMessageDialog(jframe,
                            "Campul Numele Produsului invalid",
                            "Eroare validare",
                            JOptionPane.ERROR_MESSAGE);
                }
                if(productQuantityTextField.getText().equals("")){
                    valid = false;
                    JOptionPane.showMessageDialog(jframe,
                            "Campul Cantitate invalid",
                            "Eroare validare",
                            JOptionPane.ERROR_MESSAGE);
                }
                if(control.queryProduct(productNameTextField.getText()) != null){
                    valid = false;
                    System.out.println(control.queryProduct(productNameTextField.getText()));
                    JOptionPane.showMessageDialog(jframe,
                            "Produsul nu exista in ofeta! \n Incercati altceva.",
                            "Eroare validare",
                            JOptionPane.ERROR_MESSAGE);
                }
                if(valid) {
                    JButton removeButton = new JButton("Sterge");
                    product.add(removeButton);
                    productNameTextField.setEditable(false);
                    productQuantityTextField.setEditable(false);
                    product.revalidate();
                }
            }
        });

        product.add(productNameLabel);
        product.add(productNameTextField);
        product.add(productQuantity);
        product.add(productQuantityTextField);
        product.add(productFinalPrice);
        product.add(addButton);

        return product;
    }


}
