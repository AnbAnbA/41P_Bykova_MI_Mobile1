package com.example.a41pbykova_mi_mobile1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class MainActivity extends AppCompatActivity {
    Connection connection;
    String ConnectionResult ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //GetTextFormSql();
    }



    public void AddMI(View v){
        startActivity(new Intent(this, Add_MI.class));
    }


    public void GetTextFormSql(View view) {
        TableLayout MI = findViewById(R.id.tbMI);


        try {
            ConnectionHelper connectionHelper=new ConnectionHelper();
            connection=connectionHelper.connectionClass();
            /*MI.removeAllViews();
            TableRow trMI = new TableRow(MainActivity.this);
            TextView NameMI = new TextView(MainActivity.this);
            TextView ManufacturersMI = new TextView(MainActivity.this);
            TextView ManufacturerCountryMI = new TextView(MainActivity.this);
            TextView PriceMI = new TextView(MainActivity.this);
            NameMI.setText("Название музыкального инструмента");
            ManufacturersMI.setText("Производитель музыкального инструмента");
            ManufacturerCountryMI.setText("страна производителя");
            PriceMI.setText("Стоимость");
            trMI.addView(NameMI, new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.3f));
            trMI.addView(ManufacturersMI, new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.3f));
            trMI.addView(ManufacturerCountryMI, new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.3f));
            trMI.addView(PriceMI, new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.3f));
            MI.addView(trMI);*/

            if(connection!=null) {
                String query = "Select *From Musical_Instrument";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                TableLayout tbMI = findViewById(R.id.tbMI);
                tbMI.removeAllViews();

                while (resultSet.next()) {
                    TableRow tr = new TableRow(this);
                    tr.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TableRow.LayoutParams params = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    TextView Name = new TextView(this);
                    params.weight = 3.0f;
                    Name.setLayoutParams(params);
                    Name.setText(resultSet.getString(2));
                    tr.addView(Name);


                    TextView Manufacturers = new TextView(this);
                    params.weight = 3.0f;
                    Manufacturers.setLayoutParams(params);
                    Manufacturers.setText(resultSet.getString(2));
                    tr.addView(Manufacturers);

                     /*/*TextView ManufacturerCountry = new TextView(this);
                    params.weight = 3.0f;
                    ManufacturerCountry.setLayoutParams(params);
                    ManufacturerCountry.setText(resultSet.getString(2));
                    tr.addView(ManufacturerCountry);

                    TextView Price = new TextView(this);
                    params.weight = 3.0f;
                    Price.setLayoutParams(params);
                    Price.setText(resultSet.getString(2));
                    tr.addView(Price);*/

                    MI.addView(tr);


                }
            }
            else{
                ConnectionResult="Check Connection";
            }
        }
        catch(Exception ex){
            Log.e(ConnectionResult, ex.getMessage());
        }
    }
}