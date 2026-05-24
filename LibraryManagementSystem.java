import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryManagementSystem extends JFrame implements ActionListener {

  JLabel titleLabel;

  JLabel nameLabel;
  JLabel rollLabel;
  JLabel bookTitleLabel;
  JLabel categoryLabel;
  JLabel issueDateLabel;
  JLabel returnDateLabel;
  JLabel remarksLabel;
  JLabel editionLabel;

  JTextField nameField;
  JTextField rollField;
  JTextField bookTitleField;
  JTextField issueDateField;
  JTextField returnDateField;

  JTextArea remarksArea;

  JComboBox<String> categoryCombo;

  JRadioButton newEditionRadio;
  JRadioButton oldEditionRadio;

  ButtonGroup editionGroup;

  JButton issueButton;
  JButton resetButton;
  JButton exitButton;

  JTextArea outputArea;

  public LibraryManagementSystem() {

    setTitle("Library Book Issue System");
    setSize(900, 550);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Heading
    titleLabel = new JLabel("LIBRARY BOOK ISSUE SYSTEM");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Left Panel
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new GridLayout(9, 2, 10, 10));

    nameLabel = new JLabel("Student Name:");
    nameField = new JTextField();

    rollLabel = new JLabel("Roll Number:");
    rollField = new JTextField();

    bookTitleLabel = new JLabel("Book Title:");
    bookTitleField = new JTextField();

    categoryLabel = new JLabel("Book Category:");

    categoryCombo = new JComboBox<>(new String[]{
            "Programming",
            "AI",
            "Databases",
            "Networking"
    });

    issueDateLabel = new JLabel("Issue Date:");
    issueDateField = new JTextField();

    returnDateLabel = new JLabel("Return Date:");
    returnDateField = new JTextField();

    editionLabel = new JLabel("Book Edition:");

    newEditionRadio = new JRadioButton("New Edition");
    oldEditionRadio = new JRadioButton("Old Edition");

    editionGroup = new ButtonGroup();
    editionGroup.add(newEditionRadio);
    editionGroup.add(oldEditionRadio);

    JPanel radioPanel = new JPanel();
    radioPanel.add(newEditionRadio);
    radioPanel.add(oldEditionRadio);

    remarksLabel = new JLabel("Remarks:");

    remarksArea = new JTextArea(3, 20);

    JScrollPane remarksScroll = new JScrollPane(remarksArea);

    issueButton = new JButton("ISSUE BOOK");
    resetButton = new JButton("RESET");
    exitButton = new JButton("EXIT");

    issueButton.addActionListener(this);
    resetButton.addActionListener(this);
    exitButton.addActionListener(this);

    leftPanel.add(nameLabel);
    leftPanel.add(nameField);

    leftPanel.add(rollLabel);
    leftPanel.add(rollField);

    leftPanel.add(bookTitleLabel);
    leftPanel.add(bookTitleField);

    leftPanel.add(categoryLabel);
    leftPanel.add(categoryCombo);

    leftPanel.add(issueDateLabel);
    leftPanel.add(issueDateField);

    leftPanel.add(returnDateLabel);
    leftPanel.add(returnDateField);

    leftPanel.add(editionLabel);
    leftPanel.add(radioPanel);

    leftPanel.add(remarksLabel);
    leftPanel.add(remarksScroll);

    leftPanel.add(issueButton);
    leftPanel.add(resetButton);

    // Right Panel
    JPanel rightPanel = new JPanel(new BorderLayout());

    JLabel outputLabel = new JLabel("BOOK ISSUE DETAILS");
    outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
    outputLabel.setFont(new Font("Arial", Font.BOLD, 18));

    outputArea = new JTextArea();
    outputArea.setEditable(false);
    outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

    JScrollPane outputScroll = new JScrollPane(outputArea);

    rightPanel.add(outputLabel, BorderLayout.NORTH);
    rightPanel.add(outputScroll, BorderLayout.CENTER);
    rightPanel.add(exitButton, BorderLayout.SOUTH);

    // Main Layout
    setLayout(new BorderLayout(10, 10));

    add(titleLabel, BorderLayout.NORTH);
    add(leftPanel, BorderLayout.WEST);
    add(rightPanel, BorderLayout.CENTER);

    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == exitButton) {

      System.exit(0);
    }

    else if (e.getSource() == resetButton) {

      nameField.setText("");
      rollField.setText("");
      bookTitleField.setText("");
      issueDateField.setText("");
      returnDateField.setText("");
      remarksArea.setText("");

      categoryCombo.setSelectedIndex(0);

      editionGroup.clearSelection();

      outputArea.setText("");
    }

    else if (e.getSource() == issueButton) {

      try {

        String studentName = nameField.getText();
        String rollNumber = rollField.getText();
        String bookTitle = bookTitleField.getText();
        String issueDate = issueDateField.getText();
        String returnDate = returnDateField.getText();
        String remarks = remarksArea.getText();

        if (studentName.isEmpty()) {
          throw new Exception("Enter student name!");
        }

        if (rollNumber.isEmpty()) {
          throw new Exception("Enter roll number!");
        }

        if (bookTitle.isEmpty()) {
          throw new Exception("Enter book title!");
        }

        String category =
                (String) categoryCombo.getSelectedItem();

        String edition;

        if (newEditionRadio.isSelected()) {

          edition = "New Edition";
        }

        else if (oldEditionRadio.isSelected()) {

          edition = "Old Edition";
        }

        else {

          throw new Exception("Select book edition!");
        }

        outputArea.setText(
                "======= BOOK ISSUE DETAILS =======\n\n" +
                        "Student Name : " + studentName + "\n" +
                        "Roll Number  : " + rollNumber + "\n" +
                        "Book Title   : " + bookTitle + "\n" +
                        "Category     : " + category + "\n" +
                        "Issue Date   : " + issueDate + "\n" +
                        "Return Date  : " + returnDate + "\n" +
                        "Edition      : " + edition + "\n" +
                        "Remarks      : " + remarks + "\n"
        );

        JOptionPane.showMessageDialog(this,
                "Book Issued Successfully!");

      }

      catch (Exception ex) {

        JOptionPane.showMessageDialog(this,
                ex.getMessage());
      }
    }
  }

  public static void main(String[] args) {

    new LibraryManagementSystem();
  }
}