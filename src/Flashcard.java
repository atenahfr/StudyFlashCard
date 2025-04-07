public class Flashcard {
    private String question;
    private String answer;
    /**
     * Constructs a Flashcard object with a question and answer.
     * Used to store individual flashcard data.
     */
    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Q: " + question + " | A: " + answer;
    }
}