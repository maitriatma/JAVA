import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int randomNumber = random.nextInt(100) + 1;
        int numberOfGuesses = 0;
        boolean isGameOver = false;

        System.out.println("Guess the number between 1 and 100!");

        while (!isGameOver) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            numberOfGuesses++;

            if (guess == randomNumber) {
                System.out.println("Congratulations! You guessed the correct number in " + numberOfGuesses + " guesses.");
                isGameOver = true;
            } else if (guess < randomNumber) {
                System.out.println("Your guess is too low. Try again.");
            } else {
                System.out.println("Your guess is too high. Try again.");
            }
        }

        scanner.close();
    }
}
