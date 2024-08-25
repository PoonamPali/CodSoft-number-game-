import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame implements ActionListener {
    private JTextField guessField;
    private JButton guessButton, playAgainButton;
    private JLabel messageLabel, attemptsLabel;
    private int targetNumber;
    private int attempts;
    private Random random;

    public NumberGuessingGame() {
        random = new Random();
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;

        // Set up the GUI
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(45, 45, 45)); // Dark background color
        setLayout(new GridLayout(5, 1));

        // Message Label
        messageLabel = new JLabel("Guess a number between 1 and 100", SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);
        add(messageLabel);

        // Input Field
        guessField = new JTextField();
        guessField.setHorizontalAlignment(JTextField.CENTER);
        guessField.setBackground(new Color(30, 30, 30)); // Dark input field
        guessField.setForeground(Color.WHITE);
        add(guessField);

        // Guess Button
        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        guessButton.setBackground(new Color(60, 60, 60)); // Dark button background
        guessButton.setForeground(Color.WHITE);
        add(guessButton);

        // Attempts Label
        attemptsLabel = new JLabel("Attempts: 0", SwingConstants.CENTER);
        attemptsLabel.setForeground(Color.WHITE);
        add(attemptsLabel);

        // Play Again Button
        playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(e -> resetGame());
        playAgainButton.setBackground(new Color(60, 60, 60)); // Dark button background
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setVisible(false);
        add(playAgainButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;
            attemptsLabel.setText("Attempts: " + attempts);

            if (guess < targetNumber) {
                messageLabel.setText("Too low! Try again.");
            } else if (guess > targetNumber) {
                messageLabel.setText("Too high! Try again.");
            } else {
                messageLabel.setText("Congratulations! You guessed the number.");
                guessButton.setEnabled(false);
                playAgainButton.setVisible(true);
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }

    private void resetGame() {
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;
        attemptsLabel.setText("Attempts: 0");
        messageLabel.setText("Guess a number between 1 and 100");
        guessField.setText("");
        guessButton.setEnabled(true);
        playAgainButton.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGuessingGame::new);
    }
}
