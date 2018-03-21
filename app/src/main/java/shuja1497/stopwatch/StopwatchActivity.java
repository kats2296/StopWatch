package shuja1497.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {

    private int seconds =0;
    private boolean running ;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);


        //in case of screen rotation ..activity gets destroyed ..so we have saved instance of that activty n used here ..
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        RunTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
        outState.putBoolean("wasRunning",wasRunning);
    }

/*    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = running ;
        running = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (wasRunning) {
            running = true;
        }
    }*/


    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    protected void onResume() {
        super.onResume();
        if (wasRunning) {
                    running = true;
        }
    }


    public void Start(View view ){

        running = true;

    }

    public void Stop(View view ){

        running = false;
    }

    public void Reset(View view ){

        running = false;
        seconds = 0;
    }

    private void RunTimer(){

        final TextView timeView = (TextView)findViewById(R.id.textView);

        final Handler handler  = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }

                handler.postDelayed(this,1000);
            }
        });
    }



}
