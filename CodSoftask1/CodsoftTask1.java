package codsoft.task.pkg1.CodSoft_Task;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CodsoftTask1 extends JFrame {
    public int randomNumber;
    public int GuessofNumber;
    public JTextField GuessField;
    public JLabel ResultLabel;

    public CodsoftTask1() { 
        setTitle("Welcome to Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        randomNumber = generateRandomNumber();
        GuessofNumber = 0;

        JLabel titleLabel = new JLabel("Welcome to number guessing game");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout());
        GuessField = new JTextField(10);
        JButton GuessButton = new JButton("Guess");

        GuessButton.addActionListener(new GuessButtonListener());

        centerPanel.add(new JLabel("Enter your guess"));
        centerPanel.add(GuessField);
        centerPanel.add(GuessButton);
        add(centerPanel, BorderLayout.CENTER);

        ResultLabel = new JLabel();
        add(ResultLabel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CodsoftTask1();
        });
    }

    public class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(GuessField.getText());
                GuessofNumber++;

                if (guess < randomNumber) {
                    ResultLabel.setText("Try a higher number");
                } else if (guess > randomNumber) {
                    ResultLabel.setText("Try a lower number");
                } else {
                    ResultLabel.setText("Congratulations! You guessed the number right in " + GuessofNumber + " guesses");
                    GuessField.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                ResultLabel.setText("Please enter a valid number");
            }
        }
    }
}
