package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rares on 4/1/2017.
 */
public class ProductUI {

    private JFrame jframe;
    Controller control;
    MainUI mainUI;

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
                control.addProduct(nameProductTextField.getText(), productQuantityTextField.getText(), productPriceTextField.getText());
                //control.refreshProductList();
                jframe.dispose();
            }
        });
        JButton clearButton = new JButton("Clear");

        operationButtons.add(clearButton);
        operationButtons.add(saveButton);

        productForm.add(operationButtons, BorderLayout.SOUTH);

        return productForm;

    }
}
