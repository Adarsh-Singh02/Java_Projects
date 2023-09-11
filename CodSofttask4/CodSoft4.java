package codsoft.task.pkg1.CodSoft_Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class CodSoftTask4 {
    private String question;
    private List<String> options;
    private int correctOption;

    public CodSoftTask4(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

public class CodSoft4 {
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static List<CodSoftTask4> questions = new ArrayList<>();
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        initializeQuestions();
        startQuiz();
    }

    private static void initializeQuestions() {

        questions.add(new CodSoftTask4("Which of the following option leads to the portability and security of Java?",
                List.of("A) The applet makes the Java code secure and portable", "B) Bytecode is executed by JVM", "C) Use of exception handling", "D) Dynamic binding between objects"), 1));

        questions.add(new CodSoftTask4(" Which of the following is not a Java features?",
                List.of("A) Dynamic", "B) Architecture Neutral", "C) Use of pointers", "D) Object-oriented"), 2));

        questions.add(new CodSoftTask4(" _____ is used to find and fix bugs in the Java programs.",
                List.of("A) JVM", "B) JRE", "C) JDK", "D) JDB"), 3));

        questions.add(new CodSoftTask4(" Which of the following creates a List of 3 visible items and multiple selections abled?",
                List.of("A)new List(false, 3)", "B) new List(3, true)", "C) new List(true, 3)", "D)new List(3, false)"), 1));

        questions.add(new CodSoftTask4(" Which of the following is true about the anonymous inner class?",
                List.of("A) It has only methods", "B) Objects can't be created", "C) It has a fixed class name", "D) It has no class name"), 3 ));
    }

    private static void startQuiz() {
        if (currentQuestionIndex < questions.size()) {
            CodSoftTask4 currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (String option : options) {
                System.out.println(option);
            }

            setTimer(10);
            handleUserInput(currentQuestion);
        } else {
            endQuiz();
        }
    }

    private static void handleUserInput(CodSoftTask4 currentQuestion) {
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine();
        timer.cancel();

        try {
            int selectedOption = Integer.parseInt(userAnswer) - 1;
            if (selectedOption == currentQuestion.getCorrectOption()) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is option " + (currentQuestion.getCorrectOption() + 1) + ".\n");
            }
            currentQuestionIndex++;
            startQuiz();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter the number of your choice.");
            handleUserInput(currentQuestion);
        }
    }

    private static void setTimer(int seconds) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
                currentQuestionIndex++;
                startQuiz();
            }
        }, seconds * 1000);
    }

    private static void endQuiz() {
        System.out.println("Quiz Ended!");
        System.out.println("Your Score: " + score + "/" + questions.size());

    }
}
