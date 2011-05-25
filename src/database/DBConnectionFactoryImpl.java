
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionFactoryImpl extends DBConnectionFactory{
    String driver,url,username,password;
    public DBConnectionFactoryImpl(String driver,String url,String username,String password){
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionFactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnectionFactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}




