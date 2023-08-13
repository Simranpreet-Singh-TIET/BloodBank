/**
 * Sample Skeleton for 'LoginView.fxml' Controller Class
 */

package loginview;

import java.net.URL;
import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class LoginViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="pass"
    private TextField pass; // Value injected by FXMLLoader

    @FXML // fx:id="uid"
    private TextField uid; // Value injected by FXMLLoader
    @FXML
    private Circle crcl;
//    @FXML
//    private Button loginnn;
    @FXML
    void loginn(MouseEvent event) 
    {
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
       // assert pass != null : "fx:id=\"pass\" was not injected: check your FXML file 'LoginView.fxml'.";
       // assert uid != null : "fx:id=\"uid\" was not injected: check your FXML file 'LoginView.fxml'.";
        crcl.setStroke(Color.SEAGREEN);
        Image im=new Image("C:\\Users\\simra\\OneDrive\\Desktop\\Java Proj\\295128.png");
        crcl.setFill(new ImagePattern(im));
        crcl.setEffect(new DropShadow(+25d,0d,+2d,Color.BLACK));
//        SepiaTone sepia = new SepiaTone();  
//        sepia.setLevel(0.75); 
//        loginnn.setEffect(sepia);
        
    }

}
