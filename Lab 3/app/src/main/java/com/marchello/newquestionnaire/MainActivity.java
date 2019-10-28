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
    private Button submit_btn;
    private String save_data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        this.submit_btn = findViewById(R.id.submit_btn);
        this.editText = findViewById(R.id.editText);
        this.editText.setHint(R.string.question);
        this.textView = findViewById(R.id.textView);
        if(savedInstanceState != null) {
            String data = savedInstanceState.getString("Save");
            this.textView.setText(data);
            this.save_data = data;
        }
        this.submit_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(QUESTION_KEY, this.editText.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_ANSWER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ANSWER && resultCode == RESULT_OK) {
                this.save_data = data.getStringExtra(SecondActivity.ANSWER_KEY);
                this.textView.setText(this.save_data);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("Save", this.save_data);
        super.onSaveInstanceState(savedInstanceState);
    }
}
