package application;

import java.sql.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class SignIn implements Initializable{
	
	
	
	
	@FXML 
	private Label lblerror;

	@FXML
	private TextField txtusername;
	
	
	@FXML
	private TextField txtpassword;
	
	@FXML
	private Button btnsignin;
	
	@FXML
	private Button btnsignup;
	
		
	Connection con=null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet=null;
	
	
	@FXML public void handlesignup(MouseEvent event)
	{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
			Parent root = loader.load();
			Stage stage =new Stage();
			stage.setScene(new Scene(root));
			stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@FXML public void handleButtonAction(MouseEvent event)
	{
		if(event.getSource()==btnsignin)
		{
			if(login().equals("Success")) {
			try {	
				FXMLLoader loader = new FXMLLoader(getClass().getResource("result.fxml"));
				Parent root = loader.load();
				
				Stage stage=new Stage();
				stage.setScene(new Scene(root));
				stage.show();
				
			} catch(IOException ex) {
				System.out.println(ex.getMessage());
			}
		  }
		}
	}
	

		
		

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
	}


	
	private String login()
	{
	
		String email =txtusername.getText().toString();
	
		String password=txtpassword.getText().toString();
		
		if(email.isEmpty() || password.isEmpty())
		{
			lblerror.setTextFill(Color.TOMATO);
			lblerror.setText("Wrong credentials");
			return "Error";
		}
		else {
			//String sql="SELECT * FROM crops Where email= ? and password = ?";
			try {
				con=DbConnection.connect("User");		
				preparedStatement=con.prepareStatement("SELECT * FROM crops Where email= ? and password = ?");
				
				preparedStatement.setString(1,email);
				preparedStatement.setString(2,password);
				resultSet=preparedStatement.executeQuery();
				
				if(!resultSet.next()) {
					//setLblError(Color.TOMATO ,"Enter correct email/password");
					lblerror.setText("Wrong details");
				}
				else {
					lblerror.setText("Login successful..Redirecting");
					return "Success";
				}

			}
			catch(SQLException ex) {
				System.err.println(ex.getMessage());
				return "Exception";
			}
		}
	  
		return "Ex";
	}
}
