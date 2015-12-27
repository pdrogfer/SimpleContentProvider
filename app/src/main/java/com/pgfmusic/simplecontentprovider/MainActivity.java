package com.pgfmusic.simplecontentprovider;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etName;
    EditText etGrade;
    Button btnAddName;
    Button btnRetrStudents;
    TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddName = (Button) findViewById(R.id.btn_add_name);
        btnRetrStudents = (Button) findViewById(R.id.btn_retrieve_students);
        tvResults = (TextView) findViewById(R.id.tv_results);
        btnAddName.setOnClickListener(this);
        btnRetrStudents.setOnClickListener(this);
    }

    public void onClickAddName() {
        // Add a new student record
        ContentValues values = new ContentValues();

        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.et_name)).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText)findViewById(R.id.et_grade)).getText().toString());

        Uri uri = getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents() {
        // Retrieve student records
        String URL = Contract.BASE_URI + Contract.PATH;
        Log.i(Contract.TAG, "Retrieving: " + URL);
        Uri students = Uri.parse(URL);
        Cursor c = getContentResolver().query(students, null, null, null, "name");

        ArrayList<String> results = new ArrayList<>();
        // Usually you want a ListView to show the data. Using a TextView for simplicity

        if (c.moveToFirst()) {
            do {
                results.add(c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                        ", " + c.getString(c.getColumnIndex(StudentsProvider.NAME)) +
                        ", " + c.getString(c.getColumnIndex(StudentsProvider.GRADE)));
            } while (c.moveToNext());
            String text = "";
            for (String result: results) {
                text += result + "\n";
            }
            tvResults.setText(text);
        } else {
            Log.i(Contract.TAG, "Cursor not working");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_name:
                onClickAddName();
                break;
            case R.id.btn_retrieve_students:
                onClickRetrieveStudents();
                break;
        }
    }
}
