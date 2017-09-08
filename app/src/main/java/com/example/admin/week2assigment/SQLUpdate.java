package com.example.admin.week2assigment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.Date;

public class SQLUpdate extends AppCompatActivity {

    int ID;
    EditText Name;
    EditText Date;
    SeekBar Priorty;
    CheckBox Done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlupdate);
        Intent intent = getIntent();
        String id = intent.getStringExtra("KEY_ID");
        ID = Integer.parseInt(id);

        Name = (EditText) findViewById(R.id.ETxtName);
        Date = (EditText) findViewById(R.id.ETxtDate);
        Priorty = (SeekBar) findViewById(R.id.SKPriorty);
        Done = (CheckBox) findViewById(R.id.CHKDone);

        Database DB = new Database(this);
        Tasks task = DB.getTaskById(ID);
        if (task != null) {
            Name.setText(task.getName());
            Date.setText(task.getDueDate());
            Priorty.setProgress(task.getPriorty());
            Done.setChecked(task.Done);
        }
    }

    public void UPDATESQL(View view) {
        Database DB = new Database(this);
        DB.UpdateTask(ID,Name.getText().toString(),Date.getText().toString(),Priorty.getProgress(),Done.isChecked());
        Intent i = new Intent(this,SQLReader.class);
        startActivity(i);
    }

    public void DeleteSQL(View view) {
        Database DB = new Database(this);
        DB.DeleteTask(ID);
        Intent i = new Intent(this,SQLReader.class);
        startActivity(i);

    }
}
