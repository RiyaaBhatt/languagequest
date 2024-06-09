package registrationProgram;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Driver.driver;
import learningProgram.mainLearning;
import manageTeamData.mainManageTeam;

public class Admin {

    private static int adminChoice;
    static Scanner scanner = new Scanner(System.in);

    public static void mainAdmin() {
        while (true) {
            System.out.println(
                    "-->Select\n   1)Add Words\n   2)Add upcoming features\n   3)Manage team\n   4)Customer details\n   5)Exit");
            try {
                adminChoice = scanner.nextInt();
            } catch (Exception e) {

                scanner.nextLine();
                System.out.println("Enter a integer value");
            }
            switch (adminChoice) {
                case 1: {
                    try {
                        addWords();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                }
                case 2: {
                    try {
                        addUpcomingFeatures();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                }
                case 3: {
                    mainManageTeam.mainManageTeam();

                    break;
                }
                case 4: {
                    try {
                        veiwCustomerDetails();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                } case 5: {
                    System.out.println("Doing good work Keep Working");
                    System.exit(0);
                }
                default: {
                    System.out.println("Enter a valid choice");
                    break;
                }
            }
        }
    }

    private static void addWords() throws SQLException, Exception {
        System.out.println("Enter course Id ");
        Customer.language_id = scanner.nextInt();
        System.out.println("Enter content Id\n 1 for words\n 2 for sentence");
        int contentId = scanner.nextInt();
        System.out.println("Enter unit Id ");
        Customer.unitId = scanner.nextInt();
        System.out.println("Enter English Content");
        scanner.nextLine();
        String englishContent = scanner.nextLine();
        System.out.println("Enter French Content");
        // scanner.nextLine();
        String frenchContent = scanner.nextLine();
        System.out.println("Enter Spanish Content");
        // scanner.nextLine();
        String spanishContent = scanner.nextLine();
        System.out.println("Enter German Content");
        // scanner.nextLine();
        String germanContent = scanner.nextLine();

        String insertSql = "insert into coursecontent(contentId,courseId,unitId,englishContent,frenchContent,spanishContent,germanContent) values(?,?,?,?,?,?,?)";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(insertSql);
        pst.setInt(1, Customer.language_id);
        pst.setInt(2, contentId);
        pst.setInt(3, Customer.unitId);
        pst.setString(4, englishContent);
        pst.setString(5, frenchContent);
        pst.setString(6, spanishContent);
        pst.setString(7, germanContent);

        int i = pst.executeUpdate();
        if (i > 0) {
            System.out.println("Insertion Successfull");
        } else {
            System.out.println("Insertion Failed");
        }
    }

    private static void addUpcomingFeatures() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("upcomingFeature.txt", true));
        scanner.nextLine();
        System.out.println("Enter Details");
        String insertFeature = scanner.nextLine();
        bw.write(insertFeature);
        bw.flush();
        bw.close();
    }

    private static void veiwCustomerDetails() throws Exception {
        String printSql = "select * from userm where userType = ? ";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(printSql);
        pst.setString(1, "Customer");
        ResultSet rst = pst.executeQuery();
        while (rst.next()) {
            System.out.println("User Id : " + rst.getInt("userId") + " , User Name : " + rst.getString("userName")
                    + " , User Email : " + rst.getString("userEmail") + " , User Password : "
                    + rst.getString("userPassword") + " , Login Date : " + rst.getDate("login_date"));
        }
    }
}
