import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by adityanadkarni on 4/24/17.
 */
public class Dashboard extends SplitPane{
    private Button map_button = new Button("Map of Reports");
    private Button create_report = new Button("Create Report");
    private Button reportlist = new Button("List Of Reports");
    private Button settings = new Button("Settings");
    private static Stage s = Main.getPrimaryStage();
    private static Dashboard dash = new Dashboard(s);
    Text dashboardText = new Text("Dashboard");

    private Dashboard(Stage stage) {
        super();
        this.getStylesheets().add("CSS/startscreenstyle.css");
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        dashboardText.setFont(new Font("Arial", 20));
        dashboardText.setStroke(Color.CORNFLOWERBLUE);
        map_button.setOnAction(event -> {
            this.getItems().remove(1);
        });
        create_report.setOnAction(event -> {
            this.getItems().remove(1);
            this.getItems().add(CreateWaterReportScreen.getWaterReportScreen());
        });
        vBox.getChildren().add(dashboardText);
        vBox.getChildren().add(map_button);
        vBox.getChildren().add(create_report);
        vBox.getChildren().add(reportlist);
        vBox.getChildren().add(settings);
        this.getItems().add(vBox);
        this.getItems().add(CreateWaterReportScreen.getWaterReportScreen());
        this.setDividerPositions(0.4);

    }

    public static Dashboard getDashboard() {
        return dash;
    }

}
