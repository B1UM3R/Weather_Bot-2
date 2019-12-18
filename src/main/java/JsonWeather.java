import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

class JsonWeather {
    private List<Data> js;

    List<Data> getJs() {
        return js;
    }

    JsonWeather(List<String> weatherList) {
        js = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (String line : weatherList) {
            js.add(new Data(line, objectMapper));
        }
    }

    String getFormattedData() {
        final StringBuilder sb = new StringBuilder();
        for (Data d : js) {
            sb.append(String.format("%s   %s %s %s%s", d.getDate().getLocalDateTime(), d.getFormattedTemperature(), d.getDescription(),
                    d.getWeatherIcon(), System.lineSeparator()));
        }
        return sb.toString();
    }
}


