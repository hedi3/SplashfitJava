/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import shopp.entities.Product;
import shopp.entities.User;
import shopp.services.CategoryService;
import shopp.services.ProductService;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class ProductFXMLController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField subtitleTextField;
    @FXML
    private TextField prixTextField;
    @FXML
    private TextField imageTextField;
    @FXML
    private Button submitButton;

    String name, description, subtitle, image;
    int category;
    double prix;

    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    int idProduct = productService.idProduct;
    boolean selected = productService.selected;
    @FXML
    private ComboBox<String> categoryComboBox;
    
    public boolean checkNotNull(String name) {
        return name.length() > 0;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {

        if (selected) {
            Product product = productService.findById(idProduct);
            nameTextField.setText(product.getName());
            descriptionTextField.setText(product.getDescription());
            subtitleTextField.setText(product.getSubtitle());
            imageTextField.setText(product.getImage());
            //  categoryTextField.setText("" + product.getIdCategory());
            prixTextField.setText("" + product.getPrix());
        } //else {
            categoryComboBox.setItems(categoryService.showAllCategory());
   //     }
   
        submitButton.setOnAction((ActionEvent event) -> {
            name = nameTextField.getText();
            description = descriptionTextField.getText();
            subtitle = subtitleTextField.getText();
            image = imageTextField.getText();
            category = categoryService.findByName(categoryComboBox.getValue());
            
            if (checkNotNull(prixTextField.getText()))
            prix = Double.parseDouble(prixTextField.getText());
            else prix = 0 ;
            
            Product product = new Product(name, image, subtitle, description, prix, category);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (!selected) {  
                if (checkNotNull(name) && checkNotNull(description)&& checkNotNull(subtitle) && checkNotNull(image)
                    && checkNotNull(prixTextField.getText()) && prix >= 0)  {
                    productService.addProduct(product);
                    alert.setTitle("Product added");
                alert.setHeaderText(null);
                alert.setContentText("Your product has been successfully added!");
                } else {
                   
                alert.setTitle("Invalid input");
                alert.setHeaderText(null);
                alert.setContentText("invalid  Inputs!, aucun champ ne doit etre vide et le prix doit etre >= 0");
                alert.showAndWait();
                }
         
            } else {
                
                if (checkNotNull(name) && checkNotNull(description)&& checkNotNull(subtitle) && checkNotNull(image)
                    && checkNotNull(prixTextField.getText()) && prix >= 0)  {
                               productService.updateProduct(idProduct, product);
                productService.selected = false;
                 alert.setTitle("Product updated");
                alert.setHeaderText(null);
                alert.setContentText("Your product has been successfully updated!");
                } else {
                   
                alert.setTitle("Invalid input");
                alert.setHeaderText(null);
                alert.setContentText("invalid  Inputs!, aucun champ ne doit etre vide et le prix doit etre >= 0");
                alert.showAndWait();
                }
                
         
            }
            alert.showAndWait();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        });

    }

}
