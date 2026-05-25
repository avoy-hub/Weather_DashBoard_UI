
package weatherdashboardui;

import javafx.scene.control.Label;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WeatherDashBoardUI extends Application {
    
    @Override
    public void start(Stage stage) {
        Label title=new Label("Weather App");
        Label city=new Label("Saidpur");
          Label temp =new Label("Temperature : 30°C");

        Label humidity =new Label("Humidity : 75%");

        Label condition = new Label("Condition : Cloudy");

        Label wind =new Label("Wind Speed : 12 km/h");
        TextField cityField=new TextField();
        cityField.setPromptText("Enter new city:");
        Button searchBtn=new Button("Search");
        VBox root=new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title,city,temp,humidity,condition,wind,cityField,searchBtn);
        Scene scene1=new Scene(root,300,400);
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
