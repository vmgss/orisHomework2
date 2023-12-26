package hw2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImp implements UserRepository {
    private Connection connection;

    private static final String SQL_SELECT_ALL_FROM_USERS = "select * from users";

    public UserRepositoryJdbcImp(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<User> findAllByAge(Integer age) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_USERS);
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")
                );
                if (user.getAge().equals(age)) {
                    result.add(user);
                }
                if (result.isEmpty()) {
                    System.out.println("Пользователь с данным возрастом не найден");
                }
                for (int i = 0; i < result.size(); i++) {
                    System.out.println(user.getFirstName() + " " + user.getLastName());
                }
                return result;
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    @Override
    public List<User> findByusername(String username) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")
                );
                user.setUsername(resultSet.getString("username"));

                result.add(user);
            }

            if (result.isEmpty()) {
                System.out.println("Пользователь с таким именем пользователя не найден");
            }

            for (User user : result) {
                System.out.println(user.getFirstName() + " " + user.getLastName());
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}