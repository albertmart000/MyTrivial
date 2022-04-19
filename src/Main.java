import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int MAX_SCORE = 15;
    private static final String GEO = "geografia";
    private static final String MUSIC = "music";
    private static final String SPORT = "sport";
    private static final String ART = "art";

    private static List<Player> players;
    private static Player currentPlayer;

    private static int playerScore = 0;
    private static int indexCurrentPlayer = 0;


    public static void main(String[] args) {
        List<Question> questions = buildQuestionsList();
        int numberOfPlayers = askForNumberOfPlayers();
        List<Player> players = buildPlayersList(numberOfPlayers);

        currentPlayer = players.get(0);

        while (continueWithGame(questions)) {

            printCurrentPlayer(currentPlayer);

            String selectedCategory = askForCategory();
            List<Question> questionsByCategory = buildQuestionsByCategoryList(questions, selectedCategory);
            List<Question> questionsByCategoryRandom = buildQuestionsByCategoryRandomList(questionsByCategory);
            Question currentQuestion = chooseQuestion(questionsByCategoryRandom);
            boolean userQuestionAnswer = askTheQuestion(currentQuestion);
            isAnswerCorrect(userQuestionAnswer, currentQuestion, questions);

            currentPlayer = nextPlayer(players);
        }
    }

        private static boolean continueWithGame (List < Question > questions) {
            return ((playerScore <= MAX_SCORE) && (questions.size() >= 0));

        }

        private static void isAnswerCorrect ( boolean userQuestionAnswer, Question
        currentQuestion, List < Question > questions) {
            if (userQuestionAnswer) {
                System.out.println("Has acertado!!!");
                currentPlayer.setPlayerScore(currentQuestion.getPunctuation());

            } else {
                System.out.println("Nice try, pero no chaval ;-)");
            }

            currentPlayer.getPlayerCorrectAnswer();
            currentPlayer.setPlayerQuestion(currentQuestion);

            showCurrentStadistics();
            questions.remove(currentQuestion);
        }

    private static void showCurrentStadistics() {
        System.out.println("Tu puntuacion es " + currentPlayer.getPlayerScore()+ " puntos." + "\n" +
                "Has acertado " + currentPlayer.getPlayerCorrectAnswer() + " pregunta(s) de " + currentPlayer.getPlayerQuestions().size() +
                " pregunta(s) formulada(s)." + "\n" + "Esto es un " +
                (( currentPlayer.getPlayerCorrectAnswer() * 100)/currentPlayer.getPlayerQuestions().size()) + " % de aciertos.");
    }

    private static boolean askTheQuestion (Question currentQuestion){
            System.out.println(currentQuestion.getStatement() + "\n" + "Esta pregunta vale " + currentQuestion.getPunctuation() +
                    " puntos " + "\n" + "Contesta True/False (T/F)");
            Scanner sc = new Scanner(System.in);
            String text = sc.nextLine();
            boolean userQuestionAnswer = text.equalsIgnoreCase("T");
            return userQuestionAnswer == currentQuestion.isCorrectAnswer();
        }

        private static Question chooseQuestion (List < Question > questionsByCategoryRandom) {
            if (questionsByCategoryRandom.size() <= 0) {
                System.out.println("No hay mas preguntas en esta categoria. Elige otra categoria");
                askForCategory();
            }
            return questionsByCategoryRandom.get(0);
        }

        private static List<Question> buildQuestionsByCategoryRandomList (List < Question > questionsByCategory) {
            Collections.shuffle(questionsByCategory);
            return questionsByCategory;
        }

        private static List<Question> buildQuestionsByCategoryList (List < Question > questions, String selectedCategory)
        {
            List<Question> questionsByCategory = new ArrayList<>();
            for (Question question : questions) {
                if (question.getCategory().equalsIgnoreCase(selectedCategory)) {
                    questionsByCategory.add(question);
                }
            }
            return questionsByCategory;
        }

        private static String askForCategory () {
            System.out.println("Amb quina categoria vols jugar?:\n - " + GEO + "\n - " +
                    MUSIC + "\n - " + SPORT + "\n - " + ART);
            Scanner sc = new Scanner(System.in);
            return sc.nextLine();
        }

        private static void printCurrentPlayer (Player currentPlayer){
            System.out.println(currentPlayer.getPlayerName() + " : es tu turno");
        }

        private static Player nextPlayer(List<Player> players) {

            indexCurrentPlayer = indexCurrentPlayer + 1;
            if (indexCurrentPlayer == players.size()) {
                indexCurrentPlayer = 0;
            }
            return currentPlayer = players.get(indexCurrentPlayer);
        }

        private static List<Player> buildPlayersList ( int numberOfPlayers){

            List<Player> players = new ArrayList<>();
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.println("Introdueix el nom del Jugador " + (i + 1));
                Scanner sc = new Scanner(System.in);
                String name = sc.nextLine();
                players.add(new Player(name));
            }
            return players;
        }

        private static int askForNumberOfPlayers () {
            System.out.println("Introdueix el numero de jugadors de la partida");
            Scanner sc = new Scanner(System.in);
            int numberOfPlayers = sc.nextInt();
            sc.nextLine();
            return numberOfPlayers;
        }

        private static List<Question> buildQuestionsList () {
            List<Question> questions = new ArrayList<>();

            questions.add(new Question(GEO, "La capital de Francia es Paris", true, 1));
            questions.add(new Question(GEO, "La capital de Italia es Roma", true, 1));
            questions.add(new Question(GEO, "La capital de Bielorrusia es Minsk", true, 5));
            questions.add(new Question(GEO, "La capital de Alemania es Londres", false, 1));
            questions.add(new Question(GEO, "La capital de Croacia es Dubrovnik", true, 3));

            questions.add(new Question(MUSIC, "Mozart nació en la ciudad de Salzburg", true, 3));
            questions.add(new Question(MUSIC, "Beethoven es un compositor barroco", false, 2));
            questions.add(new Question(MUSIC, "Bach inventó el clavicordio", false, 2));
            questions.add(new Question(MUSIC, "Elvis Presley es el Rey del Rock'nd Roll", true, 1));
            questions.add(new Question(MUSIC, "El tema Like a rolling stone es de los Rolling Stone", false, 1));

            questions.add(new Question(SPORT, "En un equipo de voleibol hay 5 jugadores", false, 2));
            questions.add(new Question(SPORT, "Brasil ha ganado 5 mundiales de fútbol", true, 1));
            questions.add(new Question(SPORT, "En un combate de boxeo olimpico hay 5 rounds", false, 3));
            questions.add(new Question(SPORT, "En baloncesto no puedes estar mas de 5 segundos dentro de la zona", true, 3));
            questions.add(new Question(SPORT, "El Tour de Francia siempre tiene 5 etapas", false, 2));

            questions.add(new Question(ART, "La Capilla Sixtina está en el Vaticano", true, 1));
            questions.add(new Question(ART, "Picasso nació en Paris", false, 3));
            questions.add(new Question(ART, "Velazquez es un pintor del S.XIX", false, 2));
            questions.add(new Question(ART, "Picasso pinto el Guernika en 1937", true, 3));
            questions.add(new Question(ART, "La Pieta es una escultura de Miguel Angel", true, 2));

            return questions;

        }

}