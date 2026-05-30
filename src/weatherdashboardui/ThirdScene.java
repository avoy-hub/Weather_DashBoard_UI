package weatherdashboardui;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Child Class
public class ThirdScene {

    public static class WeatherData {
        private final String cityName;
        private final String temperature;

        public WeatherData(String cityName, String temperature) {
            this.cityName = cityName;
            this.temperature = temperature;
        }

       
        public String getCityName() {
            return cityName;
        }

        public String getTemperature() {
            return temperature;
        }
    }

    public void start(Stage stage) {

       
        Label title = new Label("Bangladesh Weather Report");
        title.setStyle(
                "-fx-font-size:25px;"
                + "-fx-font-weight:bold;"
        );

        
        TableView<WeatherData> table = new TableView<>();
        table.setPrefWidth(400);
        table.setPrefHeight(400);

        
        TableColumn<WeatherData, String> cityColumn = new TableColumn<>("City Name");
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("cityName")); 
        cityColumn.setPrefWidth(190);
         cityColumn.setStyle("-fx-font-weight: bold; -fx-alignment: center-left;");
         
        
        table.setStyle(" -fx-background-color: transparent;-fx-border-color:#b2bec3;");

        TableColumn<WeatherData, String> tempColumn = new TableColumn<>("Temperature");
        tempColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        tempColumn.setPrefWidth(190);
           tempColumn.setStyle("-fx-font-weight: bold; -fx-alignment: center-left;");

        
        table.getColumns().add(cityColumn);
        table.getColumns().add(tempColumn);

        
        ObservableList<WeatherData> dataList = FXCollections.observableArrayList();

        // Cities
        String[] cities = {
            "Dhaka",
            "Rangpur",
            "Rajshahi",
            "Khulna",
            "Sylhet"
        };

        Gson gson = new Gson();

        // LOOP
        for (String cityName : cities) {

            // API CALL
            String json = WeatherService.getWeather(cityName);

           
                // JSON -> OBJECT
                WeatherResponse data = gson.fromJson(json, WeatherResponse.class);

          
                    String tempValue = data.main.temp + "°C";
                    
                    
                    dataList.add(new WeatherData(data.name, tempValue));
                }
            
        table.setItems(dataList);

        // Layout
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle(
                "-fx-background-color:linear-gradient(to bottom, #74b9ff, #dfe6e9);"
                + "-fx-padding:30;"
        );

        root.getChildren().addAll(title, table);

        // Scene
        Scene scene3 = new Scene(root, 450, 650);

        stage.setTitle("Bangladesh Weather");
        stage.setScene(scene3);
        stage.show();
    }
}