package manageTeamData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class manageTeam {

    static Scanner scanner = new Scanner(System.in);

    public static void addTeam() throws IOException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(
                    "E://finalproject//languageQuest//languageQuest//languageQuest//LanguageQuestTeam.txt",
                    true));
            bw.write("-----------------------------------------------");
            bw.newLine();
            System.out.print("Enter Name-");
            String name = scanner.nextLine();
            bw.write(name);
            bw.newLine();
            System.out.print("Enter Role-");
            String role = scanner.nextLine();
            bw.write(" " + role);
            System.out.print("Enter work details-");
            String work = scanner.nextLine();
            bw.newLine();
            bw.write(" -" + work);
            bw.newLine(); // Add a newline after the work details
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to the file.");
        }
    }


    public static void viewTeam() throws IOException {
        BufferedReader bw = new BufferedReader(new FileReader(
                "E://finalproject//languageQuest//languageQuest//languageQuest//LanguageQuestTeam.txt"));
        String r = bw.readLine();
        while (r != null) {
            System.out.println(r);
            r = bw.readLine();
        }
    }
}
