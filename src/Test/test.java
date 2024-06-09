package Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.CallableStatement;
import Driver.driver;
import Login.signUp;
import learningProgram.mainLearning;
import registrationProgram.Customer;

public class test {
    static Scanner sc = new Scanner(System.in);

    public static double totalMarks=0.0 ,  marksScored=0.0 , percentageRecived = 0.0;
    public static String answerChoice="" , answer="" , ResultStatus = "";
    public static  Connection con ;

    public static void mainTest() throws Exception
    {   
        int qCount=1 ,questionId , answerId;
        System.out.println("Before Connection");
        con = (new driver().mainConnection());
        System.out.println("Lets start test");
        String sqlTest = "{call getTest()}";
        CallableStatement cst = con.prepareCall(sqlTest);
        ResultSet rst = cst.executeQuery();
        while(rst.next()){
            if(Customer.unitId == rst.getInt("unitId"))
            {
                System.out.println(Customer.unitId);
                System.out.println("");
                System.out.println("\n                                Question "+qCount+"\n");
                System.out.println(rst.getString(mainLearning.searchQuestionColumn));
                System.out.println("  A) "+rst.getString("answer1"));
                System.out.println("  B) "+rst.getString("answer2"));
                System.out.println("  C) "+rst.getString("answer3")); 
                qCount++;

                while(true)
                {
                    answerChoice = sc.next().toUpperCase();
                    if(answerChoice.equalsIgnoreCase("a") || answerChoice.equalsIgnoreCase("b")|| answerChoice.equalsIgnoreCase("c"))
                    {
                        break;
                    }
                    else{
                        System.out.println("Enter a valid choice");
                    }
                }
                System.out.println(answerChoice);
                switch(answerChoice)
                {
                    case "A" : 
                    {
                        answer = rst.getString("answer1");
                        break;
                    }
                    case "B" :
                    {
                        answer = rst.getString("answer2");
                        break;
                    }
                    case "C" :
                    {
                        answer = rst.getString("answer3");
                        break;
                    }
                }
                System.out.println(answer);
                
                totalMarks = totalMarks + rst.getDouble("scorePerQuestion");
                questionId = rst.getInt("questionId");
                answerId = rst.getInt("answerId");

                if(answer.equals(rst.getString("correctAnswer")))
                {
                    marksScored = marksScored + rst.getDouble("scorePerQuestion");
                }
            }
        }
        percentageRecived = (marksScored/totalMarks)*100;
            verifyScore();
    }
    public static void verifyScore()
    {
        System.out.println("This is verify score");
        System.out.println("Your Score : "+percentageRecived);
        if(percentageRecived >= 72.0)
        {
            ResultStatus = "Passed , Promoted to next unit";
            System.out.println("                       Congratulation "+signUp.userName+" you have successfully completed unit "+Customer.unitId);
            Customer.unitId = Customer.unitId+1;
            try {
                updateUnit();
                addTestData();
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            }
        }
        else{

            ResultStatus = "Failed";
            System.out.println("Practice more to unlock next unit");
            try {
                addTestData();
            } catch (Exception e) {
                // TODO: handle exception
            }
            int reapperChoice = 0;
            while(true)
            {
                System.out.println("\n Select\n  1)To reapper for test\n  2)Learn unit : "+Customer.unitId+" again \n  3)Exit");
                try {
                    reapperChoice = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Enter a Integer value");
                }
                if(reapperChoice == 1 || reapperChoice ==2 || reapperChoice ==3)
                {
                    break;
                }
                System.out.println("Enter a valid choice");
            }
            if(reapperChoice != 0)
            {
                switch(reapperChoice)
                {
                    case 1 :
                    {
                        try {
                            mainTest();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    }
                    case 2 :
                    {         try {
                            mainLearning.wordsLearnt.clear();
                            mainLearning.startCourseContent();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 3 :
                    {
                        System.exit(0);
                        break;
                    }
                }
            }
            else{
                System.out.println("Practice more to learn more");
            }

        }
    }
    public static void updateUnit() throws SQLException
    {
        String updateSql = "update courseregistration set unitId = ? ";
        PreparedStatement pst = con.prepareStatement(updateSql);
        pst.setInt(1, Customer.unitId);
        int i = pst.executeUpdate();
        if(i>0)
        {
            System.out.println("Unit Updated SuccessFully");
        }
    }
    public static void addTestData() throws SQLException, Exception
    {
        String insertTestSql = "insert into test(userId , courseregId , marksScored , totalMarks , percentageScored , ResultStatus) values(?,?,?,?,?,?)";
        PreparedStatement pst = (new driver().mainConnection()).prepareStatement(insertTestSql);
        pst.setInt(1, signUp.userId);
        pst.setInt(2, Customer.coursereg_id);
        pst.setDouble(3, marksScored);
        pst.setDouble(4, totalMarks);
        pst.setDouble(5, percentageRecived);
        pst.setString(6, ResultStatus);
    }
}
