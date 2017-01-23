package com.mdrahman.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = (ListView) findViewById(R.id.myListViewId);

        final ArrayList<String> peopleList = new ArrayList<String>();
        peopleList.add("John");
        peopleList.add("Marry");
        peopleList.add("Alen");
        peopleList.add("David");
        peopleList.add("Steven");

        // Now I need adapter to set array list as view
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, peopleList);
        // set arrayAdapter into list view
        myListView.setAdapter(arrayAdapter);

        // onClick Listner
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Hi " + peopleList.get(position) + " !", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
