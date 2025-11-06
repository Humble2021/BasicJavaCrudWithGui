import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BasicJavaCrudWithGui{
    static String[] friends = new String[0];

    public static void main(String[] args) {
        JFrame f = new JFrame("Basic CRUD with GUI");
        f.setSize(600, 400);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.black);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JLabel title = new JLabel("MY FRIENDS", JLabel.CENTER);
        title.setBounds(80, 30, 440, 40);
        title.setForeground(Color.green);
        title.setFont(new Font("Monospaced", Font.BOLD, 18));
        f.add(title);

        JButton add = new JButton("ADD FRIEND");
        JButton view = new JButton("VIEW FRIENDS");
        JButton update = new JButton("UPDATE FRIEND");
        JButton search = new JButton("SEARCH FRIEND");
        JButton del = new JButton("DELETE FRIEND");
        JButton exit = new JButton("EXIT");

        add.setBounds(100, 100, 180, 40);
        view.setBounds(320, 100, 180, 40);
        update.setBounds(100, 160, 180, 40);
        search.setBounds(320, 160, 180, 40);
        del.setBounds(100, 220, 180, 40);
        exit.setBounds(320, 220, 180, 40);

        Color bg = Color.black;
        Color fg = Color.green;

        JButton[] buttons = { add, view, update, search, del, exit };
        for (JButton b : buttons) {
            b.setBackground(bg);
            b.setForeground(fg);
            b.setFocusPainted(false);
            b.setFont(new Font("Monospaced", Font.BOLD, 14));
            f.add(b);
        }

     
        add.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(f, "Enter friend's name:");
            if (name != null && !name.trim().isEmpty()) {
                String[] newList = new String[friends.length + 1];
                for (int i = 0; i < friends.length; i++) newList[i] = friends[i];
                newList[friends.length] = name.trim();
                friends = newList;
                JOptionPane.showMessageDialog(f, "Friend added successfully!");
            }
        });

      
        view.addActionListener(e -> {
            if (friends.length == 0)
                JOptionPane.showMessageDialog(f, "No friends found.");
            else {
                String list = "";
                for (int i = 0; i < friends.length; i++)
                    list += (i + 1) + ". " + friends[i] + "\n";
                JOptionPane.showMessageDialog(f, list, "Friends List", JOptionPane.PLAIN_MESSAGE);
            }
        });

    
        update.addActionListener(e -> {
            if (friends.length == 0) {
                JOptionPane.showMessageDialog(f, "No friends to update.");
                return;
            }
            JComboBox<String> combo = new JComboBox<>(friends);
            int result = JOptionPane.showConfirmDialog(f, combo, "Select a friend to update", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                int selectedIndex = combo.getSelectedIndex();
                String newName = JOptionPane.showInputDialog(f, "Enter new name for " + friends[selectedIndex] + ":");
                if (newName != null && !newName.trim().isEmpty()) {
                    friends[selectedIndex] = newName.trim();
                    JOptionPane.showMessageDialog(f, "Friend updated successfully!");
                }
            }
        });

        del.addActionListener(e -> {
            if (friends.length == 0) {
                JOptionPane.showMessageDialog(f, "No records to delete.");
                return;
            }
            JComboBox<String> combo = new JComboBox<>(friends);
            int result = JOptionPane.showConfirmDialog(f, combo, "Select a friend to delete", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                int selectedIndex = combo.getSelectedIndex();
                String[] newList = new String[friends.length - 1];
                for (int i = 0, j = 0; i < friends.length; i++)
                    if (i != selectedIndex) newList[j++] = friends[i];
                friends = newList;
                JOptionPane.showMessageDialog(f, "Friend deleted successfully!");
            }
        });

      
        search.addActionListener(e -> {
            if (friends.length == 0) {
                JOptionPane.showMessageDialog(f, "No friends to search.");
                return;
            }
            String name = JOptionPane.showInputDialog(f, "Enter name to search:");
            if (name != null && !name.trim().isEmpty()) {
                String results = "";
                for (int i = 0; i < friends.length; i++) {
                    if (friends[i].toLowerCase().contains(name.trim().toLowerCase()))
                        results += (i + 1) + ". " + friends[i] + "\n";
                }
                if (results.isEmpty())
                    JOptionPane.showMessageDialog(f, "No matches found.");
                else
                    JOptionPane.showMessageDialog(f, results, "Search Results", JOptionPane.PLAIN_MESSAGE);
            }
        });

        exit.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                f, "Are you sure you want to exit?", "Exit Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    f, "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
                );
                if (confirm == JOptionPane.YES_OPTION) System.exit(0);
            }
        });

        f.setVisible(true);
    }
}
