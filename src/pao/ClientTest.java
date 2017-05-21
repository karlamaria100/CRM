package pao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Karla on 12.05.2017.
 */
public class ClientTest {


    public static void main(String[] args){
        try (Socket socket = new Socket("localhost", 9998);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());)
        {
            oos.flush();
            oos.writeObject("Obishergiouawr");
            System.out.println((String) ois.readObject());
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
