package View;

import Model.Client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rares on 06-Apr-17.
 */
public class RaportUI {
    private JFrame jframe;
    private Client client;

    RaportUI(Client c){
        this.client = c;
        startWindow();
    }

    private void startWindow() {
        jframe = new JFrame("Client Nou");
        jframe.setPreferredSize(new Dimension(400, 300));
        jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jframe.add(new JLabel(client.getName()));
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }
}
