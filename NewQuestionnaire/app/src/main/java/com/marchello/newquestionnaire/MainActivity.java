package com.marchello.newquestionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final String QUESTION_KEY = "question";
    static final int REQUEST_CODE_ANSWER = 1;

    private EditText editText;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        Button button = findViewById(R.id.button1);
        editText = findViewById(R.id.editText1);
        textView = findViewById(R.id.textView1);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(QUESTION_KEY, editText.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_ANSWER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ANSWER) {
            if (resultCode == RESULT_OK) {
                textView.setText(data.getStringExtra(SecondActivity.ANSWER_KEY));
            }
        }
    }
}
