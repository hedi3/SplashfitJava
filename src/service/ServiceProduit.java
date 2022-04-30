/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import connexionbd.connexion;

import entity.Produit;
import java.sql.ResultSet;

/**
 *
 * @author FK Info
 */
public class ServiceProduit {
    Connection c = connexion.getinstance().getConn();
    
     public void ajouter_prod(Produit p)
     {
         PreparedStatement req;
        try {
            req = c.prepareStatement("insert into produit(nom,description,prix)values(?,?,?)");
        
         req.setString(1, p.getNom());
         req.setString(2, p.getDescription());
         req.setString(3, p.getPrix());
        
     req.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Service_projection.class.getName()).log(Level.SEVERE, null, ex);
        }
     
}
      public ResultSet getall_prod() {
         
        try {
            PreparedStatement req = c.prepareStatement("SELECT * FROM produit");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
      return null;
    }
    public boolean supprimer_prod(Produit p) {
         boolean ok=false;
        try {
            
            PreparedStatement req = c.prepareStatement("delete from produit where nom = ? ");
            req.setString(1, p.getNom());
            req.executeUpdate();
ok=true;
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        return ok;
    }
    public void modifier_prod(Produit p ,String solde) {
        try {
            PreparedStatement req = c.prepareStatement("update produit set nom=?,description=? ,prix=? where nom =? ");
            
            req.setString(1, p.getNom());
            req.setString(2, p.getDescription());
    
            req.setString(3, p.getPrix());
            req.setString(4,solde);
          
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