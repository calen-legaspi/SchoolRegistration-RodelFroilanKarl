package database;

import java.sql.SQLException;

public class DBTrial {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		 TestService test = new TestService();
		 try {
			 
			System.out.println(test.trial());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
