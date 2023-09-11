package codsoft.task.pkg1.CodSoft_Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodSoftTask2 extends JFrame {

    private JTextField[] subjectFields;
    private JLabel[] gradeLabels;
    private JLabel totalMarksLabel;
    private JLabel percentageLabel;

    public CodSoftTask2() {
        setTitle("Subject Marks and Grades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        subjectFields = new JTextField[5];
        gradeLabels = new JLabel[5];

        for (int i = 0; i < 5; i++) {
            JLabel subjectLabel = new JLabel("Subject " + (i + 1) + " Marks:");
            subjectFields[i] = new JTextField(10);

            JLabel gradeLabel = new JLabel("Grade:");
            gradeLabels[i] = new JLabel();

            mainPanel.add(subjectLabel);
            mainPanel.add(subjectFields[i]);
            mainPanel.add(gradeLabel);
            mainPanel.add(gradeLabels[i]);
        }

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        calculateButton.setBackground(new Color(72, 130, 196));
        calculateButton.setForeground(Color.WHITE);

        totalMarksLabel = new JLabel("Total Marks: ");
        totalMarksLabel.setForeground(new Color(41, 128, 185));
        percentageLabel = new JLabel("Percentage: ");
        percentageLabel.setForeground(new Color(41, 128, 185));

        mainPanel.add(new JLabel());
        mainPanel.add(calculateButton);
        mainPanel.add(totalMarksLabel);
        mainPanel.add(new JLabel()); // Empty cell
        mainPanel.add(percentageLabel);
        mainPanel.add(new JLabel()); // Empty cell

        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] sub = new int[5];
            String[] grades = new String[5];
            int totalMarks = 0;

            for (int i = 0; i < 5; i++) {
                sub[i] = Integer.parseInt(subjectFields[i].getText());
                grades[i] = calculateGrade(sub[i]);
                gradeLabels[i].setText(grades[i]);
                totalMarks += sub[i];
            }

            double percentage = (double) totalMarks / 5;
            totalMarksLabel.setText("Total Marks: " + totalMarks);
            percentageLabel.setText("Percentage: " + percentage + "%");
        }
    }

    // Function to calculate grades based on marks
    public static String calculateGrade(int marks) {
        if (marks >= 95) {
            return "O";
        } else if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CodSoftTask2 gui = new CodSoftTask2();
            gui.setVisible(true);
        });
    }
}