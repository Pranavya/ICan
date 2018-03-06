package com.pranavya.ican;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class GestureActivity extends Activity {

    private GestureLibrary gLib;

    private static final String TAG = "GestureActivity";
    TextToSpeech t1;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_gesture);
        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });

        openOptionsMenu();

        gLib = GestureLibraries.fromFile(getExternalFilesDir(null) + "/" + "gesture.txt");

        gLib.load();



        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);

        gestures.addOnGesturePerformedListener(handleGestureListener);

        gestures.setGestureStrokeAngleThreshold(90.0f);

    }



    /**

     * our gesture listener

     */

    private OnGesturePerformedListener handleGestureListener = new OnGesturePerformedListener() {

        @Override

        public void onGesturePerformed(GestureOverlayView gestureView,

                                       Gesture gesture) {



            ArrayList<Prediction> predictions = gLib.recognize(gesture);

            Log.d(TAG, "recognize");
            // one prediction needed

            if (predictions.size() > 0) {

                Prediction prediction = predictions.get(0);

                // checking prediction

                if (prediction.score >1.2) {
                    // and action
                    String toSpeak = prediction.name.toString();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

                    Toast.makeText(GestureActivity.this, prediction.name,

                            Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getBaseContext(), "Add Gesture to recognize", Toast.LENGTH_SHORT).show();
                }

            }

        }

    };

}
