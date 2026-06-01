package weatherdashboardui;

import com.google.gson.Gson;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondScene {

    public void start(Stage stage, String cityName) {

        // Title
        Label title = new Label("City Weather");

        title.setStyle(
                "-fx-font-size:25px;"
                + "-fx-font-weight:bold;"
        );

        // Labels
        Label city = new Label();

        city.setStyle(
                "-fx-font-size:22px;"
                + "-fx-font-weight:bold;"
        );

        Label temp = new Label();

        temp.setStyle(
                "-fx-font-size:35px;"
                + "-fx-font-weight:bold;"
                + "-fx-text-fill:darkblue;"
        );

        Label humidity = new Label();

        humidity.setStyle(
                "-fx-font-size:20px;"
                + "-fx-font-weight:bold;"
                + "-fx-text-fill:green;"
        );

        Label condition = new Label();

        condition.setStyle(
                "-fx-font-size:20px;"
                + "-fx-font-weight:bold;"
                + "-fx-text-fill:purple;"
        );

        Label wind = new Label();

        wind.setStyle(
                "-fx-font-size:20px;"
                + "-fx-font-weight:bold;"
                + "-fx-text-fill:brown;"
        );

        // API CALL
        String json =
                WeatherService.getWeather(cityName);

        Gson gson = new Gson();

        WeatherResponse data =
                gson.fromJson(json, WeatherResponse.class);

        // UI UPDATE
        city.setText(
                "City : " + data.name
        );

        temp.setText(
                "Temperature : "
                + Math.round(data.main.temp)
                + "°C"
        );

        humidity.setText(
                "Humidity : "
                + data.main.humidity + "%"
        );

        condition.setText(
                "Condition : "
                + data.weather[0].main
        );

        wind.setText(
                "Wind Speed : "
                + data.wind.speed + " km/h"
        );

        // Button
        Button submitBtn = new Button("Submit");

        submitBtn.setStyle(
                "-fx-background-color:green;"
                + "-fx-text-fill:white;"
                + "-fx-font-size:15px;"
        );

        // Layout
        VBox root = new VBox(20);

        root.setAlignment(Pos.CENTER);

 root.setStyle(
    "-fx-background-color:linear-gradient(to bottom, #89F7FE, #66A6FF);"
    + "-fx-padding:30;"
);

        root.getChildren().addAll(
                title,
                city,
                temp,
                humidity,
                condition,
                wind,
                submitBtn
        );

        // Scene
        Scene scene2 = new Scene(root, 450, 650);

        // Button Action
        submitBtn.setOnAction(e -> {

            ThirdScene third = new ThirdScene();

            third.start(stage);

        });

        stage.setScene(scene2);

        stage.show();
    }
}