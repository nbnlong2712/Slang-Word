import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class RandomScreen extends JFrame implements ActionListener {
    private JButton btnContinue, btnBack;
    private String randomSlangWord = "";
    private JLabel label;
    private SlangWord slangWord;

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Back":
                this.dispose();
                new MainScreen();
                break;
            case "Continue":
                List<String> slangList = slangWord.randomSlangWord();
                if (slangList.size() == 2) {
                    randomSlangWord = slangList.get(0) + " is " + slangList.get(1);
                    label.setText(randomSlangWord);
                } else if (slangList.size() > 2) {
                    randomSlangWord = slangList.get(0) + " is " + slangList.get(1);
                    for (int i = 2; i < slangList.size(); i++) {
                        randomSlangWord += ", " + slangList.get(i);
                    }
                    label.setText(randomSlangWord);
                }
                break;
        }
    }

    public RandomScreen() {
        slangWord = new SlangWord();
        try {
            slangWord.readFromFile(SlangWord.FILE_COPY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel jPanel = new JPanel();
        jPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JPanel randomPanel = new JPanel();
        randomPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        randomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        label = new JLabel("Click continue button");
        label.setFont(new Font("", Font.PLAIN, 40));
        randomPanel.add(label);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnBack = new JButton("BACK");
        btnBack.setActionCommand("Back");
        btnBack.addActionListener(this);
        btnBack.setMaximumSize(new Dimension(150, 80));

        btnContinue = new JButton("Continue");
        btnContinue.setActionCommand("Continue");
        btnContinue.addActionListener(this);
        btnContinue.setMaximumSize(new Dimension(150, 80));

        btnPanel.add(btnContinue);
        btnPanel.add(btnBack);

        jPanel.add(randomPanel);
        jPanel.add(btnPanel);

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
        new RandomScreen();
    }
}
