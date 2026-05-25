package weatherdashboardui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondScene {

    public void start(Stage stage, String cityName) {

        // Labels
        Label title = new Label("City Weather");
        Label city = new Label("City : " + cityName);
        Label temp = new Label("Temperature : -- °C");
        Label humidity = new Label("Humidity : -- %");
        Label condition = new Label("Condition : --");
        Label wind = new Label("Wind Speed : -- m/s");

        // Button
        Button submitBtn = new Button("Submit");

        // Layout
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title, city, temp, humidity, condition, wind, submitBtn);

        // Scene
        Scene scene2 = new Scene(root, 300, 400);

    //    Button Action -> ThirdScene
        submitBtn.setOnAction(e -> {
            ThirdScene third = new ThirdScene();
            third.start(stage);
        });

        stage.setScene(scene2);
        stage.show();
    }
}
