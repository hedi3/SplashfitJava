/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopp.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import shopp.entities.Materiel;
import shopp.utils.Connection;

/**
 *
 * @author FK Info
 */
public class ServiceMateriel {
    public Connection connection = new Connection();
        public static int idM = 0;
    public static boolean selected = false;
     public void ajouter_materiel(Materiel p)
     {
         PreparedStatement req;
        try {
            req = connection.getConnection().prepareStatement("insert into materiel(description,ref,categorie)values(?,?,?)");
        
         req.setString(1, p.getDescription());
         req.setString(2, p.getRef());
         req.setString(3, p.getCategorie());
        
     req.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Service_projection.class.getName()).log(Level.SEVERE, null, ex);
        }
     
}
     public ObservableList<Materiel> showMateriel() {
        ObservableList<Materiel> materielObservableList = FXCollections.observableArrayList();
        String query = "SELECT * FROM materiel";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idMateriel = resultSet.getInt("id");
                 String description = resultSet.getString("description");
                String ref = resultSet.getString("ref");
             
               
              
                String categorie = resultSet.getString("categorie");
                Materiel materiel = new Materiel(idMateriel, description,ref,categorie);
                materielObservableList.add(materiel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMateriel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materielObservableList;
    }
      public ResultSet getall_materiel() {
         
        try {
            PreparedStatement req = connection.getConnection().prepareStatement("SELECT * FROM materiel");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
      return null;
    }
    public boolean supprimer_materiel(String p) {
         boolean ok=false;
        try {
            
            PreparedStatement req = connection.getConnection().prepareStatement("delete from materiel where ref = ? ");
            req.setString(1, p);
            req.executeUpdate();
ok=true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
    }
    public void modifier_materiel(Materiel p ,String nom) {
        try {
            PreparedStatement req = connection.getConnection().prepareStatement("update materiel set description=?,ref=? ,categorie=? where description=?");
            
            req.setString(1, p.getDescription());
            req.setString(2, p.getRef());
    
            req.setString(3, p.getCategorie());
            req.setString(4,nom);
          
            req.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }/*
    public projection recherche_projection(String nom) {
        projection p = new projection();
        try {
            PreparedStatement req = c.prepareStatement("select * from projection where nom=?  ");
            req.setString(1, nom);
            ResultSet rs = req.executeQuery();
            rs.next();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setGenre(rs.getString("genre"));
            p.setAge_recommande(rs.getInt("age_recommande"));
            p.setDuree(rs.getString("duree"));
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;
    }
    public ResultSet tri_projection() {
         
        try {
            PreparedStatement req = c.prepareStatement("SELECT * FROM projection ORDER BY duree");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
}*/

}