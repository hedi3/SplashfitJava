/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shopp.entities.Category;
import shopp.entities.Order;
import shopp.entities.Product;
import shopp.utils.Connection;

/**
 *
 * @author Islem Oueslati
 */
public class CategoryService {

    public Connection connection = new Connection();

    public ObservableList<Category> showCategory() {
        ObservableList<Category> categoryObservableList = FXCollections.observableArrayList();
        String query = "SELECT * FROM category";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                categoryObservableList.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoryObservableList;
    }

    public ObservableList<String> showAllCategory() {
        ObservableList<String> categoryObservableList = FXCollections.observableArrayList();
        String query = "SELECT * FROM category";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categoryObservableList.add(resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoryObservableList;
    }

    public int findByName(String name) {
        int id = 0;
        String query = "SELECT * FROM category WHERE name = '" + name + "'";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public void addCategory(Category category) {
        String query = "INSERT INTO category (name) VALUES (?)";
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateCategory(int id, Category category) { 
        String query = "UPDATE category SET name=? WHERE id = ?";
               try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void deleteCategory(int id) {
        String query = "DELETE FROM category WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Delete Done");
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
