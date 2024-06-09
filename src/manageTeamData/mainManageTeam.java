package manageTeamData;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class mainManageTeam {
    private static int mainTeamChoice = 0, flag = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void mainManageTeam() {
        while (flag == 0) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t╔══════════════╗");
            System.out.println("\t\t\t\t\t\t\t\t\t\t║   Select     ║");
            System.out.println("\t\t\t\t\t\t\t\t\t\t║1   Add Team  ║");
            System.out.println("\t\t\t\t\t\t\t\t\t\t║2   View Team ║");
            System.out.println("\t\t\t\t\t\t\t\t\t\t║3.  Exit      ║");
            System.out.println("\t\t\t\t\t\t\t\t\t\t╚══════════════╝");

            try {
                System.out.print("Your Response=");
                mainTeamChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Not an integer value");

                scanner.next();
                continue;
            }
            switch (mainTeamChoice) {
                case 1: {
                    try {
                        manageTeam.addTeam();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 2: {
                    try {
                        manageTeam.viewTeam();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 3: {
                    flag = 1;
                    break;
                }
                default: {
                    System.out.println("Enter a valid choice");
                }
            }
        }
    }
}
