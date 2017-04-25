import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Created by adityanadkarni on 4/24/17.
 */
public class CreateWaterReportScreen extends VBox{
    private static CreateWaterReportScreen screen = new CreateWaterReportScreen();
    private Text createAReportText = new Text("Create A Water Report!");
    private Text nameText = new Text("Name:");
    private TextField nameTextField = new TextField("Enter your name...");
    private Text dateText = new Text("Date:");
    private TextField dateTimeTextField = new TextField("Enter the date...");
    private Text reportNumberText = new Text("Report Number:");
    private TextField reportNumberTextField = new TextField("Enter your report number...");
    private Text latitudeText = new Text("Latitude:");
    private TextField latitudeTextField = new TextField("Enter your latitude...");
    private Text longitudeText = new Text("Longitude:");
    private TextField longitudeTextField = new TextField("Enter your longitude...");


    private CreateWaterReportScreen() {
        super(10);
        createAReportText.setFont(new Font("Arial", 30));
        createAReportText.setStroke(Color.CORNFLOWERBLUE);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(createAReportText,
                nameText, nameTextField,
                dateText, dateTimeTextField,
                reportNumberText, reportNumberTextField,
                latitudeText, latitudeTextField,
                longitudeText, longitudeTextField);
    }
    public static CreateWaterReportScreen getWaterReportScreen() {
        return screen;
    }
}
