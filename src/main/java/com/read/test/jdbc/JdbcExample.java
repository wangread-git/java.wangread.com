package com.read.test.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * Created by yfwangrui on 2015/8/17.
 * <p>
 * jdbc example
 */
public class JdbcExample {

    @Test
    public void test() {
        String url = "jdbc:mysql://192.168.177.46:3306/mobile_app?autoReconnect=true";
        String username = "mobile_app";
        String password = "nopassword";
        Connection connection = null;
        Statement statement = null;  // static sql
        PreparedStatement ps = null; // dynamic sql
        String sql = "insert into cms_clientinfo (client, created, modified) values (?, ?, ?)";
        ResultSet resultSet = null;
        try {
            //step 1.
            //load class com.mysql.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver");

            //step 2.
            //create database connection
            connection = DriverManager.getConnection(url, username, password);

            //step 3.
            //create statement for sql
            statement = connection.createStatement();
            ps = connection.prepareStatement(sql);

            //step 4.
            //execute sql
            resultSet = statement.executeQuery("select * from cms_clientinfo where client = 'android'");
            while (resultSet.next()) {
                int id = resultSet.getInt("clientinfo_id");
                String client = resultSet.getString("client");
                System.out.println(id + ":" + client);
            }
            ps.setString(1, "iwatch");
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //step 5.
            //close result set, statement and connection
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testTransaction() {
        String url = "jdbc:mysql://192.168.177.46:3306/mobile_app?autoReconnect=true";
        String username = "mobile_app";
        String password = "nopassword";
        Connection connection = null;
        PreparedStatement ps1 = null; // dynamic sql
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        String sql = "insert into cms_clientinfo (client, created, modified) values (?, ?, ?)";
        boolean state = false;
        try {
            Savepoint savepoint1 = null;
            Savepoint savepoint2 = null;
            Savepoint savepoint3 = null;
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
                ps1 = connection.prepareStatement(sql);
                ps1.setString(1, "iwatch");
                ps1.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                ps1.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                ps1.executeUpdate();
                savepoint1 = connection.setSavepoint("save-point-1");

                ps2 = connection.prepareStatement(sql);
                ps2.setString(1, "iwatch");
                ps2.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                ps2.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                ps2.executeUpdate();
                savepoint2 = connection.setSavepoint("save-point-2");

                ps3 = connection.prepareStatement(sql);
                ps3.setString(1, "iwatch");
                ps3.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                ps3.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                ps3.executeUpdate();
                savepoint3 = connection.setSavepoint("save-point-3");

                connection.commit();
                state = true;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (!state && connection != null) {
                //roll back to begin
                connection.rollback();
                //roll back to save point
//                    connection.rollback(savepoint1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps3 != null) {
                try {
                    ps3.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
