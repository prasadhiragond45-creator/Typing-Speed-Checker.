import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Handles running the typing test.
 * Timer starts ONLY when the user presses their FIRST key.
 * Once typing starts, exactly 60 seconds (1 minute) are counted,
 * and the round auto-stops when time is up or when the user presses Enter!
 */
public class TypingTest {

    private Scanner scanner;
    private PassageManager passageManager;
    private double actualElapsedSeconds = 60.0;

    public TypingTest(Scanner scanner, PassageManager passageManager) {
        this.scanner = scanner;
        this.passageManager = passageManager;
    }

    // Prints long text nicely wrapped across lines
    private void printWrappedParagraph(String text, int maxWidth) {
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            if (line.length() + word.length() + 1 > maxWidth) {
                System.out.println("  " + line.toString());
                line = new StringBuilder();
            }
            if (line.length() > 0) {
                line.append(" ");
            }
            line.append(word);
        }
        if (line.length() > 0) {
            System.out.println("  " + line.toString());
        }
    }

    // Runs a round for the given difficulty: 1=Easy, 2=Medium, 3=Hard
    public void startRound(int difficulty) {
        ConsoleUI.clearScreen();

        String modeName;
        String themeColor;

        if (difficulty == 1) {
            modeName = "EASY";
            themeColor = ConsoleUI.GREEN;
        } else if (difficulty == 2) {
            modeName = "MEDIUM";
            themeColor = ConsoleUI.YELLOW;
        } else {
            modeName = "HARD";
            themeColor = ConsoleUI.RED;
        }

        // Header Banner
        System.out.println(ConsoleUI.colorize(themeColor, "=========================================================================="));
        System.out.println(ConsoleUI.colorize(themeColor, "  MODE: " + modeName + "  |  1-MINUTE ROUND (TIMER STARTS ON YOUR FIRST KEY)"));
        System.out.println(ConsoleUI.colorize(themeColor, "=========================================================================="));
        System.out.println();

        String passage = passageManager.getPassage(difficulty);

        // Target Paragraph Display
        System.out.println(ConsoleUI.colorize(ConsoleUI.CYAN, "----------------------- [ TARGET PARAGRAPH ] -----------------------------"));
        printWrappedParagraph(ConsoleUI.colorize(ConsoleUI.WHITE, passage), 72);
        System.out.println(ConsoleUI.colorize(ConsoleUI.CYAN, "--------------------------------------------------------------------------"));
        System.out.println();

        System.out.println(ConsoleUI.colorize(ConsoleUI.YELLOW, ">> The 60-second timer will START automatically when you type your first key!"));
        System.out.println(ConsoleUI.colorize(ConsoleUI.WHITE, ">> Ready when you are. Start typing below:"));
        System.out.print("> ");
        System.out.flush();

        // Read input with timer starting on first keypress
        String userInput = readInputTimerOnFirstKey();

        double elapsedSeconds = actualElapsedSeconds;
        TypingResult result = new TypingResult(passage, userInput, elapsedSeconds);

        showResults(modeName, themeColor, result, passage, userInput);
    }

    /**
     * Waits until the user types the FIRST character before starting the 60s timer.
     * Then runs for up to 60 seconds (1 minute) before automatically stopping.
     */
    private String readInputTimerOnFirstKey() {
        StringBuilder inputBuffer = new StringBuilder();
        long startNano = 0;
        boolean timerStarted = false;
        final long MAX_DURATION_NANOS = 60L * 1_000_000_000L; // 60 seconds

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                // If timer has started, check if 60 seconds have elapsed
                if (timerStarted) {
                    long elapsedNanos = System.nanoTime() - startNano;
                    if (elapsedNanos >= MAX_DURATION_NANOS) {
                        actualElapsedSeconds = 60.0;
                        System.out.println("\n\n" + ConsoleUI.colorize(ConsoleUI.RED, " >>> [TIME'S UP! EXACT 1 MINUTE COMPLETED] <<< "));
                        ConsoleUI.sleep(1200);
                        break;
                    }
                }

                // Check if user has entered any keys
                if (reader.ready()) {
                    int charCode = reader.read();

                    // User pressed Enter
                    if (charCode == -1 || charCode == '\n' || charCode == '\r') {
                        if (timerStarted) {
                            actualElapsedSeconds = (System.nanoTime() - startNano) / 1_000_000_000.0;
                        } else {
                            actualElapsedSeconds = 0.001; // Submitted empty without typing
                        }
                        break;
                    }

                    // First character typed -> START TIMER HERE!
                    if (!timerStarted) {
                        timerStarted = true;
                        startNano = System.nanoTime();
                        // Optional subtle indicator that timer kicked off
                    }

                    // Handle Backspace
                    if (charCode == 8 || charCode == 127) {
                        if (inputBuffer.length() > 0) {
                            inputBuffer.deleteCharAt(inputBuffer.length() - 1);
                        }
                    } else {
                        inputBuffer.append((char) charCode);
                    }
                } else {
                    // Small pause to prevent CPU burn while waiting
                    Thread.sleep(30);
                }
            }
        } catch (Exception e) {
            actualElapsedSeconds = 60.0;
        }

        return inputBuffer.toString();
    }

    // Prints the final results dashboard
    private void showResults(String modeName, String color, TypingResult result, String original, String typed) {
        ConsoleUI.clearScreen();

        System.out.println(ConsoleUI.colorize(color, "=========================================================================="));
        System.out.println(ConsoleUI.colorize(color, "                        TYPING TEST RESULTS                               "));
        System.out.println(ConsoleUI.colorize(color, "=========================================================================="));
        System.out.println();

        if (result.getTimeTakenSeconds() >= 60.0) {
            System.out.println(ConsoleUI.colorize(ConsoleUI.YELLOW, "[i] Round Completed: Full 1 Minute (60.00 seconds) reached!"));
        } else {
            System.out.println(ConsoleUI.colorize(ConsoleUI.GREEN, "[i] Finished early and submitted in " + String.format("%.2f", result.getTimeTakenSeconds()) + " seconds!"));
        }

        System.out.println("Difficulty Mode     : " + ConsoleUI.colorize(color, modeName));
        System.out.println("Words Per Minute    : " + ConsoleUI.colorize(ConsoleUI.GREEN, String.format("%.2f WPM", result.getWpm())));
        System.out.println("Accuracy            : " + ConsoleUI.colorize(ConsoleUI.CYAN, String.format("%.2f %%", result.getAccuracy())));
        System.out.println("Total Characters    : " + result.getTotalTyped());
        System.out.println("Correct Characters  : " + result.getCorrectChars());
        System.out.println("Error Count         : " + ConsoleUI.colorize(ConsoleUI.RED, "" + result.getErrors()));
        System.out.println("Time Taken          : " + String.format("%.2f seconds", result.getTimeTakenSeconds()));
        System.out.println("Performance Rating  : " + ConsoleUI.colorize(color, result.getRating()));
        System.out.println();

        System.out.println("Original Paragraph : " + original.substring(0, Math.min(70, original.length())) + "...");
        System.out.println("Your Typed Text    : " + (typed.isEmpty() ? "(nothing typed)" : typed));
        System.out.println(ConsoleUI.colorize(color, "=========================================================================="));
        System.out.println();
    }
}
