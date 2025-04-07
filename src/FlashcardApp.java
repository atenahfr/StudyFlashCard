import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

/**
 * Main application window constructor.
 * Sets up UI, buttons, panels, and loads sample flashcards.
 */
public class FlashcardApp extends JFrame {
    private FlashcardPanel flashcardPanel;
    private JButton flipButton, nextButton, shuffleButton, quizButton;
    private JLabel statusLabel;

    private ArrayList<Flashcard> flashcards;
    private int currentIndex = 0;

    public FlashcardApp() {
        super("Atena's Study Helper :)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 420);
        setLayout(new BorderLayout());

        flashcardPanel = new FlashcardPanel();
        add(flashcardPanel, BorderLayout.CENTER);

        flipButton = new JButton("Flip Card");
        nextButton = new JButton("Next Card");
        shuffleButton = new JButton("Shuffle");
        quizButton = new JButton("Quiz Me");

        flipButton.addActionListener(e -> flashcardPanel.flipCard());
        nextButton.addActionListener(e -> showNextCard());
        shuffleButton.addActionListener(e -> shuffleFlashcards());
        quizButton.addActionListener(e -> quizUser());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.add(flipButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(shuffleButton);
        buttonPanel.add(quizButton);
        add(buttonPanel, BorderLayout.SOUTH);

        statusLabel = new JLabel("Card 1 of ?", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        statusLabel.setForeground(Color.WHITE);
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(20, 20, 20));
        topPanel.add(statusLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        flashcards = loadSampleFlashcards();
        if (!flashcards.isEmpty()) showCard(0);

        setFocusable(true);
        requestFocusInWindow();
        setupKeyBindings();
        setVisible(true);
    }
    /**
     * Sets up keyboard shortcuts (F, N, Q) to flip, go to next, and quiz.
     */
    private void setupKeyBindings() {
        JRootPane root = this.getRootPane();
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke('F'), "flip");
        im.put(KeyStroke.getKeyStroke('N'), "next");
        im.put(KeyStroke.getKeyStroke('Q'), "quiz");

        am.put("flip", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                flashcardPanel.flipCard();
            }
        });
        am.put("next", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                showNextCard();
            }
        });
        am.put("quiz", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                quizUser();
            }
        });
    }
    /**
     * Displays the card at the specified index.
     */
    private void showCard(int index) {
        if (index >= 0 && index < flashcards.size()) {
            flashcardPanel.setCard(flashcards.get(index));
            updateStatusLabel();
        }
    }
    /**
     * Advances to the next flashcard in the list.
     */
    private void showNextCard() {
        currentIndex = (currentIndex + 1) % flashcards.size();
        showCard(currentIndex);
    }
    /**
     * Randomly shuffles flashcards and shows the first one.
     */
    private void shuffleFlashcards() {
        Collections.shuffle(flashcards);
        currentIndex = 0;
        showCard(currentIndex);
    }
    /**
     * Updates the label that shows current card status (e.g., "Card 2 of 10").
     */
    private void updateStatusLabel() {
        statusLabel.setText("Card " + (currentIndex + 1) + " of " + flashcards.size());
    }
    /**
     * Launches a multiple-choice quiz using the current flashcard.
     * Picks 3 random wrong answers and 1 correct one.
     */
    private void quizUser() {
        if (flashcards.size() < 4) {
            JOptionPane.showMessageDialog(this, "Not enough flashcards for quiz mode (need at least 4).");
            return;
        }

        Flashcard current = flashcards.get(currentIndex);
        String correctAnswer = current.getAnswer();

        Set<String> wrongAnswers = new HashSet<>();
        Random rand = new Random();
        while (wrongAnswers.size() < 3) {
            int index = rand.nextInt(flashcards.size());
            String candidate = flashcards.get(index).getAnswer();
            if (!candidate.equals(correctAnswer)) {
                wrongAnswers.add(candidate);
            }
        }

        List<String> options = new ArrayList<>(wrongAnswers);
        options.add(correctAnswer);
        Collections.shuffle(options);

        String selected = (String) JOptionPane.showInputDialog(
            this,
            current.getQuestion(),
            "You asked for it!",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options.toArray(),
            null
        );

        if (selected == null) return;

        if (selected.equals(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "Kudos to you! ✅");
        } else {
            JOptionPane.showMessageDialog(this, "Oops! ❌\nCorrect answer: " + correctAnswer);
        }
    }
    /**
     * Loads a predefined list of sample flashcards (programming jokes).
     * Acts as demo data for the app.
     */
    private ArrayList<Flashcard> loadSampleFlashcards() {
        ArrayList<Flashcard> list = new ArrayList<>();
        list.add(new Flashcard("Why do programmers prefer dark mode?", "Because light attracts bugs!"));
        list.add(new Flashcard("How many programmers does it take to change a light bulb?", "None. That’s a hardware problem."));
        list.add(new Flashcard("Why do Java developers wear glasses?", "Because they don’t C#"));
        list.add(new Flashcard("Why did the developer go broke?", "Because he used up all his cache."));
        list.add(new Flashcard("How do you comfort a JavaScript bug?", "You console it."));
        list.add(new Flashcard("What’s a programmer’s favorite hangout place?", "The loop."));
        list.add(new Flashcard("What’s the object-oriented way to become wealthy?", "Inheritance"));
        list.add(new Flashcard("Why couldn’t the string become an integer?", "It just couldn’t parse itself."));
        list.add(new Flashcard("Why did the Java class hate being single?", "Because it couldn’t find the right instance."));
        list.add(new Flashcard("How do you know a Java developer is an introvert?", "They’d rather use private than public."));
        return list;
    }
    /**
     * Starts the Flashcard app on the Event Dispatch Thread.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FlashcardApp::new);
    }
}