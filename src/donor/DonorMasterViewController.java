package donor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class DonorMasterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField add;
    
    @FXML
    private ImageView imag;
    
    @FXML
    private TextField age;

    @FXML
    private ComboBox<String> bgroup;

    @FXML
    private TextField city;

    @FXML
    private TextField dis;

    @FXML
    private ComboBox<String> gen;

    @FXML
    private TextField mob;

    @FXML
    private TextField name;
    Connection con;
    PreparedStatement pst;
    File f;
    ResultSet table;
    @FXML
    void do_browse(ActionEvent event) throws FileNotFoundException  
    {
    	FileChooser file=new FileChooser();
        file.getExtensionFilters().addAll(new ExtensionFilter("JPG FILES","*.jpg"),new ExtensionFilter("PNG FILES","*.png") );
        f=file.showOpenDialog(null);
        if(f!=null)
        {
      	  try {
  			Image img=new Image(new FileInputStream(f.getAbsoluteFile()));
  			imag.setImage(img);
  			//picpath.setText(f.getAbsolutePath());
  		} catch (FileNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
      	  
        }
    }

    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    boolean chkphn(String mobile)
    {
    	boolean j=false;
    	try
    	{
    		pst=con.prepareStatement("select * from donors where mobile = ?");
    		pst.setString(1, mobile);
    		table=pst.executeQuery();
    		while(table.next()) {
    			j=true;
    		}
    	}
    	catch(Exception e)
    	{
    		
    	}
    	return j;
    }
    boolean search(String mb)
    {
    	boolean j=false;
    	try
    	{
    		pst=con.prepareStatement("select * from donors where mobile = ?");
    		pst.setString(1, mb);
    		table=pst.executeQuery();
    		while(table.next()) 
    		{
    			j=true;
    			mob.setText(table.getString("mobile"));
				name.setText(table.getString("name"));
				//gen.getSelectionModel().select(table.getString("gender"));
				gen.getEditor().setText(table.getString("gender"));
				add.setText(table.getString("address"));
				city.setText(table.getString("city"));
				bgroup.getEditor().setText(table.getString("bgroup"));
				age.setText(table.getString("age"));
				dis.setText(table.getString("disease"));
				Image img=new Image(new FileInputStream(table.getString("picpath")));
	  			imag.setImage(img);
    		}
    	}
    	catch(Exception e)
    	{
    		
    	}
    	return j;
    }

    
    @FXML
    void do_clear(ActionEvent event) {
    	mob.setText("");
    	name.setText("");
    	gen.getEditor().setText("");
    	add.setText("");
    	city.setText("");
    	bgroup.getEditor().setText("");
    	age.setText("");
    	dis.setText("");
    	imag.setImage(new Image("C:\\Users\\Aman\\Desktop\\user-profile-or-my-account-avatar-login-icon-vector-31253731.jpg"));


    }
    void clear()
    {
    	mob.setText("");
    	imag.setImage(new Image("C:\\Users\\Aman\\Desktop\\user-profile-or-my-account-avatar-login-icon-vector-31253731.jpg"));
    	name.setText("");
    	gen.getEditor().setText("");
    	add.setText("");
    	city.setText("");
    	bgroup.getEditor().setText("");
    	age.setText("");
    	dis.setText("");
    }

    @FXML
    void do_delete(ActionEvent event) {
    try
    {
    	pst=con.prepareStatement("delete from donors where mobile = ?");
    	  pst.setString(1, mob.getText());
    	  pst.executeUpdate();
    	  showMsg("DELETED");
    }
    catch(SQLException e)
    {
    	showMsg("Sorry Cant be Deleted");
    }

    }
    

    @FXML
    void do_insert(ActionEvent event) 
    {
    	String m=mob.getText();
    	if(chkphn(m)==true)
    	{
    		showMsg("Record Already Occupied");
    		return;
    	}
    	
    	try {
			pst=con.prepareStatement("insert into donors values(?,?,?,?,?,?,?,?,?,current_date())");
			pst.setString(1, mob.getText());
			pst.setString(2, f.getAbsolutePath());
			pst.setString(3, name.getText());
			pst.setString(4, gen.getEditor().getText());
			pst.setString(5, add.getText());
			pst.setString(6, city.getText());
			pst.setString(7, bgroup.getEditor().getText());
			pst.setString(8, age.getText());
			pst.setString(9, dis.getText());
			pst.executeUpdate();
			showMsg("Record Updated Succesfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			showMsg("Error In Inserting a Record");
			e.printStackTrace();
		}
    }

    @FXML
    void do_search(ActionEvent event) 
    {
    	if(search(mob.getText())==false)
    	{
    		showMsg("Donor Doesnt Exist");
    		clear();
    	}
    }

    @FXML
    void do_update(ActionEvent event) {
    	
//    	String m=mob.getText();
//    	if(chkphn(m)==true)
//    	{
//    		showMsg("Record Already Occupied");
//    		return;
//    	}
    	try {
			pst=con.prepareStatement("update donors set picpath=?,name=?,gender=?,address=?,city=?,bgroup=?,age=?,disease=?,mobile=?");
			if(this.f!=null)
			{
			pst.setString(1, f.getAbsolutePath());
			
			pst.setString(2, name.getText());
			pst.setString(3, gen.getEditor().getText());
			pst.setString(4, add.getText());
			pst.setString(5, city.getText());
			pst.setString(6, bgroup.getEditor().getText());
			pst.setString(7, age.getText());
			pst.setString(8, dis.getText());
			pst.setString(9, mob.getText());
			}
			else
			{
				pst=con.prepareStatement("update donors set name=?,gender=?,address=?,city=?,bgroup=?,age=?,disease=?,mobile=?");
				pst.setString(1, name.getText());
				pst.setString(2, gen.getEditor().getText());
				pst.setString(3, add.getText());
				pst.setString(4, city.getText());
				pst.setString(5, bgroup.getEditor().getText());
				pst.setString(6, age.getText());
				pst.setString(7, dis.getText());
				pst.setString(8, mob.getText());
			}
			int count=pst.executeUpdate();
			if (count ==0)
					showMsg("Invalid Record");
			else
			{
			showMsg("Record Updated Succesfully");
			clear();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			showMsg("Error In Inserting a Record");
			e.printStackTrace();
		}

    }

    @FXML
    void initialize() {
       con=DatabaseConnection.doConnect();
       ArrayList<String> gender=new ArrayList<String>(Arrays.asList("Male","Female","Others"));
       gen.getItems().setAll(gender);
       ArrayList<String> bg =new ArrayList<String>(Arrays.asList("O positive","O negative","A positive","A negative","B positive","B negative","AB positive","AB negative"));
       bgroup.getItems().setAll(bg);
       }

}
