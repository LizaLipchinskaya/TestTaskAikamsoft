package parser;

import com.google.gson.JsonSyntaxException;
import controller.Controller;
import dto.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import repository.SearchRepository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchParser {

    private final SearchRepository searchRepository;

    public SearchParser(Controller controller) {
        searchRepository = new SearchRepository(controller);
    }

    private List<Customer> createCustomerList(ArrayList<String[]> customers) {
        List<Customer> customerList = new ArrayList<>();

        for(String[] customer : customers) {
            customerList.add(new Customer(customer[0], customer[1]));
        }

        return customerList;
    }

    public SearchDto parse(File file) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
        JSONArray jsonArray = (JSONArray) jsonObject.get("criterias");
        SearchDto searchDto = new SearchDto("criterias", new ArrayList<>());

        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) o;

            if (object.containsKey("lastName")) {
                String lastName = object.get("lastName").toString();

                ArrayList<String[]> customers = searchRepository.searchByLastName(lastName);
                searchDto.getResults().add(new Result(new LastNameCriteria(lastName), createCustomerList(customers)));
            } else if (object.containsKey("productName") && object.containsKey("minTimes")) {
                String name = object.get("productName").toString();
                int min = Integer.parseInt(object.get("minTimes").toString());

                ArrayList<String[]> customers = searchRepository.searchByMinTimesPurchase(name, min);
                searchDto.getResults().add(new Result(new ProductNameCriteria(name, min), createCustomerList(customers)));
            } else if (object.containsKey("minExpenses") && object.containsKey("maxExpenses")) {
                int min = Integer.parseInt(object.get("minExpenses").toString());
                int max = Integer.parseInt(object.get("maxExpenses").toString());

                ArrayList<String[]> customers = searchRepository.searchByInterval(min, max);
                searchDto.getResults().add(new Result(new MinMaxExpensesCriteria(min, max), createCustomerList(customers)));
            } else if (object.containsKey("badCustomers")) {
                int size = Integer.parseInt(object.get("badCustomers").toString());

                ArrayList<String[]> customers = searchRepository.searchBadCustomer(size);
                searchDto.getResults().add(new Result(new BadCustomerCriteria(size), createCustomerList(customers)));
            } else {
                throw new JsonSyntaxException("Json содержит синтаксические ошибки");
            }
        }
        return searchDto;
    }
}
