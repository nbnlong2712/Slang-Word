import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindScreen extends JFrame implements ActionListener {
    JButton btnFindSlangWord, btnFindDefinition, btnBack;

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Slang":
                this.dispose();
                new FindSlangWordScreen();
                break;
            case "Definition":
                this.dispose();
                new FindDefinitionScreen();
                break;
            case "Back":
                this.dispose();
                new MainScreen();
                break;
        }
    }

    public FindScreen() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel labelPanel = new JPanel();
        labelPanel.setMaximumSize(new Dimension(400, 120));
        JLabel label = new JLabel("FIND");
        label.setForeground(Color.RED);
        label.setFont(new Font("", Font.BOLD, 45));
        label.setAlignmentX(CENTER_ALIGNMENT);

        labelPanel.add(label, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(2, 4, 10, 10));

        btnFindSlangWord = new JButton("Find Slang Word");
        btnFindSlangWord.setMargin(new Insets(50, 110, 50, 110));
        btnFindSlangWord.setActionCommand("Slang");
        btnFindSlangWord.addActionListener(this);

        btnFindDefinition = new JButton("Find By Definition");
        btnFindDefinition.setMargin(new Insets(50, 110, 50, 110));
        btnFindDefinition.setActionCommand("Definition");
        btnFindDefinition.addActionListener(this);

        JPanel findPanel = new JPanel();
        findPanel.setLayout(new FlowLayout());
        findPanel.add(btnFindSlangWord);
        findPanel.add(btnFindDefinition);

        btnBack = new JButton("BACK");
        btnBack.setActionCommand("Back");
        btnBack.setMargin(new Insets(20,70,20,70));
        btnBack.setSize(300, 300);
        btnBack.addActionListener(this);

        JPanel backPanel = new JPanel();
        backPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        backPanel.add(btnBack);

        btnPanel.add(findPanel);
        btnPanel.add(backPanel);

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
}
