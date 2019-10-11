package com.marchello.newquestionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    static final String ANSWER_KEY = "answer";

    private EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
        Button button = findViewById(R.id.button2);
        editText = findViewById(R.id.editText2);
        TextView textView = findViewById(R.id.textView2);
        textView.setText(getIntent().getStringExtra(MainActivity.QUESTION_KEY));
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra(ANSWER_KEY, editText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}