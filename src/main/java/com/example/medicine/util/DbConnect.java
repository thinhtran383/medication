package com.example.medicine.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnect {
    private static DbConnect instance;

    private final static String jdbcUrl = "jdbc:mysql://localhost:3306/medicine_2";
    private final static String username = "root";
    private final static String password = "Thinh@123";

    private Connection connection;

    private DbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbConnect getInstance() {
        if (instance == null) {
            instance = new DbConnect();
        }
        return instance;
    }

    public List<Object[]> executeQuery(String sql, Object... parameters) {
        List<Object[]> resultList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = createPreparedStatement(sql, parameters);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                resultList.add(row);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public int executeUpdate(String sql, Object... parameters) {
        try {
            PreparedStatement preparedStatement = createPreparedStatement(sql, parameters);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private PreparedStatement createPreparedStatement(String sql, Object... parameters) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < parameters.length; i++) {
            preparedStatement.setObject(i + 1, parameters[i]);
        }

        return preparedStatement;
    }
}
