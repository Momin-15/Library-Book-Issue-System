import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class EmptyFieldException extends Exception {

  public EmptyFieldException(String message) {
    super(message);
  }
}

class InvalidRollNumberException extends Exception {

  public InvalidRollNumberException(String message) {
    super(message);
  }
}

public class LibraryBookIssueSystem extends JFrame implements ActionListener {

  JLabel nameLabel, rollLabel, bookLabel, categoryLabel;
  JLabel remarksLabel;

  JTextField nameField, rollField, bookField;

  JTextArea remarksArea;

  JComboBox<String> categoryBox;

  JButton issueButton, resetButton, exitButton;

  public LibraryBookIssueSystem() {

    setTitle("Library Book Issue System");
    setSize(600, 500);
    setLayout(null);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    getContentPane().setBackground(Color.LIGHT_GRAY);

    nameLabel = new JLabel("Student Name");
    nameLabel.setBounds(50, 50, 120, 25);
    add(nameLabel);

    nameField = new JTextField();
    nameField.setBounds(200, 50, 200, 25);
    add(nameField);

    rollLabel = new JLabel("Roll Number");
    rollLabel.setBounds(50, 100, 120, 25);
    add(rollLabel);

    rollField = new JTextField();
    rollField.setBounds(200, 100, 200, 25);
    add(rollField);

    bookLabel = new JLabel("Book Title");
    bookLabel.setBounds(50, 150, 120, 25);
    add(bookLabel);

    bookField = new JTextField();
    bookField.setBounds(200, 150, 200, 25);
    add(bookField);

    categoryLabel = new JLabel("Book Category");
    categoryLabel.setBounds(50, 200, 120, 25);
    add(categoryLabel);

    String categories[] = {
            "Programming",
            "AI",
            "Databases",
            "Networking"
    };

    categoryBox = new JComboBox<>(categories);
    categoryBox.setBounds(200, 200, 200, 25);
    add(categoryBox);

    remarksLabel = new JLabel("Remarks");
    remarksLabel.setBounds(50, 250, 120, 25);
    add(remarksLabel);

    remarksArea = new JTextArea();
    remarksArea.setBounds(200, 250, 200, 80);
    add(remarksArea);

    issueButton = new JButton("Issue Book");
    issueButton.setBounds(50, 380, 120, 35);

    resetButton = new JButton("Reset");
    resetButton.setBounds(220, 380, 100, 35);

    exitButton = new JButton("Exit");
    exitButton.setBounds(370, 380, 100, 35);

    issueButton.setBackground(Color.GREEN);
    resetButton.setBackground(Color.YELLOW);
    exitButton.setBackground(Color.RED);

    add(issueButton);
    add(resetButton);
    add(exitButton);

    issueButton.addActionListener(this);
    resetButton.addActionListener(this);
    exitButton.addActionListener(this);

    JOptionPane.showMessageDialog(this,
            "Welcome To Library Management System");

    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == issueButton) {

      try {

        String name = nameField.getText();
        String roll = rollField.getText();
        String book = bookField.getText();

        if (name.isEmpty() || roll.isEmpty() || book.isEmpty()) {

          throw new EmptyFieldException(
                  "All fields are required");
        }

        Integer.parseInt(roll);

        if (!roll.matches("[0-9]+")) {

          throw new InvalidRollNumberException(
                  "Roll Number must contain numbers only");
        }

        JOptionPane.showMessageDialog(this,
                "Book Issued Successfully\n"
                        + "Student: " + name
                        + "\nRoll No: " + roll
                        + "\nBook: " + book);

      }

      catch (EmptyFieldException ex) {

        JOptionPane.showMessageDialog(this,
                ex.getMessage());
      }

      catch (InvalidRollNumberException ex) {

        JOptionPane.showMessageDialog(this,
                ex.getMessage());
      }

      catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(this,
                "Roll Number must be numeric");
      }

      finally {

        JOptionPane.showMessageDialog(this,
                "Operation Completed");
      }
    }

    if (e.getSource() == resetButton) {

      nameField.setText("");
      rollField.setText("");
      bookField.setText("");
      remarksArea.setText("");

      JOptionPane.showMessageDialog(this,
              "Form Reset Successfully");
    }

    if (e.getSource() == exitButton) {

      System.exit(0);
    }
  }

  public static void main(String[] args) {

    new LibraryBookIssueSystem();
  }
}