/**
 * This program uses Java I/O (input/output) to produce a record of child poverty in each state in
 * the United States plus the District of Columbia. This program has a main method and three additional
 * methods in the main class. Upon executing the program, a text record of child poverty per state is
 * created and is then output to the user. This program also has exception blocks to catch any errors
 * during program execution.
 * 
 * @author Shane Harper
 * EN.605.201.84.SP21
 * Assignment 10 - Java I/O
 */

import java.io.*;
import java.util.Scanner;

public class ChildPovertyData {
    public static void main(String[] args) {
        String inputFilePath = "/Users/shane/Desktop/mastersCompSci/java/assignments/assignment10/ghRepo/SmallAreaIncomePovertyEstData.txt";
        String outputFilePath = "/Users/shane/Desktop/mastersCompSci/java/assignments/assignment10/ghRepo/output/usChildPovertyStats.txt";
        int numberOfRecordsDataFile = 13486;
        int numberOfRecordsPovertyFile = 53;

        createChildPovertyFile(inputFilePath, outputFilePath, numberOfRecordsDataFile);
        readChildPovertyFile(outputFilePath, numberOfRecordsPovertyFile);
        
    }

    /**
     * This static method is responsible for creating the text record of child poverty in the US. It takes three parameters:
     * the source path of the original text file showing child poverty per school district, the path of the destination file,
     * and the number of records to parse.
     * 
     * @param sourcePath
     * @param destinationPath
     * @param numRecords
     * @return void
     */
    public static void createChildPovertyFile(String sourcePath, String destinationPath, int numRecords) {

        // try block
        try {

            // initialize the input and output files
            File inputFile = new File(sourcePath);
            File outputFile = new File(destinationPath);
            
            // exits the method if the file already exists
            if (outputFile.exists()) {
                System.out.println("File already exists. Program exiting.");
                System.exit(1);
            } else {
                System.out.println("Creating new file.");
            }

            // creates a BufferedReader object to parse the text file more efficiently
            BufferedReader input = new BufferedReader(new FileReader(inputFile));

            // set the record variable to null to prevent an endless loop
            String record = null;

            /* An array containing the number of each state. The reason I did it this way was because for some reason, 
            some numbers were skipped in the original text file. Therefore, allthough crude, this array compensates for it. */
            int[] stateNumArr = {1, 2, 4, 5, 6, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 44, 45, 46, 47, 48, 49, 50, 51, 53, 54, 55, 56};
            
            /* This array will build each subsequent array using the correct index values in this array in addition to the 
            later mentioned getArrIndex method */
            int[] indexArr = new int[51];

            /* These next four arrays are responsible for storing each state's population, child population, 
            child poverty population, and child poverty percentage. These will later be looped through to produce the output file.*/
            int[] statePopulationArr = new int[51];
            int[] stateChildPopulationArr = new int[51];
            int[] stateChildPovertyPopulationArr = new int[51];
            double[] stateChildPovertyPercentageArr = new double[51];

            // for loop to build the indexArr array with incrementing values
            for (int i = 0; i < indexArr.length; i++) {
                indexArr[i] = i;
            }

            // integer variable that stores the state number of the record being processed
            int stateNum = 0;

            // This will keep track of the number of records being processed to ensure that it does not exceed 13846
            int loopIndex = 0;

            // while loop to process the text record
            while ((record = input.readLine()) != null && loopIndex < numRecords) {

                // get the state number from the first column of text
                String stringStateNum = record.substring(0,2);
                stateNum = Integer.parseInt(stringStateNum.trim());

                // get the index of the stateNumArr array in order to property store it in the subsequent statistics arrays
                int arrIndex = getArrIndex(stateNumArr, stateNum);
                
                // get the figures for population, child population and child poverty population and convert them into integers
                String stringStatePopulation = record.substring(83, 90);
                String stringStateChildPopulation = record.substring(92, 99);
                String stringStateChildPovertyPopulation = record.substring(102, 108);
                
                statePopulationArr[arrIndex] += Integer.parseInt(stringStatePopulation.trim());
                stateChildPopulationArr[arrIndex] += Integer.parseInt(stringStateChildPopulation.trim());
                stateChildPovertyPopulationArr[arrIndex] += Integer.parseInt(stringStateChildPovertyPopulation.trim());

                // increment the loopIndex variable
                loopIndex++;
            }

            // separate for loop to calculate child poverty percentage
            for (int j = 0; j < stateChildPovertyPercentageArr.length; j++) {

                // initialize a double variable to hold the percentage of children living in poverty and then round to two decimal points
                double notRoundedPercentage = ((double) stateChildPovertyPopulationArr[j] / stateChildPopulationArr[j]) * 100;
                stateChildPovertyPercentageArr[j] = Math.round(notRoundedPercentage * 100.0) / 100.0;
            }

            // initialize PrintWriter object to handle outputting the data to the destination file
            PrintWriter output = new PrintWriter( new BufferedWriter( new FileWriter(outputFile, true)));

            // create the format for the destination file's header and body
            String printFormatHeader = "%-8s %-12s %-17s %-28s %-15s %n";
            String printFormatBody = "%5d %13d %18d %25d %19.2f %n";

            // Print the header and line separators for the destination file
            output.printf(printFormatHeader, "State", "Population", "Child Population", "Child Poverty Population", "% Child Poverty");
            output.printf(printFormatHeader, "-----", "----------", "----------------", "------------------------", "---------------");

            // loop through each statistics array and output it to the destination file as per the print format for the file's body.
            for (int k = 0; k < indexArr.length; k++) {
                output.printf(printFormatBody, stateNumArr[k], statePopulationArr[k], stateChildPopulationArr[k], stateChildPovertyPopulationArr[k], stateChildPovertyPercentageArr[k]);
            }

            // close the output
            output.close();

        // catch blocks to look for exceptions and output them to the user
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }
    }

    /**
     * This method is responsible for outputting the recently created child poverty data file to the user.
     * There are two parameters; the source path for the input amd the number of records to parse. 
     * 
     * @param sourcePath
     * @param numRecords
     * @return void
     */
    public static void readChildPovertyFile(String sourcePath, int numRecords) {

        // try block
        try {

            // initialize the input file
            File inputFile = new File(sourcePath);

            // if the input file does not exist, exit the program
            if (!inputFile.exists()) {
                System.out.println("File does not exist. Program exiting.");
                System.exit(1);
            } else {
                System.out.println("File: " + sourcePath);
                System.out.println();
            }

            // create a BufferedReader object to parse the inputFile more efficiently
            BufferedReader input = new BufferedReader(new FileReader(inputFile));

            // set the record variable to null to prevent an endless loop
            String record = null;

            // This will keep track of the number of records being processed to ensure that it does not exceed 53
            int index = 0;

            // while loop to output each line of the text file
            while( (record = input.readLine() ) != null && index < numRecords) {
                System.out.println(record);

                // increments the index variable
                index++;
            }
            input.close();

        // catch blocks to look for exceptions and output them to the user       
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }

    }

    /**
     * This method is responsible for finding the index of a particular element of an array.
     * I used this method for finding the index of each state because the states were not
     * numbered sequentially.
     * 
     * @param arr
     * @param element
     * @return int
     */
    public static int getArrIndex(int[] arr, int element) {
        
        // fallback variable. If the method does not find the element, it returns -1
        int returnIndex = -1;

        // for loop through the array and if it finds the element, returns the index
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) {
                returnIndex = i;
                break;
            }
        }

        return returnIndex;
    }
}