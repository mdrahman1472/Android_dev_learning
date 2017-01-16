package com.mdrahman.textfielddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void clickFunc(View view){
        EditText userName = (EditText) findViewById(R.id.userName);
        EditText passtext = (EditText) findViewById(R.id.passId);

        Log.i("Username", userName.getText().toString());
        Log.i("Password", passtext.getText().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
