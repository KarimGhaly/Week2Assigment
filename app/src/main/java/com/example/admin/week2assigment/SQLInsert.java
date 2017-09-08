package com.example.admin.week2assigment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class SQLInsert extends AppCompatActivity {

    EditText Name;
    EditText Date;
    SeekBar Priorty;
    CheckBox Done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlinsert);

        Name = (EditText) findViewById(R.id.ETxtName);
        Date = (EditText) findViewById(R.id.ETxtDate);
        Priorty = (SeekBar) findViewById(R.id.SKPriorty);
        Done = (CheckBox) findViewById(R.id.CHKDone);
    }

    public void SavetoSQL(View view) {
        Database DB = new Database(this);
        DB.InsertToDB(Name.getText().toString(),Date.getText().toString(),Priorty.getProgress(),Done.isChecked());
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,SQLReader.class);
        startActivity(i);
    }
}
