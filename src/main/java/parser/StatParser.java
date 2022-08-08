package parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class StatParser {

    public void parse(File file) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));

        LocalDateTime startDate = (LocalDateTime) jsonObject.get("startDate");
        LocalDateTime endDate = (LocalDateTime) jsonObject.get("endDate");

//        Gson gson = new Gson();
//        JsonReader reader = new JsonReader(new FileReader(file));
//        Statistic statistic = gson.fromJson(reader, Statistic.class);
    }
}
