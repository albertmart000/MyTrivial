import java.util.ArrayList;
import java.util.List;

public class Player {
    private String playerName;
    private int playerScore;
    private int playerCorrectAnswer;
    private ArrayList<Question> playerQuestions= new ArrayList<>();


    public Player(String name) {
        this.playerName = name;
        this.playerScore = playerScore;
        this.playerQuestions = playerQuestions;
        this.playerCorrectAnswer = playerCorrectAnswer;

    }

    public String getPlayerName() {return playerName;}

    public void setPlayerName(String playerName) {this.playerName = playerName;}

    public int getPlayerScore() {return playerScore;}

    public void setPlayerScore(int playerScore) {this.playerScore += playerScore;}

    public List<Question> getPlayerQuestions() {return playerQuestions;}


    public void setPlayerQuestion(Question playerQuestion) {
        this.playerQuestions.add(playerQuestion);
    }

    public int getPlayerCorrectAnswer() {
        int playerCorrectAnswer = 0;
        for (Question currentQuestion: playerQuestions) {
            if (currentQuestion.isCorrectAnswer()) {
                playerCorrectAnswer++;
            }
        }
        return playerCorrectAnswer;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + playerName + '\'' +
                ", playerScore=" + playerScore +
                ", playerQuestions=" + playerQuestions +
                '}';
    }
}
