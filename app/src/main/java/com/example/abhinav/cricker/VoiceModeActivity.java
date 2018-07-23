package com.example.abhinav.cricker;

/**
 * Created by Abhinav on 11-02-2018.
 */

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class VoiceModeActivity extends Activity {
    private final int SPEECH_RECOGNITION_CODE = 1;
    private TextView txtOutput;
    private TextView txtOutput1;
    private TextView wicketsView;
    private TextView runsView;
    private ImageButton btnMicrophone;
    private int wic;
    private int run;
    private Map<Integer,String> runs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_mode);
        runs=new HashMap<Integer, String>() ;
        runs.put(1,"1 ram one fun wonder wanted wanna fun onedrive wondering london 11 vandana wonderland mandal ");
        runs.put(2,"torrents torrentz 2 two 2d tu");
        runs.put(3,"clearance 3 3d terence free three");
        runs.put(4,"forums 4 four foreigners poems");
        runs.put(5,"five runs four plus nobe ball four plus no ball four + nobe ball");
        runs.put(6,"sex dance sections 6 six");
        runs.put(7,"seven runs");
        runs.put(0,"don't ball dot ball god ball dodgeball zero 0 god dost");//. ball
        runs.put(11,"white wide");// wide
        runs.put(12,"bhopal no nope call"); //no ball
        runs.put(20," duck out caught hot");



        txtOutput = (TextView) findViewById(R.id.txt_output);
        txtOutput1 = (TextView) findViewById(R.id.translated_txt);
        runsView = (TextView) findViewById(R.id.runs);
        wicketsView = (TextView) findViewById(R.id.wickets);
        btnMicrophone = (ImageButton) findViewById(R.id.btn_mic);
        btnMicrophone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpeechToText();
            }
        });
    }
    /**
     * Start speech to text intent. This opens up Google Speech Recognition API dialog box to listen the speech input.
     *
     * */

    private void startSpeechToText()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE,true);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,3000);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speak something...");
        try {
            startActivityForResult(intent, SPEECH_RECOGNITION_CODE);
        } catch (ActivityNotFoundException a)
        {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Speech recognition is not supported in this device.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Callback for speech recognition activity
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SPEECH_RECOGNITION_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String text = result.get(0);
                    //txtOutput.setText(text);
                    sendTextAsOutput(text);
                }
                break;
            }
        }
    }

    protected void sendTextAsOutput(String text)
    {
        int temp=0;
        boolean flag=false;
        text=text.contains(" ")?text.substring(0,text.indexOf(" ")):text;
        for(Map.Entry<Integer,String> map: runs.entrySet())
        {
            if(map.getValue().contains(text.toLowerCase()))
            {
                flag=true;
                temp = map.getKey();
                if (temp >= 0 && temp < 8) {
                    run = run + temp;
                } else if (temp == 11 || temp == 12) {
                    run = run + 1;
                } else if (temp == 20) {
                    wic++;
                    wicketsView.setText(wic + "");
                }


                txtOutput1.setText(temp+"");
                runsView.setText(run + "");
                break;
            }


        }
        if(!flag)
            txtOutput1.setText("couldn't recognize your voice");
        startSpeechToText();


    }
}