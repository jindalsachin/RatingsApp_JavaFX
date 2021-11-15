package application;

import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Result implements Initializable{
	
	@FXML private TextField txtSearch;

	@FXML private Label lblDuration;

	@FXML private Label lbl1;

	@FXML private Label lbl2;
	
	@FXML private Label lbl3;

	@FXML private RadioButton radioBnMovie;
	
	@FXML private RadioButton radioBnTV;
	
	@FXML private Label lblRating;
	
	@FXML private Label lblTitle;
	
	@FXML private Button bnSearch;
	
	@FXML private ImageView img;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		// TODO Auto-generated method stub					
	}
	
	@FXML public void search()
	{
		String searchItem = txtSearch.getText().toString();
		try {
			Connection con = DbConnection.connect("Movie");
			PreparedStatement ps=con.prepareStatement("SELECT * FROM MoviesDB Where title like ? ");
			ps.setString(1, "%" + searchItem + "%");
			ResultSet rs = ps.executeQuery();
			
		/*	PreparedStatement psCast=con.prepareStatement("SELECT * FROM MoviesDB Where cast like ? ");
			psCast.setString(1, "%" + searchItem + "%");
			ResultSet rsCast = psCast.executeQuery(); */
			
			if (!rs.toString().isEmpty()) {
				String TVOrMovie = rs.getString(1).toString();
				System.out.println(TVOrMovie);
				if (TVOrMovie.contains("Movie")) {
					radioBnMovie.setSelected(true);
				}
				else if (TVOrMovie.contains("TV Show")){
					radioBnTV.setSelected(true);
				}
				lblTitle.setText(rs.getString(2).toString());
				String rating = rs.getString(4);
				String duration = rs.getString(5);
				lblRating.setText(rating);
				lblDuration.setText(duration);
			
				String cast = rs.getString(3);
				String[] allCast = cast.split(",");
				StringBuilder sb = new StringBuilder();
				for(String c : allCast) {
					sb.append(c+"\n");
				}
				lbl1.setText(sb.toString());
				lbl3.setText("Top Cast ");
				
				StringBuilder sbDesc = new StringBuilder();
				String desc = rs.getString(6).toString();
				sbDesc.append(desc);
				int idx = 0;
				while (idx+40 < desc.length()) {
					sbDesc.insert(idx+40, "\n");
					idx += 40;
				}
				lbl2.setText(sbDesc.toString());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
