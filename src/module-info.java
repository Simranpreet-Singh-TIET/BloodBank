
module bloodbank 
{
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
//	exports reports;
//	opens reports to javafx.graphics;

	
//	requires javafx.controls;

	requires javafx.media;
	requires java.desktop;
//	opens loginview to javafx.graphics,javafx.fxml;
//	opens application to javafx.graphics,javafx.fxml;
//	opens donor to javafx.graphics,javafx.fxml;
//	opens bloodunit to javafx.graphics,javafx.fxml;
//	opens issueblood to javafx.graphics,javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
	opens donormaster to javafx.graphics, javafx.fxml;
	opens jdbcc to javafx.graphics, javafx.fxml;
	opens collection to javafx.graphics, javafx.fxml;
	opens bloodavailable to javafx.graphics, javafx.fxml;
	opens operatorlogin to javafx.graphics, javafx.fxml;
	opens controlpanel to javafx.graphics, javafx.fxml;
	opens userdatatable to javafx.graphics, javafx.fxml, javafx.base;
	opens bloodissue to javafx.graphics, javafx.fxml;
	opens history to javafx.graphics, javafx.fxml;
	opens donorhistory to javafx.graphics, javafx.fxml, javafx.base;
	opens recieverhistory to javafx.graphics, javafx.fxml, javafx.base;
	opens contactdeveloper to javafx.graphics, javafx.fxml;
}
