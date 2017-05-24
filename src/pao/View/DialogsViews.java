package pao.View;

import javax.swing.*;

/**
 * Created by rares on 23-May-17.
 */
public class DialogsViews extends JFrame {

    private static DialogsViews instance = null;

    public static DialogsViews getInstance(){
        if(instance == null){
            return new DialogsViews();
        }
        return instance;
    }

    public void connectionErrorDialog(){
        JOptionPane.showMessageDialog(this, "Connection Failed");
    }
}
