package com.example.loginandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginandroid.Connection.ConnectionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginActivity extends AppCompatActivity {
    EditText cajaUsuario, cajaContraseña;
    TextView lbRegistrar;
    Button btnIngresar;
    Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        cajaUsuario = findViewById(R.id.cajaUsuario);
        cajaContraseña = findViewById(R.id.cajaContraseña);
        lbRegistrar = findViewById(R.id.lbRegistrar);
        btnIngresar = findViewById(R.id.btnIngresar);

        ConnectionBD instanceConnection = new ConnectionBD();
        con = instanceConnection.connect();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new login().execute();
            }
        });

        lbRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(getApplicationContext(), RegistrarActivity.class);
                startActivity(reg);
            }
        });
    }

    public class login extends AsyncTask<Void, Void, String> {
        String z = null;
        Boolean exito = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Void... voids) {
            if (con == null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(loginActivity.this, "Verifique su Conexion", Toast.LENGTH_SHORT).show();
                    }
                });
                z = "En conexion";
            } else {
                try {
                    String user = cajaUsuario.getText().toString();
                    String password = cajaContraseña.getText().toString();
                    String sql = "SELECT * FROM Users WHERE UserL='" + user + "' AND Password='" + password + "'";
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery(sql);

                    if (rs.next()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(loginActivity.this, "Acceso Exitoso", Toast.LENGTH_SHORT).show();
                                Intent menu = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(menu);
                            }
                        });
                        cajaUsuario.setText("");
                        cajaContraseña.setText("");
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(loginActivity.this, "Error en el Usuario O Contraseña", Toast.LENGTH_SHORT).show();
                            }
                        });
                        cajaUsuario.setText("");
                        cajaContraseña.setText("");
                    }
                } catch (Exception e) {
                    exito = false;
                    Log.e("ERROR DE CONEXION", e.getMessage());
                }
            }
            return z;
        }
    }
}
