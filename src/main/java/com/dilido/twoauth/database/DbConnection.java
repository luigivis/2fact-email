package com.dilido.twoauth.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static String driver = "org.mariadb.jdbc.Driver";
    private static String host = "us-cdbr-east-05.cleardb.net";
    private static String port = "3306";
    private static String user = "bda479a7176bec";
    private static String password = "ef95b8dd";
    private static String dbMessage;

    /**
     * Funcion para conectar con el servidor de base de datos
     *
     * @return Connection retornar conexion con la base de datoss
     */
    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://" + host + ":" + port + "/" + getDatabaseName(),
                    user, password);

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            setDbMessage(ex.toString());
            System.out.println(getDbMessage());
        }
        return connection;
    }

    /**
     * Funcion para obtener el mensaje desde la base de datos
     *
     * @return retornar el mensaje de la base de datos
     */
    public static String getDbMessage() {
        return dbMessage;
    }

    /**
     * Funcion para obtener el nombre de la base de datos de produccion o de desarrollo
     * @return String nameDataBase
     */
    public static String getDatabaseName() {
        String database = "heroku_63436d7481e6981";
        return database;
    }

    /**
     * Funcion para guardar el mensaje de la base de datos
     *
     * @param dbMessage
     */
    public static void setDbMessage(String dbMessage) {
        DbConnection.dbMessage = dbMessage;
    }
}