import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame implements ActionListener {
    JButton btnList, btnFind, btnAdd, btnRandom, btnDelete, btnHistory, btnQuiz;

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "List":
                this.dispose();
                new ListScreen().setVisible(true);
                break;
            case "Find":
                this.dispose();
                new FindScreen();
                break;
            case "History":
                this.dispose();
                new HistoryScreen();
                break;
            case "Add":
                this.dispose();
                new AddScreen();
                break;
            case "Delete":
                this.dispose();
                new DeleteScreen();
                break;
            case "Random":
                this.dispose();
                new RandomScreen();
                break;
            case "Quiz":
                this.dispose();
                new QuizScreen();
                break;
        }
    }

    public MainScreen() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel labelPanel = new JPanel();
        labelPanel.setMaximumSize(new Dimension(400, 120));
        JLabel label = new JLabel("SLANG WORD");
        label.setForeground(Color.RED);
        label.setFont(new Font("", Font.BOLD, 45));
        label.setAlignmentX(CENTER_ALIGNMENT);

        labelPanel.add(label, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(2, 4, 10, 10));

        btnList = new JButton("View All Words");
        btnList.setActionCommand("List");
        btnList.addActionListener(this);

        btnFind = new JButton("Find");
        btnFind.setActionCommand("Find");
        btnFind.addActionListener(this);

        btnAdd = new JButton("Add");
        btnAdd.setActionCommand("Add");
        btnAdd.addActionListener(this);

        btnHistory = new JButton("History");
        btnHistory.setActionCommand("History");
        btnHistory.addActionListener(this);

        btnRandom = new JButton("Random");
        btnRandom.setActionCommand("Random");
        btnRandom.addActionListener(this);

        btnDelete = new JButton("Delete");
        btnDelete.setActionCommand("Delete");
        btnDelete.addActionListener(this);

        btnQuiz = new JButton("Quiz");
        btnQuiz.setActionCommand("Quiz");
        btnQuiz.addActionListener(this);

        btnPanel.add(btnList);
        btnPanel.add(btnFind);
        btnPanel.add(btnAdd);
        btnPanel.add(btnHistory);
        btnPanel.add(btnRandom);
        btnPanel.add(btnDelete);
        btnPanel.add(btnQuiz);

        panel.add(labelPanel);
        panel.add(btnPanel);

        Container container = this.getContentPane();
        container.setMaximumSize(new Dimension(900, 600));
        container.setMinimumSize(new Dimension(900, 600));
        container.setPreferredSize(new Dimension(900, 600));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(panel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Slang Word");
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}
