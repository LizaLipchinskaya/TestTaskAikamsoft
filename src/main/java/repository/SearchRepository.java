package repository;

import controller.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchRepository {

    private final Connection connection;

    public SearchRepository(Controller controller) {
        connection = controller.getConnection();
    }

    public ArrayList<String[]> searchByLastName(String lastName) {
        ArrayList<String[]> rows = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "select last_name, first_name from customer\n" +
                            "where last_name = '" + lastName + "'");

            while (resultSet.next()) {
                String[] row = {
                        resultSet.getString(1),
                        resultSet.getString(2)
                };
                rows.add(row);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }

    public ArrayList<String[]> searchByMinTimesPurchase(String productName, int minTimes) {
        ArrayList<String[]> rows = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "select last_name, first_name from customer\n" +
                            "join\n" +
                            "(select id_customer, count(id_customer) from purchase\n" +
                            "join product p on p.id = purchase.id_product\n" +
                            "where name = '" + productName + "'\n" +
                            "group by id_customer\n" +
                            "having count(id_customer) = " + minTimes + ") t on id = t.id_customer");

            while (resultSet.next()) {
                String[] row = {
                        resultSet.getString(1),
                        resultSet.getString(2)
                };
                rows.add(row);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }

    public ArrayList<String[]> searchByInterval(int minExpenses, int maxExpenses) {
        ArrayList<String[]> rows = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "select last_name, first_name from customer\n" +
                            "join\n" +
                            "(select id_customer, sum(price) from purchase\n" +
                            "join product p on purchase.id_product = p.id\n" +
                            "group by id_customer\n" +
                            "having sum(price) between " + minExpenses + " and " + maxExpenses + ") t " +
                            "on id = t.id_customer");

            while (resultSet.next()) {
                String[] row = {
                        resultSet.getString(1),
                        resultSet.getString(2)
                };
                rows.add(row);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }

    public ArrayList<String[]> searchBadCustomer(int size) {
        ArrayList<String[]> rows = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "select last_name, first_name from customer\n" +
                            "join\n" +
                            "(select id_customer, count(id_customer) as count_purchase from purchase\n" +
                            " join product p on p.id = purchase.id_product\n" +
                            " group by id_customer\n" +
                            " order by count_purchase\n" +
                            " limit " + size + ") t on id = t.id_customer");

            while (resultSet.next()) {
                String[] row = {
                        resultSet.getString(1),
                        resultSet.getString(2)
                };
                rows.add(row);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }
}
