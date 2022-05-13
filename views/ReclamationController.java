/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.views;

import shopp.entities.Reclamation;
import shopp.entities.mail;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.mail.MessagingException;
import shopp.services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author hedia
 */
public class ReclamationController implements Initializable {
    @FXML
    private Button rec;
  @FXML
    private TableView<Reclamation> tb_reclamation;
    @FXML
    private TableColumn<Reclamation, String> tb_object;
    @FXML
    private TableColumn<Reclamation, String> tb_description;
   
      @FXML
    private AnchorPane ap;
            @FXML
    private ImageView imgg;
@FXML
    private Button ajouter;
    @FXML
    private TextField object;
    @FXML
    private TextField description;
   
    @FXML
    private Button modifier_reclamation;
    @FXML
    private Button supp_reclamation;
    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         imgg.setImage(new Image("file:C:/Users/hedia/Downloads/pidev-main (1)/pidev-main/src/view/logo.png"));
        AnchorPane ap=new AnchorPane();
     afficher_reclamation();
    }  
    
     private void Error(String msg) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText(msg);
alert.showAndWait();
    }
    public void afficher_reclamation()
    {
        ReclamationService ps = new ReclamationService();
        ResultSet resultset=ps.getall_rc();
        List<Reclamation> pl = new ArrayList<Reclamation>();
        try {
            while(resultset.next()){
                Reclamation p1 = new Reclamation();
              
                p1.setObject(resultset.getString("object"));
                p1.setDescription(resultset.getString("description"));
             
              
               
                pl.add(p1);
                
               
                }
            ObservableList<Reclamation> listab = FXCollections.observableArrayList(pl);
        //tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_object.setCellValueFactory(new PropertyValueFactory<>("object"));
        tb_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        
      
        tb_reclamation.setItems(listab);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
 @FXML
 void of() throws IOException{
      AnchorPane pane=FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
        ap.getChildren().setAll(pane);
 }
   @FXML
    void ajouter_reclamation(ActionEvent event) throws MessagingException {
       ReclamationService ps= new ReclamationService();
        Reclamation p =new Reclamation();
        //p.setId(Integer.parseInt(id.getText()) );
       

         if (object.getText().isEmpty() || description.getText().isEmpty())
{Error("Veuillez remplir tous le champs");


    Error("Veuillez saisir correctement le numero");
}
         else{         p.setObject(object.getText());
        p.setDescription(description.getText());
       
       ps.ajouterReclamation(p);
       String test;
     mail.sendmail();
      
       System.out.println("reclamation ajouter avec succé");
       afficher_reclamation();}
    }
    @FXML
    private void modifier_reclamation(ActionEvent event) {
        ReclamationService ps= new ReclamationService();
        Reclamation p =new Reclamation();
        p.setObject(object.getText());
        p.setDescription(description.getText());
       
       
     
       ps.modifier_reclamation(p,p.getObject());
       System.out.println("reclamation modifier avec succé");
       afficher_reclamation();
    }
     @FXML
    private void supp_reclamation(ActionEvent event) throws SQLException{
        ReclamationService ps= new ReclamationService();
      Reclamation x=tb_reclamation.getSelectionModel().getSelectedItem();
ps.supprimer_rec(x.getObject());
object.setText("");
description.setText("");


afficher_reclamation();
    }
}
