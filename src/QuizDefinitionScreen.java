import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizDefinitionScreen extends JFrame implements ActionListener {
    private JLabel questionLabel;
    private String question = "", correctAnswer = "";
    private JButton btnA, btnB, btnC, btnD, btnContinue, btnBack;
    private SlangWord slangWord;

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand())
        {
            case "Back":
                this.dispose();
                new QuizScreen();
                break;
            case "Continue":
                java.util.List<String> quiz = slangWord.quizByDefinition();
                question = quiz.get(0) + " is ?";
                questionLabel.setText(question);
                correctAnswer = quiz.get(1);

                List<String> answers = new ArrayList<>();
                for (int i=1; i<quiz.size();i++)
                {
                    answers.add(quiz.get(i));
                }

                Collections.shuffle(answers);
                btnA.setText(answers.get(0));
                btnB.setText(answers.get(1));
                btnC.setText(answers.get(2));
                btnD.setText(answers.get(3));

                btnContinue.setText("Continue");
                break;
            case "A":
                if (btnA.getText().trim().equals(correctAnswer))
                    JOptionPane.showMessageDialog(this, "CORRECT! Congratulation");
                else JOptionPane.showMessageDialog(this, "Wrong!");
                break;
            case "B":
                if (btnB.getText().trim().equals(correctAnswer))
                    JOptionPane.showMessageDialog(this, "CORRECT! Congratulation");
                else JOptionPane.showMessageDialog(this, "Wrong!");
                break;
            case "C":
                if (btnC.getText().trim().equals(correctAnswer))
                    JOptionPane.showMessageDialog(this, "CORRECT! Congratulation");
                else JOptionPane.showMessageDialog(this, "Wrong!");
                break;
            case "D":
                if (btnD.getText().trim().equals(correctAnswer))
                    JOptionPane.showMessageDialog(this, "CORRECT! Congratulation");
                else JOptionPane.showMessageDialog(this, "Wrong!");
                break;
        }
    }

    public QuizDefinitionScreen()
    {
        slangWord = new SlangWord();
        try {
            slangWord.readFromFile(SlangWord.FILE_COPY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("QUIZ DEFINITION");
        label.setForeground(Color.RED);
        label.setFont(new Font("", Font.BOLD, 50));
        topPanel.add(label);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        questionLabel = new JLabel("Question");
        questionLabel.setFont(new Font("", Font.PLAIN, 20));
        questionPanel.add(questionLabel);

        JPanel answerPanel = new JPanel();
        answerPanel.setBorder(BorderFactory.createEmptyBorder(55,55,55,55));
        answerPanel.setLayout(new GridLayout(2,2,30,30));

        btnA = new JButton("A");
        btnA.setActionCommand("A");
        btnA.addActionListener(this);

        btnB = new JButton("B");
        btnB.setActionCommand("B");
        btnB.addActionListener(this);

        btnC = new JButton("C");
        btnC.setActionCommand("C");
        btnC.addActionListener(this);

        btnD = new JButton("D");
        btnD.setActionCommand("D");
        btnD.addActionListener(this);

        answerPanel.add(btnA);
        answerPanel.add(btnB);
        answerPanel.add(btnC);
        answerPanel.add(btnD);

        centerPanel.add(questionPanel, BorderLayout.NORTH);
        centerPanel.add(answerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        btnContinue = new JButton("Start");
        btnContinue.setMargin(new Insets(10,30,10,30));
        btnContinue.setActionCommand("Continue");
        btnContinue.addActionListener(this);

        btnBack = new JButton("Back");
        btnBack.setMargin(new Insets(10,30,10,30));
        btnBack.setActionCommand("Back");
        btnBack.addActionListener(this);

        bottomPanel.add(btnContinue);
        bottomPanel.add(btnBack);

        jPanel.add(topPanel, BorderLayout.NORTH);
        jPanel.add(centerPanel, BorderLayout.CENTER);
        jPanel.add(bottomPanel, BorderLayout.SOUTH);

        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(jPanel, BorderLayout.CENTER);
        container.setMaximumSize(new Dimension(900, 600));
        container.setMinimumSize(new Dimension(900, 600));
        container.setPreferredSize(new Dimension(900, 600));

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new QuizDefinitionScreen();
    }
}
