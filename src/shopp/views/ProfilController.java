/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.views;

import shopp.entities.User;
import shopp.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class ProfilController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
  
    @FXML
    private Label roles;

    @FXML
    private Button supp1;
    @FXML
    private TextField Nom2;
    @FXML
    private TextField email2;
    private TextField cin2;
    @FXML
    private TextField mdp2;
    @FXML
    private TextField Prenom2;
    @FXML
    private Label email;
    @FXML
    private Button Confirmer;
    @FXML
    private Label labelid;
    @FXML
    private Label adresse;
    @FXML
    private TextField inputadresse;
    @FXML
    private Hyperlink prec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             nom.setText(LoginController.connectedUser.getNom());
                          prenom.setText(LoginController.connectedUser.getPrenom()); 

                                       adresse.setText(LoginController.connectedUser.getAdresse()); 

                                                    roles.setText(LoginController.connectedUser.getType()); 
   email.setText(LoginController.connectedUser.getEmail()); 

         labelid.setText(Integer.toString((int)LoginController.connectedUser.getId())); 
      
    }    

    @FXML
    private void Modif(ActionEvent event) {
     Nom2.setVisible(true);
    
    email2.setVisible(true);
    
    inputadresse.setVisible(true);
   
    mdp2.setVisible(true);
    
    Prenom2.setVisible(true);   
    
    
    
         Nom2.setText(nom.getText());
    
    email2.setText(email.getText());
    
    inputadresse.setText(adresse.getText());
   
mdp2.setText( LoginController.connectedUser.getPassword());
    
    Prenom2.setText(prenom.getText());   
    
    
        
        
        
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
    private void Confirmer(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException {
        
          UserService productService = new UserService();
         
         //   public User(String email, String roles, String password, String nom, String prenom, String cin, String image, String sexe) {

            User c = new User(Integer.parseInt(labelid.getText()),Nom2.getText(),Prenom2.getText(),email2.getText()
                        ,inputadresse.getText(),LoginController.connectedUser.getImage(),LoginController.connectedUser.getPassword(),LoginController.connectedUser.getType());

         
         
         
                       
             Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText(" Confirmer ");
            a.setHeaderText(null);
            a.showAndWait();         
        
        productService.modifierUser(c);
       
       nom.setText(Nom2.getText());
    
    email.setText(email2.getText());
    
    adresse.setText(inputadresse.getText());
   
       

    prenom.setText(Prenom2.getText());   
       Nom2.setVisible(false);
    
    email2.setVisible(false);
    
   
   
    mdp2.setVisible(false);
    
    Prenom2.setVisible(false);   
    Confirmer.setVisible(false);  
        
    }

    @FXML
    private void prec(ActionEvent event) throws IOException {
           Parent page1 = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Liste des Users");
        stage.setScene(scene);
        stage.show();
        
        
    }
    
}
