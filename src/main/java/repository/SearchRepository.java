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
                            "where last_name = '" + lastName +"'");

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
