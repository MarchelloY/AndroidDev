package com.marchello.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputNumber;
    private Button submitButton;
    private TextView amountPrimeNumbers;
    private TextView listPrimeNumbers;
    private String saveAmount;
    private String saveList;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            String amount = context.getResources().getString(R.string.primeNumbers) + " "
                    + intent.getIntExtra("amountPrimeNumbers", 0) + " "
                    + context.getResources().getString(R.string.pieces);
            String list = context.getResources().getString(R.string.listOfNumbers)
                    + intent.getStringExtra("listPrimeNumbers");
            amountPrimeNumbers.setText(amount);
            listPrimeNumbers.setText(list);
            saveAmount = amount;
            saveList = list;
            submitButton.setEnabled(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.broadcastReceiver, new IntentFilter("filter"));
        this.inputNumber = findViewById(R.id.inputNumber);
        this.submitButton = findViewById(R.id.submitButton);
        this.amountPrimeNumbers = findViewById(R.id.amountPrimeNumbers);
        this.listPrimeNumbers = findViewById(R.id.listPrimeNumbers);
        if (savedInstanceState != null) {
            String dataAmound = savedInstanceState.getString("SaveAmount");
            String dataList = savedInstanceState.getString("SaveList");
            this.amountPrimeNumbers.setText(dataAmound);
            this.listPrimeNumbers.setText(dataList);
            this.saveAmount = dataAmound;
            this.saveList = dataList;
        }
        this.submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(MainActivity.this, MainService.class);
        intent.putExtra("number", Integer.valueOf(this.inputNumber.getText().toString()));
        this.submitButton.setEnabled(false);
        startService(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("SaveAmount", this.saveAmount);
        savedInstanceState.putString("SaveList", this.saveList);
        super.onSaveInstanceState(savedInstanceState);
    }
}
