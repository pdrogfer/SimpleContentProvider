package com.pgfmusic.simplecontentprovider;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        // TODO: 25/12/15 this doesn't work: "java.lang.IllegalArgumentException: Unknown URL content://com.pgfmusic.simplecontentprovider.College/students"
        Uri uri = getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents() {
        Toast.makeText(this, "retrieve", Toast.LENGTH_SHORT).show();
        // Retrieve student records
        String URL = Contract.BASE_URI + Contract.PATH;

        Uri students = Uri.parse(URL);
        Cursor c = getContentResolver().query(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( StudentsProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( StudentsProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
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
