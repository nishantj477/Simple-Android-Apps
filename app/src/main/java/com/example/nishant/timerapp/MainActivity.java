package com.example.nishant.timerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Button button;
    CountDownTimer countDownTimer;
    boolean counterIsAactive = false;

    public void resetTimer() {
        button.setText("GO!");
        timerSeekBar.setEnabled(true);
        timerSeekBar.setProgress(30);
        timerTextView.setText("0:30");
        countDownTimer.cancel();
        counterIsAactive = false;
    }


    public void updateTimer(int secondsLeft) {
        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;
        String secondString = Integer.toString(seconds);
        if(seconds < 10) {
            secondString = "0" + seconds;
        }
        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }


    public void controlTimer(View view) {
        if (counterIsAactive == false) {
                counterIsAactive = true;
                timerSeekBar.setEnabled(false);
                button.setText("STOP!!");

                countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                    @Override
                    public void onTick(long millsUntilFinished) {
                        updateTimer((int) millsUntilFinished / 1000);
                    }

                    @Override
                    public void onFinish() {
                       resetTimer();
                    }
                }.start();
        } else {
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.seekBar1);
        timerTextView = (TextView) findViewById(R.id.textView1);
        button = (Button) findViewById(R.id.button1);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            updateTimer(progress);
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
