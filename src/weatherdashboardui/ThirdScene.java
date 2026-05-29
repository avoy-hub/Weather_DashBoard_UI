package weatherdashboardui;

import com.google.gson.Gson;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Child Class
public class ThirdScene {

    public void start(Stage stage) {

        // Title
        Label title = new Label("Bangladesh Weather Dashboard");

        title.setStyle(
                "-fx-font-size:25px;"
                + "-fx-font-weight:bold;"
        );

        // Layout
        VBox root = new VBox(15);

        root.setAlignment(Pos.CENTER);

        root.setStyle(
                "-fx-background-color:linear-gradient(to bottom, #74b9ff, #dfe6e9);"
                + "-fx-padding:30;"
        );

        // Add Title
        root.getChildren().add(title);

        // Cities
        String[] cities = {
                "Dhaka",
                "Rangpur",
                "Khulna",
                "Chittagong",
                "Sylhet",
                "Rajshahi",
                "Barisal",
                "Mymensingh"
        };

        Gson gson = new Gson();

        // LOOP
        for (String cityName : cities) {

            // API CALL
            String json =
                    WeatherService.getWeather(cityName);

            // JSON -> OBJECT
            WeatherResponse data =
                    gson.fromJson(
                            json,
                            WeatherResponse.class
                    );

            // Weather Label
            Label weatherLabel = new Label(

                    data.name
                    + " : "
                    + Math.round(data.main.temp)
                    + "°C"
            );

            weatherLabel.setStyle(
                    "-fx-font-size:20px;"
                    + "-fx-font-weight:bold;"
                    + "-fx-text-fill:darkblue;"
            );

            // Add Label
            root.getChildren().add(weatherLabel);
        }

        // Summary
        Label summary = new Label(
                "Summary: Bangladesh weather is mostly warm with scattered clouds across all divisions."
        );

        summary.setStyle(
                "-fx-font-size:15px;"
                + "-fx-font-weight:bold;"
        );

        root.getChildren().add(summary);

        // Scene
        Scene scene = new Scene(root, 400, 500);

        // Stage
        stage.setTitle("Weather Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}