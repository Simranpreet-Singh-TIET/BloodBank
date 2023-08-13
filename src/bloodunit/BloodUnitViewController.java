package bloodunit;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.List;
import java.util.ResourceBundle;

import donor.DatabaseConnection;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert.AlertType;

public class BloodUnitViewController 
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField bg;

    @FXML
    private ComboBox<String> mobile;

    @FXML
    private DatePicker rdate;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
 

    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    void clear()
    {
    	mobile.getEditor().setText("");
    	bg.setText("");
    	rdate.getEditor().setText("");
    	
    }
   
//    List<String> getdata()
//    {
//    	List<String> options = new ArrayList<>();
//    	
//    	try
//    	{
//    		pst=con.prepareStatement("select mobile from donors ");
//    		rs=pst.executeQuery();
//    		while(rs.next())
//    		{
//    			options.add(rs.getString("mobile"));
//    		}
//    	}
//    	catch(Exception e)
//    	{
//    		
//    		
//    	}
//    	return options;
//    }
    @FXML
    void do_clear(ActionEvent event) 
    {
    	mobile.getEditor().setText("");
    	bg.setText("");
    	rdate.getEditor().setText("");

    }
    
//    boolean search(String mob)
//    {
//    	boolean j=false;
//    	try
//    	{
//    		pst=con.prepareStatement("select * from donors where mobile = ?");
//    		pst.setString(1, mob);
//    		rs=pst.executeQuery();
//    		while(rs.next()) 
//    		{
//    			j=true;
//    			bg.setText(rs.getString("bgroup"));
//    		}
//    	}
//    	catch(Exception e)
//    	{
//    		showMsg("Not Run");
//    	}
//    	return j;
//    }
    String bgp="";
    @FXML
    void do_search(ActionEvent event) 
    {
    	try {
    		pst=con.prepareStatement("select*from donors where mobile=?");
    		pst.setString(1, mobile.getEditor().getText());
    		rs=pst.executeQuery();
    		while(rs.next()) {
    			bg.setText(rs.getString("bgroup"));
    			Date date=rs.getDate("dor");
    			bgp=rs.getString("bgroup");
    			//java.sql.Date date1=java.sql.Date.valueOf(date);
    			//txtDOD.setText(date);
    			LocalDate date1=date.toLocalDate();
    			rdate.setValue(date1);
    		}
    	}
    	catch(SQLException exp) {
    		
    	}
    }

    String chkbg( )
    {
    	String s="";
    	if(bgp=="O positive")
    	{
    		return s+"opos";
    	}
    	else if(bgp=="O negative")
    	{
    		return s+"oneg";
    	}
    	return s;
    	
    }
    
    @FXML
    void do_upload(ActionEvent event) 
    {
    	String bgt=chkbg();
    	
    	System.out.println(bgt);
    	System.out.println(bgp);
//    	if(bgp=="O(-ve)")
//    	{
//    		System.out.println(bg.getText());
//    		bgp="oneg";
//    		
//    	}
    	
//    	try {
//    		pst=con.prepareStatement("insert into donations values(?,?,?)");
//    		pst.setString(1, mobile.getEditor().getText());
//    		
//    		pst.setString(2, bg.getText());
//    		pst.setString(3, String.valueOf( rdate.getValue()));
//    		pst.executeUpdate();
//    		showMsg("Registered");
//    	}
//    	catch(SQLException exp) {
 		
//    	}
    	
    	try {
    		pst=con.prepareStatement("update total_blood_record set "+bgt+"="+bgt+"+?");
    		pst.setInt(1,1);
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
    		rs=pst.executeQuery();
    		while(rs.next()) {
    			mobileNums.add(rs.getString("mobile"));
    		}
    	}
    	catch(SQLException exp) {
    		
    	}
    }
    ArrayList<String>mobileNums=new ArrayList<String>();
    @FXML
    void initialize() 
    {
    	   con=DatabaseConnection.doConnect();
    	   doFillMob();
       	   mobile.getItems().setAll(mobileNums);
       }
    	
    }


