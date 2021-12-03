import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FindSlangWordScreen extends JFrame implements ActionListener {
    private JButton btnBack, btnFind;
    private SlangWord slangWord;
    private JTextField jTextField;
    private JTable jTable;
    private String[] columnName = {
            "Slang word", "Mean"
    };
    private String[][] data = {};

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Back":
                this.dispose();
                new FindScreen();
                break;
            case "Find":
                data = slangWord.findByKey(jTextField.getText().trim());
                if (data != null) {
                    if (data.length != 0) {
                        try {
                            slangWord.saveHistory(jTextField.getText().trim());
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    else JOptionPane.showMessageDialog(this, "Can't find " + jTextField.getText().trim());
                }
                else JOptionPane.showMessageDialog(this, "Can't find " + jTextField.getText().trim());
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                model.setRowCount(0);
                jTextField.setText("");
                if (data != null) {
                    for (String[] str : data) {
                        model.addRow(str);
                    }
                }
                break;
        }
    }

    public FindSlangWordScreen() {
        slangWord = new SlangWord();
        try {
            slangWord.readFromFile(SlangWord.FILE_COPY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setMaximumSize(new Dimension(1000, 300));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        JLabel jLabel = new JLabel("Enter word: ");
        jLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        jTextField = new JTextField();

        btnFind = new JButton("Find");
        btnFind.setActionCommand("Find");
        btnFind.addActionListener(this);

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(jLabel);
        topPanel.add(jTextField);
        topPanel.add(btnFind);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        jTable = new JTable(new DefaultTableModel(columnName, 0));

        JScrollPane jScrollPane = new JScrollPane(jTable);
        centerPanel.add(jScrollPane);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        btnBack = new JButton("BACK");
        btnBack.setMaximumSize(new Dimension(150, 100));
        btnBack.setActionCommand("Back");
        btnBack.addActionListener(this);
        bottomPanel.add(btnBack);

        panel.add(topPanel);
        panel.add(centerPanel);
        panel.add(bottomPanel);

        Container container = this.getContentPane();
        container.setMinimumSize(new Dimension(900, 600));
        container.setMaximumSize(new Dimension(900, 600));
        container.setPreferredSize(new Dimension(900, 600));
        container.setLayout(new BorderLayout());

        container.add(panel);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}