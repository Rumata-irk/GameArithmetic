package GameArithmetic.Controllers;


import GameArithmetic.GameOperations;
import GameArithmetic.Main;
import GameArithmetic.ThirdPage;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SecondPageController {

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_2digit;

    @FXML
    private Button btn_3digit;

    @FXML
    void initialize() {

        anchor.setOnKeyPressed(event -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
            if (event.getCode() == KeyCode.DIGIT2 || event.getCode() == KeyCode.NUMPAD2) {
                btn_2digit.arm();
                pause.setOnFinished(e -> {
                    btn_2digit.disarm();
                    btn_2digit.fire();
                });
                pause.play();
            }
            if (event.getCode() == KeyCode.DIGIT3 || event.getCode() == KeyCode.NUMPAD3) {
                btn_3digit.arm();
                pause.setOnFinished(e -> {
                    btn_3digit.disarm();
                    btn_3digit.fire();
                });
                pause.play();
            }

        });

        btn_home.setOnAction(event -> {
            GameOperations.operation = "";
            btn_home.getScene().getWindow().hide();
            try {
                goToFirstPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        btn_2digit.setOnAction(event -> {
            GameOperations.numberOfDigits = new int[]{10, 99};
            btn_2digit.getScene().getWindow().hide();
            try {
                goToThirdPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        btn_3digit.setOnAction(event -> {
            GameOperations.numberOfDigits = new int[]{10, 999};
            btn_3digit.getScene().getWindow().hide();
            try {
                goToThirdPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void goToFirstPage() throws Exception {
        Main main = new Main();
        main.showWindow();
    }

    public void goToThirdPage() throws Exception {
        GameArithmetic.ThirdPage thirdPage = new ThirdPage();
        thirdPage.showWindow();
    }


}
