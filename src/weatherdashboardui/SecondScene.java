package weatherdashboardui;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondScene {

    public void start(Stage stage, String cityName) {

        Label title = new Label("City Weather");
        title.setStyle("-fx-font-size:25px; -fx-font-weight:bold;");

        Label city = new Label();
        city.setStyle("-fx-font-size:22px; -fx-font-weight:bold;");

        Label temp = new Label();
        temp.setStyle("-fx-font-size:35px; -fx-font-weight:bold; -fx-text-fill:darkblue;");

        Label humidity = new Label();
        humidity.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-text-fill:green;");

        Label condition = new Label();
        condition.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-text-fill:purple;");

        Label wind = new Label();
        wind.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-text-fill:brown;");

        Label loading = new Label("⏳ Loading weather data...");
        loading.setStyle("-fx-font-size:18px; -fx-text-fill:blue;");
        
        Label error = new Label();
        error.setStyle("-fx-text-fill:red; -fx-font-size:16px; -fx-font-weight:bold;");
        error.setVisible(false);

        Button submitBtn = new Button("Next →");
        submitBtn.setStyle("-fx-background-color:green; -fx-text-fill:white; -fx-font-size:15px;");

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color:linear-gradient(to bottom, #89F7FE, #66A6FF); -fx-padding:30;");
        
        root.getChildren().addAll(title, city, temp, humidity, condition, wind, loading, error, submitBtn);
        
        city.setVisible(false);
        temp.setVisible(false);
        humidity.setVisible(false);
        condition.setVisible(false);
        wind.setVisible(false);
        submitBtn.setVisible(false);
    
        Scene scene2 = new Scene(root, 450, 650);
        
        new Thread(() -> {
            String json = WeatherService.getWeather(cityName);
            
            Platform.runLater(() -> {
                loading.setVisible(false);
                try {
                    Gson gson = new Gson();
                    WeatherResponse data = gson.fromJson(json, WeatherResponse.class);
                    
                    if (data == null || data.name == null || data.name.isEmpty()) {
                        error.setText("❌ City '" + cityName + "' not found!");
                        error.setVisible(true);
                        new Thread(() -> {
                            try {
                                Thread.sleep(2000);
                                Platform.runLater(() -> {
                                    WeatherDashBoardUI firstScene = new WeatherDashBoardUI();
                                    firstScene.start(stage);
                                });
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }).start();
                        return;
                    }
                    
                    city.setText("City : " + data.name);
                    temp.setText("Temperature : " + Math.round(data.main.temp) + "°C");
                    humidity.setText("Humidity : " + data.main.humidity + "%");
                    condition.setText("Condition : " + data.weather[0].main);
                    wind.setText("Wind Speed : " + data.wind.speed + " km/h");
                    
                    city.setVisible(true);
                    temp.setVisible(true);
                    humidity.setVisible(true);
                    condition.setVisible(true);
                    wind.setVisible(true);
                    submitBtn.setVisible(true);
                    error.setVisible(false);
                    
                } catch (JsonSyntaxException ex) {
                    error.setText("❌ City '" + cityName + "' not found!");
                    error.setVisible(true);
                    new Thread(() -> {
                        try { Thread.sleep(2000); } catch (InterruptedException exc) {}
                        Platform.runLater(() -> new WeatherDashBoardUI().start(stage));
                    }).start();
                }
            });
            
        }).start();

        submitBtn.setOnAction(e -> {
            ThirdScene third = new ThirdScene();
            third.start(stage);
        });

        stage.setScene(scene2);
        stage.show();
    }
}