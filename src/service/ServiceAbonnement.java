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
import entity.Abonnement;
import java.sql.ResultSet;

/**
 *
 * @author FK Info
 */
public class ServiceAbonnement {
    Connection c = connexion.getinstance().getConn();
    
     public void ajouter_ab(Abonnement p)
     {
         PreparedStatement req;
        try {
            req = c.prepareStatement("insert into abonnement(name,email,num_tel)values(?,?,?)");
        
         req.setString(1, p.getName());
         req.setString(2, p.getEmail());
         req.setString(3, p.getNumtel());
        
     req.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Service_projection.class.getName()).log(Level.SEVERE, null, ex);
        }
     
}
      public ResultSet getall_ab() {
         
        try {
            PreparedStatement req = c.prepareStatement("SELECT * FROM abonnement");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
      return null;
    }
    public boolean supprimer_ab(Abonnement p) {
         boolean ok=false;
        try {
            
            PreparedStatement req = c.prepareStatement("delete from abonnement where name = ? ");
            req.setString(1, p.getName());
            req.executeUpdate();
ok=true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
    }
    public void modifier_ab(Abonnement p ,String nom) {
        try {
            PreparedStatement req = c.prepareStatement("update abonnement set name=?,email=? ,num_tel=? where name=?");
            
            req.setString(1, p.getName());
            req.setString(2, p.getEmail());
    
            req.setString(3, p.getNumtel());
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