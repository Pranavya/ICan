package com.pranavya.ican;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Locale;

public class Home extends Fragment implements View.OnClickListener{

    TextToSpeech t1;
    EditText ed1;
    ImageButton ib1;

    private OnFragmentInteractionListener mListener;

    /*
    This method has the action to be performed for Floating action button
    */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.home, container, false);
        ib1 =(ImageButton) v.findViewById(R.id.imageButton);
        ed1 = (EditText) v.findViewById(R.id.editText2);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this.getActivity(),GestureActivity.class);
                startActivity(i);
            }
        });

        t1 = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR ) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        ib1.setOnClickListener(this);
        return v;
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*
    Instant text-speech convertor
     */
    @Override
    public void onClick(View view) {
        String toSpeak = ed1.getText().toString();
        String error = getString(R.string.err_msg);
        if(toSpeak.trim().isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(),error,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity().getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
            t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}