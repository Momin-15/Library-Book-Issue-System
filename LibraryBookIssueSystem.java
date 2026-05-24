import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

class InvalidDateException extends Exception {
  public InvalidDateException(String message) {
    super(message);
  }
}

class NullSelectionException extends Exception {
  public NullSelectionException(String message) {
    super(message);
  }
}

public class LibraryBookIssueSystem extends JFrame implements ActionListener {

  JLabel issueDateLabel, returnDateLabel;
  JLabel typeLabel, categoryLabel;

  JTextField issueDateField, returnDateField;

  JComboBox<String> categoryBox;

  JRadioButton newEdition, oldEdition;

  ButtonGroup group;

  JButton issueButton, resetButton, exitButton;

  public LibraryBookIssueSystem() {

    setTitle("Library Book Issue System");
    setSize(600, 450);
    setLayout(null);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    getContentPane().setBackground(ColorTIHW);

    categoryLabel = new JLabel("Book Category");
    categoryLabel.setBounds(50, 50, 120, 25);
    add(categoryLabel);

    String categories[] = {
            "Select Category",
            "Programming",
            "AI",
            "Databases",
            "Networking"
    };

    categoryBox = new JComboBox<>(categories);
    categoryBox.setBounds(220, 50, 200, 25);
    add(categoryBox);

    typeLabel = new JLabel("Book Type");
    typeLabel.setBounds(50, 100, 120, 25);
    add(typeLabel);

    newEdition = new JRadioButton("New Edition");
    newEdition.setBounds(220, 100, 120, 25);

    oldEdition = new JRadioButton("Old Edition");
    oldEdition.setBounds(350, 100, 120, 25);

    add(newEdition);
    add(oldEdition);

    group = new ButtonGroup();
    group.add(newEdition);
    group.add(oldEdition);

    issueDateLabel = new JLabel("Issue Date");
    issueDateLabel.setBounds(50, 160, 120, 25);
    add(issueDateLabel);

    issueDateField = new JTextField();
    issueDateField.setBounds(220, 160, 200, 25);
    add(issueDateField);

    returnDateLabel = new JLabel("Return Date");
    returnDateLabel.setBounds(50, 220, 120, 25);
    add(returnDateLabel);

    returnDateField = new JTextField();
    returnDateField.setBounds(220, 220, 200, 25);
    add(returnDateField);

    issueButton = new JButton("Issue Book");
    issueButton.setBounds(50, 320, 120, 35);

    resetButton = new JButton("Reset");
    resetButton.setBounds(220, 320, 100, 35);

    exitButton = new JButton("Exit");
    exitButton.setBounds(380, 320, 100, 35);

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

        // empty field validation
        if (issueDateField.getText().trim().isEmpty()
                || returnDateField.getText().trim().isEmpty()) {

          throw new NullSelectionException(
                  "Issue Date and Return Date cannot be empty");
        }

        if (categoryBox.getSelectedIndex() == 0) {

          throw new NullSelectionException(
                  "Please select book category");
        }

        if (!newEdition.isSelected()
                && !oldEdition.isSelected()) {

          throw new NullSelectionException(
                  "Please select book type");
        }

        LocalDate issue =
                LocalDate.parse(issueDateField.getText());

        LocalDate ret =
                LocalDate.parse(returnDateField.getText());

        if (ret.isBefore(issue)) {

          throw new InvalidDateException(
                  "Return date cannot be earlier than issue date");
        }

        // same day validation
        if (ret.equals(issue)) {

          throw new InvalidDateException(
                  "Issue date and return date cannot be same");
        }

        JOptionPane.showMessageDialog(this,
                "Book Issued Successfully");

      }

      catch (NullSelectionException ex) {

        JOptionPane.showMessageDialog(this,
                ex.getMessage());
      }

      catch (InvalidDateException ex) {

        JOptionPane.showMessageDialog(this,
                ex.getMessage());
      }

      catch (Exception ex) {

        JOptionPane.showMessageDialog(this,
                "Use Date Format YYYY-MM-DD");
      }

      finally {

        JOptionPane.showMessageDialog(this,
                "Operation Completed");
      }
    }

    if (e.getSource() == resetButton) {

      issueDateField.setText("");
      returnDateField.setText("");

      categoryBox.setSelectedIndex(0);

      group.clearSelection();

      JOptionPane.showMessageDialog(this,
              "Form Reset Successfully");
    }

    if (e.getSource() == exitButton) {

      int option = JOptionPane.showConfirmDialog(
              this,
              "Are you sure you want to exit?",
              "Exit Confirmation",
              JOptionPane.YES_NO_OPTION);

      if (option == JOptionPane.YES_OPTION) {
        System.exit(0);
      }
    }
  }

  public static void main(String[] args) {

    new LibraryBookIssueSystem();
  }
}