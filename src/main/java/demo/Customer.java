package demo;

/**
 * Created by cx on 2018/11/27.
 */
public class Customer {
    private String name;
    private String surname;
    private String country;

    public Customer(String name, String surname, String country) {
        super();

        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public Customer() {
        super();
    }

    public String getCountry() {
        return this.country;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append("\t-----Customer-----\n");
        buff.append("\t\tName=" + this.name + "\n");
        buff.append("\t\tSurname Name=" + this.surname + "\n");
        buff.append("\t\tCountry=" + this.country + "\n");
        buff.append("\t-----Customer end-\n");
        return buff.toString();
    }
}
