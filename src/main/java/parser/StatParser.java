package parser;

import controller.Controller;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import repository.StatRepository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class StatParser {

    private final StatRepository statRepository;

    public StatParser(Controller controller) {
        statRepository = new StatRepository(controller);
    }

    public void parse(File file) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));

        LocalDateTime startDate = (LocalDateTime) jsonObject.get("startDate");
        LocalDateTime endDate = (LocalDateTime) jsonObject.get("endDate");

        int countOfWorkingDay = countWorkingDay(startDate, endDate);

    }

    private int countWorkingDay(LocalDateTime start, LocalDateTime end) {
        int count = 0;

        if (start.equals(end)) {
            if (isWorkingDay(start)) {
                count++;
            }
            return count;
        }

        while (start.isBefore(end)) {
            if (isWorkingDay(start)) {
                count++;
            }
            start = start.plusDays(1);
        }

        if (isWorkingDay(end)) {
            count++;
        }

        return count;
    }

    private boolean isWorkingDay(LocalDateTime date) {
        return date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY;
    }
}
