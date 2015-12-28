package com.pgfmusic.simplecontentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etName;
    EditText etGrade;
    Button btnAddName;
    Button btnRetrStudents;
    ListView lvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText) findViewById(R.id.et_name);
        etGrade = (EditText) findViewById(R.id.et_grade);
        btnAddName = (Button) findViewById(R.id.btn_add_name);
        btnRetrStudents = (Button) findViewById(R.id.btn_retrieve_students);
        lvResults = (ListView) findViewById(R.id.lvResults);
        btnAddName.setOnClickListener(this);
        btnRetrStudents.setOnClickListener(this);
    }

    public void onClickAddName() {
        // Add a new student record
        ContentValues values = new ContentValues();

        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.et_name)).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText) findViewById(R.id.et_grade)).getText().toString());

        Uri uri = getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
        etName.setText("");
        etGrade.setText("");
    }

    public void onClickRetrieveStudents() {
        // Retrieve student records
        String URL = Contract.BASE_URI + Contract.PATH;
        Log.i(Contract.TAG, "Retrieving: " + URL);
        Uri students = Uri.parse(URL);
        Cursor c = getContentResolver().query(students, null, null, null, "name");

        if (c.moveToFirst()) {
            populateListViewResults(c);
        } else {
            Log.i(Contract.TAG, "Cursor not working");
        }
    }

    public void populateListViewResults(Cursor cursor) {
        StudentsAdapter studentsAdapter = new StudentsAdapter(this, cursor, 0);
        lvResults.setAdapter(studentsAdapter);
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
