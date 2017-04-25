
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by adityanadkarni on 4/22/17.
 */
public class StartScreen extends GridPane {
    private Button login = new Button("Login");
    private Button register = new Button("Register");
    private Text t = new Text("WaterWhere!");
    private String switchScreens = "";
    private LoginScreen loginScreen = LoginScreen.getLoginScreen();
    private Main m = new Main();
    private RegisterScreen registerScreen = RegisterScreen.getRegisterScreen(m.getPrimaryStage());


    private StartScreen(Stage s) {
        super();
        VBox v = new VBox(10);
        login.setOnMouseClicked(event -> {
            Scene scene = new Scene(loginScreen, 500, 500);
            scene.getStylesheets().add("CSS/startscreenstyle.css");
            s.setScene(scene);

        });
        register.setOnMouseClicked(event -> {
            Scene scene = new Scene(registerScreen, 500, 500);
            scene.getStylesheets().add("CSS/startscreenstyle.css");
            s.setScene(scene);
        });
        this.setAlignment(Pos.CENTER);
        t.setId("text");

        v.getChildren().add(t);
        v.getChildren().add(login);
        v.getChildren().add(register);
        this.getChildren().add(v);

    }

    public static StartScreen getStartScreen(Stage stage) {
        return new StartScreen(stage);
    }

    public String getSwitchScreens() {
        return switchScreens;
    }

}
