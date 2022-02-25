import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int MAX_SCORE = 50;
    private static final String GEO = "geografia";
    private static final String MUSIC = "music";
    private static final String SPORT = "sport";
    private static final String ART = "art";
    private static List<Question> questionsByCategory = new ArrayList<>();

    private static int userScore = 0;


    public static void main(String[] args) {
        List<Question> questions = buildQuestionsList();

        while (continueWithGame(questions)) {

            String selectedCategory = askForCategory();
            Question currentQuestion = chooseQuestion(questions, selectedCategory);
            boolean userQuestionAnswer = askTheQuestion(currentQuestion);
            //isAnswerCorrect(userQuestionAnswer, currentQuestion, questionsByCategory);
            isAnswerCorrect(userQuestionAnswer, currentQuestion, questions);
        }
    }

    private static boolean continueWithGame(List<Question> questions) {
        //return (userScore >= MAX_SCORE || questionsByCategory.size() == 0);
        return !(userScore >= MAX_SCORE || questions.size() == 0);
    }

    private static void isAnswerCorrect(boolean userQuestionAnswer, Question currentQuestion, List<Question> questionsByCategory) {
        if (userQuestionAnswer){
            System.out.println("Has acertado!!!");
            calculateUserScore(currentQuestion);
            System.out.println("Tu nueva puntuacion es " + userScore + " puntos");
        }
        else {
            System.out.println("Nice try, pero no chaval ;-)");
        }
        questionsByCategory.remove(0);
    }

    private static int calculateUserScore(Question currentQuestion) {
        userScore += currentQuestion.getPunctuation();
        return userScore;
    }

    private static boolean askTheQuestion(Question currentQuestion) {
        System.out.println(currentQuestion.getStatement() + "\n" + "Esta pregunta vale " + currentQuestion.getPunctuation() + " puntos " + "\n" +"Contesta True/False (T/F)");
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        boolean userQuestionAnswer = text.equalsIgnoreCase("T");
        return userQuestionAnswer == currentQuestion.isCorrectAnswer();
    }

    private static Question chooseQuestion(List<Question> questions, String selectedCategory) {
        List<Question> questionsByCategory = new ArrayList<>();
        for (Question question: questions) {
            if (question.getCategory().equalsIgnoreCase(selectedCategory)) {
                questionsByCategory.add(question);
                }
            }
        Question currentQuestion = questionsByCategory.get(0);
        return currentQuestion;
    }

    private static String askForCategory () {
        System.out.println("Amb quina categoria vols jugar?" + '\'' + GEO + '\'' +
                MUSIC + '\'' + SPORT + '\'' + ART);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
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
        questions.add(new Question(MUSIC, "Elvis Presjey es el Rey del Rock'nd Roll", true, 1));
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
        questions.add(new Question(ART, "La pieta es una escultura de Miguel Angel", true, 2));

        return questions;
    }

}