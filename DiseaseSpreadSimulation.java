import java.util.Scanner;

public class DiseaseSpreadSimulation {
    public static void main(String[] args) {
        // inputs needed:
        // Number of individuals (N) => (*N NEEDS TO BE PERFECT SQUARE)
        // Number of time steps (T) => (program exits after completing steps)
        // Infection rate (α) => You should accept a value 0 ≤ 𝛼 ≤ 1. Also note that
        // the cumulative probability that and individual can get infected should not be more than 1
        // Recover rate (β) => You should accept a value 0 ≤ β ≤ 1.
        Scanner sc = new Scanner(System.in);

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

        
    }
}