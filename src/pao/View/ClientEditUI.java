package pao.View;

import pao.Model.Client;
import pao.Network.ConnectionController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by rares on 18-May-17.
 */
public class ClientEditUI {
    private int type;
    private Controller control;
    private MainUI mainUI;
    private Client c;

    ClientEditUI(int type, Controller control, MainUI ui, Client c) {

        this.c = c;
        this.mainUI = ui;
        this.type = type;
        this.control = control;
        startWindow();
    }



    private JFrame jframe;

    private void startWindow() {
        jframe = new JFrame("Editare Client");
        jframe.setPreferredSize(new Dimension(400, 300));
        jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jframe.add(clientForm());
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    private JPanel clientForm() {
        JPanel clientForm = new JPanel(new BorderLayout());

        //WINDOW TITLE
        JLabel newClientText = new JLabel("Editati Clientul");
        newClientText.setBorder(new EmptyBorder(20, 20, 10, 20));
        clientForm.add(newClientText, BorderLayout.NORTH);

        //FORM INFOMATION
        JPanel form = new JPanel();
        BoxLayout boxlayout = new BoxLayout(form, BoxLayout.Y_AXIS);
        form.setLayout(boxlayout);

        //NAME PANE
        JPanel namePane = new JPanel();
        FlowLayout experimentLayout = new FlowLayout(FlowLayout.LEFT);
        namePane.setLayout(experimentLayout);
        namePane.setBorder(new EmptyBorder(20, 20, 5, 0));

        JLabel namePerson = new JLabel("Numele Persoanei:");
        JTextField namePersonTextField = new JTextField();
        namePersonTextField.setPreferredSize(new Dimension(200, 24));

        JPanel surnamePane = new JPanel();
        surnamePane.setLayout(experimentLayout);
        surnamePane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel surnamePerson = new JLabel("Prenumele Persoanei:");
        JTextField surnamePersonTextField = new JTextField();
        surnamePersonTextField.setPreferredSize(new Dimension(200, 24));

        JPanel nameCompanyPane = new JPanel();
        nameCompanyPane.setLayout(experimentLayout);
        nameCompanyPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel nameCompany = new JLabel("Numele Companiei:");
        JTextField nameCompanyTextField = new JTextField();
        nameCompanyTextField.setPreferredSize(new Dimension(200, 24));

        nameCompanyPane.add(nameCompany);
        nameCompanyPane.add(nameCompanyTextField);
        namePane.add(namePerson);
        namePane.add(namePersonTextField);
        surnamePane.add(surnamePerson);
        surnamePane.add(surnamePersonTextField);

        if(type == 1) {
            form.add(namePane);
            form.add(surnamePane);
        }
        else{
            form.add(nameCompanyPane);
        }

        clientForm.add(form, BorderLayout.CENTER);



        //CLEAR AND SAVE BUTTONS
        JPanel operationButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        operationButtons.setBorder(new EmptyBorder(5, 0, 20, 20));
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type == 2 && !nameCompanyTextField.getText().equals("")) {
                    control.editClient(c.getId(), nameCompanyTextField.getText());
                }
                else if(!namePersonTextField.getText().equals("") && !surnamePersonTextField.getText().equals("")){
                    control.editClient(c.getId(), namePersonTextField.getText(), surnamePersonTextField.getText());
                }
                jframe.dispose();
            }
        });
        operationButtons.add(saveButton);
        clientForm.add(operationButtons, BorderLayout.SOUTH);

        return clientForm;
    }
}
