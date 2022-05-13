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
public class Materiel {
    private int id;
    private String description;
    private String ref;
     private String categorie;
 
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

  

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Materiel(String description, String ref, String categorie) {
        this.description = description;
        this.ref = ref;
        this.categorie = categorie;
    }

    public Materiel(int id, String description, String ref, String categorie) {
        this.id = id;
        this.description = description;
        this.ref = ref;
        this.categorie = categorie;
    }



    public Materiel() {
    }
     
}

