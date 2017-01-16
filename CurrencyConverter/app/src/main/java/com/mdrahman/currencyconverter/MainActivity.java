package com.mdrahman.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convertFunc(View view){
        double amount = 0.0;
        double total = 0.0;
        EditText input = (EditText) findViewById(R.id.textId);
        amount = Double.parseDouble(input.getText().toString());

        total = amount * 78.90;

        Toast.makeText(MainActivity.this, total + " TK", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
