/**
 * Calculates typing statistics: WPM, Accuracy, and Errors.
 * Simple math and variables that are easy to explain to mentors.
 */
public class TypingResult {

    private double wpm;
    private double accuracy;
    private int errors;
    private int correctChars;
    private int totalTyped;
    private double timeTakenSeconds;
    private boolean timeExceeded;

    public TypingResult(String original, String typed, double timeTakenSeconds) {
        this.timeTakenSeconds = timeTakenSeconds;
        this.timeExceeded = (timeTakenSeconds > 60.0);

        calculate(original, typed);
    }

    private void calculate(String original, String typed) {
        this.totalTyped = typed.length();
        int originalLength = original.length();

        // 1. Count matching characters
        int minLength = Math.min(originalLength, totalTyped);
        int correct = 0;

        for (int i = 0; i < minLength; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                correct++;
            }
        }
        this.correctChars = correct;

        // 2. Count errors (wrong characters + missing/extra characters)
        int wrongChars = minLength - correct;
        int lengthDifference = Math.abs(originalLength - totalTyped);
        this.errors = wrongChars + lengthDifference;

        // 3. Accuracy percentage = (correct / total typed) * 100
        if (totalTyped > 0) {
            this.accuracy = ((double) correctChars / totalTyped) * 100.0;
        } else {
            this.accuracy = 0.0;
        }

        // 4. Words Per Minute (WPM) = (total typed / 5) / minutes
        double minutes = timeTakenSeconds / 60.0;
        if (minutes > 0 && totalTyped > 0) {
            this.wpm = (totalTyped / 5.0) / minutes;
        } else {
            this.wpm = 0.0;
        }
    }

    public double getWpm() {
        return wpm;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getErrors() {
        return errors;
    }

    public int getCorrectChars() {
        return correctChars;
    }

    public int getTotalTyped() {
        return totalTyped;
    }

    public double getTimeTakenSeconds() {
        return timeTakenSeconds;
    }

    public boolean isTimeExceeded() {
        return timeExceeded;
    }

    // Feedback rating
    public String getRating() {
        if (timeExceeded) {
            return "Time limit exceeded (> 60 seconds)";
        }
        if (totalTyped == 0) {
            return "No text entered";
        }
        if (wpm >= 60 && accuracy >= 90) {
            return "Expert Hacker (Fast & Accurate!)";
        } else if (wpm >= 40 && accuracy >= 80) {
            return "Good Typist (Solid Speed)";
        } else if (wpm >= 20) {
            return "Average Speed (Keep practicing)";
        } else {
            return "Beginner (Focus on accuracy first)";
        }
    }
}
