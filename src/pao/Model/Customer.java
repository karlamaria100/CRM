package pao.Model;

import java.io.Serializable;

public class Customer extends Client implements Serializable{

    private String name;
    private String surname;
    transient private static final int minID = 1000;
    transient private static final int maxID = 2000;

    public Customer(String name, String surname, int id) {
        super();
        this.name = name;
        this.surname = surname ;
        setID(id);
    }

    public static boolean isCustomer(int id){
        if(minID < id && id < maxID)
            return true;
        return false;
    }

    public static int generateID(){
        int range = (maxID - minID) + 1;
        return (int)(Math.random() * range) + minID;
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
