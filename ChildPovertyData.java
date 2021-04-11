import java.io.*;
import java.util.Scanner;

public class ChildPovertyData {
    public static void main(String[] args) {
        // Scanner fileInput = new Scanner(new File("/Users/shane/Desktop/mastersCompSci/java/assignments/assignment10/ghRepo/povertyDataFiveRows.txt"));
        // System.out.print("Enter a name for the file (including .txt): ");
        // String fileName = input.next();
        File inputFile = new File("/Users/shane/Desktop/mastersCompSci/java/assignments/assignment10/ghRepo/povertyDataFiveRows.txt");

        // if (!f.exists()) {
        //     System.out.println("File does not exist. Program exiting.");
        //     System.exit(1);
        // } else {
        //     System.out.println("Reading from the file.");
        // }

        try {
            // program to write to the file
            // String printFormatHeader = "%-8s %-12s %-17s %-28s %-15s %n";
            // String printFormatBody = "%5d %13d %18d %25d %19.2f %n";
    
            // PrintWriter output = new PrintWriter(new BufferedWriter (new FileWriter(f, true)));
    
            // output.printf(printFormatHeader, "State", "Population", "Child Population", "Child Poverty Population", "% Child Poverty");
            // output.printf(printFormatHeader, "-----", "----------", "----------------", "------------------------", "---------------");
            // output.printf(printFormatBody, 01, 4833722, 814377, 205023, 25.18);
    
            // output.close();

            // program to read from the file

            BufferedReader input = new BufferedReader(new FileReader(file))
            String record = null;

            int stateNum = 0;
            int statePopulation = 0;
            int stateChildPopulation = 0;
            int stateChildPovertyPopulation = 0;

            while (input.hasNext()) {
                stateNum = input.substring(0,1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }
        
    }
}