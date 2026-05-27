/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weatherdashboardui;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {
     private static final String API_KEY = "f68d1ea7635dd625fae6fbbe6cefffcd";

    public static String getWeather(String city) {

        try {
           

            String url =
                    "https://api.openweathermap.org/data/2.5/weather?q="
                    + city+",BD"
                    + "&appid="
                    + API_KEY
                    + "&units=metric";

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request;
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response;
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {

            return null;
        }
    }
}

  //  static String getWeather(String saidpur) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  //  }


