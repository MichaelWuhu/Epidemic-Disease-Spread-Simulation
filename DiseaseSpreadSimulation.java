import java.io.File;
import java.io.FileWriter;  
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class DiseaseSpreadSimulation {
    
    public static void main(String[] args) {
        // inputs needed:
        // Number of individuals (N) => (*N NEEDS TO BE PERFECT SQUARE)
        // Number of time steps (T) => (program exits after completing steps)
        // Infection rate (α) => You should accept a value 0 ≤ 𝛼 ≤ 1. Also note that
        // the cumulative probability that and individual can get infected should not be more than 1
        // Recover rate (β) => You should accept a value 0 ≤ β ≤ 1.
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        // taking in 'number of individuals' input
        System.out.println("Enter the number of individuals (this should be a perfect square): ");
        int N = sc.nextInt(); // N = number of individuals
        while (N <=0 ||     !(Math.sqrt(N) % 1 == 0)) {
            System.out.println("Please input a perfect square: ");
            N = sc.nextInt();
        }

        // taking in 'number of time steps' input
        System.out.println("Enter the numnber of time steps: ");
        int T = sc.nextInt(); // T = number of time steps
        while(T < 0){
            System.out.println("Please enter a non-negative integer: ");
            T = sc.nextInt();
        }

        // taking in 'infection rate' input
        System.out.println("Enter the infection rate (between 0 and 1 inclusive): ");
        double α = sc.nextDouble(); // α = infection rate
        while(!(0 <= α && α <= 1)){
            System.out.println("Please enter a number between 0 and 1 (inclusive): ");
            α = sc.nextDouble();
        }

        // taking in 'recovery rate' input
        System.out.println("Enter the recovery rate (between 0 and 1 inclusive): ");
        double β = sc.nextDouble(); // β = recovery rate
        while(!(0 <= β && β <= 1)){
            System.out.println("Please enter a number between 0 and 1 (inclusive): ");
            β = sc.nextDouble();
        }


        //
        //
        //
        //
        // creating the grid (2D array)
        int size = (int) Math.sqrt(N); //Size of the square array.
        char[][] epidemicGrid = new char[size][size];//Creates char grid with 'Math.sqrt(N)' rows and 'Math.sqrt(N)' columns

        for (int i = 0; i < size; i++) { // Instantiates every element in the epidemicGrid with 'S' 
            for (int j = 0; j < size; j++) {
                epidemicGrid[i][j] = 'S'; // S stands for susceptible
            }
        }

        int randRow = rand.nextInt(size); //Chooses random number from 0 to Math.sqrt(N)
        int randCol = rand.nextInt(size); //Chooses random number from 0 to Math.sqrt(N)

        epidemicGrid[randRow][randCol] = 'I'; // instantiates the randomly chosen row and randomly chosen column with I, A.K.A. 'Patient Zero'

        
        //!!!THIS WILL BE THE INITIAL TIMESTEP (I don't know if this is necessary, so you can *delete it if you want.*)!!!
        // This essentially just checks whether Patient Zero will initially infect other people or Recover, feel free to delete if you want.
        // if (randRow == 0) {
        //     if (randCol == 0) { //Checks to see if patient zero is in upper left corner
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[0][1] = 'I';
        //         }
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[1][0] = 'I';
        //         }
        //     }
        //     else if (randCol == size-1) { // Checks to see if patient zero is in upper right corner
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[0][size-2] = 'I';
        //         }
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[1][size-1] = 'I';
        //         }
        //     }
        //     else {
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[0][randCol-1] = 'I';
        //         }
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[0][randCol+1] = 'I';
        //         }
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[1][randCol] = 'I'; 
        //         }
        //     }
        // }
        // else if (randRow == size-1) {
        //     if (randCol == 0) {
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[0][1] = 'I';
        //         }
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[1][0] = 'I';
        //         }
        //     }
        //     else if (randCol == size-1) {
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[size-1][size-2] = 'I';
        //         }
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[size-2][size-1] = 'I';
        //         }
        //     }
        //     else {
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[size-1][randCol-1] = 'I';
        //         }
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[size-1][randCol+1] = 'I';
        //         }
        //         if (rand.nextDouble(1) < α) {
        //             epidemicGrid[size-2][randCol] = 'I'; 
        //         }
        //     }
        // }
        // else if (randCol == 0) {
        //     if (rand.nextDouble(1) < α) {
        //             epidemicGrid[randRow-1][0] = 'I';
        //         }
        //     if (rand.nextDouble(1) < α) {
        //             epidemicGrid[randRow+1][0] = 'I';
        //         }
        //     if (rand.nextDouble(1) < α) {
        //             epidemicGrid[randRow][1] = 'I'; 
        //         }
        // }
        // else if (randCol == size-1) {
        //     if (rand.nextDouble(1) < α) {
        //             epidemicGrid[randRow-1][size-1] = 'I';
        //         }
        //     if (rand.nextDouble(1) < α) {
        //             epidemicGrid[randRow+1][size-1] = 'I';
        //         }
        //     if (rand.nextDouble(1) < α) {
        //             epidemicGrid[randRow][size-2] = 'I'; 
        //         }
        // }
        // else {
        //     if (rand.nextDouble(1) < α) {
        //             epidemicGrid[randRow-1][randCol] = 'I';
        //         }
        //     if (rand.nextDouble(1) < α) {
        //             epidemicGrid[randRow+1][randCol] = 'I';
        //         }
        //     if (rand.nextDouble(1) < α) {
        //             epidemicGrid[randRow][randCol-1] = 'I'; 
        //         }
        //     if (rand.nextDouble(1) < α) {
        //             epidemicGrid[randRow][randCol+1] = 'I'; 
        //         }
        // }

        if (rand.nextDouble(1) < β) {
            epidemicGrid[randRow][randCol] = 'I'; // I means infected
        }
        System.out.println("\n\n\n");

        System.out.println("Patient Zero is (" + randRow + ", " + randCol + ") Meaning row with index " + randRow + " and column with index " + randCol + ".");
        System.out.println("Here is the initial grid.");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(epidemicGrid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        for (int h = 0; h < T; h++) { //each time step 
            char[][] tempArr = new char[size][size]; //temporary array to store changes and transfer to original after time step
            for (int a = 0; a < size; a++) {
                for (int b = 0; b < size; b++) {
                    tempArr[a][b] = epidemicGrid[a][b];
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) { //nested for loop to iterate through each individual in the epidemicGrid
                    if (epidemicGrid[i][j] == 'R') {//check if the current individual is recovered.
                        continue;
                    }

                    else if (epidemicGrid[i][j] == 'I') {//check if current individual is 'I'
                        if (rand.nextDouble() < β) {
                            tempArr[i][j] = 'R'; //changes status to R for individual if both above statesments are true
                        }

                    } else { //checking status of individuals at (row-1, col), (row+1, col), (row, col-1), (row, col+1) where applicable (row/col – 1 must be >= 0 and row/col + 1 must be < numRows/Cols)​​
                        if (i - 1 >= 0 && epidemicGrid[i - 1][j] == 'I' && rand.nextDouble() < α) {
                            tempArr[i][j] = 'I';
                        } if (i + 1 < size && epidemicGrid[i + 1][j] == 'I' && rand.nextDouble() < α) {
                            tempArr[i][j] = 'I';
                        } if (j - 1 >= 0 && epidemicGrid[i][j - 1] == 'I' && rand.nextDouble() < α) {
                            tempArr[i][j] = 'I';
                        } if (j + 1 < size && epidemicGrid[i][j + 1] == 'I' && rand.nextDouble() < α) {
                            tempArr[i][j] = 'I';
                        } 
                    }
                }
            
            }
            // epidemicGrid = tempArr;

            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    epidemicGrid[i][j] = tempArr[i][j];
                }
            }

            outputGridInfo(epidemicGrid, (h+1));
            System.out.println();
        }
    }

    // outputGridInfo method will output information on the grid
    public static void outputGridInfo(char[][] grid, int timeStep) {	
    	// Tracking Numbers
    	int numInfected = 0;
    	int numRecovered = 0;
    	int numSus = 0;
    	int total = 0;
    	// Names for the file path
    	String folderName = "Epidemic";
    	String directoryPath = "C:/";
    	String fileName = "TimeStep " + timeStep +".txt";

    	// Create the folder
    	File directory = new File("C:/", folderName);
    	directory.mkdirs(); 

    	// Combine the directory path and file name to create the complete file path
    	String filePath = directoryPath + folderName +"/" + fileName;
        try {
        	// Create a FileWriter object to write to the file
          FileWriter writer = new FileWriter(filePath);

          // Write and print the current time step
          writer.write("/////Step "  + timeStep + "/////");
          System.out.println("/////Step "  + timeStep + "/////");
          writer.write(System.lineSeparator());

          // Writes the array to the file and print to console
          for (char[] row : grid) { 
            for (char c : row) {
              writer.write(c+" ");
              System.out.print(c+" ");
              // Count the numbers
              switch (c) {
                case 'I':
                  ++numInfected;
                  break;
                case 'R':
                  ++numRecovered;
                  break;
                default:
                  ++numSus;
                  break;
              }
            }
            // Add a newline after each row
            System.out.println();
            writer.write(System.lineSeparator());
          }

          total = numSus+numRecovered+numInfected;

          // Prints some values
          System.out.println("Number Infected: " + numInfected);
          System.out.println("Number Recovered: " + numRecovered);
          System.out.println("Number Susceptible: " + numSus);
          System.out.printf("Percent Infected: %,.2f%%\n", (100.0)*((double)numInfected/(double)total));
          System.out.printf("Percent Recovered: %,.2f%%\n", (100.0)*((double)numRecovered/(double)total));
          System.out.printf("Percent Susceptible: %,.2f%%\n", (100.0)*((double)numSus/(double)total));

          // Close the FileWriter to release system resources
          writer.close();
      } catch (IOException e) {
          // Handle IO exception
        	e.printStackTrace();
      }
    } 
}
