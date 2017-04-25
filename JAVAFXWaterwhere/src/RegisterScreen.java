import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityanadkarni on 4/23/17.
 */
public class RegisterScreen extends VBox {
    private Stage s = Main.getPrimaryStage();
    private LoginScreen loginScreen = LoginScreen.getLoginScreen();
    private Button registerButton = new Button("Register");
    private TextField userNameField = new TextField("Username...");
    private PasswordField passwordField = new PasswordField();
    private Text userName = new Text("Username:");
    private Text password = new Text("Password:");
    private Text spinnerText = new Text("Type of Account:");
    private Spinner<String> spinner;
    private List<String> userTypes = new ArrayList<String>();
    private Text registerText = new Text("Register here!");

    private RegisterScreen(Stage s) {
        super(10);
        userTypes.add("USER");
        userTypes.add("MANAGER");
        userTypes.add("ADMIN");
        userTypes.add("WORKER");
        spinner = new Spinner<>(FXCollections.observableList(userTypes));
        registerButton.setOnAction(event -> {
            Scene scene = new Scene(loginScreen, 500, 500);
            scene.getStylesheets().add("CSS/startscreenstyle.css");
            s.setScene(scene);
        });
        registerText.setId("text");
        this.getChildren().add(registerText);
        this.getChildren().add(userName);
        this.getChildren().add(userNameField);
        this.getChildren().add(password);
        this.getChildren().add(passwordField);
        this.getChildren().add(spinnerText);
        this.getChildren().add(spinner);
        this.getChildren().add(registerButton);

    }

    public static RegisterScreen getRegisterScreen(Stage s) {
        return new RegisterScreen(s);
    }
}
