public class Question {

    private String category;
    private String statement;
    private boolean correctAnswer;
    private int punctuation;
    private boolean usersAnswer;


    public Question(String category, String statement, boolean correctAnswer, int punctuation) {
        this.statement = statement;
        this.correctAnswer = correctAnswer;
        this.punctuation = punctuation;
        this.category = category;
    }


    public String getCategory() {
        return category;
    }

    public boolean isUsersAnswer() {
        return usersAnswer;
    }

    public void setUsersAnswer(boolean usersAnswer) {
        this.usersAnswer = usersAnswer;
    }

    public int getPunctuation() {
        return punctuation;
    }

    public String getStatement() {
        return statement;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

}
