package com.marchello.lab5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.marchello.lab5.model.Person;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputName;
    private EditText inputSurname;
    private DatePicker inputDate;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        this.inputName = findViewById(R.id.inputName);
        this.inputSurname = findViewById(R.id.inputSurname);
        this.inputDate = findViewById(R.id.inputDate);
        this.submitButton = findViewById(R.id.submitButton);
        this.submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(!this.inputName.getText().toString().equals("") || !this.inputSurname.getText().toString().equals("")) {
            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .getPersonDao()
                    .insert(new Person(
                            this.inputName.getText().toString().trim(),
                            this.inputSurname.getText().toString().trim(),
                            new SimpleDateFormat("dd-MM-yyyy")
                                    .format(new Date(inputDate.getYear(), inputDate.getMonth(), inputDate.getDayOfMonth()))));
            this.inputName.setText("");
            this.inputSurname.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.searchItem) {
            Intent intent = new Intent(AddActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
