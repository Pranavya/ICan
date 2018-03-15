package com.pranavya.ican;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;


public class GestureListActivity extends AppCompatActivity {
    private static final String TAG = "GestureListActivity";
    private String mCurrentGestureName,name;
    private ListView mGestureListView;
    private static ArrayList<GestureHolder> mGestureList;
    private GestureAdapter mGestureAdapter;
    private GestureLibrary gLib;
    private long backPressedTime;

    /*
    Displaying all the gestures in a list
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestures_list);
        Log.d(TAG, getApplicationInfo().dataDir);

        mGestureListView = (ListView) findViewById((R.id.gestures_list));
        mGestureList = new ArrayList<GestureHolder>();
        list();
        mGestureAdapter = new GestureAdapter(mGestureList, GestureListActivity.this);
        mGestureListView.setLongClickable(true);
        mGestureListView.setAdapter(mGestureAdapter);
        registerForContextMenu(mGestureListView);
    }

    public void addGesture(View v){
        Intent i = new Intent(GestureListActivity.this, SaveGestureActivity.class);
        startActivity(i);
    }

    /*
    This method redirects you to the gestures list page
     */
    @Override
    public void onResume(){
        super.onResume();
        setContentView(R.layout.gestures_list);
        Log.d(TAG, getApplicationInfo().dataDir);
        openOptionsMenu();
        mGestureListView = (ListView) findViewById((R.id.gestures_list));
        mGestureList = new ArrayList<GestureHolder>();
        list();

        mGestureAdapter = new GestureAdapter(mGestureList, GestureListActivity.this);
        mGestureListView.setLongClickable(true);
        mGestureListView.setAdapter(mGestureAdapter);
        // displays the popup context top_menu to either delete or resend measurement
        registerForContextMenu(mGestureListView);
    }

    /*
    Retrieve the data from file which is in storage and add all the items to a list
     */

    private void list() {
        try {
            mGestureList = new ArrayList<GestureHolder>();
            gLib = GestureLibraries.fromFile(getExternalFilesDir(null) + "/" + "gesture.txt");
            gLib.load();
            Set<String> gestureSet = gLib.getGestureEntries();
            for(String gestureName: gestureSet){
                ArrayList<Gesture> list = gLib.getGestures(gestureName);
                for(Gesture g : list) {
                    mGestureList.add(new GestureHolder(g, gestureName));
                }
            }
            if(mGestureList.isEmpty()){
                setContentView(R.layout.empty);
                FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(GestureListActivity.this, SaveGestureActivity.class));
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
    Generates a pop-up menu so that you can enter and save your text for a particular gesture
     */

    public void populateMenu(View view){
        LinearLayout vwParentRow = (LinearLayout)view.getParent().getParent();
        TextView tv = (TextView)vwParentRow.findViewById(R.id.gesture_name_ref);
        mCurrentGestureName = tv.getText().toString();
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.gesture_item_options, popup.getMenu());
        popup.show();
    }

    public void deleteButtonClick(MenuItem item){
        gLib.removeEntry(mCurrentGestureName);
        gLib.save();
        mCurrentGestureName = "";
        onResume();
    }

    /*
    Rename the saved gesture text with other text
     */

    public void renameButtonClick(MenuItem item){
        AlertDialog.Builder namePopup = new AlertDialog.Builder(this);
        namePopup.setTitle(getString(R.string.enter_new_name));
        //namePopup.setMessage(R.string.enter_name);
        final EditText nameField = new EditText(this);
        namePopup.setView(nameField);
        namePopup.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {
                if (!nameField.getText().toString().matches("")) {
                    name = nameField.getText().toString();
                    saveGesture();
                } else {
                    String n = getString(R.string.vn);
                    renameButtonClick(null);  //TODO : validation
                    showToast(n);
                }
            }

        });
        namePopup.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {
                name = "";
                mCurrentGestureName = "";
                return;
            }

        });
        namePopup.show();
    }

    /*
    Saving renamed gestures
     */
    private void saveGesture() {
        ArrayList<Gesture> list = gLib.getGestures(mCurrentGestureName);
        if (list.size() > 0) {
            gLib.removeEntry(mCurrentGestureName);
            gLib.addGesture(name, list.get(0));
            if (gLib.save()) {
                Log.e(TAG, "gesture renamed!");
                onResume();
            }
        }
        name = "";
    }
    private void showToast(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

}
