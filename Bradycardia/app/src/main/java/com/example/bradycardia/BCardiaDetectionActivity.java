package com.example.bradycardia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class BCardiaDetectionActivity extends AppCompatActivity {

    void detectBradycardia(int heartbeats[], String user) {
        long startTime = System.currentTimeMillis();
        int count=0;
        for (int i = 0; i < heartbeats.length; i++) {
            if (heartbeats[i] < 60) {
                count = count + 1;
                Log.d("Count: ", "Count:" + count);
            }
        }


        int fp = 0, fn = 0, tp = 0, tn = 0;
        for (int i = 0; i < heartbeats.length - 4; i++) {
            double average = (heartbeats[i] + heartbeats[i + 1] + heartbeats[i + 2] + heartbeats[i + 3]) / 4;
            if (average < 60 && heartbeats[i] < 60 && i < (heartbeats.length - 4)) {
                tn = tn + 1;
            }
            if (average > 60 && heartbeats[i] > 60 && i < (heartbeats.length - 4)) {
                tp = tp + 1;
            }
            if (average > 60 && heartbeats[i] <= 60 && i < (heartbeats.length - 4)) {
                fn = fn + 1;
            }

            if (average < 60 && heartbeats[i] >= 60 && i < (heartbeats.length - 4)) {
                fp = fp + 1;
            }

        }



        double falsePositive =  ((double) fp) / ((double) (fp + tn));
        double falseNegative = ((double) fn) / ((double) (fn + tp));

        TextView textViewDetect=(TextView) findViewById(R.id.textViewdetect);
        textViewDetect.setText("");

        if(count==0){
            textViewDetect.append("User "+user+":\n\nNo Bardycardia Detected \n");
            textViewDetect.append(" False Positive: " + 0 + "\n");
            textViewDetect.append(" False Negative: " + 0);

        }else {
            textViewDetect.append("User "+user+":\n\nBardycardia Detected !\n\n");

            textViewDetect.append("In a 30 minute range, Bradycardia is detected for" + count + " minutes\n\n");

            textViewDetect.append(" False Positive: " + falsePositive + "\n");
            textViewDetect.append(" False Negative: " + falseNegative);
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        Toast.makeText(this, "Time taken for detection is "+elapsedTime+" ms", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_detect);
        Button karthiga_btn = (Button) findViewById(R.id.Karthiga);
        Button mandar_btn = (Button) findViewById(R.id.Mandar);
        Button adhithiya_btn = (Button) findViewById(R.id.Adhithiya);
        Button aikya_btn = (Button) findViewById(R.id.Aikya);
        Button redirect=(Button) findViewById(R.id.redirectToPredict);

        karthiga_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] values={96,100,97,96,97,96,92,94,89,88,93,81,86,88,67,76,83,83,84,77,80,81,74,60,72,61,69,80,64,75};
                detectBradycardia(values, "Karthiga");
            }
        });
        mandar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] values={73,73,73,74,73,76,75,76,76,73,76,71,72,72,73,76,90,96,105,108,113,105,114,99,85,80,83,86,83,90};
                detectBradycardia(values, "Mandar");
            }
        });
        adhithiya_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] values={79,79,77,88,98,81,65,65,63,65,65,65,68,66,66,73,72,71,70,70,71,73,67,87,99,105,105,111,97,100};
                detectBradycardia(values, "Adhithiya");
            }
        });
        aikya_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] values={58,58,60,58,60,60,60,61,61,63,59,57,56,58,59,60,61,57,68,74,69,58,55,56,60,55,61,66,57,57};
                detectBradycardia(values, "Aikya");
            }
        });
        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(BCardiaDetectionActivity.this, BCaridaPredictionActivity.class);
                BCardiaDetectionActivity.this.startActivity(intent);

            }
        });


    }



}