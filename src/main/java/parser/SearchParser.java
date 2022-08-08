package parser;

import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import repository.SearchRepository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SearchParser {

    private SearchRepository searchRepository;

    public void parse(File file) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
        JSONArray jsonArray = (JSONArray) jsonObject.get("criterias");

        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) o;

            if (object.containsKey("lastName")) {
                String lastName = object.get("lastName").toString();
                ArrayList<String[]> customers = searchRepository.searchByLastName(lastName);
                //...
            } else if (object.containsKey("productName")) {
                String name = object.get("productName").toString();
                String min = object.get("minTimes").toString();
                //...
            } else if (object.containsKey("minExpenses")) {
                String min = object.get("minExpenses").toString();
                String max = object.get("maxExpenses").toString();
                //...
            } else if (object.containsKey("badCustomers")) {
                String size = object.get("badCustomers").toString();
                //...
            }
        }
    }
}
