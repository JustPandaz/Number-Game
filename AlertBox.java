package nbguesser.panda.org;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXLabel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    static boolean answer = false;

    public static void okPopup(String title, String message) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.getIcons().add(new Image("/exclamation.png"));
        window.setResizable(false);
        window.setMinWidth(300);

        MFXLabel messageLabel = new MFXLabel();
        messageLabel.setText(message);
        MFXButton okButton = new MFXButton("Ok");

        okButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(messageLabel, okButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 100, 100);
        window.setScene(scene);
        window.showAndWait();
    }

    public static boolean yesPopup(String title, String message) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setResizable(false);
        window.getIcons().add(new Image("/question.png"));
        window.setMinWidth(300);

        MFXLabel messageLabel = new MFXLabel();
        messageLabel.setText(message);
        MFXButton yesButton = new MFXButton("Yes");
        MFXButton noButton = new MFXButton("No");

        yesButton.setOnAction(e ->  {

            window.close();
            answer = true;
        });
        noButton.setOnAction(e -> window.close());

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(yesButton, noButton);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(messageLabel, hbox);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 100, 100);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
