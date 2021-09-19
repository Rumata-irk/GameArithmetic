package GameArithmetic.Controllers;

import GameArithmetic.GameOperations;

import GameArithmetic.SecondPage;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class FirstPageController {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private Button btn_addition;

    @FXML
    private Button btn_subtraction;

    @FXML
    private Button btn_multiply;

    @FXML
    void initialize() {
        anchor_pane.setOnKeyPressed(event -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
            if (event.getCode() == KeyCode.ADD || event.getCode() == KeyCode.EQUALS && event.isShiftDown()) {
                btn_addition.arm();
                pause.setOnFinished(e -> {
                    btn_addition.disarm();
                    btn_addition.fire();
                });
                pause.play();
            }
            if (event.getCode() == KeyCode.SUBTRACT || event.getCode() == KeyCode.MINUS) {
                btn_subtraction.arm();
                pause.setOnFinished(e -> {
                    btn_subtraction.disarm();
                    btn_subtraction.fire();
                });
                pause.play();
            }
            if (event.getCode() == KeyCode.MULTIPLY || event.getCode() == KeyCode.DIGIT8 && event.isShiftDown()) {
                btn_multiply.arm();
                pause.setOnFinished(e -> {
                    btn_multiply.disarm();
                    btn_multiply.fire();
                });
                pause.play();
            }

        });

        btn_addition.setOnAction(event -> {
            GameOperations.operation = "+";
            btn_addition.getScene().getWindow().hide();
            try {
                changeWindow();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        btn_subtraction.setOnAction(event -> {
            GameOperations.operation = "-";
            btn_subtraction.getScene().getWindow().hide();
            try {
                changeWindow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        btn_multiply.setOnAction(event -> {
            GameOperations.operation = "*";
            btn_multiply.getScene().getWindow().hide();
            try {
                changeWindow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public void changeWindow() throws Exception {
        GameArithmetic.SecondPage secondPage = new SecondPage();
        secondPage.showWindow();
    }


}

