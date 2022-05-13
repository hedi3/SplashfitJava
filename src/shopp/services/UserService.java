/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.services;

import shopp.utils.MyDB;
import shopp.entities.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrateur
 */
public class UserService {
    Connection connexion;   
  public UserService() {
        connexion = MyDB.getInstance().getConnection();
    }


 
 
     
 
  public void ajouterUser(User u) throws SQLException {
        String req = "INSERT INTO `user` (`nom`,`prenom`,`email`,`adresse`,`image`,`password`,`type`) "
                + "VALUES (?,?,?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, u.getNom());
        ps.setString(2, u.getPrenom());
       
    
           ps.setString(3, u.getEmail());
              ps.setString(4, u.getAdresse());
                 ps.setString(5, u.getImage());
                  
                      try {
            ps.setString(6, hashmdp (u.getPassword()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }     
                       
      ps.setString(7, u.getType());

        ps.executeUpdate();
    }
  ////////
     
    public String hashmdp (String mdp) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        StringBuffer hexString = new StringBuffer();
     for (int i=0;i<byteData.length;i++) {
      String hex=Integer.toHexString(0xff & byteData[i]);
          if(hex.length()==1) hexString.append('0');
          hexString.append(hex);
     }
     
    
       return hexString.toString();
    }
  
  //////////
  

     public List<User> AfficherAllUsers() throws SQLException {

        List<User> Users = new ArrayList<>();
        String req = "select * from user ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            User u = new User(rst.getInt("id")
                    , rst.getString("nom")
                    , rst.getString("prenom")
                    , rst.getString("email")
                    , rst.getString("adresse")
                     , rst.getString("image")
                     , rst.getString("password")
                     , rst.getString("type"));
                   
            Users.add(u);
        }
        return Users;
    }
     

     public void SupprimerUser(User u) throws SQLException {

        String req = "DELETE FROM user WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
       

      public void modifierUser(User u) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE user SET "
                + " nom='"+u.getNom()+"'"
                + ", prenom='"+u.getPrenom()+"'"
                
                + ", email='"+u.getEmail()+"'"
                 + ", adresse='"+u.getAdresse()+"'"
              
                 + ", image='"+u.getImage()+"'"
                  + ", password='"+hashmdp(u.getPassword())+"'"
                
                
                + ", type='"+u.getType()+"' where id  = "+u.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    } 
      
   
     
     
            
             

   

   
 
 
   
 
}
