package pao.controllers;

import pao.ConnectionData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Karla on 10.05.2017.
 */
public class ClientConnection {

    private Socket socket;
    private ServerSocket serverSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientConnection(){
        try {
            serverSocket = new ServerSocket(ConnectionData.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean accept(){
        try {
            socket = serverSocket.accept();
            return setUpStreams();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller.getInstance().write("Connection to socket failed");
        return false;
    }

    private boolean setUpStreams(){
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());
            Controller.getInstance().setUpMessage();
            Controller.getInstance().write("Connection succesfull");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller.getInstance().write("Cannot get streams");
        return false;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

}
