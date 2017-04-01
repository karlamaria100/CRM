package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Rares on 4/1/2017.
 */
public class Client implements Serializable {

    ArrayList<Factura> facturaList = new ArrayList<>();
    static AtomicInteger nextId = new AtomicInteger();
    private int id;

}
