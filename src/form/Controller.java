/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


/**
 *
 * @author Kishore E
 */
public class Controller implements Initializable {
 
    
    @FXML
    private TextField name;
    @FXML
    private TextField roll;
    @FXML
    private TextField dept;
    @FXML
    private TextField phone;
    @FXML
    private Button submit;
    @FXML
    private Button delete;
    
    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student, String> col1; // Roll No
    @FXML
    private TableColumn<Student, String> col2; // Name
    @FXML
    private TableColumn<Student, String> col3; // Dept
    @FXML
    private TableColumn<Student, String> col4; // Phone

    ObservableList<Student> data = FXCollections.observableArrayList();

    
    String url = "jdbc:mysql://localhost:3306/studentdb";
    String user = "root"; // replace with your MySQL username
    String password = ""; // replace with your MySQL password
//    String url = "jdbc:mysql://10.0.11.250:3306/db27";
//    String user = "user27";  
//    String password = "Dbms@123";
//    
    @FXML   
    private void handlesubmit(){
        String Name = name.getText();
        String Roll = roll.getText();
        String Dept = dept.getText();
        String Phone = phone.getText();
        
        String query="call fins( ?,?,?,? )";
    
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, Roll);
        pst.setString(2, Name);
        pst.setString(3, Dept);
        pst.setString(4, Phone);
//        pst.executeUpdate();
        
        int rowsInserted = pst.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Data inserted successfully!");
        }
        conn.close();
        
        name.clear();
        roll.clear();
        dept.clear();
        phone.clear();
        loadTable();

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    @FXML
    private void handledelete(){
        String Name = name.getText();
        String Roll = roll.getText();

        String query="call fdel(?)";
        
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = conn.prepareStatement(query);
//        pst.setString(1, Name);
        pst.setString(1, Roll);
//        pst.executeUpdate();
        
        int rowsInserted = pst.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Data deleted successfully!");
        }
        else{
            System.out.println("Data not deleted");
        }
        conn.close();
        
        name.clear();
        roll.clear();
        dept.clear();
        phone.clear();
        loadTable();

    } catch (Exception e) {
        e.printStackTrace();
    }
        
    }
    @FXML
    private void handleupdate(){
        String Name = name.getText();
        String Roll = roll.getText();
        String Dept = dept.getText();
        String Phone = phone.getText();

        String query="call fupd(?,?,?,?)";
        
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, Roll);
        pst.setString(2, Name);
        pst.setString(3, Dept);
        pst.setString(4, Phone);
//        pst.setString(4, Roll);
//        pst.executeUpdate();
        
        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Data updated successfully!");
        } else {
            System.out.println("No matching record to update.");
        }
        conn.close();
        
        name.clear();
        roll.clear();
        dept.clear();
        phone.clear();
        loadTable();

    } catch (Exception e) {
        e.printStackTrace();
    }
        
    }
        
    //tableview controller
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col1.setCellValueFactory(new PropertyValueFactory<>("roll"));
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));
        col3.setCellValueFactory(new PropertyValueFactory<>("dept"));
        col4.setCellValueFactory(new PropertyValueFactory<>("phone"));

    loadTable();
}

    public void loadTable() {
    data.clear();
    String query = "SELECT * FROM stform1";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new Student(
                rs.getString("roll"),
                rs.getString("name"),
                rs.getString("dept"),
                rs.getString("phone")
            ));
        }

        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    table.setItems(data);
}

    
    
}
