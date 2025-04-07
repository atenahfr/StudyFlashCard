import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FlashcardPanel extends JPanel implements MouseListener {
    private Flashcard currentCard;
    private boolean isFlipped = false;
    /**
     * Initializes the panel that displays flashcards visually.
     * Sets preferred size and enables mouse interaction.
     */
    public FlashcardPanel() {
        this.setPreferredSize(new Dimension(400, 250));
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
    }
    /**
     * Sets the flashcard to be displayed and resets flip state.
     * Called whenever the current card is changed.
     */
    public void setCard(Flashcard card) {
        this.currentCard = card;
        this.isFlipped = false;
        repaint();
    }
    /**
     * Flips the card to show the other side (question <-> answer).
     * Triggered on mouse click or button press.
     */
    public void flipCard() {
        if (currentCard != null) {
            isFlipped = !isFlipped;
            repaint();
        }
    }
    /**
     * Custom rendering of the flashcard.
     * Draws question or answer depending on flip state.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (currentCard == null) return;

        Graphics2D g2 = (Graphics2D) g;
        int cardWidth = 500, cardHeight = 200;
        int x = (getWidth() - cardWidth) / 2;
        int y = (getHeight() - cardHeight) / 2;

        g2.setColor(isFlipped ? new Color(30, 30, 30) : new Color(50, 50, 50));
        g2.fillRoundRect(x, y, cardWidth, cardHeight, 30, 30);
        g2.setColor(Color.LIGHT_GRAY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, cardWidth, cardHeight, 30, 30);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Monospaced", Font.BOLD, 13));
        String text = isFlipped ? currentCard.getAnswer() : currentCard.getQuestion();

        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textX = x + (cardWidth - textWidth) / 2;
        int textY = y + cardHeight / 2;
        g2.drawString(text, textX, textY);

        if (isFlipped) {
            int underlineY = textY + 5;
            g2.drawLine(textX, underlineY, textX + textWidth, underlineY);
        }
    }
    // MouseListener methods (only mouseClicked is used for flipping the card)
    @Override 
    public void mouseClicked(MouseEvent e) { 
        flipCard(); 
    }
    @Override 
    public void mousePressed(MouseEvent e) {}
    @Override 
    public void mouseReleased(MouseEvent e) {}
    @Override 
    public void mouseEntered(MouseEvent e) {}
    @Override 
    public void mouseExited(MouseEvent e) {}
}