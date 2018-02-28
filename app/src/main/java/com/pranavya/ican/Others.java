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

/**
 * Created by Bindu on 2/27/2018.
 */

public class Others extends AppCompatActivity {
    Button btnRead , btnSave;
    EditText txtInput;
    //TextView txtContent;
    //  ListView listv;


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
        setContentView(R.layout.others);

        verifyStoragePermissions(this);
        //txtContent = (TextView) findViewById(R.id.txtContent);
        txtInput = (EditText) findViewById(R.id.txtInput2);
//        listv = (ListView) findViewById(R.id.genres_list);

        btnRead = (Button) findViewById(R.id.btnRead2);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Others.this, ReadOthersData.class);
                startActivity(intent);
                //txtContent.setText(ReadData.ReadFile(SaveGenres.this));
            }
        });

        btnSave = (Button) findViewById(R.id.btnSave2);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ReadOthersData.saveToFile( txtInput.getText().toString())){
                    Toast.makeText(Others.this,"Saved to file",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Others.this,"Error save file!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
