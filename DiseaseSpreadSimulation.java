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

        int size = (int) Math.sqrt(N); //Size of the square array.

        char[][] epidemicGrid = new char[size][size];//Creates char grid with 'Math.sqrt(N)' rows and 'Math.sqrt(N)' columns

        for (int i = 0; i < size; i++) { // Instantiates every element in the epidemicGrid with 'S' 
            for (int j = 0; j < size; j++) {
                epidemicGrid[i][j] = 'S';
            }
        }

        int randRow = rand.nextInt(size); //Chooses random number from 0 to Math.sqrt(N)
        int randCol = rand.nextInt(size); //Chooses random number from 0 to Math.sqrt(N)

        epidemicGrid[randRow][randCol] = 'I'; // instantiates the randomly chosen row and randomly chosen column with I, A.K.A. 'Patient Zero'

        /*
        for (int i = 0; i < size; i++) { // Prints out epidemic grid prior to any infections. This is only used for output testing. 
            for (int j = 0; j < size; j++) {
                System.out.print(epidemicGrid[i][j] + " ");
            }
            System.out.println();
        }
        */
        
        //!!!THIS WILL BE THE INITIAL TIMESTEP (I don't know if this is necessary, so you can *delete it if you want.*)!!!
        // This essentially just checks whether Patient Zero will initially infect other people or Recover, feel free to delete if you want.
        if (randRow == 0) {
            if (randCol == 0) { //Checks to see if patient zero is in upper left corner
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[0][1] = 'I';
                }
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[1][0] = 'I';
                }
            }
            else if (randCol == size-1) { // Checks to see if patient zero is in upper right corner
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[0][size-2] = 'I';
                }
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[1][size-1] = 'I';
                }
            }
            else {
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[0][randCol-1] = 'I';
                }
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[0][randCol+1] = 'I';
                }
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[1][randCol] = 'I'; 
                }
            }
        }
        else if (randRow == size-1) {
            if (randCol == 0) {
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[0][1] = 'I';
                }
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[1][0] = 'I';
                }
            }
            else if (randCol == size-1) {
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[size-1][size-2] = 'I';
                }
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[size-2][size-1] = 'I';
                }
            }
            else {
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[size-1][randCol-1] = 'I';
                }
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[size-1][randCol+1] = 'I';
                }
                if (rand.nextDouble(1) < α) {
                    epidemicGrid[size-2][randCol] = 'I'; 
                }
            }
        }
        else if (randCol == 0) {
            if (rand.nextDouble(1) < α) {
                    epidemicGrid[randRow-1][0] = 'I';
                }
            if (rand.nextDouble(1) < α) {
                    epidemicGrid[randRow+1][0] = 'I';
                }
            if (rand.nextDouble(1) < α) {
                    epidemicGrid[randRow][1] = 'I'; 
                }
        }
        else if (randCol == size-1) {
            if (rand.nextDouble(1) < α) {
                    epidemicGrid[randRow-1][size-1] = 'I';
                }
            if (rand.nextDouble(1) < α) {
                    epidemicGrid[randRow+1][size-1] = 'I';
                }
            if (rand.nextDouble(1) < α) {
                    epidemicGrid[randRow][size-2] = 'I'; 
                }
        }
        else {
            if (rand.nextDouble(1) < α) {
                    epidemicGrid[randRow-1][randCol] = 'I';
                }
            if (rand.nextDouble(1) < α) {
                    epidemicGrid[randRow+1][randCol] = 'I';
                }
            if (rand.nextDouble(1) < α) {
                    epidemicGrid[randRow][randCol-1] = 'I'; 
                }
            if (rand.nextDouble(1) < α) {
                    epidemicGrid[randRow][randCol+1] = 'I'; 
                }
        }

        if (rand.nextDouble(1) < β) {
            epidemicGrid[randRow][randCol] = 'R';
        }
        System.out.println("\n\n\n");

        System.out.println("Patient Zero is (" + randRow + ", " + randCol + ").");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(epidemicGrid[i][j] + " ");
            }
            System.out.println();
        }

    }
    
    public static void outputGridInfo(char[][] grid, int timeStep) {	
		// Names for the file path
		String folderName = "Epidemic";
		String directoryPath = "C:/";
        String fileName = "TimeStep "+timeStep+".txt";
        
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
                }
                // Add a newline after each row
                System.out.println();
                writer.write(System.lineSeparator());
            }

            // Close the FileWriter to release system resources
            writer.close();
        } catch (IOException e) {
            // Handle IO exception
            e.printStackTrace();
        }
    } 
}
