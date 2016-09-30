package com.example.chrisbennett.mylistview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class SummaryView extends AppCompatActivity {

    ReviewDBHelper mDbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_view);

        mDbHelper = new ReviewDBHelper(this);
        db = mDbHelper.getReadableDatabase();

        Intent intent = getIntent();
        String fTerm = intent.getStringExtra("fTerm");
        Cursor c = db.rawQuery("SELECT * FROM " + ReviewSchema.Review.TABLE_NAME, null);

        if(fTerm!=null){
            c = db.rawQuery("SELECT * FROM " + ReviewSchema.Review.TABLE_NAME + " WHERE " + ReviewSchema.Review.COLUMN_NAME_HEX + " LIKE" + "'%" + fTerm + "%'", null);
        }
        ReviewCursorAdapter adapter = new ReviewCursorAdapter(this,c);


        ListView listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);

// TO - Detail Act
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), DetailView.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }


        });
    }

//Reload THIS w/Filter
    protected void filter(View v) {
        EditText txtIn = (EditText) findViewById(R.id.txtIn);
        String str = txtIn.getText().toString();

        Intent intent = new Intent(this, SummaryView.class);
        intent.putExtra("fTerm",str);
        finish();
        startActivity(intent);
    }
}
