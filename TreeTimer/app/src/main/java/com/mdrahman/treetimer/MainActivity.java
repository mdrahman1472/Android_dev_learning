package com.mdrahman.treetimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timeText;
    SeekBar seekBar;
    boolean activity = false;
    Button controllerButoon;
    CountDownTimer countDownTimer;


    public void controllTimer(View view){

        if (activity == false) {
            activity = true;
            seekBar.setEnabled(false); // seekBar will disappear and will not all seeking
            controllerButoon.setText("STOP");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) { // +100 is for delay process of onTicks
                @Override
                public void onTick(long millisecondsUntilDone) {

                    int totalTime_seconds = (int) millisecondsUntilDone / 1000;
                    int minutes = (int) totalTime_seconds / 60;
                    int seconds = totalTime_seconds - minutes * 60;

                    timeText.setText(minutes + ":" + seconds);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.tone);

                    timeText.setText("0:00");
                    mplayer.start();
                }
            }.start();
        }
        else{
            timeText.setText("0:30");
            seekBar.setProgress(30);
            countDownTimer.cancel();
            controllerButoon.setText("GO");
            seekBar.setEnabled(true);
            activity = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBarId);
        timeText = (TextView) findViewById(R.id.timeTextId);
        controllerButoon = (Button) findViewById(R.id.controllerId);

        seekBar.setMax(600); // 10 min ==> (60 sec x 10)
        seekBar.setProgress(30); // 30 seconds

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes = (int)progress / 60;
                int seconds =  progress - minutes * 60;

                timeText.setText(minutes + ":" + seconds);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
