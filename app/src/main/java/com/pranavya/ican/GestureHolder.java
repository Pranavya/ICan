package com.pranavya.ican;

import android.gesture.Gesture;

/**
 * Created by NAVYA on 26-02-2018.
 */

public class GestureHolder {
    private Gesture gesture;
    private String gestureName;
    public GestureHolder(Gesture gesture, String name){
        this.gesture = gesture;
        this.gestureName = name;
    }

    public Gesture getGesture(){
        return gesture;
    }

    public void setGesture(Gesture gesture){
        this.gesture = gesture;
    }

    public String getName(){
        return gestureName;
    }

    public void setName(String name){
        this.gestureName = name;
    }
}
