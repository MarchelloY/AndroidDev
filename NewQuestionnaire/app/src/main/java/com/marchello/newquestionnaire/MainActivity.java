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
    private Button button;
    private String save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        this.button = findViewById(R.id.button1);
        this.editText = findViewById(R.id.editText1);
        this.textView = findViewById(R.id.textView1);
        if(savedInstanceState != null) {
            String data = savedInstanceState.getString("Save");
            this.textView.setText(data);
            this.save = data;
        }
        this.button.setOnClickListener(this);
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
                this.save = data.getStringExtra(SecondActivity.ANSWER_KEY);
                this.textView.setText(this.save);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("Save", this.save);
        super.onSaveInstanceState(savedInstanceState);
    }
}
