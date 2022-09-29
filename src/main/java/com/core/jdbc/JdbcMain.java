package com.core.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
Java DataBase Connectivity
 */
public class JdbcMain {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/javajdbc";
        String user = "root";
        String password = "admin";
        String sql = "SELECT * FROM directions";

        // 1. Conexión a mysql
        Connection connection = DriverManager.getConnection(url, user, password);

        // 2. Crear y ejecutar sentencia
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        var directions = new ArrayList<Direction>();
        // 3. Procesar resultados
        while(resultSet.next()){
            var id = resultSet.getLong("id");
            var street = resultSet.getString("street");
            var postalCode = resultSet.getString("postal_code");
            var province = resultSet.getString("province");
            var country = resultSet.getString("country");

            var direction = new Direction(id, street, postalCode, province, country);
            directions.add(direction);
        }
        System.out.println(directions);


        resultSet.close();
        statement.close();
        connection.close();
    }
}






