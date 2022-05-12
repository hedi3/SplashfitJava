/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.views;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shopp.entities.Order;
import shopp.entities.OrderDetails;
import shopp.entities.Product;
import shopp.services.OrderService;
import shopp.services.OrderDetailsService;
import shopp.services.ProductService;
import shopp.utils.LoginSession;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class StoreFXMLController implements Initializable {

    @FXML
    private TableView<OrderDetails> orderTableView;
    @FXML
    private TableColumn<OrderDetails, String> idProductColumn;
    @FXML
    private TableColumn<OrderDetails, String> quantityColumn;
    @FXML
    private TableColumn<OrderDetails, String> totalColumn;
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
    private TextField quantityTextField;
    @FXML
    private Button addQuabtityButton;
    @FXML
    private Label totalLabel;
    
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
       public boolean checkNotNull(String name) {
        return name.length() > 0;
    }
    

    ProductService productService = new ProductService();
    OrderDetailsService orderDetailsService = new OrderDetailsService();
    OrderService orderService = new OrderService();
    OrderDetails orderDetails;
    @FXML
    private Button orderButton;
    private TextField descriptionTextView;
    @FXML
    private TextField descriptionTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        totalLabel.setText("0");
        displayProducts();
        addQuabtityButton.setOnAction((t) -> {
            int idProduct = productTableView.getSelectionModel().getSelectedItem().getId();
            
            int quantity = 1;
            if(checkNotNull(quantityTextField.getText())){
             quantity = Integer.parseInt(quantityTextField.getText());
            }else { 
                quantity = 1;
                alert.setTitle("Invalid input");
                alert.setHeaderText(null);
                alert.setContentText("la quantite doit pas etre vide");
                alert.showAndWait();}
            
            if (quantity <=0 ){
                quantity = 1;
                alert.setTitle("Invalid input");
                alert.setHeaderText(null);
                alert.setContentText("la quantite doit etre >= 1");
                alert.showAndWait();
                
            }
            double total = quantity * productTableView.getSelectionModel().getSelectedItem().getPrix();
            orderDetails = new OrderDetails(idProduct, 0, quantity, total);
            displayOrder();
            orderTableView.getItems().add(orderDetails);
//if doesn't work just try setIems
            double totalOrder = Double.parseDouble(totalLabel.getText()) + total;
            totalLabel.setText(totalOrder + "");
            orderTableView.refresh();
            quantityTextField.setText("1");

        });
        orderButton.setOnAction((t) -> {
            Order order = new Order(LoginSession.idLoggedUser, descriptionTextField.getText(), Double.parseDouble(totalLabel.getText()));
            try {
                orderService.addOrder(order);
            } catch (Exception ex) {
                Logger.getLogger(StoreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            saveOrderDetails();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ordering");
            alert.setHeaderText(null);
            alert.setContentText("Your order has been successfully saved!");
            alert.showAndWait();
            orderTableView.getItems().clear();
            orderTableView.refresh();
            totalLabel.setText("0");
            descriptionTextField.setText("");
        });
    }

    public void displayProducts() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        subtitleColumn.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        productTableView.setItems(productService.showProduct());
    }

    public void displayOrder() {
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    public void saveOrderDetails() {
        ObservableList<OrderDetails> orderDetailsObservableList = orderTableView.getItems();
        for (int i = 0; i < orderDetailsObservableList.size(); i++) {
            orderDetailsObservableList.get(i).setIdOrder(orderService.findLastOrder());
            orderDetailsService.addOrderDetails(orderDetailsObservableList.get(i));
        }
    }

}
