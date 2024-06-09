package Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class driver {
    public static Connection mainConnection() throws Exception {
        //Step 1
        String driverName = "com.mysql.cj.jdbc.Driver";
        Class.forName(driverName);
        System.out.println("Driver installed Successfull");
        //Step2 connection
        String dbusrl = "jdbc:mysql://localhost:3306/languagequest";
        String dbuser = "root";
        String dbpassword = "1410";
        Connection con = DriverManager.getConnection(dbusrl, dbuser, dbpassword);
        if(con != null)
        {
            System.out.println("Connection Success");
        }
        else
        {
            System.out.println("Not Connected");
        }
        return con;
        
    }
}
