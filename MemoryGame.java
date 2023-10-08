import java.util.Random;
import java.util.Scanner;
public class MemoryGame {
    private static final int NUMBERS_TO_REMEMBER = 7;
    private static final int DISPLAY_TIME_MS = 4000;
    private static final int MAX_DISPLAY_LINES = 20;
    private static final int MIN_RANDOM_NUMBER = 1;
    private static final int MAX_RANDOM_NUMBER = 5;

    public static void main(String[] args) {
        Random random = new Random();
        try (Scanner scanner = new Scanner(System.in)) {
            int[] generatedNumbers = generateRandomNumbers(random);
            displayNumbers(generatedNumbers);
            waitForDisplay();
            clearScreen();
            int[] userNumbers = getUserInput(scanner);
            displayResults(generatedNumbers, userNumbers);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static int[] generateRandomNumbers(Random random) {
        int[] generatedNumbers = new int[NUMBERS_TO_REMEMBER];
        for (int i = 0; i < NUMBERS_TO_REMEMBER; i++) {
            generatedNumbers[i] = random.nextInt(MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER + 1) + MIN_RANDOM_NUMBER;
        }
        return generatedNumbers;
    }

    private static void displayNumbers(int[] numbers) {
        System.out.println("Try to remember the following numbers. The numbers will be shown for 4 seconds.\n");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }

    private static void waitForDisplay() throws InterruptedException {
        Thread.sleep(DISPLAY_TIME_MS);
    }

    private static void clearScreen() {
        for (int i = 0; i < MAX_DISPLAY_LINES; i++) {
            System.out.println();
        }
    }

    private static int[] getUserInput(Scanner scanner) {
        System.out.println("Type the numbers one by one:");
        int[] userNumbers = new int[NUMBERS_TO_REMEMBER];
        for (int i = 0; i < NUMBERS_TO_REMEMBER; i++) {
            System.out.print("Type number " + (i + 1) + ": ");
            userNumbers[i] = scanner.nextInt();
        }
        return userNumbers;
    }

    private static void displayResults(int[] generatedNumbers, int[] userNumbers) {
        System.out.println("\nThe numbers were:");
        for (int number : generatedNumbers) {
            System.out.print(number + " ");
        }
        System.out.println("\n\nYour numbers were:");
        for (int number : userNumbers) {
            System.out.print(number + " ");
        }
        int correctCount = countCorrectAnswers(generatedNumbers, userNumbers);
        double percentage = (correctCount / (double) NUMBERS_TO_REMEMBER) * 100;
        System.out.println("\n\nYou got " + correctCount + " out of " + NUMBERS_TO_REMEMBER + " correct answers.");
        System.out.println("Percentage of correct answers: " + percentage + "%");
    }

    private static int countCorrectAnswers(int[] generatedNumbers, int[] userNumbers) {
        int correctCount = 0;
        for (int i = 0; i < NUMBERS_TO_REMEMBER; i++) {
            if (generatedNumbers[i] == userNumbers[i]) {
                correctCount++;
            }
        }
        return correctCount;
    }
}
