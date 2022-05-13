/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.views;


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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javax.mail.MessagingException;
import shopp.entities.Materiel;
import shopp.services.ServiceMateriel;


/**
 * FXML Controller class
 *
 * @author hedia
 */
public class MaterielController implements Initializable {
    @FXML
    private Button of;
  @FXML
    private TableView<Materiel> tb_materiel;
    @FXML
    private TableColumn<Materiel, String> tb_ref;
    @FXML
    private TableColumn<Materiel, String> tb_description;
    @FXML
    private TableColumn<Materiel, String> tb_categorie;
      @FXML
    private AnchorPane ap;
            @FXML
    private ImageView imgg;
@FXML
    private Button ajouter;
    @FXML
    private TextField ref;
    @FXML
    private TextField description;
    @FXML
    private TextField categorie;
    @FXML
    private Button modifier_materiel;
    @FXML
    private Button supp_materiel;
    @FXML
    private TextField searchTextField;
   ServiceMateriel pService = new ServiceMateriel();
    /**
     * Initializes the controller class.
     * 
     */
  
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         imgg.setImage(new Image("file:C:/Users/hedia/Downloads/pidev-main (1)/pidev-main/src/view/logo.png"));
        AnchorPane ap=new AnchorPane();
afficher_materiel();
    }  
    
     private void Error(String msg) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText(msg);
alert.showAndWait();
    }
    public void afficher_materiel()
    {
        ServiceMateriel ps = new ServiceMateriel();
        ResultSet resultset=ps.getall_materiel();
        List<Materiel> pl = new ArrayList<Materiel>();
        try {
            while(resultset.next()){
                Materiel p1 = new Materiel();
              
                p1.setRef(resultset.getString("ref"));
                p1.setDescription(resultset.getString("description"));
                p1.setCategorie(resultset.getString("categorie"));
              
               
                pl.add(p1);
                
               
                }
            ObservableList<Materiel> listmateriel = FXCollections.observableArrayList(pl);
        //tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        tb_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        tb_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
      
        tb_materiel.setItems(listmateriel);
        } catch (SQLException ex) {
            Logger.getLogger(MaterielController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
 @FXML
 void of() throws IOException{
      AnchorPane pane=FXMLLoader.load(getClass().getResource("Materiel.fxml"));
        ap.getChildren().setAll(pane);
 }
   @FXML
    void ajouter_materiel(ActionEvent event) throws MessagingException, IOException {
       ServiceMateriel ps= new ServiceMateriel();
        Materiel p =new Materiel();
        //p.setId(Integer.parseInt(id.getText()) );
       

         if (ref.getText().isEmpty() || description.getText().isEmpty()||categorie.getText().isEmpty())
{Error("Veuillez remplir tous le champs");

}else if(categorie.getText().length()>2)
{
    Error("Veuillez saisir correctement le numero");
}
         else{         p.setRef(ref.getText());
        p.setDescription(description.getText());
        p.setCategorie(categorie.getText()) ;
       ps.ajouter_materiel(p);
       String test;
     mail.sendmail();
      
       System.out.println("materiel ajouter avec succé");
       afficher_materiel();
       
   
}
    }
    @FXML
    private void modifier_materiel(ActionEvent event) throws IOException {
        ServiceMateriel ps= new ServiceMateriel();
        Materiel p =new Materiel();
        p.setRef(ref.getText());
        p.setDescription(description.getText());
       
        p.setCategorie(categorie.getText());
     
       ps.modifier_materiel(p, (ref.getText() ));
       System.out.println("materiel modifier avec succé");
       afficher_materiel();
     
    }
    @FXML
    private void supp_materiel(ActionEvent event) throws SQLException{
        ServiceMateriel ps= new ServiceMateriel();
      Materiel x=tb_materiel.getSelectionModel().getSelectedItem();
ps.supprimer_materiel(x.getRef());
ref.setText("");
description.setText("");
categorie.setText("");
afficher_materiel();
    }
 @FXML
    private void search(KeyEvent event) {
         FilteredList<Materiel> filteredData = new FilteredList<>(pService.showMateriel());
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (p.getRef().toLowerCase().contains(newValue.toLowerCase()) || p.getDescription().toLowerCase().contains(newValue.toLowerCase()) || p.getDescription().toString().toLowerCase().contains(newValue.toLowerCase()) ) {
                        return true; // Filter matches first name.
                    } else {
                        return false;
                    }
                });
            });
            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<Materiel> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedData.comparatorProperty().bind(tb_materiel.comparatorProperty());
            // 5. Add sorted (and filtered) data to the table.
            tb_materiel.setItems(sortedData);
    }
    
}
