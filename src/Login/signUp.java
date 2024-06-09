package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Driver.driver;

public class signUp {

    static Scanner sc = new Scanner(System.in);

    public static String userName , userEmail , userPasswords , dol ,loginStatus , user_type="Customer";
    public static boolean status = true;
    public static int userId ;
    public static void mainSign() throws Exception
     {
        
        //MainMenu obj = new MainMenu();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\tWELCOME TO LanguageQuest");
        System.out.println();
        int flag1 = 0;
        while (flag1 ==0) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t╔════════════════════════╗");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t║      Main Menu         ║");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t╠════════════════════════╣");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t║ 1. Login               ║");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t║ 2. Register            ║");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t║ 3. Exit                ║");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t╚════════════════════════╝");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 :
                {
                    login();
                    System.out.println(loginStatus);
                    flag1 = 1;
                    break;
                }
                case 2:
                    Register();
                    flag1 =1;
                    break;
                case 3:
                    System.exit(0);
                    break;

                default:
                    break;
            }
        }
    }

    public static void login() throws Exception
    {
        Connection con = (new Driver.driver().mainConnection());
        System.out.println("Enter you userm e-mail : ");
         userEmail = sc.next();
        System.out.println("Enter password : ");
         userPasswords = sc.next();
        String sql = "select * from userm";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        int exists = 0;
        while(rst.next())
        {
            if(userEmail.equals(rst.getString("userEmail"))&&userPasswords.equals(rst.getString("userPassword")))
            {
                userId = rst.getInt("userId");
                userName = rst.getString("userName");
                user_type = rst.getString("userType");
                exists =1;
            }
        }
        if(exists == 1)
        {
            try {
                
            System.out.println("Login Successfull!");
            getUserId();
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            }
            loginStatus = "Success";
        }
        else{
            System.out.println("Enter\n 1). to try login again\n 2 to Create new Account\n 3 To Exit app");
            int reTryChoice = sc.nextInt();
            if(reTryChoice == 1)
            {
                login();
            }
            else if(reTryChoice == 2)
            {
                Register();
            }
            else if(reTryChoice == 3)
            {
                System.exit(0);
            }
            else{
                System.out.println("Enter a valid choice");
            }
        }


    }
    public static void Register() throws Exception {

        Connection con = (new driver().mainConnection());

        System.out.println("Enter your details");

        System.out.print("Enter your full name: ");
        sc.nextLine();
        userName = sc.nextLine();
        
        do 
        {
            System.out.print("Enter your email address: ");
            userEmail = sc.nextLine();
            if (!isValidEmail(userEmail)) {
                System.out.println("Invalid email address. Please enter a valid email.");
            }
        }
         while (!isValidEmail(userEmail));
        
        System.out.print("Create a password: ");
        userPasswords = sc.nextLine();
        System.out.print("Confirm your password: ");
        Boolean passchecker = true;
        while (passchecker) {
            String confirmPassword = sc.nextLine();
            if (userPasswords.equals(confirmPassword)) {
                passchecker = false;
            } else {
                System.out.println("please enter same password as entered above");
            }
        }

        Date javaData =new Date();
        java.sql.Date time = new java.sql.Date(javaData.getTime());
        
        String sqlInsert = "insert into userm(userName , userEmail , userPassword , login_date , userType ) values(?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sqlInsert);
        pst.setString(1, userName);
        pst.setString(2, userEmail);
        pst.setString(3, userPasswords);
        pst.setDate(4, time);
        pst.setString(5, "Customer");
        pst.executeUpdate();

        System.out.println("Account added successfully");
        loginStatus = "Success";
        getUserId();
    }

    static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@+[A-Za-z](.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    static boolean isValidUsername(String username) {
        // Basic alphanumeric validation, at least 3 characters long
        String regex = "^[A-Za-z0-9]{3,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static void getUserId() throws SQLException, Exception
    {
        String sql = "select * from userm";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        while(rst.next())
        {
            if(userEmail.equals(rst.getString("userEmail")) && (userPasswords.equals(rst.getString("userPassword"))))
            {
                userId = rst.getInt("userId");
                user_type = rst.getString("userType");
                break;
            }
        }
    }
}

