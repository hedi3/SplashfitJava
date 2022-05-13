/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.views;

import shopp.utils.MyDB;
import shopp.entities.User;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class LoginController implements Initializable {

    @FXML
    private Button inscrivezvous;
 
    
       public LoginController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = MyDB.getInstance().getConnection();
    }
    Connection connexion;
    public static User connectedUser;
    @FXML
    private TextField email;
    @FXML
    private TextField mdp;
    @FXML
    private Button login;
    /**
     * Initializes the controller class.
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    
      private String hashmdp(String mdp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
    
    @FXML
    private void login(ActionEvent event) throws SQLException, NoSuchAlgorithmException, IOException {
      String req = "SELECT * from user WHERE email LIKE '" + email.getText() + "' and password LIKE '" + hashmdp(mdp.getText()) + "' ";

            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {
//String req = "INSERT INTO `user` (`email`,`roles`,`password`,`nom`,`prenom`,`cin`,`image`,`sexe`) "
                User p = new User(rst.getInt("id")
                    , rst.getString("nom")
                    , rst.getString("prenom")
                    , rst.getString("email")
                    , rst.getString("adresse")
                     , rst.getString("image")
                     , rst.getString("password")
                     , rst.getString("type")
                
                );
                      
                LoginController.connectedUser = p;  
        
                
                if(LoginController.connectedUser.getType().equals("Admin")){
                         try {
            Parent parent = FXMLLoader.load(getClass().getResource("List.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("profil");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
           
                
                
                else   if(LoginController.connectedUser.getType().equals("Client")){
                    
       try {
            Parent parent = FXMLLoader.load(getClass().getResource("Profil.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Profil");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
                }  
       
        
    }
            
    


    @FXML
    private void InscrivezVous(ActionEvent event) throws IOException {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("Ajout.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ajout");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
