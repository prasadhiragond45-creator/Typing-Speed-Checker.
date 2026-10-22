# ⚡ Simple Typing Speed Checker (Core Java)

A beginner-friendly, clean, and stylish console-based **Typing Speed Checker** in pure **Core Java**. Designed specifically for internship presentations and viva exams.

---

## 🚀 How to Run (Windows)

Open **Command Prompt (CMD)** or **PowerShell** inside the project folder:

```powershell
# Step 1: Compile
javac -encoding UTF-8 *.java

# Step 2: Run
java Main
```

---

## 📂 Project Structure (Simple & Clean)

The project is split into 5 simple classes, each doing one clear job:

| File | Purpose in Simple English |
| :--- | :--- |
| [Main.java](file:///c:/Users/prasa/OneDrive/Pictures/Documents/Desktop/Java%20Project/Main.java) | The starting point. Contains the main menu and loops until the user exits. |
| [TypingTest.java](file:///c:/Users/prasa/OneDrive/Pictures/Documents/Desktop/Java%20Project/TypingTest.java) | Runs the 60-second test round, counts down 3..2..1, measures time with `System.nanoTime()`, and displays the final dashboard. |
| [PassageManager.java](file:///c:/Users/prasa/OneDrive/Pictures/Documents/Desktop/Java%20Project/PassageManager.java) | Holds sentences for Easy, Medium, and Hard modes using simple String arrays. |
| [TypingResult.java](file:///c:/Users/prasa/OneDrive/Pictures/Documents/Desktop/Java%20Project/TypingResult.java) | Calculates WPM, Accuracy %, and Character Errors using basic loops and arithmetic. |
| [ConsoleUI.java](file:///c:/Users/prasa/OneDrive/Pictures/Documents/Desktop/Java%20Project/ConsoleUI.java) | Defines standard ANSI colors (Green, Yellow, Red, Cyan) and clear screen helper. |

---

## 🎯 The Three Modes (60-Second Round)

1. **Easy Mode (1)**: Short, simple sentences with common words (`Green` theme).
2. **Medium Mode (2)**: Longer sentences with commas, periods, and capitals (`Yellow` theme).
3. **Hard Mode (3)**: Complex sentences with technical words and quotes (`Red` theme).

---

## 🧮 How Calculations Work

1. **Words Per Minute (WPM)**:
   - A standard typing "word" is 5 characters (including spaces).
   - Formula:
     $$\text{WPM} = \frac{\text{Total Characters Typed} / 5.0}{\text{Time in Minutes}}$$
2. **Accuracy Percentage**:
   - Compares user input character-by-character with the original sentence.
   - Formula:
     $$\text{Accuracy} = \frac{\text{Correct Characters}}{\text{Total Characters Typed}} \times 100$$
3. **Error Count**:
   - Counts wrong characters plus any missing or extra characters typed.
4. **Time Measurement**:
   - `System.nanoTime()` is used to measure the exact time when you hit `[Enter]`.
   - If time is over 60 seconds, a warning alert is displayed.

---

## 🎓 5 Simple Viva Questions & Answers for Your Presentation

### Q1: What Core Java concepts are used in this project?
> **Answer:** We used basic Core Java concepts:
> - **Classes & Objects** (modular design separating test logic, passages, and results).
> - **Methods & Encapsulation** (private variables with public getters).
> - **Arrays** (storing sample sentences in `String[]`).
> - **Scanner** (reading user console input).
> - **Control flow** (`if-else`, `while` loops, and `for` loops).

### Q2: Why did you use `System.nanoTime()` instead of `System.currentTimeMillis()`?
> **Answer:** `System.nanoTime()` gives high-precision time measurement and is unaffected by changes to the system clock. Dividing nanoseconds by $1,000,000,000.0$ gives accurate seconds.

### Q3: Why doesn't the timer update live every second while typing?
> **Answer:** In standard Java console programming, `Scanner.nextLine()` pauses (blocks) until the user presses `[Enter]`. Without complex external C libraries, Java waits for input from the terminal. We honestly measure the exact elapsed time between start and submit, comparing it against the 60-second limit.

### Q4: How do the terminal colors work without Swing or JavaFX?
> **Answer:** We use standard **ANSI escape codes** (e.g. `\u001B[32m` for Green). Modern terminals (Windows Terminal, VS Code, PowerShell) read these codes and display colored text directly in the console.

### Q5: How do you handle empty input or division by zero?
> **Answer:** In `TypingResult.java`, we check `if (totalTyped > 0)`. If nothing was typed, accuracy and WPM are safely set to `0.0` to avoid division by zero errors.
