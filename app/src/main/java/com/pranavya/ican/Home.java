package com.pranavya.ican;

import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Sucharita on 24-02-2018.
 */

public class Home extends Fragment implements View.OnClickListener{

    TextToSpeech t1;
    EditText ed1;
    ImageButton ib1;

    private OnFragmentInteractionListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.home, container, false);
        ib1 =(ImageButton) v.findViewById(R.id.imageButton);
        ed1 = (EditText) v.findViewById(R.id.editText2);

        t1 = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
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


    @Override
    public void onClick(View view) {
        //if (view == ib1) {
            String toSpeak = ed1.getText().toString();
            Toast.makeText(getActivity().getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
            t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
        //}
        //else if (view == b2) {
            //Intent intent = new Intent(this, SaveGenres.class);
            //Intent intent = new Intent(Home.this.getActivity(), SaveGenres.class);
          //  startActivity(intent);
            //Intent intent = new Intent(Home.this.getActivity(), Main2Activity.class);
            //ed2 = (EditText) view.findViewById(R.id.editText2);
            //intent.putExtra("text", ed2.getText().toString());
            //startActivity(intent);
        //}
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
