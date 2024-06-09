package learningProgram;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Stack;

import Driver.driver;
import Login.signUp;
import registrationProgram.Customer;

public class trackProgress {
    private static int currentUnit , lastunit =8;
    // private static double completedProcess;
    
    public static void veiwProgress() throws Exception
    {
        
        String sqlGetCurrentUnit = "select unitId from courseRegistration where userId = ?";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(sqlGetCurrentUnit);
        pst.setInt(1, signUp.userId);
        ResultSet rst = pst.executeQuery();
        while(rst.next())
        {
            currentUnit = rst.getInt("unitId");
        }

        System.out.println("");
        System.out.print("Last Units : "+lastunit);
    
        System.out.println("Currently on : "+currentUnit);

        double completedProcess = ((double)currentUnit/lastunit)*100.0;
        
        System.out.println("\n Progress : "+completedProcess);
    } 
    
}
