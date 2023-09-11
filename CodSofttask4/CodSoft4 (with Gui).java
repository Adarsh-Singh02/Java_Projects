package codsoft.task.pkg1.CodSoft_Task;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
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
    private int score = 0;
    private int currentQuestionIndex = 0;
    private List<CodSoftTask4> questions = new ArrayList<>();
    private Timer timer = new Timer();
    private JFrame frame;
    private JLabel questionLabel;
    private ButtonGroup buttonGroup;
    private JRadioButton[] optionButtons;
    private JButton nextButton;

    public CodSoft4() {
        initializeQuestions();

        frame = new JFrame("CodSoft Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(2, 1));
        questionLabel = new JLabel("", JLabel.CENTER);
        questionPanel.add(questionLabel);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        buttonGroup = new ButtonGroup();
        optionButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionsPanel.add(optionButtons[i]);
            buttonGroup.add(optionButtons[i]);
        }
        frame.add(questionPanel, BorderLayout.NORTH);
        frame.add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        frame.add(nextButton, BorderLayout.SOUTH);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextButtonClick();
            }
        });

        showNextQuestion();

        frame.setVisible(true);
    }

    private void initializeQuestions() {
        questions.add(new CodSoftTask4("Which of the following option leads to the portability and security of Java?",
                List.of("A) The applet makes the Java code secure and portable", "B) Bytecode is executed by JVM", "C) Use of exception handling", "D) Dynamic binding between objects"), 1));

        questions.add(new CodSoftTask4(" Which of the following is not a Java features?",
                List.of("A) Dynamic", "B) Architecture Neutral", "C) Use of pointers", "D) Object-oriented"), 2));

        questions.add(new CodSoftTask4(" _____ is used to find and fix bugs in the Java programs.",
                List.of("A) JVM", "B) JRE", "C) JDK", "D) JDB"), 3));

        questions.add(new CodSoftTask4(" Which of the following creates a List of 3 visible items and multiple selections abled?",
                List.of("A)new List(false, 3)", "B) new List(3, true)", "C) new List(true, 3)", "D)new List(3, false)"), 1));

        questions.add(new CodSoftTask4(" Which of the following is true about the anonymous inner class?",
                List.of("A) It has only methods", "B) Objects can't be created", "C) It has a fixed class name", "D) It has no class name"), 3));
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            CodSoftTask4 currentQuestion = questions.get(currentQuestionIndex);
            questionLabel.setText("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText(options.get(i));
            }
            buttonGroup.clearSelection();
            startTimer(10);
        } else {
            endQuiz();
        }
    }

    private void startTimer(int seconds) {
        timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        handleNextButtonClick();
                    }
                });
            }
        }, seconds * 1000);
    }

    private void handleNextButtonClick() {
        if (currentQuestionIndex < questions.size()) {
            CodSoftTask4 currentQuestion = questions.get(currentQuestionIndex);
            int selectedOption = -1;
            for (int i = 0; i < optionButtons.length; i++) {
                if (optionButtons[i].isSelected()) {
                    selectedOption = i;
                    break;
                }
            }
            if (selectedOption == currentQuestion.getCorrectOption()) {
                score++;
            }
            currentQuestionIndex++;
            showNextQuestion();
        }
    }

    private void endQuiz() {
        frame.dispose();
        JOptionPane.showMessageDialog(null, "Quiz Ended!\nYour Score: " + score + "/" + questions.size());
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CodSoft4();
            }
        });
    }
}
