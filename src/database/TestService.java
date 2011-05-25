package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestService {

	public String trial() throws SQLException {

		String result = null;
		DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();

		String sql = "Select myValue from test where myKey = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, 1);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			result = rs.getString("myValue");
		}

		conn.close();

		return result;

	}

}
