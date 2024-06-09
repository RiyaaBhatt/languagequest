package registrationProgram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import Login.signUp;
    
public class mainMenu {
    // public static String user_type="Customer" ;
    
    public static void mainM() throws Exception {
        switch(signUp.user_type)
        {
            case "admin" :
            {
                Admin admin = new Admin();
                admin.mainAdmin();
                break;
            }
            case "Customer" :
            {
                Customer customer = new Customer();
                customer.mainCustomer();
                break;
            }
        }
    }    
}
