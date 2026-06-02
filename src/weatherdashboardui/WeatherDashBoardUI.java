
package weatherdashboardui;
import com.google.gson.Gson;
import javafx.scene.control.Label;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WeatherDashBoardUI extends Application {
    
    @Override
    public void start(Stage stage) {
        Label title=new Label("Weather Update");
        title.setStyle("-fx-font-size:25px;"+"-fx-font-weight:bold;");
        
        Label city=new Label();
        city.setStyle("-fx-font-size:22px;"+"-fx-font-weight:bold;");
          Label temp =new Label();
          temp.setStyle("-fx-font-size:35px;"+"-fx-font-weight:bold;"+"-fx-text-fill:darkblue");

        Label humidity =new Label();
        humidity.setStyle("-fx-font-size:20px;"+"-fx-font-weight:bold;"+"-fx-text-fill:green");

        Label condition = new Label();
        condition.setStyle("-fx-font-size:20px;"+"-fx-font-weight:bold;"+"-fx-text-fill:purple");

        Label wind =new Label();
        wind.setStyle("-fx-font-size:20px;"+"-fx-font-weight:bold;"+"-fx-text-fill:brown");
         String json = WeatherService.getWeather("Saidpur");
         System.out.println(json);
        

        Gson gson = new Gson();

        WeatherResponse data =
                gson.fromJson(json, WeatherResponse.class);

        
        city.setText(data.name);

        temp.setText(
                "Temperature : "
                + data.main.temp + "°C"
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

        TextField cityField=new TextField();
        cityField.setPromptText("Enter new city:");
        cityField.setMaxWidth(150);
        Button searchBtn=new Button("Search");
        VBox root=new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color:linear-gradient(to bottom, #74b9ff, #dfe6e9);"+"-fx-padding:20;");
        searchBtn.setStyle("-fx-background-color:green;"+"-fx-text-fill:white;");
        DropShadow shadow=new DropShadow();
        shadow.setRadius(15);
        root.setEffect(shadow);
        
        root.getChildren().addAll(title,city,temp,humidity,condition,wind,cityField,searchBtn);
        Scene scene1=new Scene(root,450,650);
        scene1.setFill(javafx.scene.paint.Color.LIGHTGRAY);
         searchBtn.setOnAction(e -> {

            String newCity =cityField.getText();
            SecondScene second=new SecondScene();
            second.start(stage,newCity);


        });
           stage.setTitle("Weather Dashboard");

        stage.setScene(scene1);

        stage.show();

    }
        
    public static void main(String[] args) {
        launch(args);
    }
}
