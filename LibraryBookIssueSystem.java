import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

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

class NullSelectionException extends Exception {
  public NullSelectionException(String message) {
    super(message);
  }
}

class InvalidDateException extends Exception {
  public InvalidDateException(String message) {
    super(message);
  }
}

public class LibraryBookIssueSystem extends JFrame implements ActionListener {

  JLabel nameLabel, rollLabel, bookLabel, categoryLabel;
  JLabel issueDateLabel, returnDateLabel, remarksLabel, typeLabel;

  JTextField nameField, rollField, bookField;
  JTextField issueDateField, returnDateField;

  JTextArea remarksArea;

  JComboBox<String> categoryBox;

  JRadioButton newEdition, oldEdition;
  ButtonGroup group;

  JButton issueButton, resetButton, exitButton;

  public LibraryBookIssueSystem() {

    setTitle("Library Book Issue System");
    setSize(650, 550);
    setLayout(null);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    getContentPane().setBackground(Color.WHITE);

    nameLabel = new JLabel("Student Name");
    nameLabel.setBounds(50, 40, 120, 25);
    add(nameLabel);

    nameField = new JTextField();
    nameField.setBounds(220, 40, 200, 25);
    add(nameField);

    rollLabel = new JLabel("Roll Number");
    rollLabel.setBounds(50, 80, 120, 25);
    add(rollLabel);

    rollField = new JTextField();
    rollField.setBounds(220, 80, 200, 25);
    add(rollField);

    bookLabel = new JLabel("Book Title");
    bookLabel.setBounds(50, 120, 120, 25);
    add(bookLabel);

    bookField = new JTextField();
    bookField.setBounds(220, 120, 200, 25);
    add(bookField);

    categoryLabel = new JLabel("Book Category");
    categoryLabel.setBounds(50, 160, 120, 25);
    add(categoryLabel);

    String[] categories = {
            "Select Category",
            "Programming",
            "AI",
            "Databases",
            "Networking"
    };

    categoryBox = new JComboBox<>(categories);
    categoryBox.setBounds(220, 160, 200, 25);
    add(categoryBox);

    typeLabel = new JLabel("Book Type");
    typeLabel.setBounds(50, 200, 120, 25);
    add(typeLabel);

    newEdition = new JRadioButton("New Edition");
    newEdition.setBounds(220, 200, 120, 25);

    oldEdition = new JRadioButton("Old Edition");
    oldEdition.setBounds(350, 200, 120, 25);

    add(newEdition);
    add(oldEdition);

    group = new ButtonGroup();
    group.add(newEdition);
    group.add(oldEdition);

    issueDateLabel = new JLabel("Issue Date (YYYY-MM-DD)");
    issueDateLabel.setBounds(50, 240, 180, 25);
    add(issueDateLabel);

    issueDateField = new JTextField();
    issueDateField.setBounds(220, 240, 200, 25);
    add(issueDateField);

    returnDateLabel = new JLabel("Return Date (YYYY-MM-DD)");
    returnDateLabel.setBounds(50, 280, 180, 25);
    add(returnDateLabel);

    returnDateField = new JTextField();
    returnDateField.setBounds(220, 280, 200, 25);
    add(returnDateField);

    remarksLabel = new JLabel("Remarks");
    remarksLabel.setBounds(50, 320, 120, 25);
    add(remarksLabel);

    remarksArea = new JTextArea();
    remarksArea.setBounds(220, 320, 250, 60);
    add(remarksArea);

    issueButton = new JButton("Issue Book");
    issueButton.setBounds(50, 420, 130, 35);

    resetButton = new JButton("Reset");
    resetButton.setBounds(220, 420, 100, 35);

    exitButton = new JButton("Exit");
    exitButton.setBounds(350, 420, 100, 35);

    add(issueButton);
    add(resetButton);
    add(exitButton);

    issueButton.addActionListener(this);
    resetButton.addActionListener(this);
    exitButton.addActionListener(this);

    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == issueButton) {

      try {

        String name = nameField.getText();
        String roll = rollField.getText();
        String book = bookField.getText();

        String issueDate = issueDateField.getText();
        String returnDate = returnDateField.getText();

        if (name.isEmpty() || roll.isEmpty() || book.isEmpty()
                || issueDate.isEmpty() || returnDate.isEmpty()) {
          throw new EmptyFieldException("All fields are required!");
        }

        if (!roll.matches("[0-9]+")) {
          throw new InvalidRollNumberException("Roll number must be numeric!");
        }

        if (categoryBox.getSelectedIndex() == 0) {
          throw new NullSelectionException("Please select category!");
        }

        if (!newEdition.isSelected() && !oldEdition.isSelected()) {
          throw new NullSelectionException("Please select book type!");
        }

        LocalDate issue = LocalDate.parse(issueDate);
        LocalDate ret = LocalDate.parse(returnDate);

        if (ret.isBefore(issue)) {
          throw new InvalidDateException("Return date cannot be before issue date!");
        }

        JOptionPane.showMessageDialog(this,
                "Book Issued Successfully!");

      }

      catch (EmptyFieldException | InvalidRollNumberException |
             NullSelectionException | InvalidDateException ex) {

        JOptionPane.showMessageDialog(this, ex.getMessage());
      }

      catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this,
                "Roll number must be numeric!");
      }

      catch (Exception ex) {
        JOptionPane.showMessageDialog(this,
                "Invalid Date Format (Use YYYY-MM-DD)");
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
      issueDateField.setText("");
      returnDateField.setText("");
      remarksArea.setText("");

      categoryBox.setSelectedIndex(0);
      group.clearSelection();
    }

    if (e.getSource() == exitButton) {
      System.exit(0);
    }
  }

  public static void main(String[] args) {
    new LibraryBookIssueSystem();
  }
}