package View;

import Model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rares on 4/14/2017.
 */
public class ProductEditUI {

    private JFrame jframe;
    Controller control;
    private Product product;

    ProductEditUI(Product product, Controller control){
        this.control = control;
        this.product = product;

        startWindow();
    }

    private void startWindow() {
        jframe = new JFrame("Editeaza Produs");
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
        JLabel newProductText = new JLabel("Editati Produs");
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
        JTextField nameProductTextField = new JTextField(product.getName());
        nameProductTextField.setPreferredSize(new Dimension(200, 24));

        nameProductPane.add(nameProduct);
        nameProductPane.add(nameProductTextField);

        //QUANTITY PANE

        JPanel productQuantityPane = new JPanel();
        productQuantityPane.setLayout(experimentLayout);
        productQuantityPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel productQuantity = new JLabel("Cantitate:");
        JTextField productQuantityTextField = new JTextField(String.valueOf(product.getQuantity()));
        productQuantityTextField.setPreferredSize(new Dimension(200,24));

        productQuantityPane.add(productQuantity);
        productQuantityPane.add(productQuantityTextField);


        //PRICE PANE

        JPanel pricePane = new JPanel();
        pricePane.setLayout(experimentLayout);
        pricePane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel productPrice = new JLabel("Pret:");
        JTextField productPriceTextField = new JTextField(String.valueOf(product.getPrice()));
        productPriceTextField.setPreferredSize(new Dimension(100,24));
        JLabel productRON = new JLabel ("RON");

        pricePane.add(productPrice);
        pricePane.add(productPriceTextField);
        pricePane.add(productRON);

        form.add(nameProductPane);
        form.add(productQuantityPane);
        form.add(pricePane);

        productForm.add(form, BorderLayout.CENTER);

        //CLEAR AND SAVE BUTTONS
        JPanel operationButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        operationButtons.setBorder(new EmptyBorder(5, 0, 20, 20));
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.editProduct(product, nameProductTextField.getText(), productQuantityTextField.getText(), productPriceTextField.getText());
                jframe.dispose();
            }
        });
        operationButtons.add(saveButton);

        productForm.add(operationButtons, BorderLayout.SOUTH);

        return productForm;

    }
}
