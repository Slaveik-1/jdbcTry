package org.example;

import java.sql.*;

public class noPageObject {
    public static void main(String[] args) throws SQLException {
    Connection connection = DriverManager.getConnection(
            "jdbc:h2:tcp://localhost:9092/mem:testdb",
            "user",
            "pass");
        String insertOne = "   INSERT INTO FOOD(food_id, food_name, food_type, food_exotic) VALUES" +
                "(31,'Мандарин','FRUIT',1), " +
                "(32,'Цукини','VEGETABLE',0)";
            String query = "SELECT food_id, food_name, food_type, food_exotic FROM FOOD";
            Statement statement = connection.createStatement();
        statement.executeUpdate(insertOne);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id = resultSet.getInt("food_id");
                String name = resultSet.getString("food_name");
                String type = resultSet.getString("food_type");
                int ex = resultSet.getInt("food_exotic");
                System.out.printf("%d, %s, %s, %d%n", id, name, type, ex);
            }
        System.out.println("а удалиться ли оно вот так ? ");

            statement.executeUpdate("DELETE FROM FOOD WHERE FOOD_ID=31");
        statement.executeUpdate("DELETE FROM FOOD WHERE FOOD_ID=32");
            query = "SELECT * FROM FOOD";

        resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            int id = resultSet.getInt("food_id");
            String name = resultSet.getString("food_name");
            String type = resultSet.getString("food_type");
            int ex = resultSet.getInt("food_exotic");
            System.out.printf("%d, %s, %s, %d%n", id, name, type, ex);
        }
            connection.close();




    }
}
