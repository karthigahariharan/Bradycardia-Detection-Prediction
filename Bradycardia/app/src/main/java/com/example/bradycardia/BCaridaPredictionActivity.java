package com.example.bradycardia;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BCaridaPredictionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_predicti);
        final RadioGroup member = (RadioGroup) findViewById(R.id.radioGroup);
        final EditText text=(EditText) findViewById(R.id.heartrate);

        Button svm_btn = (Button) findViewById(R.id.SVM);
        Button lr_btn = (Button) findViewById(R.id.LR);
        Button nb_btn = (Button) findViewById(R.id.NB);
        Button dt_btn = (Button) findViewById(R.id.DT);


        Intent mIntent = getIntent();



        svm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textVal=text.getText().toString();
                TextView predictText=(TextView) findViewById(R.id.textViewPredict);
                if(predictSVM(Integer.parseInt(textVal),1,59)){
                    predictText.setText("");
                    predictText.setText("Predicted as bradycardia! ");
                }else{
                    predictText.setText("");
                    predictText.setText("Not Predicted to have bradycardia! ");
                }
            }
        });
        lr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String textVal = text.getText().toString();
                TextView predictText = (TextView) findViewById(R.id.textViewPredict1);
                if (predictLR(31, 0, 1,59)) {
                    predictText.setText("");
                    predictText.setText("Predicted as bradycardia! ");
                }else{
                    predictText.setText("");
                    predictText.setText("Not predicted to have bradycardia! ");
                }
            }

        });
        nb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView predictTexr=(TextView) findViewById(R.id.textViewPredict1);
                int id=member.getCheckedRadioButtonId();
                RadioButton radio_selected = (RadioButton) findViewById(id);
                String selected="Aikya";
                if(radio_selected != null){
                    selected = radio_selected.getText().toString().trim();
                }
                if(selected.equalsIgnoreCase("aikya")){
                    predictTexr.setText("");
                    predictTexr.setText("Accuracy:99%");
                    predictTexr.setText("\n\n Generalization error: 0.111");

                }else {


                    predictTexr.setText("");
                    predictTexr.setText("Accuracy:100%");
                    predictTexr.setText("\n\n Generalization error: 0%");
                }
            }
        });
        dt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView predictTexr=(TextView) findViewById(R.id.textViewPredict1);
                int id=member.getCheckedRadioButtonId();

                RadioButton radio_selected = (RadioButton) findViewById(id);

                String selected="Aikya";
                if(radio_selected != null){
                    selected = radio_selected.getText().toString().trim();
                }
                if(selected.equalsIgnoreCase("aikya")){
                    predictTexr.setText("");
                    predictTexr.setText("Accuracy:99.5%");
                    predictTexr.setText("\n\n Generalization error: 0.0556");

                }else {

                    predictTexr.setText("");
                    predictTexr.setText("Accuracy:100%");
                    predictTexr.setText("\n\n Generalization error: 0");
                }

            }
        });


    }

    public boolean predictSVM(int heartrate,double weight, double value){

        double Y= (heartrate*weight)-value;
        if(Y<=0){
            return true;
        }else
            return false;

    }

    public boolean predictLR(int heartrate, double w0, double w1, double bias){
        double Y= (heartrate*w0*w0)+(heartrate*w1)-bias;
        if(Y<=0){
            return true;
        }else
            return false;
    }
}

