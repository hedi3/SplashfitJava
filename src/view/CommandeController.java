
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import entity.Commande;
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

import service.ServiceCommande;

/**
 * FXML Controller class
 *
 * @author hedia
 */
public class CommandeController implements Initializable {
  @FXML
    private TableView<Commande> tb_com;
    @FXML
    private TableColumn<?,?> tb_adresse;
    @FXML
    private TableColumn<Commande, String> tb_payement;
    @FXML
    private TableColumn<Commande, String> tb_produits;
@FXML
    private Button ajouter;

    @FXML
    private TextField adresse;
    @FXML
    private TextField payement;
    @FXML
    private TextField produits;
    @FXML
    private Button modifier_com;
    @FXML
    private Button supp_com;
    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
     afficher_com();
    }    
    
     private void Error(String msg) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText(msg);
alert.showAndWait();
    }

    public void afficher_com()
    {
        ServiceCommande ps = new ServiceCommande();
        ResultSet resultset=ps.getall_com();
        List<Commande> pl = new ArrayList<Commande>();
        try {
            while(resultset.next()){
                Commande p1 = new Commande();
              
                p1.setAdresse(resultset.getString("adresse"));
                p1.setPayement(resultset.getString("payement"));
                p1.setProduits(resultset.getString("produits"));
              
               
                pl.add(p1);
                
               
                }
            ObservableList<Commande> listab = FXCollections.observableArrayList(pl);
        //tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tb_payement.setCellValueFactory(new PropertyValueFactory<>("payement"));
        tb_produits.setCellValueFactory(new PropertyValueFactory<>("produits"));
      
        tb_com.setItems(listab);
        } catch (SQLException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
   @FXML
    void ajouter(ActionEvent event) throws MessagingException {
       ServiceCommande ps= new ServiceCommande();
        Commande p =new Commande();
        //p.setId(Integer.parseInt(id.getText()) );
       

         if (adresse.getText().isEmpty() || payement.getText().isEmpty()||produits.getText().isEmpty())
{Error("Veuillez remplir tous le champs");

}
         else{         p.setAdresse(adresse.getText());
        p.setPayement(payement.getText());
        p.setProduits(produits.getText()) ;
         mail.sendmail();
       ps.ajouter_com(p);
       String test;
    
      
       System.out.println("commande ajouter avec succé");
       afficher_com();}
    }
    @FXML
    private void modifier_com(ActionEvent event) {
        ServiceCommande ps= new ServiceCommande();
        Commande p =new Commande();
        p.setAdresse(adresse.getText());
        p.setPayement(payement.getText());
       
        p.setProduits(produits.getText());
     
       ps.modifier_com(p, (adresse.getText() ));
       System.out.println("commande modifier avec succé");
       afficher_com();
    }
     @FXML
    private void supp_com(ActionEvent event) throws SQLException{
        ServiceCommande ps= new ServiceCommande();
      Commande x=tb_com.getSelectionModel().getSelectedItem();
ps.supprimer_com(x);
adresse.setText("");
payement.setText("");
produits.setText("");

afficher_com();
    }
    
}
