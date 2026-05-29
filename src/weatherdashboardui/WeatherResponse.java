/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weatherdashboardui;


public class WeatherResponse {
      Main main;
    Weather[] weather;
    Wind wind;
    String name;
}

class Main {
    double temp;
    int humidity;
}

class Weather {
    String main;
}

class Wind {
    double speed;
}
