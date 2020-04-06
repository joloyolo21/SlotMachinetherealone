package com.example.slotmachinetherealone;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button startSlotMachine;
    private boolean on;
    private int count;
    private SeekBar Seeker;
    private ImageView Slot1;
    private ImageView Slot2;
    private ImageView Slot3;
    private Drawable pear;
    private Drawable grape;
    private Drawable cherry;
    private Drawable strawberry;
    private Random random;
    private Handler handler;
    private UpdateCount update1;
    private UpdateCount2 update2;
    private UpdateCount3 update3;
    private Button Stop;
    private TextView numSpeed;
    private TextView numPoints;
    private int s1;
    private int s2;
    private int s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startSlotMachine = findViewById(R.id.startSlotMachine);
        Slot1 = findViewById(R.id.Slot1);
        Slot2 = findViewById(R.id.Slot2);
        Slot3 = findViewById(R.id.Slot3);
        grape = getDrawable(R.drawable.grape);
        strawberry = getDrawable(R.drawable.strawberry);
        cherry = getDrawable(R.drawable.cherry);
        pear = getDrawable(R.drawable.pear);
        Stop = findViewById(R.id.Stop);
        numSpeed = findViewById(R.id.numSpeed);
        numPoints =findViewById(R.id.numPoints);
        random = new Random();
        handler = new Handler();
        update1 = new UpdateCount();
        update2 = new UpdateCount2();
        update3 = new UpdateCount3();
        Seeker = findViewById(R.id.Speed);
        Seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numSpeed.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
    public void Start(View v){
        if (startSlotMachine.isPressed()) {
            int r = Seeker.getProgress();
            s1 = (r+1)*70;
            s2 = (r+1)*60;
            s3 = (r+1)*50;
            handler.postDelayed(update1, s1);
            handler.postDelayed(update2, s2);
            handler.postDelayed(update3, s3);
            points();
        }
    }


    public void Stop(View v) {
        if (Stop.isPressed()) {
        handler.removeCallbacks(update1);
        handler.removeCallbacks(update2);
        handler.removeCallbacks(update3);

    }
}

    public void points() {
        if(Slot1.getDrawable().equals(Slot2.getDrawable())&Slot1.getDrawable().equals(Slot3.getDrawable())){
            numPoints.setText("100");
        } else if (Slot1.getDrawable().equals(Slot2.getDrawable())) {
            numPoints.setText("50");
        } else if (Slot1.getDrawable().equals(Slot3.getDrawable())) {
            numPoints.setText("50");
        } else if (Slot2.getDrawable().equals(Slot3.getDrawable())) {
            numPoints.setText("50");
        } else {
            numPoints.setText("0");
        }
    }


    private class UpdateCount implements Runnable {

        public void run() {
            count++;
            if(Slot1.getDrawable()==cherry) {
                Slot1.setImageDrawable(strawberry);
            }else if(Slot1.getDrawable()==strawberry) {
                Slot1.setImageDrawable(grape);
            }else if(Slot1.getDrawable()==grape) {
                Slot1.setImageDrawable(pear);
            }else {
                Slot1.setImageDrawable(cherry);
            }
            handler.postDelayed(update1,s1);
        }
    }

    private class UpdateCount2 implements Runnable {
        @Override
        public void run() {
            count++;
            if (Slot2.getDrawable()==cherry) {
                Slot2.setImageDrawable(grape);
            }
            else if (Slot2.getDrawable()==grape) {
                Slot2.setImageDrawable(strawberry);
            }
            else if (Slot2.getDrawable()==strawberry) {
                Slot2.setImageDrawable(pear);
            } else {
                Slot2.setImageDrawable(cherry);
                }
            handler.postDelayed(update2,s2);
            }
        }

    private class UpdateCount3 implements Runnable {
        @Override
        public void run() {
            count++;
            if (Slot3.getDrawable()==cherry) {
                Slot3.setImageDrawable(pear);
            }
            else if (Slot3.getDrawable()==pear) {
                Slot3.setImageDrawable(grape);
            }
            else if (Slot3.getDrawable()==grape) {
                Slot3.setImageDrawable(strawberry);
            }
            else {
                Slot3.setImageDrawable(cherry);
            }
            handler.postDelayed(update3,s3);
        }
    }
    }
