/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import entity.Produit;
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
import service.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author hedia
 */
public class ProduitController implements Initializable {
  @FXML
    private TableView<Produit> tb_prod;
    @FXML
    private TableColumn<Produit, String> tb_nom;
    @FXML
    private TableColumn<Produit, String> tb_description;
    @FXML
    private TableColumn<Produit, String> tb_prix;
@FXML
    private Button ajouter;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField prix;
    @FXML
    private Button modifier_prod;
    @FXML
    private Button supp_prod;
    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     afficher_prod();
    }    
     private void Error(String msg) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText(msg);
alert.showAndWait();
    }
    public void afficher_prod()
    {
        ServiceProduit ps = new ServiceProduit();
        ResultSet resultset=ps.getall_prod();
        List<Produit> pl = new ArrayList<Produit>();
        try {
            while(resultset.next()){
                Produit p1 = new Produit();
              
                p1.setNom(resultset.getString("nom"));
                p1.setDescription(resultset.getString("description"));
                p1.setPrix(resultset.getString("prix"));
              
               
                pl.add(p1);
                
               
                }
            ObservableList<Produit> listprod = FXCollections.observableArrayList(pl);
        //tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tb_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        tb_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
      
        tb_prod.setItems(listprod);
        } catch (SQLException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

   @FXML
    void ajouter(ActionEvent event)  {
       ServiceProduit ps= new ServiceProduit();
        Produit p =new Produit();
        //p.setId(Integer.parseInt(id.getText()) );
       

         if (nom.getText().isEmpty() || description.getText().isEmpty()||prix.getText().isEmpty())
{Error("Veuillez remplir tous le champs");

}else if(prix.getText().length()>4)
{
    Error("Veuillez saisir correctement le prix");
}
         else{         p.setNom(nom.getText());
        p.setDescription(description.getText());
        p.setPrix(prix.getText()) ;
       ps.ajouter_prod(p);
       String test;
    
      
       System.out.println("produit ajouter avec succé");
       afficher_prod();}
    }
    @FXML
    private void modifier(ActionEvent event) {
        ServiceProduit ps= new ServiceProduit();
        Produit p =new Produit();
        p.setNom(nom.getText());
        p.setDescription(description.getText());
       
        p.setPrix(prix.getText());
     
       ps.modifier_prod(p, (nom.getText() ));
       System.out.println("produit modifier avec succé");
       afficher_prod();
    }
     @FXML
    private void supp(ActionEvent event) throws SQLException{
        ServiceProduit ps= new ServiceProduit();
      Produit x=tb_prod.getSelectionModel().getSelectedItem();
ps.supprimer_prod(x);
nom.setText("");
description.setText("");
prix.setText("");

afficher_prod();
    }
}
