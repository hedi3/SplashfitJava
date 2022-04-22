/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Abonnement;
import entity.mail;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.MessagingException;
import service.ServiceAbonnement;

/**
 * FXML Controller class
 *
 * @author hedia
 */
public class AbonnementController implements Initializable {
  @FXML
    private TableView<Abonnement> tb_ab;
    @FXML
    private TableColumn<Abonnement, String> tb_name;
    @FXML
    private TableColumn<Abonnement, String> tb_email;
    @FXML
    private TableColumn<Abonnement, String> tb_numtel;
@FXML
    private Button ajouter;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField num_tel;
    @FXML
    private Button modifier_ab;
    @FXML
    private Button supp_ab;
    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     afficher_ab();
    }    
     private void Error(String msg) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText(msg);
alert.showAndWait();
    }
    public void afficher_ab()
    {
        ServiceAbonnement ps = new ServiceAbonnement();
        ResultSet resultset=ps.getall_ab();
        List<Abonnement> pl = new ArrayList<Abonnement>();
        try {
            while(resultset.next()){
                Abonnement p1 = new Abonnement();
              
                p1.setName(resultset.getString("name"));
                p1.setEmail(resultset.getString("email"));
                p1.setNumtel(resultset.getString("num_tel"));
              
               
                pl.add(p1);
                
               
                }
            ObservableList<Abonnement> listab = FXCollections.observableArrayList(pl);
        //tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tb_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tb_numtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
      
        tb_ab.setItems(listab);
        } catch (SQLException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

   @FXML
    void ajouter(ActionEvent event) throws MessagingException {
       ServiceAbonnement ps= new ServiceAbonnement();
        Abonnement p =new Abonnement();
        //p.setId(Integer.parseInt(id.getText()) );
       

         if (name.getText().isEmpty() || email.getText().isEmpty()||num_tel.getText().isEmpty())
{Error("Veuillez remplir tous le champs");

}else if(num_tel.getText().length()!=8)
{
    Error("Veuillez saisir correctement le numero");
}
         else{         p.setName(name.getText());
        p.setEmail(email.getText());
        p.setNumtel(num_tel.getText()) ;
       ps.ajouter_ab(p);
       String test;
     mail.sendmail();
      
       System.out.println("abonnement ajouter avec succé");
       afficher_ab();}
    }
    @FXML
    private void modifier_ab(ActionEvent event) {
        ServiceAbonnement ps= new ServiceAbonnement();
        Abonnement p =new Abonnement();
        p.setName(name.getText());
        p.setEmail(email.getText());
       
        p.setNumtel(num_tel.getText());
     
       ps.modifier_ab(p, (name.getText() ));
       System.out.println("abo modifier avec succé");
       afficher_ab();
    }
     @FXML
    private void supp_ab(ActionEvent event) throws SQLException{
        ServiceAbonnement ps= new ServiceAbonnement();
      Abonnement x=tb_ab.getSelectionModel().getSelectedItem();
ps.supprimer_ab(x);
name.setText("");
email.setText("");
num_tel.setText("");

afficher_ab();
    }
}
