package bloodunit;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import donor.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class BloodCollectionViewController {

    @FXML
    private ResourceBundle resources;

    ResultSet table;
    Connection con;
    PreparedStatement pst;
    @FXML
    private URL location;
    
    @FXML
    private DatePicker datePick;
    

    @FXML
    private ComboBox<String> comboMob;

    @FXML
    private TextField txtBG;

    @FXML
    void doClear(ActionEvent event) {
    	txtBG.setText("");
    	comboMob.getEditor().setText("");
    	datePick.setValue(null);
    }
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }

    @FXML
    void doSearch(ActionEvent event) {
    	try {
    		pst=con.prepareStatement("select*from donors where mobile=?");
    		pst.setString(1, comboMob.getEditor().getText());
    		table=pst.executeQuery();
    		while(table.next()) {
    			txtBG.setText(table.getString("bgroup"));
    			Date date=table.getDate("dor");
    			//java.sql.Date date1=java.sql.Date.valueOf(date);
    			//txtDOD.setText(date);
    			LocalDate date1=date.toLocalDate();
    			datePick.setValue(date1);
    		}
    	}
    	catch(SQLException exp) {
    		
    	}
    }

    @FXML
    void doUpload(ActionEvent event) {
    	String bg=txtBG.getText();

    	try {
    		pst=con.prepareStatement("insert into donations values(?,?,?)");
    		pst.setString(1, comboMob.getEditor().getText());
    		
    		pst.setString(2, txtBG.getText());
    		pst.setString(3, String.valueOf( datePick.getValue()));
    		pst.executeUpdate();
    		showMsg("Registered");
    	}
    	catch(SQLException exp) {
    		
    	}
    	
    	try {
    		pst=con.prepareStatement("update total_blood_record set "+bg+"="+bg+"+?");
    		pst.setInt(1, 1);
    		pst.executeUpdate();
    		showMsg("Updated tabel total");
    	}
    	catch(SQLException exp) {
    		showMsg(exp.toString());
    	}
    }

    void doFillMob() {
    	try {
    		pst=con.prepareStatement("select*from donors");
    		table=pst.executeQuery();
    		while(table.next()) {
    			mobileNums.add(table.getString("mobile"));
    		}
    	}
    	catch(SQLException exp) {
    		
    	}
    }

    ArrayList<String>mobileNums=new ArrayList<String>();
    @FXML
    void initialize() {
    	con=DatabaseConnection.doConnect();
    	doFillMob();
    	comboMob.getItems().setAll(mobileNums);
    }

}
