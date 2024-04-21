package com.example.loginandroid.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import android.os.StrictMode;
import android.util.Log;

public class ConnectionBD {
    private String ip = "192.168.1.5";
    private String usuario = "marceloo";
    private String password = "123";
    private String basedatos = "LoginAndroid";

    public Connection connect() {
        Connection connection = null;
        String connectionURL = null;
        try {
            // Permitir todas las operaciones de red en el hilo principal para el desarrollo
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            // Cargar el controlador JTDS
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

            // Construir la URL de conexión
            connectionURL = "jdbc:jtds:sqlserver://" + this.ip + "/" + this.basedatos + ";user=" + this.usuario + ";password=" + this.password + ";trustServerCertificate=true;";
            connection = DriverManager.getConnection(connectionURL);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
            Log.e("Error de conexión SQL", e.getMessage());
        }
        return connection;
    }
}
