/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author hedia
 */
public class Offre {
     private int id;
    private String duree;
    private String solde;
     private String description ;
     
      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }
    
    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }
    public String getDescription() {
        return description;
    }
     
    public void setDescription(String description) {
        this.description = description;
    }


    public Offre(String duree, String solde, String description) {
        this.duree = duree;
        this.solde = solde;
        this.description = description;
    }
     

    public Offre(int id, String duree, String solde, String description) {
        this.id = id;
        this.duree = duree;
        this.solde = solde;
        this.description = description;
    }

    public Offre() {
    }
    
}
