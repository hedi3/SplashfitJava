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
public class Produit {
    private int id;
    private String nom;
    private String description;
     private String prix;
 
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

  

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public Produit(String nom, String description, String prix) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    public Produit (int id, String nom, String description, String prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }



    public Produit() {
    }
     
}



    

