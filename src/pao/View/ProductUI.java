package pao.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Rares on 4/1/2017.
 */
public class ProductUI {

    private JFrame jframe;
    Controller control;
    MainUI mainUI;
    boolean type = false;

    ProductUI(Controller control, MainUI ui){
        this.control = control;
        this.mainUI = ui;
        startWindow();

    }

    private void startWindow() {
        jframe = new JFrame("Produs Nou");
        jframe.setPreferredSize(new Dimension(400, 300));
        jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jframe.add(productForm());
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    private JPanel productForm() {
        JPanel productForm = new JPanel(new BorderLayout());

        //WINDOW TITLE
        JLabel newProductText = new JLabel("Adaugati Produs Nou");
        newProductText.setBorder(new EmptyBorder(20, 20, 10, 20));
        productForm.add(newProductText, BorderLayout.NORTH);

        //FORM INFOMATION
        JPanel form = new JPanel();
        BoxLayout boxlayout = new BoxLayout(form, BoxLayout.Y_AXIS);
        form.setLayout(boxlayout);

        //NAME PANE
        FlowLayout experimentLayout = new FlowLayout(FlowLayout.LEFT);

        JPanel nameProductPane = new JPanel();
        nameProductPane.setLayout(experimentLayout);
        nameProductPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel nameProduct = new JLabel("Numele Produsului:");
        JTextField nameProductTextField = new JTextField();
        nameProductTextField.setPreferredSize(new Dimension(200, 24));

        nameProductPane.add(nameProduct);
        nameProductPane.add(nameProductTextField);

        //QUANTITY PANE

        JPanel productQuantityPane = new JPanel();
        productQuantityPane.setLayout(experimentLayout);
        productQuantityPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel productQuantity = new JLabel("Cantitate:");
        JTextField productQuantityTextField = new JTextField();
        productQuantityTextField.setPreferredSize(new Dimension(200,24));

        productQuantityPane.add(productQuantity);
        productQuantityPane.add(productQuantityTextField);


        //PRICE PANE

        JPanel pricePane = new JPanel();
        pricePane.setLayout(experimentLayout);
        pricePane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel productPrice = new JLabel("Pret:");
        JTextField productPriceTextField = new JTextField();
        productPriceTextField.setPreferredSize(new Dimension(100,24));
        JLabel productRON = new JLabel ("RON");

        pricePane.add(productPrice);
        pricePane.add(productPriceTextField);
        pricePane.add(productRON);



        // PRODUCT OR SERVICE PANE

        JPanel servicePane = new JPanel(new FlowLayout());

        JRadioButton serviceButton = new JRadioButton("Serviciu");
        serviceButton.setMnemonic(KeyEvent.VK_B);
        serviceButton.setActionCommand("Serviciu");
        serviceButton.setSelected(true);

        JRadioButton productButton = new JRadioButton("Produs");
        productButton.setMnemonic(KeyEvent.VK_B);
        productButton.setActionCommand("Produs");
        productButton.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(serviceButton);
        group.add(productButton);

        servicePane.add(serviceButton);
        servicePane.add(productButton);

        form.add(nameProductPane);
        form.add(productQuantityPane);
        form.add(pricePane);
        form.add(servicePane);

        productForm.add(form, BorderLayout.CENTER);



        //CLEAR AND SAVE BUTTONS
        JPanel operationButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        operationButtons.setBorder(new EmptyBorder(5, 0, 20, 20));
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(control.checkProductExistance(nameProductTextField.getText())) {
                    control.addProduct(nameProductTextField.getText(), productQuantityTextField.getText(), productPriceTextField.getText(), type);
                }
                jframe.dispose();
            }
        });
        operationButtons.add(saveButton);

        productForm.add(operationButtons, BorderLayout.SOUTH);

        return productForm;

    }
}
