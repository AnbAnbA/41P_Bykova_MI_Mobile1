package com.example.a41pbykova_mi_mobile1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class MainActivity extends AppCompatActivity {
    Connection connection;
    View v;

    List<Mask> data;
    String ConnectionResult ="";
    ListView listView;
    AdapterMask pAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v = findViewById(com.google.android.material.R.id.ghost_view);
        GetTextFormSql(v);
    }

    public void enterMobile() {
        pAdapter.notifyDataSetInvalidated();
        listView.setAdapter(pAdapter);
    }

    public void AddMI(View v){
        startActivity(new Intent(this, Add_MI.class));
    }


    public void GetTextFormSql(View v) {
        data = new ArrayList<Mask>();
        listView = findViewById(R.id.lvData);
        pAdapter = new AdapterMask(MainActivity.this, data);

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




                while (resultSet.next()) {
                    Mask tempMask=new Mask
                            (resultSet.getInt("ID"),
                                    resultSet.getString("name_of_MI"),


                            );
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