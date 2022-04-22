/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Offre;
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
import service.ServiceOffre;

/**
 * FXML Controller class
 *
 * @author hedia
 */
public class OffreController implements Initializable {
  @FXML
    private TableView<Offre> tb_off;
    @FXML
    private TableColumn<Offre, String> tb_solde;
    @FXML
    private TableColumn<Offre, String> tb_description;
    @FXML
    private TableColumn<Offre, String> tb_duree;
@FXML
    private Button ajouterO;
    @FXML
    private TextField solde;
    @FXML
    private TextField description;
    @FXML
    private TextField duree;
    @FXML
    private Button modifier_off;
    @FXML
    private Button supp_O;
    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     afficher_off();
    }    
     private void Error(String msg) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText(msg);
alert.showAndWait();
    }
    public void afficher_off()
    {
        ServiceOffre ps = new ServiceOffre();
        ResultSet resultset=ps.getall_off();
        List<Offre> pl = new ArrayList<Offre>();
        try {
            while(resultset.next()){
                Offre p1 = new Offre();
              
                p1.setSolde(resultset.getString("solde"));
                p1.setDescription(resultset.getString("description"));
                p1.setDuree(resultset.getString("duree"));
              
               
                pl.add(p1);
                
               
                }
            ObservableList<Offre> listab = FXCollections.observableArrayList(pl);
        //tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_solde.setCellValueFactory(new PropertyValueFactory<>("solde"));
        tb_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        tb_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
      
        tb_off.setItems(listab);
        } catch (SQLException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

   @FXML
    void ajouterO(ActionEvent event) {
       ServiceOffre ps= new ServiceOffre();
        Offre p =new Offre();
        //p.setId(Integer.parseInt(id.getText()) );
       

         if (solde.getText().isEmpty() || description.getText().isEmpty()||duree.getText().isEmpty())
{Error("Veuillez remplir tous le champs");

}
         else if(solde.getText().length() > 2||duree.getText().length()>2)
         {
             Error("Veuille saisir la duree et le solde correctemeent");
         }
         else{         p.setSolde(solde.getText());
        p.setDescription(description.getText());
        p.setDuree(duree.getText()) ;
       ps.ajouter_off(p);
       System.out.println("offre ajouter avec succé");
       afficher_off();}
    }
    @FXML
    private void modifier_off(ActionEvent event) {
        ServiceOffre ps= new ServiceOffre();
       Offre p =new Offre();
        p.setSolde(solde.getText());
        p.setDescription(description.getText());
       
        p.setDuree(duree.getText());
     
       ps.modifier_off(p, (solde.getText() ));
       System.out.println("offre modifier avec succé");
       afficher_off();
    }
     @FXML
    private void supp_O(ActionEvent event) throws SQLException{
        ServiceOffre ps= new ServiceOffre();
      Offre x=tb_off.getSelectionModel().getSelectedItem();
ps.supprimer_off(x);
solde.setText("");
description.setText("");
duree.setText("");

afficher_off();
    }
}
