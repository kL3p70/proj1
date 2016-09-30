package com.example.chrisbennett.mylistview;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ReviewDBHelper mDbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new ReviewDBHelper(this);
        db = mDbHelper.getWritableDatabase();
        //db.execSQL(ReviewSchema.SQL_DELETE_ENTRIES);
        //db.execSQL(ReviewSchema.SQL_CREATE_ENTRIES);
    }

    protected void viewRecords(View v) {
        Intent intent = new Intent(this,SummaryView.class);
        startActivity(intent);
    }

    protected void addRecord(View v) {

        Intent intent = new Intent(this,AddRecord.class);
        startActivity(intent);
    }


}
