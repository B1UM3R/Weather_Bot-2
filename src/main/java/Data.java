import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

class Data {
    private int temperature;
    private int feelsLike;
    private int minTemp;
    private int maxTemp;
    private int pressure;
    private int seaLevel;
    private int groundLevel;
    private String weatherIcon;
    private String formattedTemperature;
    private String description;
    private Date date;

    int getTemperature() {
        return temperature;
    }

    String getWeatherIcon() {
        return weatherIcon;
    }

    String getFormattedTemperature() {
        return formattedTemperature;
    }

    String getDescription() {
        return description;
    }

    Date getDate() {
        return date;
    }

    Data(String line, ObjectMapper objectMapper) {
        JsonNode mainNode;
        JsonNode weatherArrNode;
        String dateTime;
        try {
            mainNode = objectMapper.readTree(line).get("main");
            weatherArrNode = objectMapper.readTree(line).get("weather");
            for (final JsonNode objNode : weatherArrNode) {
                dateTime = objectMapper.readTree(line).get("dt_txt").toString();
                formatForecastData(dateTime, objNode.get("main").toString(), mainNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void formatForecastData(String dateTime, String d, JsonNode mainNode) {
        temperature = mainNode.get("temp").asInt();
        feelsLike = mainNode.get("feels_like").asInt();
        minTemp = mainNode.get("temp_min").asInt();
        maxTemp = mainNode.get("temp_max").asInt();
        pressure = mainNode.get("pressure").asInt();
        seaLevel = mainNode.get("sea_level").asInt();
        groundLevel = mainNode.get("grnd_level").asInt();
        date = new Date(dateTime);
        if (temperature > 0) {
            formattedTemperature = "+" + temperature;
        } else {
            formattedTemperature = String.valueOf(temperature);
        }
        description = d.replaceAll("\"", "");
        weatherIcon = Utils.weatherIconsCodes.get(description);
    }
}
