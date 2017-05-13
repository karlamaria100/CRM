package pao.Model;

import java.io.Serializable;

public class Company extends Client implements Serializable{

    private String name;

    public Company(String name, int id){
        super();
        this.name = name;
        setID(id);
    }

    public String getFullName() {
        return name;
    }
}
