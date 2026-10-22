/**
 * Colors and styling for the terminal.
 * Uses standard ANSI color codes.
 */
public class ConsoleUI {

    // Set to false if terminal does not support colors
    public static boolean enableAnsi = true;

    // Standard ANSI colors
    public static final String RESET  = "\u001B[0m";
    public static final String GREEN  = "\u001B[32m";  // Easy mode (Green)
    public static final String YELLOW = "\u001B[33m";  // Medium mode (Yellow)
    public static final String RED    = "\u001B[31m";  // Hard mode (Red)
    public static final String CYAN   = "\u001B[36m";  // Headings (Cyan)
    public static final String WHITE  = "\u001B[37m";  // White text

    // Color helper
    public static String colorize(String color, String text) {
        if (!enableAnsi) {
            return text;
        }
        return color + text + RESET;
    }

    // Clears the screen
    public static void clearScreen() {
        if (enableAnsi) {
            System.out.print("\u001B[H\u001B[2J");
            System.out.flush();
        } else {
            for (int i = 0; i < 20; i++) {
                System.out.println();
            }
        }
    }

    // Simple pause helper
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            // Ignore interruption
        }
    }
}
