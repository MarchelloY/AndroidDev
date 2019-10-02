package com.marchello.questionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    Button button2;
    EditText editText2;
    TextView textView2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
        button2 = findViewById(R.id.button2);
        editText2 = findViewById(R.id.editText2);
        textView2 = findViewById(R.id.textView2);
        textView2.setText(getIntent().getStringExtra("question"));
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("answer", editText2.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
