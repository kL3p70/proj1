package com.example.chrisbennett.mylistview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailView extends AppCompatActivity {


    ReviewDBHelper mDbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        TextView txtColor = (TextView) findViewById(R.id.txtColor);
        TextView txtHex = (TextView) findViewById(R.id.txtHex);
        TextView txtReviewer = (TextView) findViewById(R.id.txtReviewer);
        TextView txtRating = (TextView) findViewById(R.id.txtRating);
        TextView txtReview = (TextView) findViewById(R.id.txtReview);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);

        mDbHelper = new ReviewDBHelper(this);
        db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "  + ReviewSchema.Review.TABLE_NAME,null);

        cursor.moveToPosition(position);

        String col = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.Review.COLUMN_NAME_COLOR));
        String h = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.Review.COLUMN_NAME_HEX));
        String rev = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.Review.COLUMN_NAME_REVIEWER));
        String rat = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.Review.COLUMN_NAME_RATING));
        String review = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.Review.COLUMN_NAME_REVIEW));

        txtColor.setText(col);
        txtHex.setText(h);
        txtReviewer.setText(rev);
        txtRating.setText(rat);
        txtReview.setText(review);
    }
}
