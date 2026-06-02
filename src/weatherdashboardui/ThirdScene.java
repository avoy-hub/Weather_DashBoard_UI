package weatherdashboardui;
import com.google.gson.Gson;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class ThirdScene {
    public void start(Stage stage) {
        Gson gson = new Gson();
        String rangpurJson = WeatherService.getWeather("Rangpur");
        WeatherResponse rangpur =
                gson.fromJson(rangpurJson, WeatherResponse.class);
        String[] cityList = {
                "Dhaka",
                "Chittagong",
                "Khulna",
                "Rajshahi",
                "Sylhet",
                "Barisal",
                "Mymensingh"
        };
        String cityWeather = "";
        for (String city : cityList) {
            String json = WeatherService.getWeather(city);
            WeatherResponse data =
            gson.fromJson(json, WeatherResponse.class);
            cityWeather += data.name
            + ": "
            + data.main.temp
            + "°C\n";
        }
        Label title = new Label("Bangladesh Weather");
        title.setStyle(
           "-fx-font-size:22px;" +
            "-fx-font-weight:bold;" +
              "-fx-text-fill:#2d3436;"
        );
        VBox titleBox = new VBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle(
             "-fx-background-color:#c8f7c5;" +
                "-fx-padding:20;" +
                "-fx-background-radius:20;"
        );
        Label summary = new Label("Division Weather Overview");
        summary.setStyle(
                "-fx-font-size:14px;" +
                "-fx-text-fill:#2d3436;"
        );
        VBox summaryBox = new VBox(summary);
        summaryBox.setAlignment(Pos.CENTER);
        summaryBox.setStyle(
                "-fx-background-color:#eaffea;" +
                "-fx-padding:18;" +
                "-fx-background-radius:20;"
        );
        Label mainCity = new Label(
                rangpur.name + ": "
                        + rangpur.main.temp
                       + "°C"
        );
        mainCity.setStyle(
                "-fx-font-size:18px;" +
                "-fx-text-fill:#2d3436;"
        );
        VBox mainBox = new VBox(mainCity);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setStyle(
        "-fx-background-color:#f1fff1;" +
         "-fx-padding:20;" +
                "-fx-background-radius:20;"
        );
        Label cities = new Label(cityWeather);
        cities.setStyle(
          "-fx-font-size:14px;" +
            "-fx-text-fill:#2d3436;"
        );
        VBox citiesBox = new VBox(cities);
        citiesBox.setAlignment(Pos.CENTER);
        citiesBox.setStyle(
                "-fx-background-color:#f1fff1;" +
                "-fx-padding:20;" +
                "-fx-background-radius:20;"
        );
        VBox root = new VBox(
                15,
                titleBox,
                summaryBox,
                mainBox,
                citiesBox
        );
        root.setAlignment(Pos.CENTER);
        root.setStyle(
                "-fx-background-color:#dfffe0;" +
                "-fx-padding:25;"
        );
        Scene scene = new Scene(root, 430, 500);
        stage.setTitle("Weather Dashboard");
        stage.setScene(scene);
        stage.show();
    }}