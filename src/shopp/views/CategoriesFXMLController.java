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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import shopp.entities.Category;
import shopp.services.CategoryService;

/**
 * FXML Controller class
 *
 * @author Sonia
 */
public class CategoriesFXMLController implements Initializable {
    
    @FXML
    private TableColumn<Category, String> idColumn;
    @FXML
    private TableColumn<Category, String> nameColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button refreshButton;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    private TextField nameTextField;
    CategoryService categoryService = new CategoryService();
    @FXML
    private AnchorPane rootPane;
    
       public boolean checkNotNull(String name) {
        return name.length() > 0;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayCategories();
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (categoryTableView.getSelectionModel().getSelectedItem() != null) {
                    System.out.println(categoryTableView.getSelectionModel().getSelectedItem().getId());
                    categoryService.deleteCategory(categoryTableView.getSelectionModel().getSelectedItem().getId());
                    //  productTableView.refresh();
                    displayCategories();
                }
            }
        });
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
        addButton.setOnAction((t) -> {
            if (checkNotNull(nameTextField.getText())) {
                categoryService.addCategory(new Category(nameTextField.getText()));
                displayCategories();
            } else {
                  alert.setTitle("Invalid input");
                alert.setHeaderText(null);
                alert.setContentText("champ vide");
                alert.showAndWait();
            }
        });
        editButton.setOnAction((t) -> {
            if (checkNotNull(nameTextField.getText())) {
               categoryService.updateCategory(categoryTableView.getSelectionModel().getSelectedItem().getId(), new Category(nameTextField.getText()));
            displayCategories();
            } else {
                  alert.setTitle("Invalid input");
                alert.setHeaderText(null);
                alert.setContentText("champ vide");
                alert.showAndWait();
            }
            
        });
    }
    
    public void displayCategories() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryTableView.setItems(categoryService.showCategory());
    }
    
    @FXML
    private void getData(MouseEvent event) {
        nameTextField.setText(                categoryTableView.getSelectionModel().getSelectedItem().getName());
    }
    
}
