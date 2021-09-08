package nbguesser.panda.org;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXCheckBox;
import io.github.palexdev.materialfx.controls.MFXLabel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.concurrent.ThreadLocalRandom;

public class guesserController {

    int attempts = 10;
    int randomNum, hint1, hint2;

    @FXML
    Pane gamePane;
    @FXML
    Pane rulesPane;
    @FXML
    Pane mainMenuPane;
    @FXML
    Pane resultsPane;
    @FXML
    Button btnGo;
    @FXML
    TextField guessField;
    @FXML
    Label hintsLabel;
    @FXML
    Label resultLabel;
    @FXML
    MFXLabel statusLabel;
    @FXML
    MFXLabel attemptsLabel;
    @FXML
    JFXCheckBox hintsBox;

    public void playButtonClicked() {

        new ZoomIn(gamePane).setSpeed(10).play();
        randomNum = ThreadLocalRandom.current().nextInt(1, 50 + 1);
        hint1 = ThreadLocalRandom.current().nextInt(1, 15 + 1);
        hint2 = ThreadLocalRandom.current().nextInt(1, 15 + 1);

        attempts = 10;
        attemptsLabel.setText("Attempts left: " + attempts);
        attemptsLabel.setTextFill(Color.WHITE);
        hintsLabel.setText("");
        statusLabel.setText("Take a guess!");
        statusLabel.setTextFill(Color.GREEN);

        System.out.println(randomNum);
        gamePane.toFront();
    }

    public void rulesButtonClicked() {

        new ZoomIn(rulesPane).setSpeed(10).play();
        rulesPane.toFront();
    }

    public void okButtonClicked() {

        new ZoomIn(mainMenuPane).setSpeed(10).play();
        mainMenuPane.toFront();
    }

    public void goButtonClicked() {

        int guessedNb = Integer.parseInt(guessField.getText());

        if(guessedNb == randomNum) {
            statusLabel.setText("Your guess is correct!");
            statusLabel.setTextFill(Color.GREEN);
        }
        if(guessedNb > randomNum) {
            statusLabel.setText("Your guess is larger than the correct answer.");
            statusLabel.setTextFill(Color.BLUE);
        }
        if(guessedNb < randomNum) {
            statusLabel.setText("Your guess is smaller than the correct answer.");
            statusLabel.setTextFill(Color.BLUE);
        }
        if(guessedNb != randomNum) {
            attempts--;
            attemptsLabel.setText("Attempts left: " + attempts);
            if(attempts < 3) {
                attemptsLabel.setTextFill(Color.RED);
            }
            if(attempts > 3) {
                attemptsLabel.setTextFill(Color.WHITE);
            }
        }
        if(hintsBox.isSelected()) {
            if(attempts <= 7) {
                int plus = randomNum + hint1;
                int minus = randomNum - hint2;

                hintsLabel.setText("Hint: The smaller range is from " + plus + " to " + minus);
            }
        }
        if(attempts == 0) {
            new ZoomIn(resultsPane).setSpeed(10).play();
            resultLabel.setText("You Lost!");
            resultsPane.toFront();
        }
        if(randomNum == guessedNb) {
            new ZoomIn(resultsPane).setSpeed(10).play();
            resultLabel.setText("You Won!");
            resultsPane.toFront();
        }
    }

    public void noButtonClicked() {
        boolean result = AlertBox.yesPopup("Guess That Number!", "Are you sure you want to exit?");
        if(result) {
            System.exit(0);
        }
    }
}
