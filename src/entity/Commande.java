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
public class Commande {
    private int id;
    private String adresse;
    private String payement;
     private String produits;
 
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getPayement() {
        return payement;
    }

    public void setPayement(String payement) {
        this.payement = payement;
    }

  

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getProduits() {
        return produits;
    }

    public void setProduits(String produits) {
        this.produits = produits;
    }

    public Commande(String adresse, String payement, String produits) {
        this.adresse = adresse;
        this.payement = payement;
        this.produits = produits;
    }

    public Commande (int id, String adresse, String payement, String produits) {
        this.id = id;
        this.adresse = adresse;
        this.payement = payement;
        this.produits = produits;
    }



    public Commande() {
    }
     
}



    

