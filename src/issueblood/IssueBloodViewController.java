package issueblood;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import donor.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class IssueBloodViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nbg;

    @FXML
    private DatePicker ndate;

    @FXML
    private TextField nhosp;

    @FXML
    private TextField nmob;

    @FXML
    private TextField nname;

    @FXML
    private TextField npurp;

    @FXML
    private TextField nunit;
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    @FXML
    void doUpdate(ActionEvent event) 
    {
    	try
    	{
    	pst=con.prepareStatement("insert into blood_unit values(?,?,?,?,?,?,?)");
		pst.setString(1, nname.getText());
    	pst.setString(2, nmob.getText());
		pst.setString(3, nhosp.getText());
		pst.setString(4, npurp.getText());
    	pst.setString(5, nbg.getText());
		pst.setString(6, nunit.getText());
		pst.setString(7, String.valueOf( ndate.getValue()));
		pst.executeUpdate();
		showMsg("Registered");
		
    	}
    	catch(Exception e)
    	{
    		showMsg(e.toString());
    	}

    }

    @FXML
    void initialize() {
    	con=DatabaseConnection.doConnect();
       
    }

}
