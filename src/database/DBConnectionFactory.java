
package database;

import java.sql.Connection;


public abstract class DBConnectionFactory {

    public static DBConnectionFactoryImpl getInstance(){
        String driver,url,username,password;
        driver= "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/schoolregistration";
        username = "root";
        password = "";
        return new DBConnectionFactoryImpl(driver,url,username,password);
    }

    public abstract Connection getConnection();
}







