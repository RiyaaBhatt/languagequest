package learningProgram;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Driver.driver;
import registrationProgram.Customer;
import Test.test;

public class mainLearning {

    public static String searchColumnName , searchQuestionColumn;
    static Scanner scanner = new Scanner(System.in);
    public static ArrayList<String> wordsLearnt = new ArrayList<>();

    public static void mainLearn()
    {
        System.out.println("Hello it a trial");
        System.out.println("Language : "+Customer.language_id);
        switch(Customer.language_id)
        {
            case 1 :
            {
                searchColumnName = "frenchContent";
                searchQuestionColumn = "questionFrench";
                break;
            }
            case 2 :
            {
                searchColumnName = "spanishContent";
                searchQuestionColumn = "questionSpanish";
                break;
            }
            case 3 : 
            {
                searchColumnName = "germanContent";
                searchQuestionColumn = "questionGerman";
                break;
            }
        }
        try {
            // System.out.println("hii its after language switch case");

            startCourseContent();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void startCourseContent() throws SQLException, Exception
    {
        int exists = 0;
        // int words_count =0 , sentence_count =0;
        String sql_start = "select * from courseRegistration where courseId = ?";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(sql_start);
        pst.setInt(1, Customer.course_id);
        ResultSet rs = pst.executeQuery();
        while(rs.next())
        {
            if(Customer.coursereg_id == rs.getInt("courseregId"))
            {    
                printWords();
                printSentences();
                System.out.println("ENd OF print");
                exists = 1;
                break;
            }
        }
        if(exists ==1)
        {
            //take test and if pass by marks greater then 90% then  update unit to next unit in course registration
            //else reset unit to prev or on going unit
            int testChoice = 0;
            while(true)
            {
                System.out.println("Select\n  1)Store Words learnt to your device\n  2)Start Test\n  3)Reveiw Learnt Words");
                try {
                    testChoice = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Enter a Integer value");
                }
                if(testChoice == 1 || testChoice ==2 || testChoice ==3)
                {
                    break;
                }
                System.out.println("Enter a valid choice");
            }
            if(testChoice != 0 )
            {
                switch(testChoice)
                {
                    case 1 :
                    {
                        System.out.println("Enter Path to store file");
                        scanner.nextLine();
                        String storePath = scanner.nextLine();
                        BufferedWriter bw = new BufferedWriter(new FileWriter(storePath, true));
                        bw.newLine();
                        for (String string : wordsLearnt) {
                            bw.write("  "+string);
                            bw.newLine();
                        }
                        System.out.println("Learnt Words Stored to : "+storePath);
                        bw.flush();
                        bw.close();
                        break;
                    }
                    case 2 :
                    {            
                        System.out.println("Let's start a small test!!");
                        test.mainTest();
                        System.out.println("after test");
                        break;
                    }
                    case 3 :
                    {
                        System.out.println("Words Learnt in current session unit : "+Customer.unitId);
                        for (String string : wordsLearnt) {
                            System.out.println(" "+string);
                        }
                        break;
                    }
                }
            }
            else{
                System.out.println("Visit Again");
            }
        }
        
    }
    public static void printWords() throws Exception{
        String sql_printW = "select * from courseContent where contentId = ? ";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(sql_printW);
        pst.setInt(1, 1);
        ResultSet rs = pst.executeQuery();
        System.out.println("englishContent"+" ---> "+searchColumnName);
        wordsLearnt.add(" Unit : "+Customer.unitId);
        wordsLearnt.add("-->Words : ");
        while(rs.next())
        {
            if(Customer.unitId == rs.getInt("unitId"))
            {
                System.out.println(rs.getString("englishContent")+" ---> "+rs.getString(searchColumnName));
                wordsLearnt.add("    "+rs.getString(searchColumnName));
                Thread.sleep(1000);
            }
        }
    }
    public static void printSentences() throws Exception
    {
        String sql_printS = "select * from courseContent where contentId = ? ";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(sql_printS);
        pst.setInt(1, 2);
        ResultSet rs = pst.executeQuery();
        wordsLearnt.add("-->Sentences : ");
        while(rs.next())
        {
            if(Customer.unitId == rs.getInt("unitId"))
            {
                System.out.println(rs.getString("englishContent")+" ---> "+rs.getString(searchColumnName));
                wordsLearnt.add("    "+rs.getString(searchColumnName));
                Thread.sleep(1000);
            }
        }
    }
}