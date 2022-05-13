    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.views;


import shopp.entities.Offre;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.stream;
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
import net.glxn.qrgen.image.ImageType;
import net.glxn.qrgen.QRCode;
import shopp.services.ServiceOffre;

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
        @FXML
    private Button ab;
       @FXML
    private AnchorPane op;
         @FXML
    private ImageView img;
             @FXML
    private ImageView image;
    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        img.setImage(new Image("file:C:/Users/hedia/Downloads/pidev-main (1)/pidev-main/src/view/logo.png"));
          AnchorPane op=new AnchorPane();
     afficher_off();
    }    public void ab() throws IOException{
         AnchorPane pane=FXMLLoader.load(getClass().getResource("Offre.fxml"));
        op.getChildren().setAll(pane);
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
            Logger.getLogger(OffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

   @FXML
    void ajouterO(ActionEvent event) throws FileNotFoundException, IOException {
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
       
//       sms.sms();
      //  sms s=new sms();
//        s.smss();
       ps.ajouter_off(p);
       System.out.println("qrcode0");
        ByteArrayOutputStream out=QRCode.from(description.getText()).to(ImageType.JPG).stream();
        System.out.println("qrcode1");
        File f = new File("C:/Users/hedia/Downloads/complexeSportif-master/complexeSportif-master/public/front/assets/images/test.jpg");
        System.out.println("qrcode2");
        FileOutputStream fos= new FileOutputStream(f);
        System.out.println("qrcode3");
        fos.write(out.toByteArray());
             System.out.println("qrcode4");
        fos.flush();
        image.setImage(new Image("file:C:/Users/hedia/Downloads/complexeSportif-master/complexeSportif-master/public/front/assets/images/test.jpg"));
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
