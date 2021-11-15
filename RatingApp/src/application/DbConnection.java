package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbConnection {
	public static Connection connect(String data) {
		
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			if(data == "User") {
				con = DriverManager.getConnection("jdbc:sqlite:UserData.db");
				System.out.println("User Database connected");
			}
			else if(data == "Movie") {
				con = DriverManager.getConnection("jdbc:sqlite:MoviesData");
				System.out.println("Movie Database connected");
			}
			else if(data == "App") {
				con = DriverManager.getConnection("jdbc:sqlite:AppsData");
				System.out.println("Apps Database connected");
			}
			else if(data == "Article") {
				con = DriverManager.getConnection("jdbc:sqlite:ArticlesData");
				System.out.println("BooksDatabase connected");
			}
			else if(data == "Book") {
				con = DriverManager.getConnection("jdbc:sqlite:BooksData");
				System.out.println("BooksDatabase connected");
			}
						
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			System.out.println("Exception" + ex.getMessage());
		}
		return con;
	}
}
