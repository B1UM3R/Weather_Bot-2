import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class Date {
    private int month;
    private int day;
    private String localDateTime;
    private int hour;

    private final DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM-dd HH:mm", Locale.US);

    int getMonth() {
        return month;
    }

    int getDay() {
        return day;
    }

    String getLocalDateTime() {
        return localDateTime;
    }

    int getHour() {
        return hour;
    }

    Date(String dateTime) {
        LocalDateTime parse = LocalDateTime.parse(dateTime.replaceAll("\"", ""), INPUT_DATE_TIME_FORMAT);
        localDateTime = parse.format(OUTPUT_DATE_TIME_FORMAT);
        month = getMonth(localDateTime.substring(0, 3));
        day = Integer.parseInt(localDateTime.substring(4, 6));
        hour = getTime(localDateTime.substring(7, 9));
    }

    private static int getMonth(String month) {
        Map<String, Integer> months = new HashMap<>();
        months.put("Jan", 1);
        months.put("Feb", 2);
        months.put("Mar", 3);
        months.put("Apr", 4);
        months.put("May", 5);
        months.put("Jun", 6);
        months.put("Jul", 7);
        months.put("Aug", 8);
        months.put("Sep", 9);
        months.put("Oct", 10);
        months.put("Nov", 11);
        months.put("Dec", 12);
        return months.get(month);
    }

    private static int getTime(String t) {
        char[] time = t.toCharArray();
        if (time[0] != '0')
            return Integer.parseInt(t);
        return Integer.parseInt(String.valueOf(time[1]));
    }
}
