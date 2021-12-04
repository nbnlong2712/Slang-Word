import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddScreen extends JFrame implements ActionListener {
    private JTextField tfSlangWord, tfDefinition;
    private JButton btnAdd, btnBack;
    private SlangWord slangWord;

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add":
                if (tfSlangWord.getText().trim().isEmpty() || tfSlangWord.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill enough!");
                } else {
                    if (!slangWord.checkDuplicate(tfSlangWord.getText().trim())) {
                        try {
                            slangWord.addNew(tfSlangWord.getText().trim(), tfDefinition.getText().trim());
                            JOptionPane.showMessageDialog(this, "Add success!");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                            JOptionPane.showMessageDialog(this, "Add failed!");
                        }
                    } else {
                        Object[] options = {"Overwrite", "Duplicate"};
                        int opt = JOptionPane.showOptionDialog(this, "This word existed! Overwrite or Duplicate"
                                , "Existed word!", JOptionPane.YES_NO_CANCEL_OPTION
                                , JOptionPane.QUESTION_MESSAGE, null, options, null);
                        if (opt == 0) {
                            try {
                                slangWord.addOverwrite(tfSlangWord.getText().trim(), tfDefinition.getText().trim());
                                JOptionPane.showMessageDialog(this, "Add success!");
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Add failed!");
                            }
                        } else if (opt == 1) {
                            try {
                                slangWord.addDuplicate(tfSlangWord.getText().trim(), tfDefinition.getText().trim());
                                JOptionPane.showMessageDialog(this, "Add success!");
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Add failed!");
                            }
                        }
                    }
                    tfSlangWord.setText("");
                    tfDefinition.setText("");
                }
                break;
            case "Back":
                this.dispose();
                new MainScreen();
                break;
        }
    }

    public AddScreen() {
        slangWord = new SlangWord();
        try {
            slangWord.readFromFile(SlangWord.FILE_COPY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JPanel slangPanel = new JPanel();
        slangPanel.setLayout(new BoxLayout(slangPanel, BoxLayout.X_AXIS));
        slangPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel slangLabel = new JLabel("Slang Word: ");
        tfSlangWord = new JTextField();
        tfSlangWord.setMaximumSize(new Dimension(400, 40));
        slangPanel.add(slangLabel);
        slangPanel.add(tfSlangWord);

        JPanel definitionPanel = new JPanel();
        definitionPanel.setLayout(new BoxLayout(definitionPanel, BoxLayout.X_AXIS));
        definitionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel definitionLabel = new JLabel("Definition:    ");
        tfDefinition = new JTextField();
        tfDefinition.setMaximumSize(new Dimension(400, 40));
        definitionPanel.add(definitionLabel);
        definitionPanel.add(tfDefinition);

        inputPanel.add(slangPanel);
        inputPanel.add(definitionPanel);

        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 200, 50));
        btnPanel.setLayout(new FlowLayout());

        btnAdd = new JButton("ADD");
        btnAdd.setMargin(new Insets(20, 50, 20, 50));
        btnAdd.setActionCommand("Add");
        btnAdd.addActionListener(this);

        btnBack = new JButton("BACK");
        btnBack.setMargin(new Insets(20, 50, 20, 50));
        btnBack.setActionCommand("Back");
        btnBack.addActionListener(this);

        btnPanel.add(btnAdd);
        btnPanel.add(btnBack);

        jPanel.add(inputPanel, BorderLayout.CENTER);
        jPanel.add(btnPanel, BorderLayout.SOUTH);

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
}
