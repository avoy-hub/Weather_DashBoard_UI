package weatherdashboardui;

import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Child Class
public class ThirdScene extends WeatherDashBoardUI {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Bangladesh Weather Dashboard");

        Label dhaka = new Label("Dhaka : 30°C");
        Label rangpur = new Label("Rangpur : 28°C");
        Label khulna = new Label("Khulna : 31°C");
        Label chittagong = new Label("Chittagong : 29°C");
        Label sylhet = new Label("Sylhet : 27°C");
        Label rajshahi = new Label("Rajshahi : 32°C");
        Label barisal = new Label("Barisal : 30°C");
        Label mymensingh = new Label("Mymensingh : 28°C");

        TextField cityField = new TextField();
        cityField.setPromptText("Enter Division Name");

        Button searchBtn = new Button("Search");

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                title,
                dhaka,
                rangpur,
                khulna,
                chittagong,
                sylhet,
                rajshahi,
                barisal,
                mymensingh,
                cityField,
                searchBtn
        );

        searchBtn.setOnAction(e -> {

            String division = cityField.getText();

            title.setText("Searching Weather for : " + division);

        });

        Scene scene = new Scene(root, 400, 500);

        stage.setTitle("Weather Dashboard");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}