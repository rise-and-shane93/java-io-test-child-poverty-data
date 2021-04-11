import java.io.*;
import java.util.IllegalFormatPrecisionException;
import java.util.Scanner;
// import java.util.ArrayList;

public class ChildPovertyData {
    public static void main(String[] args) {
        // Scanner fileInput = new Scanner(new File("/Users/shane/Desktop/mastersCompSci/java/assignments/assignment10/ghRepo/povertyDataFiveRows.txt"));
        // System.out.print("Enter a name for the file (including .txt): ");
        // String fileName = input.next();
        File inputFile = new File("/Users/shane/Desktop/mastersCompSci/java/assignments/assignment10/ghRepo/povertyDataTenRows.txt");

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

            BufferedReader input = new BufferedReader(new FileReader(inputFile));
            String record = null;

            int[] stateNumArr = {1, 2, 4, 5, 6, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 44, 45, 46, 47, 48, 49, 50, 51, 53, 54, 55, 56};
            int[] indexArr = new int[51];
            int[] statePopulationArr = new int[51];
            int[] stateChildPopulationArr = new int[51];
            int[] stateChildPovertyPopulationArr = new int[51];
            double[] stateChildPovertyPercentageArr = new double[51];

            // System.out.println(stateNumArr.length);
            for (int i = 0; i < indexArr.length; i++) {
                indexArr[i] = i;
            }

            int indexOfTest = getArrIndex(stateNumArr, 56);

            // System.out.println(indexOfTest);

            int stateNum = 0;
            int statePopulation = 0;
            int stateChildPopulation = 0;
            int stateChildPovertyPopulation = 0;
            double stateChildPovertyPercentage = 0;
            double percentageRounded = 0;

            // int stateIndex = 0;

            while ((record = input.readLine()) != null) {

                String stringStateNum = record.substring(0,2);
                stateNum = Integer.parseInt(stringStateNum.trim());
                int arrIndex = getArrIndex(stateNumArr, stateNum);
                

                String stringStatePopulation = record.substring(83, 90);
                String stringStateChildPopulation = record.substring(92, 99);
                String stringStateChildPovertyPopulation = record.substring(102, 108);

                // statePopulation += Integer.parseInt(stringStatePopulation.trim());
                // stateChildPopulation += Integer.parseInt(stringStateChildPopulation.trim());
                // stateChildPovertyPopulation += Integer.parseInt(stringStateChildPovertyPopulation.trim());
                
                // stateNumArr[stateNum - 1] = stateNum;
                statePopulationArr[arrIndex] += Integer.parseInt(stringStatePopulation.trim());
                stateChildPopulationArr[arrIndex] += Integer.parseInt(stringStateChildPopulation.trim());
                stateChildPovertyPopulationArr[arrIndex] += Integer.parseInt(stringStateChildPovertyPopulation.trim());
                // }

                
                // stateChildPovertyPercentage = (double) ((stateChildPovertyPopulation / stateChildPopulation) * 100);

                // stateIndex++;
                // if ((record = input.readLine()) != null) {
                //     String stringStateNumNext = record.substring(0,2);
                //     System.out.println(stringStateNumNext);
                // }
            }

            // for (int j = 0; j < stateChildPovertyPercentageArr.length; j++) {
            //     double notRoundedPercentage = ((double) stateChildPovertyPopulationArr[j] / stateChildPopulationArr[j]) * 100;
            //     stateChildPovertyPercentageArr[j] = Math.round(notRoundedPercentage * 100.0) / 100.0;
            // }

            // stateChildPovertyPercentage = ((double) stateChildPovertyPopulation / stateChildPopulation) * 100;
            // percentageRounded = Math.round(stateChildPovertyPercentage * 100.0) / 100.0;

            String printFormatHeader = "%-8s %-12s %-17s %-28s %-15s %n";
            String printFormatBody = "%5d %13d %18d %25d %19.2f %n";
    
            // PrintWriter output = new PrintWriter(new BufferedWriter (new FileWriter(f, true)));
    
            System.out.printf(printFormatHeader, "State", "Population", "Child Population", "Child Poverty Population", "% Child Poverty");
            System.out.printf(printFormatHeader, "-----", "----------", "----------------", "------------------------", "---------------");

            for (int k = 0; k < 3; k++) {
                // int stateNumLoop = stateNumArr[k];
                // System.out.println("State number is " + stateNumArr[k] + " state population is " + statePopulationArr[k] + " state's child population is " + stateChildPopulationArr[k] + " and state's child poverty population is " + stateChildPovertyPopulationArr[k]);
                // System.out.printf(printFormatBody, stateNumArr[k], statePopulationArr[k], stateChildPopulationArr[k], stateChildPopulationArr[k], stateChildPovertyPercentageArr[k]);
                System.out.printf(printFormatBody, stateNumArr[k], statePopulationArr[k], stateChildPopulationArr[k], stateChildPopulationArr[k], 10.01);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        } /*catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }*/
        
    }

    public static int getArrIndex(int[] arr, int element) {
        // int[] arr = arr;
        // int element = element;
        int returnIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) {
                returnIndex = i;
                break;
            }
        }

        return returnIndex;
    }
}