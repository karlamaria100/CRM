package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Rares on 4/1/2017.
 */
public class Company implements Serializable{

    String name;
    ArrayList<Factura> facturaList = new ArrayList<>();
    static AtomicInteger nextId = new AtomicInteger();
    private int id;

    public Company(String name){
        this.name = name;
        id = nextId.incrementAndGet();
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Factura> getFacturaList() {
        return facturaList;
    }

    public void addFactura(Factura f){
        facturaList.add(f);
    }
}
