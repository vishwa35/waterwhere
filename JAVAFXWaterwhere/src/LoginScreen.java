import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by adityanadkarni on 4/23/17.
 */
public class LoginScreen extends VBox{
    private Button button = new Button("Login");
    private TextField usernameTextField = new TextField("Username...");
    private PasswordField passTextField = new PasswordField();
    private Text usernameText = new Text("Username");
    private Text passWordText = new Text("Password");
    private Text loginText = new Text("Login to WaterWhere!");
    private static LoginScreen login = new LoginScreen();

    private LoginScreen() {
        super(10);
        this.setAlignment(Pos.CENTER);
        loginText.setId("text");
        this.getChildren().add(loginText);
        this.getChildren().add(usernameText);
        this.getChildren().add(usernameTextField);
        this.getChildren().add(passWordText);
        this.getChildren().add(passTextField);
        this.getChildren().add(button);
    }

    public static LoginScreen getLoginScreen() {
        return login;
    }
}
