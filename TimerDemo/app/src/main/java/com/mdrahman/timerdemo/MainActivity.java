package com.mdrahman.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ---------------------------- 1st way --------------------------------------
        // Pros: this method object will finished or kiled after countdown is done

        // first parameter is how long(in this case 10 sec) and second parameter is
        // frequency of each tick (in this case 1 second)
        new CountDownTimer(10000,1000){
            // this func will call every 1000 mili sec or 1 second
            public void onTick(long millisecondsUntilDone){

                // countdown is counting down (in our every second)
                Log.i("second left", String.valueOf(millisecondsUntilDone/1000)); // milliSec/1000 gives second
            }

            public void onFinish(){
                // counter finished! (in our case every 10 sec)
                Log.i("Done", "Countdown time finished");
            }
        }.start();

        // ------------------------------------------------------------------------------

        /*-------------------------- 2nd way---------------------------------------------
        // in this method time keep runnig without terminate

        // Handler take care timing delay
        final Handler handler = new Handler();
        Runnable run = new Runnable(){
            @Override
            public void run() {
                // Insert code to be run every second
                Log.i("Runable has run", "second have must passed");
                handler.postDelayed(this, 1000); // "this" refer to run method
            }
        };

        // Initialize run for fist time app start. then above method will work after
        handler.post(run);
        ---------------------------------------------------------------------------------
        */
    }
}
