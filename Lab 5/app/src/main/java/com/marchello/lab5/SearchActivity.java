package com.marchello.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputSearch;
    private TextView personInfo;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        this.inputSearch = findViewById(R.id.inputSearch);
        this.personInfo = findViewById(R.id.personInfo);
        this.searchButton = findViewById(R.id.searchButton);
        this.searchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        personInfo.setText(DatabaseClient.getInstance(getApplicationContext())
                .getAppDatabase()
                .getPersonDao()
                .getAllPersonWithSearchParam(this.inputSearch.getText().toString().trim())
                .toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addItem) {
            Intent intent = new Intent(SearchActivity.this, AddActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
