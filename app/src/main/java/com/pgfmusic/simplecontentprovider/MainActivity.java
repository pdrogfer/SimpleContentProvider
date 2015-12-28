package com.pgfmusic.simplecontentprovider;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor>{

    private static final int STUDENTS_LOADER = 0;
    StudentsAdapter studentsAdapter;

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

        studentsAdapter = new StudentsAdapter(this, null, 0);
        lvResults.setAdapter(studentsAdapter);
        getLoaderManager().initLoader(STUDENTS_LOADER, null, this);
    }

    public void onClickAddName() {
        // Add a new student record
        ContentValues values = new ContentValues();

        values.put(StudentsProvider.NAME,
                ((EditText) findViewById(R.id.et_name)).getText().toString());

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

    }

    public void populateListViewResults(Cursor cursor) {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_name:
                onClickAddName();
                break;
            case R.id.btn_retrieve_students:
                // the loader is suposed to work automatically
                break;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Retrieve student records
        String URL = Contract.BASE_URI + Contract.PATH;
        Log.i(Contract.TAG, "Retrieving: " + URL);
        Uri studentsUri = Uri.parse(URL);
        String sortOrder = Contract.COL_ID + " ASC";
        return new CursorLoader(this, studentsUri, null, null, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        studentsAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        studentsAdapter.swapCursor(null);

    }
}
