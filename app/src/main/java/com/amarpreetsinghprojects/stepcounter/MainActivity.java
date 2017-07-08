package com.amarpreetsinghprojects.stepcounter;

import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String TAG = "msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView stepcount = (TextView)findViewById(R.id.stepCountTV);


        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor pedo = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        Sensor stepDetect = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (pedo == null){
            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Step Counter")
                    .setMessage("Device doesn't support pedometer...")
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            finish();
                        }
                    })
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }
        else {
            sensorManager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    Log.e(TAG, "onSensorChanged: "+event.values[0] );
                    stepcount.setText(""+event.values[0]);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            },pedo,100000);
        }

        if (stepDetect == null){
            final AlertDialog alertDialog1 = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Step Counter")
                    .setMessage("Device doesn't support pedometer...")
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            finish();
                        }
                    })
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }
        else {
            sensorManager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    Toast.makeText(MainActivity.this,"Step Detected",Toast.LENGTH_LONG);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            },stepDetect,1000);
        }

    }
}
