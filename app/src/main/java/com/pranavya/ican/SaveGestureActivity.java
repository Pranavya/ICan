package com.pranavya.ican;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;

public class SaveGestureActivity extends AppCompatActivity {

    private GestureLibrary gLib;
    private static final String TAG = "SaveGestureActivity";
    private boolean mGestureDrawn;
    private Gesture mCurrentGesture;
    private String mGesturename;
    public Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_gesture);


        Log.d(TAG, "path = " + Environment.getExternalStorageDirectory().getAbsolutePath());
        //openOptionsMenu();
        gLib = GestureLibraries.fromFile(getExternalFilesDir(null) + "/" + "gesture.txt");
        gLib.load();
        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.save_gesture);
        gestures.addOnGestureListener(mGestureListener);
        resetEverything();
    }

    private GestureOverlayView.OnGestureListener mGestureListener = new GestureOverlayView.OnGestureListener() {

        @Override
        public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
            mGestureDrawn = true;
            Log.d(TAG, "in");
        }

        @Override
        public void onGesture(GestureOverlayView overlay, MotionEvent event) {
            mCurrentGesture = overlay.getGesture();
        }

        @Override
        public void onGestureEnded(GestureOverlayView gestureView, MotionEvent motion) {
            Log.d(TAG, "out");
        }

        @Override
        public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
            Log.d(TAG, "cancel");
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topmenu, menu);
        return true;
    }

    /*
    Creating options menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.REDRAW:
                reDrawGestureView();
                break;

            case R.id.Save:
                if(mGestureDrawn){
                    if(GestureListActivity.gestureCount > 50) {
                        Toast.makeText(getApplicationContext(), "Limit Exceeded. Delete Few Gestures To Add More" , Toast.LENGTH_LONG).show();
                    }
                    else{
                        getName();
                    }
                } else{
                    showToast(getString(R.string.no_gesture));
                }
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    Get the name corresponding to the gesture
     */
    private void getName() {
        AlertDialog.Builder namePopup = new AlertDialog.Builder(this);
        namePopup.setTitle(getString(R.string.enter_name));
        final EditText nameField = new EditText(this);
        namePopup.setView(nameField);
        namePopup.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {
                if (!nameField.getText().toString().matches("")) {
                    mGesturename = nameField.getText().toString();
                    saveGesture();
                } else {
                    getName();  //TODO : set name field with old name string user added
                    showToast(getString(R.string.invalid_name));
                }
            }

        });
        namePopup.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {
                mGesturename = "";
                return;
            }

        });
        namePopup.show();
    }

    private void showToast(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    private void saveGesture() {

        gLib.addGesture(mGesturename, mCurrentGesture);
        if (!gLib.save()) {
            Log.e(TAG, "gesture not saved!");
        }else {
            showToast(getString(R.string.gesture_saved) + getExternalFilesDir(null) + "/gesture.txt");
            Intent i = new Intent(SaveGestureActivity.this, GestureListActivity.class);
            startActivity(i);
        }
    }

    /*
    Clear screen
     */
    private void resetEverything(){
        mGestureDrawn = false;
        mCurrentGesture = null;
        mGesturename = "";
    }

    private void reDrawGestureView() {
        setContentView(R.layout.save_gesture);
        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.save_gesture);
        gestures.removeAllOnGestureListeners();
        gestures.addOnGestureListener(mGestureListener);
        resetEverything();
    }
}