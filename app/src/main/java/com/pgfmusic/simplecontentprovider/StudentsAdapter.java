package com.pgfmusic.simplecontentprovider;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by USUARIO on 28/12/2015.
 */
public class StudentsAdapter extends CursorAdapter {
    public StudentsAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView textView = (TextView)view;
        String tempId = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String tempName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String tempGrade = cursor.getString(cursor.getColumnIndexOrThrow("grade"));
        textView.setText("ID: " + tempId + ", NAME: " + tempName + ", GRADE: " + tempGrade);
    }
}
