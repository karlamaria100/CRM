package pao.Model;

import java.io.Serializable;

public class Customer extends Client implements Serializable{

    private String name;
    private String surname;

    public Customer(String name, String surname) {
        super();
        this.name = name;
        this.surname = surname;
        setId(nextId.incrementAndGet());
    }

    public String getSurname(){
        return surname;
    }

    public String getName() {
        return name;
    }

}
