package GameArithmetic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ThirdPage extends Application {



    Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/thirdPage.fxml"));
        primaryStage.setTitle("Game arithmetic");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

    public void showWindow() throws Exception {
        start(stage);
    }
}
