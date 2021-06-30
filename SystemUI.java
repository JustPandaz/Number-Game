import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SystemUI {

    JFrame frame;
    JButton btnPlay, btnExit, btnCheck;
    JLabel rulesLabel, rules2Label, mainLabel, hintLabel, hint2Label, attemptsLabel, statusLabel, proximityLabel;
    JTextField inputField;

    int attemptsLeft = 10, randomHint, randomHint2, randomInt, input, above, below;

    SystemUI() {

        frame = new JFrame("Number Game");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Gaming MSI\\Pictures\\Saved Pictures\\download (1).png");
        frame.setIconImage(icon);
        frame.setSize(500, 300);

        UISetup();

        frame.setVisible(true);
    }

    void UISetup() {

        btnPlay = new JButton("Play");
        btnPlay.setBounds(120, 190, 80, 60);

        btnExit = new JButton("Exit");
        btnExit.setBounds(260, 190, 80, 60);

        btnCheck = new JButton("Check");
        btnCheck.setBounds(120, 50, 80, 25);

        rulesLabel = new JLabel("Rules: A random number from 1 till 50 will be generated.");
        rulesLabel.setBounds(10, 10, 500, 100);

        rules2Label = new JLabel("You have 10 tries to guess that number.");
        rules2Label.setBounds(48, 23, 500, 100);

        mainLabel = new JLabel("Guess the number!");
        mainLabel.setBounds(120, 1, 500, 70);

        attemptsLabel = new JLabel("");
        attemptsLabel.setBounds(340, 13, 100, 100);

        statusLabel = new JLabel("");
        statusLabel.setBounds(120, 50, 400, 100);

        hintLabel = new JLabel("");
        hintLabel.setBounds(120, 70, 400, 100);

        hint2Label = new JLabel("");
        hint2Label.setBounds(120, 83, 400, 100);

        proximityLabel = new JLabel("");
        proximityLabel.setBounds(120, 100, 400, 100);

        inputField = new JTextField();
        inputField.setBounds(210, 50, 125, 25);

        btnClicks();

        frame.add(rulesLabel);
        frame.add(rules2Label);
        frame.add(btnExit);
        frame.add(btnPlay);
    }

    void btnClicks() {

        btnPlay.addActionListener(e -> runGame());
        btnCheck.addActionListener(e -> checks());
        btnExit.addActionListener(e -> {

            int result = JOptionPane.showConfirmDialog(frame,"Are you sure want to exit?", "Exit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if(result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    void fixes(int i) {

        if(i == 1) {

            attemptsLeft = 10;

            attemptsLabel.setText("Attempts Left: " + attemptsLeft);
            inputField.setText("");

            statusLabel.setText("");
            hintLabel.setText("");
            hint2Label.setText("");
            proximityLabel.setText("");
        }

        if(i == 2) {

            for(ActionListener al : btnCheck.getActionListeners() ) {
                btnCheck.removeActionListener(al);
            }

            attemptsLabel.setText("");
            statusLabel.setText("");
            hintLabel.setText("");
            hint2Label.setText("");
            proximityLabel.setText("");

            btnCheck.addActionListener(e -> checks());
        }
    }

    void runGame() {

        fixes(2);

        double randomDouble = Math.random();
        double randomDouble2 = Math.random();
        double randomDouble3 = Math.random();

        randomDouble = randomDouble * 50 + 1;
        randomInt = (int) randomDouble;

        randomDouble2 = randomDouble2 * (15 - 5 + 1) + 5;
        randomHint = (int) randomDouble2;

        randomDouble3 = randomDouble3 * (15 - 5 + 1) + 5;
        randomHint2 = (int) randomDouble3;

        System.out.println("Answer: " + randomInt);

        attemptsLabel.setText("Attempts Left: " + attemptsLeft);

        rulesLabel.setVisible(false);
        rules2Label.setVisible(false);

        frame.add(statusLabel);
        frame.add(hintLabel);
        frame.add(hint2Label);
        frame.add(attemptsLabel);
        frame.add(inputField);
        frame.add(mainLabel);
        frame.add(btnCheck);
        frame.add(proximityLabel);
    }

    void checks() {

        attemptsLabel.setText("Attempts Left: " + attemptsLeft);

        input = Integer.parseInt(inputField.getText());

        if(input == randomInt) {

            statusLabel.setText("You have guessed the correct number!");
            statusLabel.setForeground(Color.GREEN);

            hintLabel.setText("");
            hint2Label.setText("");
            proximityLabel.setText("");
        } else {

            statusLabel.setText("Your guess was wrong! Try again.");
            statusLabel.setForeground(Color.RED);

            attemptsLeft--;

            attemptsLabel.setText("Attempts Left: " + attemptsLeft);
        }

        if(input < randomInt) {

            proximityLabel.setText("Your answer is smaller than the correct answer");
        } if(input > randomInt) {

            proximityLabel.setText("Your answer is larger than the correct answer");
        }

        if(attemptsLeft == 7) {

            above = randomInt + randomHint;
            below = randomInt - randomHint2;

            if(above > 50) {
                above = 50;
            }
            if(below < 1) {
                below = 1;
            }

            hintLabel.setText("Hint: A lower rage has been generated.");
            hint2Label.setText("The lowered range is from " + above + " to " + below);
        }

        if(attemptsLeft == 0) {

            hintLabel.setText("You have ran out of attempts");
            hintLabel.setForeground(Color.red);
            hint2Label.setText("The game is now over. ");
            hint2Label.setForeground(Color.red);

            int result = JOptionPane.showConfirmDialog(frame,"Do you want to play again?", "Game Over",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION) {

                for(ActionListener al : btnCheck.getActionListeners() ) {
                    btnCheck.removeActionListener(al);
                }

                fixes(1);
                runGame();
            } else {

                System.exit(0);
            }
        }
    }
}
