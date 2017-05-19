package pao.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Client implements Serializable {

    private int id;
    private int numberFacturi;

    public static int generateID(){
        return 0;
    }

    public String getFullName(){
        return "Null";
    }
    public int getId(){
        return id;
    }

    protected void setID(int id){
        this.id = id;
    }

    public void setNumberFacturi(int number){
        numberFacturi = number;
    }
}
