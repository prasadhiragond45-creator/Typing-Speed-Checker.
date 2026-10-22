import java.util.Scanner;

/**
 * Main class - Starting point of the program.
 * Contains the main menu and loops until user quits.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PassageManager passageManager = new PassageManager();
        TypingTest typingTest = new TypingTest(scanner, passageManager);

        showWelcomeBanner();

        boolean running = true;

        while (running) {
            showMenu();
            System.out.print("Enter your choice (1-4): ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                // Easy Mode
                typingTest.startRound(1);
                running = askPlayAgain(scanner);
            } else if (choice.equals("2")) {
                // Medium Mode
                typingTest.startRound(2);
                running = askPlayAgain(scanner);
            } else if (choice.equals("3")) {
                // Hard Mode
                typingTest.startRound(3);
                running = askPlayAgain(scanner);
            } else if (choice.equals("4")) {
                // Exit
                running = false;
            } else {
                System.out.println("Invalid choice. Please choose 1, 2, 3, or 4.\n");
            }
        }

        System.out.println("\nThank you for practicing! Goodbye.");
        scanner.close();
    }

    // Prints the welcome screen
    private static void showWelcomeBanner() {
        ConsoleUI.clearScreen();
        System.out.println(ConsoleUI.colorize(ConsoleUI.GREEN, "=========================================================="));
        System.out.println(ConsoleUI.colorize(ConsoleUI.GREEN, "               TYPING SPEED CHECKER                       "));
        System.out.println(ConsoleUI.colorize(ConsoleUI.GREEN, "               CORE JAVA CONSOLE APP                      "));
        System.out.println(ConsoleUI.colorize(ConsoleUI.CYAN,  "           [ SPEED ] [ ACCURACY ] [ PRECISION ]           "));
        System.out.println(ConsoleUI.colorize(ConsoleUI.GREEN, "=========================================================="));
        System.out.println();
    }

    // Displays the main difficulty menu
    private static void showMenu() {
        System.out.println("----------------------------------------------------------");
        System.out.println("                   SELECT DIFFICULTY                      ");
        System.out.println("----------------------------------------------------------");
        System.out.println(ConsoleUI.colorize(ConsoleUI.GREEN,  "  1. Easy Mode    (Short, simple sentences)   [Green]"));
        System.out.println(ConsoleUI.colorize(ConsoleUI.YELLOW, "  2. Medium Mode  (Punctuation and capitals)  [Yellow]"));
        System.out.println(ConsoleUI.colorize(ConsoleUI.RED,    "  3. Hard Mode    (Challenging vocabulary)    [Red]"));
        System.out.println("  4. Exit");
        System.out.println("----------------------------------------------------------");
    }

    // Asks if the user wants to take another test or quit
    private static boolean askPlayAgain(Scanner scanner) {
        System.out.print("Do you want to try again? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.startsWith("y");
    }
}
