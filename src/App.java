import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Driver.driver;
import Login.*;
import registrationProgram.Customer;
import registrationProgram.mainMenu;

public class App {

    public static void main(String[] args) throws Exception {

        // Connection con = (new driver().mainConnection());
        // step1 login
        //  set();
        signUp signup = new signUp();
        signup.mainSign();

        if (signUp.loginStatus.equalsIgnoreCase("Success")) {
            mainMenu m = new mainMenu();
            m.mainM();
        }
        // trial();

    }

    public static void trial() throws Exception{
        String sql = "update courseregistration set unitId = 2 where userId = ?";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(sql);
        pst.setInt(1, 1);
        pst.executeUpdate();
    }
    // public static void displayLanguage() throws Exception {
    //     Connection con = new Driver.driver().mainConnection();
    //     System.out.println("-->Select Language");
    //     String search_language = "select * from french_language";
    //     PreparedStatement pst = con.prepareStatement(search_language);
    //     ResultSet rst = pst.executeQuery();
    //     while (rst.next()) {
    //         System.out.println(" " + rst.getInt("french_id") + ") " + rst.getString("french_words"));
    //     }
    // }

    // public static void set() throws Exception {
    //     Connection con = (new driver().mainConnection());

    //     String sql = "select * from sample_content ";
    //     PreparedStatement pst = con.prepareStatement(sql);
    //     String sql1 = "insert into courseContent(contentId , courseId , unitId , frenchContent , spanishContent , germanContent ,englishContent ) values(?,?,?,?,?,?,?) ";
    //     PreparedStatement pst1 = con.prepareStatement(sql1);
    //     ResultSet rs = pst.executeQuery();
    //     while (rs.next()) {

    //         System.out.println("hii");
    //         pst1.setInt(1, 2);
    //         pst1.setInt(2, 1);
    //         pst1.setInt(3, 2);
    //         pst1.setString(4, rs.getString("french"));
    //         pst1.setString(5, rs.getString("spanish"));
    //         pst1.setString(6, rs.getString("german"));
    //         pst1.setString(7, rs.getString("english"));
    //         pst1.executeUpdate();
    //     }
    // }

    // public static void set() throws Exception {
    // Connection con = (new driver().mainConnection());

    // String sql = "select english_words,french_words,german_words , spanish_words from translater ";
    // PreparedStatement pst = con.prepareStatement(sql);
    // String sql1 = "insert into translater(english_words,french_words,german_words, spanish_words) values(?,?,?,?) ";
    // PreparedStatement pst1 = con.prepareStatement(sql1);
    // ResultSet rs = pst.executeQuery();
    // while (rs.next()) {

    // System.out.println("hii");
    // pst1.setString(1, rs.getString("english_words"));
    // pst1.setString(2, rs.getString("french_words"));
    // pst1.setString(3, rs.getString("german_words"));
    // pst1.setString(4, rs.getString("spanish_words"));

    // pst1.executeUpdate();

    // }
    // }
    // public static void setUser() throws Exception {
    //     Connection con = new Driver.driver().mainConnection();
    //     String sql1 = "insert into complexity(complexity) values(?) ";
    //     PreparedStatement pst1 = con.prepareStatement(sql1);
    //     pst1.setString(1, "Have some experience!");
    //     pst1.executeUpdate();
    // }
}
