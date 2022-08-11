package parser;

import com.google.gson.Gson;
import controller.Controller;
import dto.stat.CustomerStat;
import dto.stat.Purchase;
import dto.stat.StatDto;
import dto.stat.StatInputDto;
import org.json.simple.parser.ParseException;
import repository.StatRepository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatParser {

    private final StatRepository statRepository;

    public StatParser(Controller controller) {
        statRepository = new StatRepository(controller);
    }

    private List<CustomerStat> createCustomerList(ArrayList<String[]> customers, ArrayList<String[]> totalExpenses) {
        List<CustomerStat> customerList = new ArrayList<>();
        Map<String, List<Purchase>> purchases = new HashMap<>();
        Map<String, Integer> customerAndTotalExpenses = new HashMap<>();

        for (String[] total : totalExpenses) {
            String name = total[1] + ' ' + total[0];
            Integer totalExp = Integer.parseInt(total[2]);
            customerAndTotalExpenses.put(name, totalExp);
        }

        for (String[] customer : customers) {
            String name = customer[1] + ' ' + customer[0];
            String nameProduct = customer[2];
            Integer expenses = Integer.parseInt(customer[3]);

            if (purchases.containsKey(name)) {
                List<Purchase> purchaseList = purchases.get(name);
                purchaseList.add(new Purchase(nameProduct, expenses));
            } else {
                List<Purchase> purchaseList = new ArrayList<>();
                purchaseList.add(new Purchase(nameProduct, expenses));
                purchases.put(name, purchaseList);
            }
        }

        for (String key : customerAndTotalExpenses.keySet()) {
            customerList.add(new CustomerStat(key, purchases.get(key), customerAndTotalExpenses.get(key)));
        }

        return customerList;
    }

    public StatDto parse(File file) throws IOException, ParseException {
        StatDto statDto = new StatDto("stat", 0, new ArrayList<>(), 0, 0.0);
        StatInputDto statInputDto = new Gson().fromJson(new FileReader(file), StatInputDto.class);

        String startDate = statInputDto.getStartDate();
        String endDate = statInputDto.getEndDate();

        int countOfWorkingDay = statRepository.countDay(startDate, endDate);
        ArrayList<String[]> customers = statRepository.findCustomer(startDate, endDate);
        ArrayList<String[]> totalExpensesForCustomer = statRepository.sumPurchaseForCustomer(startDate, endDate);
        int totalExpenses = statRepository.sumPurchase(startDate, endDate);
        double avgExpenses = statRepository.avgPurchase(startDate, endDate);

        statDto.setTotalDays(countOfWorkingDay);
        statDto.setTotalExpenses(totalExpenses);
        statDto.setAvgExpenses(avgExpenses);
        statDto.setCustomers(createCustomerList(customers, totalExpensesForCustomer));

        return statDto;
    }
}
