    /*
     * Name: Atena Hosseinifar
     * Student ID: 501267191
     * 
     /**
/******** Project Description ********
 *
 * This program is an interactive Flashcard Study Helper built with Java Swing.
 * It allows users to study flashcards by viewing a question, clicking or pressing
 * a key to flip the card and reveal the answer, and navigating to the next card.
 * The flashcards are visually displayed in a stylized panel using 2D graphics.
 * Users can interact with the flashcards via mouse clicks, buttons, or keyboard
 * shortcuts. The application loops through a set of predefined flashcards with
 * jokes and programming puns for demonstration purposes.
 *
 * The program is structured using four classes:
 * - Flashcard.java: Represents individual question-answer pairs.
 * - FlashcardPanel.java: A custom JPanel that handles rendering and flipping cards.
 * - FlashcardApp.java: Sets up the main application frame, UI controls, and logic.
 * - Project2Runner.java: Entry point that launches the FlashcardApp GUI.
 *
 * The structure follows OOP principles and separates logic, data representation,
 * and GUI rendering for better readability, scalability, and modularity.
 *
 ******** Swing Requirement ********
 *
 * The program uses at least three unique Swing components: JButton, JLabel, and JPanel.
 * - JButtons (`flipButton`, `nextButton`, `shuffleButton`, `quizButton`) are defined
 *   in FlashcardApp.java around line 33.
 * - JLabel (`statusLabel`) is initialized in FlashcardApp.java at line 46.
 * - A custom JPanel is defined in FlashcardPanel.java at line 5 to render flashcards.
 *
 * These components are arranged using a BorderLayout and are fully functional.
 * They allow the user to flip cards, navigate, shuffle, and initiate a quiz mode.
 *
 ******** 2D Graphics Requirement ********
 *
 * The 2D graphics are handled in a custom JPanel subclass called FlashcardPanel.
 * This class is defined in FlashcardPanel.java, starting at line 12.
 * The overridden `paintComponent` method, starting at line 40, uses Graphics2D
 * to draw a rounded rectangle representing the flashcard and display either the
 * question or the answer text in the center. It uses dynamic colors and font
 * rendering for a polished visual experience.
 *
 ******** Event Listener Requirement ********
 *
 * The program uses both ActionListener and MouseListener interfaces:
 * - ActionListeners are attached to buttons (`flipButton`, `nextButton`, etc.)
 *   in FlashcardApp.java starting around lines 28–36.
 *   These trigger methods like `flipCard()`, `showNextCard()`, and `quizUser()`.
 * - A MouseListener is implemented directly in FlashcardPanel.java at line 5.
 *   The `mouseClicked` method (line 73) is used to flip the card on mouse click.
 *
 * This setup provides multiple intuitive ways for the user to interact with the app
 * via keyboard, mouse, or buttons, fulfilling the interactivity requirements.
 */

    
 import javax.swing.*;

 public class Project2Runner {
    /**
     * Launches the flashcard application.
     * Acts as the project entry point (alternate main).
     */
     public static void main(String[] args) {
         SwingUtilities.invokeLater(FlashcardApp::new);
     }

}