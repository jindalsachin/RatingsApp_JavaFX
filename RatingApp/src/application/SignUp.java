package application;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUp implements Initializable{

	@FXML private TextField txtusername;
	
	@FXML private TextField txtpassword;
	
	@FXML private Label lblmsg;
	
	

	
	public void handlesignup(MouseEvent event)
	{
		String email = txtusername.getText().toString();
		String password = txtpassword.getText().toString();
		
		if(email.isEmpty()||password.isEmpty())
		{
			lblmsg.setText("Empty Credentials");
		}
		else
		{
			if(accountexists(email,password)==1)
				{
					lblmsg.setText("Account already exists");
				}
				else {
					insertion(email,password);
					lblmsg.setText("Account Created Succesfully");
				}
		}
		
	}
	public static int accountexists(String email,String password)
	{
		int flag=0;
		try {
			Connection con = DbConnection.connect("User");
			PreparedStatement ps=con.prepareStatement("SELECT * FROM crops Where email= ? and password= ?");
			ps.setString(1, email);
			ps.setString(2,password);
			ResultSet rs =ps.executeQuery();
			if(rs.next())
			{
				flag=1;
			}
			con.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}
	public static void insertion(String email,String password)
	{
		try {
			Connection conn=DbConnection.connect("User");
			PreparedStatement pre= conn.prepareStatement("insert into crops VALUES ('"+email+"','"+password+"')");
			pre.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	 
}
