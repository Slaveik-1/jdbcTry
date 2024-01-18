package org.example;

import java.sql.*;

public class noPageObject {
    public static void main(String[] args) throws SQLException {
    Connection connection = DriverManager.getConnection(
            "jdbc:h2:tcp://localhost:9092/mem:testdb",
            "user",
            "pass");
    String odinn = "SELECT food_id, food_name, food_type, food_exotic FROM FOOD";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(odinn);
        while (resultSet.next()){
            int id = resultSet.getInt("food_id");
            String name = resultSet.getString("food_name");
            String type = resultSet.getString("food_type");
            int ex = resultSet.getInt("food_exotic");
            System.out.printf("%d, %s, %s, %d%n", id, name, type, ex);
            //Получаем изначальную таблицу
        }
        System.out.println("Очень важный пробел что бы не поплыли глаза на таблицах");
        String insertOne = "   INSERT INTO FOOD(food_name, food_type, food_exotic) VALUES" +
                "('Мандарин','FRUIT',1)," +
                "('Цукини','VEGETABLE',0);";
            String query = "SELECT * FROM FOOD order by food_id desc limit 2";
        statement.executeUpdate(insertOne);
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id = resultSet.getInt("food_id");
                String name = resultSet.getString("food_name");
                String type = resultSet.getString("food_type");
                int ex = resultSet.getInt("food_exotic");
                System.out.printf("%d, %s, %s, %d%n", id, name, type, ex);
                //Добавилось ?
            }
        System.out.println("Очень важный пробел что бы не поплыли глаза на таблицах");

            statement.executeUpdate("DELETE FROM FOOD WHERE FOOD_ID in (SELECT FOOD_ID FROM FOOD ORDER BY FOOD_ID DESC LIMIT 2)");
            query = "SELECT * FROM FOOD";

        resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            int id = resultSet.getInt("food_id");
            String name = resultSet.getString("food_name");
            String type = resultSet.getString("food_type");
            int ex = resultSet.getInt("food_exotic");
            System.out.printf("%d, %s, %s, %d%n", id, name, type, ex);
            //Удалилось ?

        }
            connection.close();




    }
}
