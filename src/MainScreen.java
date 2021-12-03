import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame implements ActionListener {
    JButton btnList, btnFind, btnAdd, btnRandom, btnDelete, btnHistory, btnReset, btnQuiz;

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
        label.setFont(new Font("Gill Sans MT", Font.ITALIC, 45));
        label.setAlignmentX(CENTER_ALIGNMENT);

        labelPanel.add(label, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(2, 4, 10, 10));

        btnList = new JButton("View All Words");
        btnList.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        btnList.setActionCommand("List");
        btnList.setSize(300, 300);
        btnList.addActionListener(this);

        btnFind = new JButton("Find");
        btnFind.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        btnFind.setActionCommand("Find");
        btnFind.setSize(300, 300);
        btnFind.addActionListener(this);

        btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        btnAdd.setActionCommand("Add");
        btnAdd.setSize(300, 300);
        btnAdd.addActionListener(this);

        btnHistory = new JButton("History");
        btnHistory.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        btnHistory.setActionCommand("History");
        btnHistory.setSize(300, 300);
        btnHistory.addActionListener(this);

        btnRandom = new JButton("Random");
        btnRandom.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        btnRandom.setActionCommand("Random");
        btnRandom.setSize(300, 300);
        btnRandom.addActionListener(this);

        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        btnDelete.setActionCommand("Delete");
        btnDelete.setSize(300, 300);
        btnDelete.addActionListener(this);

        btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        btnReset.setActionCommand("Reset");
        btnReset.setSize(300, 300);
        btnReset.addActionListener(this);

        btnQuiz = new JButton("Quiz");
        btnQuiz.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        btnQuiz.setActionCommand("Quiz");
        btnQuiz.setSize(300, 300);
        btnQuiz.addActionListener(this);

        btnPanel.add(btnList);
        btnPanel.add(btnFind);
        btnPanel.add(btnAdd);
        btnPanel.add(btnHistory);
        btnPanel.add(btnRandom);
        btnPanel.add(btnDelete);
        btnPanel.add(btnReset);
        btnPanel.add(btnQuiz);

        panel.add(labelPanel);
        panel.add(btnPanel);

        Container con = this.getContentPane();
        con.setMaximumSize(new Dimension(900, 600));
        con.setMinimumSize(new Dimension(900, 600));
        con.setPreferredSize(new Dimension(900, 600));
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(panel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Slang Word");
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}
