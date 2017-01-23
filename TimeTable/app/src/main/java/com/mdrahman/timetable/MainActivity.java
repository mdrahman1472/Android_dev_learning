package com.mdrahman.timetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView myListView;

    public void genarateTimeTable(int progress){
        ArrayList<String> myArrayList = new ArrayList<String>();

        for(int i = 1; i<=10; i++){
            myArrayList.add(Integer.toString(progress * i));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, myArrayList);
        myListView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SeekBar mySeekBar = (SeekBar) findViewById(R.id.seekBarId);
        mySeekBar.setMax(20);
        mySeekBar.setProgress(10); // always be at 10 at beginning of app

        myListView = (ListView) findViewById(R.id.myListViewId);


        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0) {
                    mySeekBar.setProgress(1);
                    progress = 1;
                }

                // calling function to veiw on List View
                genarateTimeTable(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Deafault time table
        genarateTimeTable(10);
    }
}
