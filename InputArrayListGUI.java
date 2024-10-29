package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputArrayListGUI extends JFrame {
    private inputArrayList list;
    private JTextField inputField, indexField;
    private JButton addButton, updateButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private int maxSize; 

    public InputArrayListGUI() {
    	String sizeStr = JOptionPane.showInputDialog("Enter the number of strings:");
        int N = Integer.parseInt(sizeStr);
        this.maxSize = N;
        list = new inputArrayList(N);

        // Window settings
        setTitle("Palindrome Checker");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(45, 45, 45)); // Window background color


        // Top panel: String input and insert button
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(160, 163, 165));
        inputPanel.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        inputField.setBackground(new Color(30, 30, 30));
        inputField.setForeground(Color.WHITE);
        
        addButton = new JButton("Add String");
        addButton.setBackground(new Color(85, 170, 85));
        addButton.setForeground(Color.BLACK);
        
        inputPanel.add(new JLabel("Enter a String:"));
        inputPanel.add(inputField);
        inputPanel.add(addButton);

        // Middle panel: Contains the table
        String[] columnNames = {"Index", "String", "Palindrome?"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setBackground(new Color(40, 40, 40));
        table.setForeground(Color.WHITE);
        table.setRowHeight(30);
        table.getTableHeader().setBackground(new Color(70, 70, 70));
        table.getTableHeader().setForeground(Color.WHITE);

     // Palindrome lines appear in green
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String palindromeStatus = (String) table.getValueAt(row, 2);
                if (palindromeStatus.equals("Palindrome")) {
                    c.setBackground(new Color(85, 170, 85));
                } else {
                    c.setBackground(new Color(170, 85, 85));
                }
                c.setForeground(Color.WHITE);
                return c;
            }
        };
        table.setDefaultRenderer(Object.class, renderer);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel: Index entry and update button
        JPanel updatePanel = new JPanel();
        updatePanel.setBackground(new Color(160, 163, 165));
        updatePanel.setLayout(new FlowLayout());

        indexField = new JTextField(5);
        indexField.setBackground(new Color(30, 30, 30));
        indexField.setForeground(Color.WHITE);
        
        updateButton = new JButton("Update Index");
        updateButton.setBackground(new Color(85, 85, 170));
        updateButton.setForeground(Color.BLACK);

        updatePanel.add(new JLabel("Enter Index to Update:"));
        updatePanel.add(indexField);
        updatePanel.add(updateButton);

        // Add panels to window
        add(inputPanel, BorderLayout.NORTH);
        add(updatePanel, BorderLayout.SOUTH);

        // Add action listener to buttons
        addButton.addActionListener(new AddStringListener());
        updateButton.addActionListener(new UpdateIndexListener());
    }

    // Listener that manages string appending process
    private class AddStringListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText();
            if (!input.isEmpty()) {
                if (list.size() < maxSize) {  // Check index limit
                    list.addArrayList(input);
                    addToTable(list.size() - 1, input);
                    inputField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Maximum index reached! Use 'Update Index' to modify the list.");
                }
            }
        }
    }

    // Adding a new row to a table
    private void addToTable(int index, String input) {
        String palindromeStatus = list.isPalindromeString(input) ? "Palindrome" : "Not a Palindrome";
        tableModel.addRow(new Object[]{index, input, palindromeStatus});
    }

    // Listener that manages index updates
    private class UpdateIndexListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int newSize = Integer.parseInt(indexField.getText());

                if (newSize > maxSize) {  // Do not allow adding if new index limit is exceeded
                    JOptionPane.showMessageDialog(null, 
                        "Index exceeds the maximum allowed size!");
                } else if (newSize > list.size()) {  
                    // If the new index is larger than the current size, add new elements.
                    for (int i = list.size(); i < newSize; i++) {
                        String newString = JOptionPane.showInputDialog(
                            "Enter a string for index " + i + ":");
                        list.addArrayList(newString);
                        addToTable(i, newString);
                    }
                } else if (newSize < list.size()) {  
                    // If the new index is smaller than the current size, remove the extra elements.
                    for (int i = list.size() - 1; i >= newSize; i--) {
                        list.remove(i);
                        tableModel.removeRow(i);
                    }
                }
                indexField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            }
        }
    }

    // Updating the table
    private void refreshTable() {
        tableModel.setRowCount(0);  // Clean up old data
        for (int i = 0; i < list.size(); i++) {
            String input = list.get(i);
            String palindromeStatus = list.isPalindromeString(input) ? "Palindrome" : "Not a Palindrome";
            tableModel.addRow(new Object[]{i, input, palindromeStatus});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InputArrayListGUI frame = new InputArrayListGUI();
            frame.setVisible(true);
        });
    }
}
