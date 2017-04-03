package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Company extends Client implements Serializable{

    private String name;

    public Company(String name){
        this.name = name;
        setId(nextId.incrementAndGet());
    }

    public String getName() {
        return name;
    }
}
