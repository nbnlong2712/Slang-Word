import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ListScreen extends JFrame implements ActionListener {
    private JButton btnBack;
    private JTable table;
    SlangWord slangWord;
    String[] columnNames = {
            "Slang word", "Mean"
    };

    String[][] data = {};

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Back":
                this.dispose();
                new MainScreen();
                break;
        }
    }


    public ListScreen() {
        slangWord = new SlangWord();
        try {
            slangWord.readFromFile(SlangWord.FILE_COPY);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = slangWord.convertData();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setLayout(new FlowLayout());
        topPanel.setMaximumSize(new Dimension(1000, 150));
        JLabel label = new JLabel("List Word");
        label.setForeground(Color.RED);
        label.setFont(new Font("Gill Sans MT", Font.ITALIC, 45));
        topPanel.add(label);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        table = new JTable(data, columnNames);
        table.setRowHeight(25);
        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                TableModel model = (TableModel) e.getSource();
                Object data2 = model.getValueAt(row, column);
                if (column == 1) {
                    try {
                        slangWord.editSlangSword((String) model.getValueAt(row, 0), data[row][1], ((String) data2).trim());
                        JOptionPane.showMessageDialog(ListScreen.this, "Edit successful!");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        JOptionPane.showMessageDialog(ListScreen.this, "Edit failed!");
                    }
                }
            }
        });

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