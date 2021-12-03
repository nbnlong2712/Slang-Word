import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HistoryScreen extends JFrame implements ActionListener {
    private JButton btnBack;
    SlangWord slangWord;
    String[] columnNames = {
            "Slang word", "Mean"
    };

    String[][] data = {};

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand())
        {
            case "Back":
                this.dispose();
                new MainScreen();
                break;
        }
    }

    public HistoryScreen() {
        slangWord = new SlangWord();
        try {
            slangWord.readFromFile(SlangWord.FILE_COPY);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            data = slangWord.loadHistory();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setLayout(new FlowLayout());
        topPanel.setMaximumSize(new Dimension(1000, 150));
        JLabel label = new JLabel("History");
        label.setForeground(Color.RED);
        label.setFont(new Font("Gill Sans MT", Font.ITALIC, 45));
        topPanel.add(label);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        JTable table = new JTable(data, columnNames);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnBack = new JButton("BACK");
        btnBack.setActionCommand("Back");
        btnBack.addActionListener(this);
        btnBack.setMaximumSize(new Dimension(150, 80));
        bottomPanel.add(btnBack);

        panel.add(topPanel);
        panel.add(centerPanel);
        panel.add(bottomPanel);

        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.setMinimumSize(new Dimension(900, 600));
        container.setMaximumSize(new Dimension(900, 600));
        container.setPreferredSize(new Dimension(900, 600));
        container.add(panel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
