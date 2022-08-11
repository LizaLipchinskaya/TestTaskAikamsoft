package repository;

import controller.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StatRepository {

    private final Connection connection;

    public StatRepository(Controller controller) {
        connection = controller.getConnection();
    }

    public int countDay(String start, String end) {
        int sum = 0;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "select sum((extract(dow from all_days) between 1 and 5)::int)\n" +
                            "from generate_series('" + start + "', '" + end + "', interval '1 day') as all_days");

            while (resultSet.next()) {
                sum = resultSet.getInt(1);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sum;
    }

    public ArrayList<String[]> findCustomer(String start, String end) {
        ArrayList<String[]> rows = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "select customer.first_name, customer.last_name, product.name, sum(product.price) as sum_price " +
                            "from purchase\n" +
                            "inner join customer ON customer.id = purchase.id_customer\n" +
                            "inner join product ON product.id = purchase.id_product\n" +
                            "where purchase.date_purchase between '" + start + "' and '" + end + "'\n" +
                            "  and extract(isodow from purchase.date_purchase) not in (6,7)\n" +
                            "group by customer.first_name, customer.last_name, product.name\n" +
                            "order by sum_price");

            while (resultSet.next()) {
                String[] row = {
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        String.valueOf(resultSet.getInt(4))
                };
                rows.add(row);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }

    public ArrayList<String[]> sumPurchaseForCustomer(String start, String end) {
        ArrayList<String[]> rows = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "select customer.first_name, customer.last_name, sum(product.price) as sum_price from purchase\n" +
                            "inner join customer ON customer.id = purchase.id_customer\n" +
                            "inner join product ON product.id = purchase.id_product\n" +
                            "where purchase.date_purchase between '" + start + "' and '" + end + "'\n" +
                            "  and extract(isodow from purchase.date_purchase) not in (6,7)\n" +
                            "group by customer.first_name, customer.last_name\n" +
                            "order by sum_price");

            while (resultSet.next()) {
                String[] row = {
                        resultSet.getString(1),
                        resultSet.getString(2),
                        String.valueOf(resultSet.getInt(3))
                };
                rows.add(row);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }

    public int sumPurchase(String start, String end) {
        int sum = 0;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "select sum(product.price)\n" +
                            "from purchase\n" +
                            "inner join customer ON customer.id = purchase.id_customer\n" +
                            "inner join product ON product.id = purchase.id_product\n" +
                            "where purchase.date_purchase between '" + start + "' and '" + end + "'\n" +
                            "  and extract(isodow from purchase.date_purchase) not in (6,7)");

            while (resultSet.next()) {
                sum = resultSet.getInt(1);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sum;
    }

    public double avgPurchase(String start, String end) {
        double avg = 0.0;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "select avg(product.price) from purchase\n" +
                            "inner join customer ON customer.id = purchase.id_customer\n" +
                            "inner join product ON product.id = purchase.id_product\n" +
                            "where purchase.date_purchase between '" + start + "' and '" + end + "'\n" +
                            "  and extract(isodow from purchase.date_purchase) not in (6,7)");

            while (resultSet.next()) {
                avg = resultSet.getDouble(1);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avg;
    }
}
