package com.pranavya.ican;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Restaurant extends AppCompatActivity {


    Button btnRead , btnSave;
    EditText txtInput;

    // Storage Permissions variables
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //permission method.
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);

        verifyStoragePermissions(this);
        //txtContent = (TextView) findViewById(R.id.txtContent);
        txtInput = (EditText) findViewById(R.id.txtInput);
//        listv = (ListView) findViewById(R.id.genres_list);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant.this, ReadData.class);
                startActivity(intent);
                //txtContent.setText(ReadData.ReadFile(Restaurant.this));
            }
        });

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ReadData.saveToFile( txtInput.getText().toString())){
                    Toast.makeText(Restaurant.this,"Saved to file",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Restaurant.this,"Error save file!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

   /* @Override
    public void finishActivity(int requestCode) {
        super.finishActivity(requestCode);
    }*/
    /*
    Button btnRead , btnSave;
    EditText txtInput;
    //TextView txtContent;


    // Storage Permissions variables
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //permission method.
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);

        verifyStoragePermissions(this);
        //txtContent = (TextView) findViewById(R.id.txtContent);
        txtInput = (EditText) findViewById(R.id.editText);
        //listv = (ListView) findViewById(R.id.genres_list);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //txtContent.setText(ReadData.ReadFile(Restaurant.this));
                Intent intent = new Intent(Restaurant.this, ReadData.class);
                startActivity(intent);
            }
        });

        /*gestureScreen = (Button) findViewById(R.id.gesturescreen);
        gestureScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant.this, SaveGestureActivity.class);
                startActivity(intent);
            }

        });*/

  /*      btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ReadData.saveToFile( txtInput.getText().toString())){
                    Toast.makeText(Restaurant.this,"Saved to file",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Restaurant.this,"Error save file!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/
}
