package pao.Model;

import java.io.Serializable;

public class Company extends Client implements Serializable{

    private String name;

    public Company(String name){
        super();
        this.name = name;
        setId(nextId.incrementAndGet());
    }

    public String getName() {
        return name;
    }
}
