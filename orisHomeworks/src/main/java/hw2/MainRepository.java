package hw2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;

public class MainRepository {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "55555";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) throws Exception{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        UserRepository userRepository = new UserRepositoryJdbcImp(connection);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите возраст:");
        int age = scanner.nextInt();
        List<User> usersByAge = userRepository.findAllByAge(age);
        System.out.println("Users with age: " + age);
        for (User user : usersByAge) {
            System.out.println(user.getFirstName() + " " + user.getLastName());
        }
        System.out.print("Введи логин:");
        String login = scanner.next();
        List<User> usersByusername = userRepository.findByusername(login);
        System.out.printf("Пользователи '%s':", login);
        for (User user : usersByusername) {
            System.out.println(user.getFirstName() + " " + user.getLastName());
        }
    }
}