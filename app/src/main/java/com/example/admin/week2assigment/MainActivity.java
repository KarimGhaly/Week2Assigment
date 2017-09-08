package com.example.admin.week2assigment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLTransactionRollbackException;

public class MainActivity extends AppCompatActivity {

    EditText TXTName;
    EditText TXTPhone;
    EditText TXTDOB;
    TextView TxtViewName;
    TextView TxtViewPhone;
    TextView TxtViewDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TXTName = (EditText) findViewById(R.id.TXTName);
        TXTPhone = (EditText) findViewById(R.id.TXTPhone);
        TXTDOB = (EditText) findViewById(R.id.TXTDOB);
        TxtViewName = (TextView) findViewById(R.id.TxtViewName);
        TxtViewPhone = (TextView) findViewById(R.id.TxtViewPhone);
        TxtViewDOB = (TextView) findViewById(R.id.TxtViewDOB);
    }

    public void SavetoFile(View view) {
        File file = new File(this.getFilesDir(), "Information");
        try {
            file.createNewFile();
            FileOutputStream ofile = new FileOutputStream(file, false);
            String Txt = TXTName.getText().toString() + "\n";
            Txt += TXTPhone.getText().toString() + "\n";
            Txt += TXTDOB.getText().toString();
            ofile.write(Txt.getBytes());
            ofile.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void RetriveFromFile(View view) {
        File file = new File(this.getFilesDir(), "Information");
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                TxtViewName.setText(br.readLine());
                TxtViewPhone.setText(br.readLine());
                TxtViewDOB.setText(br.readLine());
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void GoToSQL(View view) {
        Intent intent = new Intent(this, SQLReader.class);
        startActivity(intent);
    }
}
