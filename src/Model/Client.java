package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Client implements Serializable {

    ArrayList<Factura> listFacturi = new ArrayList<Factura>();
    public static AtomicInteger nextId = new AtomicInteger();
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

    public ArrayList<Factura> getListFacturi(){
        return listFacturi;
    }

    public int getNumberFacturi(){
        return listFacturi.size();
    }

    public void addFactura(Factura factura){
        listFacturi.add(factura);
    }
}
