package com.marchello.newquestionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    static final String ANSWER_KEY = "answer";

    private EditText editText;
    private TextView textView;
    private Button submit_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
        this.submit_btn = findViewById(R.id.submit_btn);
        this.editText = findViewById(R.id.editText);
        this.editText.setHint(R.string.answer);
        this.textView = findViewById(R.id.textView);
        this.textView.setText(getIntent().getStringExtra(MainActivity.QUESTION_KEY));
        this.submit_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra(ANSWER_KEY, this.editText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.exitItem) {
            MainExitDialog exitDialogFragment = new MainExitDialog();
            exitDialogFragment.show(getSupportFragmentManager(), "MainExitDialog");
        }
        return super.onOptionsItemSelected(item);
    }
}