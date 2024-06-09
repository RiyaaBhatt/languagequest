package registrationProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Driver.driver;
import Login.*;
import learningProgram.*;
import manageTeamData.manageTeam;
import registrationProgram.*;

public class Customer {
    public static int language_id, languageChoice, complexityId, complexityChoice, course_id, coursereg_id, unitId;
    public static String registrationStatus, languageName;
    static Scanner scanner = new Scanner(System.in);

    public static void mainCustomer() throws Exception {
        while (true) {
            int customerChoice = 0;
            System.out.println("\t\t\t\t\t\t\t\t\t\tWelcome "+signUp.userName+"\n");
            System.out.println(
                            "\t\t\t\t\t\t\t\t\t╔══════════════════════════════╗\n" +
                            "\t\t\t\t\t\t\t\t\t║   -->Select                  ║\n" +
                            "\t\t\t\t\t\t\t\t\t║   1) Start Learning          ║\n" +
                            "\t\t\t\t\t\t\t\t\t║   2) View Progress           ║\n" +
                            "\t\t\t\t\t\t\t\t\t║   3) View Upcoming Features  ║\n" +
                            "\t\t\t\t\t\t\t\t\t║   4) About our team          ║\n" +
                            "\t\t\t\t\t\t\t\t\t║   5) Contact us              ║\n" +
                            "\t\t\t\t\t\t\t\t\t║   6) Exit                    ║\n" +
                            "\t\t\t\t\t\t\t\t\t╚══════════════════════════════╝");
            try {
                customerChoice = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Enter an integer value");
            }
            switch (customerChoice) {
                case 1: {
                    verifyRegistration();
                    break;
                }
                case 2: {
                    trackProgress.veiwProgress();
                    break;
                }
                case 3: {
                    viewUpComingEvents();
                    break;
                }
                case 4: {
                    manageTeam.viewTeam();
                    break;
                }
                case 5: {
                    contactUs();
                    break;
                }
                case 6: {
                    System.out.println("Exiting the program.");
                    return; // Exit the loop and program
                }
                default: {
                    System.out.println("Enter a valid choice");
                }
            }
        }
    }

    private static void contactUs() throws IOException, FileNotFoundException {
        BufferedReader bw = new BufferedReader(new FileReader(
                "E://finalproject//languageQuest//languageQuest//languageQuest//Contact Us.txt"));
        String r = bw.readLine();
        while (r != null) {
            System.out.println(r);
            r = bw.readLine();
        }
    }

    private static void viewUpComingEvents() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(
                "D:\\languageQuest\\languageQuest\\upcomingFeature.txt"));
                System.out.println("Upcoming Features are-");
            
                String readL = br.readLine();
 
        while (readL != null) {
 
            System.out.println(readL);
            readL = br.readLine();
        }
    }

    public static void verifyRegistration() throws Exception {

        System.out.println("main customer");
        if (searchRegistration()) {
            System.out.println("has course account");
            getCourseRegDetails();
            mainLearning.mainLearn();
        } else {
            System.out.println("Opp! dont have a account/nCreate a new account");
            // create new course registration;
            createRegistration();
            getCourseRegDetails();
            if (complexityId == 1) {
                unitId = 1;
                mainLearning.mainLearn();
            } else {
                // start test
            }
        }
    }

    public static void getUnitId() throws Exception {
        String sql_searchCouseReg = "select * from courseRegistration";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(sql_searchCouseReg);
        ResultSet rst = pst.executeQuery();
        int unitExists = 0;
        while (rst.next()) {
            if ((coursereg_id == rst.getInt("courseregId")) && (rst.getInt("unitId") != 0)) {
                unitId = rst.getInt("unitId");
                unitExists = 1;
                break;
            }
        }
        if (unitExists == 0) {
            unitId = 1;
            updateUnitId();
        }
    }

    public static void updateUnitId() throws Exception {
        String sql_updateCouseReg = "update courseRegistration set unitId = ? where userId = ?";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(sql_updateCouseReg);
        pst.setInt(1, unitId);
        pst.setInt(2, signUp.userId);
        pst.executeUpdate();
    }

    public static void getCourseRegDetails() throws Exception {
        String sql_searchCouseReg = "select * from courseRegistration";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(sql_searchCouseReg);
        ResultSet rst = pst.executeQuery();
        System.out.println(" Hi its course verification ");
        while (rst.next()) {
            if (signUp.userId == rst.getInt("userId")) {
                System.out.println(signUp.userId);
                System.out.println("its there");
                signUp.userId = rst.getInt("userId");
                coursereg_id = rst.getInt("courseregId");
                System.out.println(coursereg_id);
                if (coursereg_id == 0) {
                    createRegistration();
                }
                course_id = rst.getInt("courseId");
                // langId needed furthure so create a method to get lang Id
                getLaguageId();
                getUnitId();
                break;
            }
        }
    }

    public static void getLaguageId() throws Exception {

        String sql_searchCouse = "select * from course";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(sql_searchCouse);
        ResultSet rst = pst.executeQuery();
        while (rst.next()) {
            if ((course_id == rst.getInt("courseId"))) {
                language_id = rst.getInt("langId");
                break;
            }
        }

    }

    public static void displayLanguage() throws Exception {
        Connection con = new Driver.driver().mainConnection();
        System.out.println("-->Select Language");
        String search_language = "select * from language";
        PreparedStatement pst = con.prepareStatement(search_language);
        ResultSet rst = pst.executeQuery();
        while (rst.next()) {
            System.out.println(" " + rst.getInt("langId") + ") " + rst.getString("langName"));
        }
    }

    public static void selectLanguage() throws Exception {
        Connection con = new Driver.driver().mainConnection();
        languageChoice = scanner.nextInt();
        String search_language = "select * from language";
        PreparedStatement pst = con.prepareStatement(search_language);
        ResultSet rst = pst.executeQuery();
        while (rst.next()) {
            if (languageChoice == rst.getInt("langId")) {
                language_id = rst.getInt("langId");
                languageName = rst.getString("langName");
                break;
            }
        }
    }

    public static void displayComplexity() throws Exception {
        Connection con = new Driver.driver().mainConnection();
        System.out.println("-->Select ");
        String search_complexity = "select * from complexity";
        PreparedStatement pst = con.prepareStatement(search_complexity);
        ResultSet rst = pst.executeQuery();
        while (rst.next()) {
            System.out.println(" " + rst.getInt("ComplexityId") + ") " + rst.getString("complexity"));
        }
    }

    public static void searchComplexity() throws Exception {
        Connection con = new Driver.driver().mainConnection();
        complexityChoice = scanner.nextInt();
        String search_complexity = "select * from complexity";
        PreparedStatement pst = con.prepareStatement(search_complexity);
        ResultSet rst = pst.executeQuery();
        while (rst.next()) {
            if (complexityChoice == rst.getInt("complexityId")) {
                complexityId = rst.getInt("complexityId");
                break;
            }
        }
    }

    public static boolean searchRegistration() throws Exception {
        Connection con = (new driver().mainConnection());
        String searchUserCourseExistance = "select * from courseRegistration";
        PreparedStatement pst = con.prepareStatement(searchUserCourseExistance);
        ResultSet rst = pst.executeQuery();
        signUp.getUserId();
        while (rst.next()) {
            if (signUp.userId == rst.getInt("userId")) {
                signUp.userId = rst.getInt("userId");
                return true;
            }
        }
        return false;
    }

    public static void createRegistration() throws Exception {
        // print and search language
        displayLanguage();
        selectLanguage();
        System.out.println("language id : " + language_id);
        // print and search complexity of language
        displayComplexity();
        searchComplexity();
        // get course Id
        getCourseDetails();
        System.out.println("Complexity id : " + complexityId);

        String sql_enrollUser = "insert into courseRegistration(userId , courseId , unitId ) values(?,?,?)";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(sql_enrollUser);
        signUp.getUserId(); // get userId if have user account and and has not selected any course
        pst.setInt(1, signUp.userId);
        pst.setInt(2, course_id);
        pst.setInt(3, 1);
        pst.executeUpdate();
    }

    public static void getCourseDetails() throws Exception {
        String sql_searchCouse = "select c.* , co.complexityId from course c join courseType co on co.courseTypeId = c.courseTypeId";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(sql_searchCouse);
        ResultSet rst = pst.executeQuery();
        while (rst.next()) {
            if ((language_id == rst.getInt("langId")) && (complexityId == rst.getInt("complexityId"))) {
                course_id = rst.getInt("courseId");
                language_id = rst.getInt("langId");
                break;
            }
        }
    }

    // public static void setCourse() throws Exception {
    // Connection con = (new driver().mainConnection());
    // String insert_courser = "insert into course(langId , complexityId ,
    // courseName) values(?,?)";
    // PreparedStatement pst = con.prepareStatement(insert_courser);
    // pst.setInt(1, signUp.userId);
    // pst.setInt(2, complexityId);
    // int i = pst.executeUpdate();
    // if (i > 0) {
    // System.out.println("Enrolled Suucessfully in " + languageName + "
    // course!!!");
    // registrationStatus = "Success";
    // } else {
    // System.out.println("Enrolled failed");
    // }
    // }

}
