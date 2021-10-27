/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author artie
 */
public class Customer {
    private String fName;
    private String lName;
    private int id;
    private int funds;

    public String getFname() {
        return fName;
    }

    public void setFname(String fName) {
        this.fName = fName;
    }

    public String getLname() {
        return lName;
    }

    public void setLname(String lName) {
        this.lName = lName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }
    @Override
    public String toString() {
        return "Customer{"
                + "First name=" + fName
                + ",\n Last name=" + lName
                + ",\n Customer ID=" + id
                + ",\n Funds=" + funds
                + "\n}";
    }
    
}
