/**
 * Created by adityanadkarni on 4/22/17.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Group root = new Group();
//        StartScreen startScreen = StartScreen.getStartScreen(primaryStage);
        Dashboard d = Dashboard.getDashboard();
        Scene s = new Scene (d, 500, 500);
//        Scene s = new Scene(startScreen, 500, 500);
//        startScreen.getStylesheets().add("CSS/startscreenstyle.css");

        primaryStage.setScene(s);
        primaryStage.setTitle("Waterwhere");
        primaryStage.setResizable(false);
        primaryStage.show();
        LoginScreen loginScreen = LoginScreen.getLoginScreen();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

}
