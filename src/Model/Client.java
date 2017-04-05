package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Client implements Serializable {

    static AtomicInteger nextId = new AtomicInteger();
    private int id;

    public String getName(){
        return "Null";
    }
    public int getId(){
        return id;
    }

    protected void setId(int id){
        this.id = id;
    }
}
