/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.entities;

/**
 *
 * @author wided
 */
public class Reclamation {
    private int id;
    private String object,description;

    public Reclamation() {
    }

    public Reclamation(int id, String object, String description) {
        this.id = id;
        this.object = object;
        this.description = description;
    }

    public Reclamation(String object, String description) {
        this.object = object;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", object=" + object + ", description=" + description + '}';
    }
    
    
    
}
