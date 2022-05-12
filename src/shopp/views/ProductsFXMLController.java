/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import shopp.entities.Product;
import shopp.services.ProductService;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class ProductsFXMLController implements Initializable {

    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, String> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    @FXML
    private TableColumn<Product, String> subtitleColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private TableColumn<Product, String> prixColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    ProductService productService = new ProductService();
    @FXML
    private Button refreshButton;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        displayProducts();
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (productTableView.getSelectionModel().getSelectedItem() != null) {
                    
                   
                    
                    System.out.println(productTableView.getSelectionModel().getSelectedItem().getId());
                    productService.deleteProduct(productTableView.getSelectionModel().getSelectedItem().getId());
                    //  productTableView.refresh();
                    displayProducts();
                }
            }
        });
        addButton.setOnAction((ActionEvent event) -> {
            navigate();
        });
        editButton.setOnAction((ActionEvent event) -> {
            productService.idProduct = productTableView.getSelectionModel().getSelectedItem().getId();
            productService.selected = true;
            navigate();
        });
        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // productTableView.refresh();
                displayProducts();
            }
        });
    }

    public void displayProducts() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("idCategory"));
        subtitleColumn.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        productTableView.setItems(productService.showProduct());
    }

    public void navigate() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("ProductFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Update Product");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void search(KeyEvent event) {
         FilteredList<Product> filteredData = new FilteredList<>(productService.showProduct());
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(product -> {
                    if (product.getSubtitle().toLowerCase().contains(newValue.toLowerCase()) || product.getName().toLowerCase().contains(newValue.toLowerCase()) || product.getPrix().toString().toLowerCase().contains(newValue.toLowerCase()) ) {
                        return true; // Filter matches first name.
                    } else {
                        return false;
                    }
                });
            });
            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<Product> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedData.comparatorProperty().bind(productTableView.comparatorProperty());
            // 5. Add sorted (and filtered) data to the table.
            productTableView.setItems(sortedData);
    }
}
