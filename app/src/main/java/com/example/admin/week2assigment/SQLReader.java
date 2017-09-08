package com.example.admin.week2assigment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.List;

public class SQLReader extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlreader);
        listView = (ListView) findViewById(R.id.list_view);
        Database DB = new Database(this);
        List<Tasks> tasksList;
        tasksList = DB.ReadFromDatabase();
        List<String> taskListStrings = new ArrayList<String>();

        if (tasksList.size() > 0) {
            for (Tasks t : tasksList) {
                taskListStrings.add(t.toString());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    taskListStrings);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String Item = listView.getItemAtPosition(position).toString();
                    String Id = Item.substring(4,8).trim();
                    Intent i = new Intent(SQLReader.this,SQLUpdate.class);
                    i.putExtra("KEY_ID",Id);
                    startActivity(i);
                }
            });
        } else {
            Toast.makeText(this, "Empty Database", Toast.LENGTH_LONG).show();
        }

    }

    public void InsertSQL(View view) {
        Intent intent = new Intent(this,SQLInsert.class);
        startActivity(intent);
    }
}
