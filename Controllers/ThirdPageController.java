package GameArithmetic.Controllers;

import GameArithmetic.BestTime;
import GameArithmetic.GameOperations;
import GameArithmetic.Main;
import GameArithmetic.SecondPage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ThirdPageController {

    Timeline timeline;
    LocalTime time;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss");

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_back;

    @FXML
    private Label text_round;

    @FXML
    private Label text_game_stat;

    @FXML
    private Label text_question;

    @FXML
    private TextField text_answer;

    @FXML
    private Button btn_check;

    @FXML
    private Button btn_restart;

    @FXML
    private Label label_info;

    @FXML
    private Label label_time;

    @FXML
    private Label best_time;

    private int countRound = 1;
    private int countWin = 0, countLose = 0;
    private String checkResult;

    BestTime bt = new BestTime();


    @FXML
    void initialize() {

        bt.createMap();
        bt.keySelection();
        best_time.setText(bt.bestTimeFirst());
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> incrementTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        time = LocalTime.parse("00:00");
        label_time.setText(time.format(dtf));
        timeline.play();


        anchor.setOnKeyPressed(event -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
            if (event.getCode() == KeyCode.ENTER) {
                btn_check.arm();
                pause.setOnFinished(e -> {
                    btn_check.disarm();
                    btn_check.fire();
                });
                pause.play();
            }
            if (event.getCode() == KeyCode.E && event.isControlDown()) {
                btn_restart.arm();
                pause.setOnFinished(e -> {
                    btn_restart.disarm();
                    btn_restart.fire();
                });
                pause.play();
            }
            if (event.getCode() == KeyCode.Q && event.isControlDown()) {
                btn_home.arm();
                pause.setOnFinished(e -> {
                    btn_home.disarm();
                    btn_home.fire();
                });
                pause.play();
            }
            if (event.getCode() == KeyCode.W && event.isControlDown()) {
                btn_back.arm();
                pause.setOnFinished(e -> {
                    btn_back.disarm();
                    btn_back.fire();
                });
                pause.play();
            }
        });

        game();

        btn_home.setOnAction(event -> {
            GameOperations.operation = "";
            GameOperations.numberOfDigits = null;
            btn_home.getScene().getWindow().hide();
            try {
                goToFirstPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        btn_back.setOnAction(event -> {
            GameOperations.numberOfDigits = null;
            btn_back.getScene().getWindow().hide();
            try {
                goToSecondPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        text_answer.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        text_answer.setText(oldValue);
                    }
                    checkResult = text_answer.getText();

                }
        );
        btn_check.setOnAction(event -> {
            if (checkEmptyStr()) {
                countRound++;
                if (countRound < 11) {
                    checkResult();
                    game();
                } else if (countRound == 11) {
                    timeline.stop();
                    checkResult();
                    setCountWin();
                    text_answer.setText("");
                    checkWin();
                    btn_check.setDisable(true);
                    text_answer.setDisable(true);
                    text_round.setFocusTraversable(true);


                }
            }
        });
        btn_restart.setOnAction(event -> {

            countRound = 1;
            countWin = 0;
            countLose = 0;
            timeline.stop();
            time = LocalTime.parse("00:00");
            label_time.setText(time.format(dtf));
            timeline.play();

            game();
            setCountWin();
            text_answer.setText("");
            label_info.setText("");
            label_info.setStyle("-fx-text-fill: black");

            btn_check.setDisable(false);
            text_answer.setDisable(false);
            focusTextAnswer();

        });

    }


    public void goToFirstPage() throws Exception {
        Main main = new Main();
        main.showWindow();
    }

    public void goToSecondPage() throws Exception {
        GameArithmetic.SecondPage secondPage = new SecondPage();
        secondPage.showWindow();

    }

    private void game() {
        if (!text_answer.isDisabled()) {
            text_answer.setFocusTraversable(true);
        }

        text_round.setText("Round: " + countRound + "/10");
        GameOperations.randomNumbers();
        GameOperations.calculate();
        text_question.setText(GameOperations.numbers[1] + " " + GameOperations.operation + " " + GameOperations.numbers[0] + " = ?");


    }

    private void setCountWin() {
        text_game_stat.setText("Win: " + countWin + " / " + "Lose: " + countLose);
    }

    private boolean checkEmptyStr() {

        if (checkResult == null) {
            label_info.setText("You need to enter \nthe answer");
            return false;
        } else {
            return true;
        }

    }

    private void checkResult() {
        if (GameOperations.result == Integer.parseInt(checkResult)) {
            countWin++;
            setCountWin();
            label_info.setText("You right!");
            text_answer.setText("");
            checkResult = null;
        } else {

            countLose++;
            setCountWin();
            label_info.setText("You wrong! \nCorrect result: " + GameOperations.result);
            text_answer.setText("");
            checkResult = null;
        }


    }

    private void checkWin() {
        if (countWin > countLose) {
            label_info.setStyle("-fx-text-fill: green");
            label_info.setText("Congratulations, you win!");
            bt.checkTime(time);
            System.out.println("Label_time:" + time);
            best_time.setText(BestTime.bestTime.format(dtf));
        } else {
            label_info.setStyle("-fx-text-fill: red");
            label_info.setText("Sorry, you lose!");
        }
    }

    private void focusTextAnswer() {
        Platform.runLater(() -> text_answer.requestFocus());
    }

    private void incrementTime() {
        time = time.plusSeconds(1);
        label_time.setText(time.format(dtf));
    }
}