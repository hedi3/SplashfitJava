/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.entities;

/**
 *
 * @author hedia
 */
public class Abonnement {
    private int id;
    private String name;
    private String email;
     private String numtel;
 
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public Abonnement(String name, String email, String numtel) {
        this.name = name;
        this.email = email;
        this.numtel = numtel;
    }

    public Abonnement(int id, String name, String email, String numtel) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.numtel = numtel;
    }



    public Abonnement() {
    }
     
}

