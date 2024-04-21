package com.example.loginandroid;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    TextView cajaUsers,cajaNombre,cajaGmail,cajaTelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cajaUsers=findViewById(R.id.cajaUsers);
        cajaNombre=findViewById(R.id.cajaNombre);
        cajaGmail=findViewById(R.id.cajaGmail);
        cajaTelefono=findViewById(R.id.cajaTelefono);
        QueryUsers();
    }
    public Connection conectionBd(){
        Connection cnn=null;
        try {
            StrictMode.ThreadPolicy politica=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            cnn= DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.5;databaseName=LoginAndroid;user=marceloo;password=123;trustServerCertificate=true;");

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        return cnn;
    }
    public void QueryUsers(){
        try {
            Statement stm=conectionBd().createStatement();
            ResultSet rs =stm.executeQuery("SELECT* FROM Users");
            if(rs.next()){
                cajaUsers.setText(rs.getString(5));
                cajaNombre.setText(rs.getString(2));
                cajaGmail.setText(rs.getString(3));
                cajaTelefono.setText(rs.getString(4));
            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


}