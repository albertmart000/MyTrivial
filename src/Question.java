public class Question {

    private String category;
    private String statement;
    private boolean correctAnswer;
    private boolean userAnswer;
    private int punctuation;

    public Question(String category, String statement, boolean correctAnswer,
                    int punctuation) {
        this.category = category;
        this.statement = statement;
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
        this.punctuation = punctuation;
    }

    public String getCategory() {
        return category;
    }

    public String getStatement() {
        return statement;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(boolean userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getPunctuation() {
        return punctuation;
    }

    @Override
    public String toString() {
        return "Question{" +
                "category='" + category + '\'' +
                ", statement='" + statement + '\'' +
                ", correctAnswer=" + correctAnswer +
                ", userAnswer=" + userAnswer +
                ", punctuation=" + punctuation +
                '}';
    }
}
