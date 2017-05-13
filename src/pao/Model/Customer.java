package pao.Model;

import java.io.Serializable;

public class Customer extends Client implements Serializable{

    private String name;
    private String surname;

    public Customer(String name, String surname, int id) {
        super();
        this.name = name;
        this.surname = surname ;
        setID(id);
    }

    public String getSurname(){
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getFullName(){
        return name + ' ' + surname;
    }

}
