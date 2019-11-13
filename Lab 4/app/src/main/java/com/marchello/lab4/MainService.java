package com.marchello.lab4;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import static java.lang.Math.sqrt;

public class MainService extends IntentService {

    public MainService () {
        super("MainService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        assert intent != null;
        int number = intent.getIntExtra("number", 1);
        int count = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 2; i <= number; i++)
            if(isPrime(i)){
                if(i > 2) result.append(", ");
                result.append(i);
                count++;
            }
        Intent newIntent = new Intent("filter");
        newIntent.putExtra("listPrimeNumbers", result.toString());
        newIntent.putExtra("amountPrimeNumbers", count);
        LocalBroadcastManager.getInstance(this).sendBroadcast(newIntent);
    }

    private boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
