package com.example.chrisbennett.mylistview;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddRecord extends AppCompatActivity {

    ReviewDBHelper mDbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        mDbHelper = new ReviewDBHelper(this);
        db = mDbHelper.getWritableDatabase();

        //db.execSQL(ReviewSchema.SQL_DELETE_ENTRIES);
        //db.execSQL(ReviewSchema.SQL_CREATE_ENTRIES);
    }

    protected void insertRecord(View v) {
        ContentValues values = new ContentValues();
        String color = ((TextView) findViewById(R.id.txtColor)).getText().toString();
        String hex = ((TextView) findViewById(R.id.txtHex)).getText().toString();
        String reviewer = ((TextView) findViewById(R.id.txtReviewer)).getText().toString();
        String rating = ((TextView) findViewById(R.id.txtRating)).getText().toString();
        String review = ((TextView) findViewById(R.id.txtReview)).getText().toString();

        values.put(ReviewSchema.Review.COLUMN_NAME_COLOR, color);
        values.put(ReviewSchema.Review.COLUMN_NAME_HEX, hex);
        values.put(ReviewSchema.Review.COLUMN_NAME_REVIEWER, reviewer);
        values.put(ReviewSchema.Review.COLUMN_NAME_RATING, rating);
        values.put(ReviewSchema.Review.COLUMN_NAME_REVIEW, review);


        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ReviewSchema.Review.TABLE_NAME, null, values);


        if(newRowId > -1) {
            TextView success = (TextView) findViewById(R.id.txtSuccess);
            success.setText("Review successfully added");
        }
        else {
            TextView success = (TextView) findViewById(R.id.txtSuccess);
            success.setText("something went horribly wrong");

        }

    }
}
