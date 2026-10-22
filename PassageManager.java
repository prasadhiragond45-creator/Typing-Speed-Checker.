import java.util.Random;

/**
 * Stores full-length typing paragraphs for Easy, Medium, and Hard difficulty.
 * Uses simple String arrays with realistic paragraph lengths for a true 60-second test.
 */
public class PassageManager {

    // Easy: Full paragraphs with simple vocabulary and common English words
    private String[] easyPassages = {
        "The quick brown fox jumps gracefully over the lazy sleeping dog near the quiet river bank. Learning computer programming in Java is an exciting and rewarding journey for every beginner. As you practice typing every day, your fingers naturally memorize the position of each key on the keyboard. Always remember that maintaining a steady rhythm and typing accurately is much more important than trying to rush through the text quickly.",

        "Morning sunlight brings fresh energy and positive thoughts to start a productive day of work. Writing computer code is like solving a creative puzzle where every line builds something meaningful. When you write simple and well organized programs, computer science becomes easier and much more enjoyable. Keep your eyes on the screen, breathe calmly, and let your hands type each word with confidence."
    };

    // Medium: Full paragraphs with punctuation, commas, numbers, and structured sentences
    private String[] mediumPassages = {
        "Consistent daily practice is the true secret behind mastering Java programming and building real software. Object-oriented principles, such as encapsulation, inheritance, and polymorphism, enable engineers to write clean and modular applications. Always remember: readable code is self-documenting, maintainable, and easy for other developers to review. Stay focused on your keyboard technique, maintain proper posture, and verify each sentence carefully before submitting.",

        "Modern software development relies heavily on automated test suites, clean architectures, and disciplined debugging. Every time you encounter a runtime exception or a syntax error, treat it as an opportunity to understand how the system executes under the hood. Great developers are not those who never write bugs, but those who systematically diagnose, isolate, and fix them with patience."
    };

    // Hard: Complex full paragraphs with advanced vocabulary, quotes, semicolons, and technical terms
    private String[] hardPassages = {
        "Algorithmic complexity, typically denoted by Big-O notation like O(N log N), differentiates high-throughput scalable microservices from sluggish monolithic bottlenecks. In modern distributed architectures, engineers must handle asynchronous non-blocking I/O, idempotent event streams, and fault-tolerant consensus mechanisms like Raft or Paxos; failure to account for network partitions inevitably leads to cascading service degradation!",

        "\"Any fool can write code that a computer can understand; good programmers write code that humans can understand.\" Robust enterprise systems demand strict encapsulation, polymorphic dispatch, thread-safe memory models, and resilient exception handling frameworks. Balancing low latency performance with high transactional consistency requires continuous profiling, heap optimization, and rigorous integration benchmarking."
    };

    private Random random = new Random();

    // Returns a random full paragraph based on chosen difficulty (1=Easy, 2=Medium, 3=Hard)
    public String getPassage(int difficulty) {
        if (difficulty == 1) {
            int index = random.nextInt(easyPassages.length);
            return easyPassages[index];
        } else if (difficulty == 2) {
            int index = random.nextInt(mediumPassages.length);
            return mediumPassages[index];
        } else {
            int index = random.nextInt(hardPassages.length);
            return hardPassages[index];
        }
    }
}
