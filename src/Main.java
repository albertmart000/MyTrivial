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

    private static int userScore = 0;
    private static int userNumberOFQuestions = 0;
    private static int userCorrectAnswer = 0;

    public static void main(String[] args) {

        List<Question> questions = buildQuestionsList();

        while (continueWithGame(questions)) {

            String selectedCategory = askForCategory();
            List<Question> questionsByCategory = buildQuestionsByCategoryList(questions, selectedCategory);
            List<Question> questionsByCategoryRandom = buildQuestionsByCategoryRandomList(questionsByCategory);
            Question currentQuestion = chooseQuestion(questionsByCategoryRandom);
            boolean userQuestionAnswer = askTheQuestion(currentQuestion);
            isAnswerCorrect(userQuestionAnswer, currentQuestion, questions);
        }
    }

    private static void isAnswerCorrect(boolean userQuestionAnswer, Question currentQuestion, List<Question> questions) {
        if (userQuestionAnswer){
            System.out.println("Has acertado!!!");
            calculateUserScore(currentQuestion);
            calculateUserCorrectAnswer();
        }

        else {
            System.out.println("Nice try, pero no chaval ;-)");
        }

        calculateUserNumberOFQuestions();
        showCurrentStadistics();
        questions.remove(currentQuestion);
    }

    private static void showCurrentStadistics() {
        System.out.println("Tu puntuacion es " + userScore + " puntos." + "\n" +
                "Has acertado " + userCorrectAnswer + " pregunta(s) de " + userNumberOFQuestions +
                " pregunta(s) formulada(s)." + "\n" + "Esto es un " +
                ((userCorrectAnswer * 100)/userNumberOFQuestions) + " % de aciertos.");
    }

    private static void calculateUserNumberOFQuestions() {
        userNumberOFQuestions += 1;
    }

    private static void calculateUserCorrectAnswer() {
        userCorrectAnswer += 1;
    }

    private static void calculateUserScore(Question currentQuestion) {
        userScore += currentQuestion.getPunctuation();
    }

    private static boolean askTheQuestion(Question currentQuestion) {
        System.out.println(currentQuestion.getStatement() + "\n" + "Esta pregunta vale " + currentQuestion.getPunctuation() +
                " puntos " + "\n" +"Contesta True/False (T/F)");
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        boolean userQuestionAnswer = text.equalsIgnoreCase("T");
        return userQuestionAnswer == currentQuestion.isCorrectAnswer();
    }

    private static Question chooseQuestion(List<Question> questionsByCategoryRandom) {
            if (questionsByCategoryRandom.size() <= 0){
                System.out.println("No hay mas preguntas en esta categoria. Elige otra categoria");
                askForCategory();
            }
            return questionsByCategoryRandom.get(0);

    }

    private static List<Question> buildQuestionsByCategoryRandomList(List<Question> questionsByCategory) {
        Collections.shuffle(questionsByCategory);
        return questionsByCategory;
    }

    private static List<Question> buildQuestionsByCategoryList(List<Question> questions, String selectedCategory) {
        List<Question> questionsByCategory = new ArrayList<>();
        for (Question question: questions) {
            if (question.getCategory().equalsIgnoreCase(selectedCategory)) {
                questionsByCategory.add(question);
            }
        }
        return questionsByCategory;
    }

    private static String askForCategory() {
        System.out.println("Amb quina categoria vols jugar?:\n - " +  GEO + "\n - " +
                MUSIC + "\n - " + SPORT + "\n - " + ART);
        Scanner sc= new Scanner(System.in);
        return sc.nextLine();
    }

    private static boolean continueWithGame(List<Question> questions) {
        return ((userScore <= MAX_SCORE) && (questions.size() >0));
    }

    private static List<Question> buildQuestionsList() {
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

