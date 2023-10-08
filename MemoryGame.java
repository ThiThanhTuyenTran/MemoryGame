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

}
