package com.mdrahman.toastdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickFunc(View view){
        EditText nameInput = (EditText) findViewById(R.id.nameId);

        Toast.makeText(MainActivity.this, "Hi there, " + nameInput.getText().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
