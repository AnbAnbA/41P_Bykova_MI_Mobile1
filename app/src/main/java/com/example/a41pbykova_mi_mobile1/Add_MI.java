package com.example.a41pbykova_mi_mobile1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;


public class Add_MI extends AppCompatActivity {

    Connection connection;
    String ConnectionResult="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_mi);
    }

    public void Add(View view) {
        EditText Names=findViewById(R.id.Name);
        EditText Manufacturerss=findViewById(R.id.Manufacturers);
        EditText ManufacturerCountrys=findViewById(R.id.ManufacturerCountry);
        EditText Prices=findViewById(R.id.Price);


        try{
            String Name = Names.getText().toString();
            String Manufacturers = Manufacturerss.getText().toString();
            String ManufacturerCountry = ManufacturerCountrys.getText().toString();
            float Price = Float.parseFloat(Prices.getText().toString());
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if(connection!=null){
                String query = "Insert into Musical_Instrument Values('"+Name+"', '"+Manufacturers+"', '"+ManufacturerCountry+"', "+Price+");";
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                Names.setText("");
                Manufacturerss.setText("");
                ManufacturerCountrys.setText("");
                Prices.setText("");
                Toast.makeText(this, "Запись добавлена!", Toast.LENGTH_LONG).show();
            }
            else{
                ConnectionResult="Check connection";
            }
        }
        catch(Exception ex){
            Log.e(ConnectionResult, ex.getMessage());
            Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show();
        }
    }

    public void Back(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}