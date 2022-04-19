import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int MAX_SCORE = 15;
    private static final String GEO = "geografia";
    private static final String MUSIC = "music";
    private static final String SPORT = "sport";
    private static final String ART = "art";

    private static Player player1;
    private static Player player2;
    private static Player currentPlayer;


    public static void main(String[] args){

        List<Question> questions = buildQuestionList();
        String namePlayer1 = askUserForName();
        player1 = new Player(namePlayer1);
        String namePlayer2 = askUserForName();
        player2 = new Player(namePlayer2);


        currentPlayer = player1;
        System.out.println("Empieza el jugador " + currentPlayer.getPlayerName());


        while (questions.size() > 0 && currentPlayer.getPlayerScore() < MAX_SCORE) {

            System.out.println("Es tu turno " + currentPlayer.getPlayerName());
            String selectedCategory = askForCategory();
            Question questionByCategory = selectQuestionByCategory(questions, selectedCategory);

            if (questionByCategory != null) {
                playWithTheQuestion(questionByCategory);
            } else {
                System.out.println("No hay mas preguntas en esta categoria. Elige otra categoria");
            }
            switchPlayers();
        }

        printResult();
    }


    private static void printResult () { //Mostrar las puntuaciones de ambos jugadores

        System.out.println("Tu puntuación final es: " + player1.getPlayerScore() + " Jugador " + player1.getPlayerName());
        System.out.println("Tu puntuación final es: " + player2.getPlayerScore() + " Jugador " + player2.getPlayerName());

    }

    private static void switchPlayers () {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    private static String askUserForName () {
        System.out.println("Introdueix el teu nom.");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        return name;
    }

    private static void playWithTheQuestion (Question question){
        System.out.println(question.getStatement());
        System.out.println("Es verdadero o falso? T/F ");
        Scanner sc = new Scanner(System.in);
        String userAnswer = sc.nextLine();
        boolean userChoice = userAnswer.equalsIgnoreCase("T");

        if (userChoice == question.isCorrectAnswer()) {
            int score = currentPlayer.getPlayerScore() + question.getPunctuation();
            currentPlayer.setPlayerScore(score);
            System.out.println("Has acertado!");

        } else System.out.println("Nice try, pero no chaval ;-)");

    }

    private static Question selectQuestionByCategory(List < Question > questions, String selectedCategory){
        Question oneQuestion = null;
        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            if (currentQuestion.getCategory().equals(selectedCategory)) {
                oneQuestion = currentQuestion;
                questions.remove(i);
            }
        }
        return oneQuestion;
    }


    private static String askForCategory() {
        System.out.println(" Amb quina categoria vols jugar? ");
        System.out.println(" 1. geografía / 2. musica / 3. esport / 4. art ");
        Scanner sc = new Scanner(System.in);
        int selectedCategory = sc.nextInt();
        if (selectedCategory == 1) return GEO;
        else if (selectedCategory == 2) return MUSIC;
        else if (selectedCategory == 3) return SPORT;
        else if (selectedCategory == 4) return ART;
        return null;
    }

    private static List<Question> buildQuestionList () {
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
