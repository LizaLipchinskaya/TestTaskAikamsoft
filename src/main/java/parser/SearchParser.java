package parser;

import controller.Controller;
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

    private final SearchRepository searchRepository;

    public SearchParser(Controller controller) {
        searchRepository = new SearchRepository(controller);
    }

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
                int min = Integer.parseInt(object.get("minTimes").toString());
                ArrayList<String[]> customers = searchRepository.searchByMinTimesPurchase(name, min);
                //...
            } else if (object.containsKey("minExpenses")) {
                int min = Integer.parseInt(object.get("minExpenses").toString());
                int max = Integer.parseInt(object.get("maxExpenses").toString());
                ArrayList<String[]> customers = searchRepository.searchByInterval(min, max);
                //...
            } else if (object.containsKey("badCustomers")) {
                int size = Integer.parseInt(object.get("badCustomers").toString());
                ArrayList<String[]> customers = searchRepository.searchBadCustomer(size);
                //...
            }
        }
    }
}
