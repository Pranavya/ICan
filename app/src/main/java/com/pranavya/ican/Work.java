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

public class Work extends AppCompatActivity {
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
        setContentView(R.layout.work);

        verifyStoragePermissions(this);
        txtInput = (EditText) findViewById(R.id.txtInput3);

        btnRead = (Button) findViewById(R.id.btnRead3);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Work.this, ReadWorkData.class);
                startActivity(intent);
            }
        });

        btnSave = (Button) findViewById(R.id.btnSave3);
        final String sf = getString(R.string.save_to_file);
        final String esf = getString(R.string.error_to_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ReadWorkData.saveToFile( txtInput.getText().toString())){
                    Toast.makeText(Work.this,sf,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Work.this,esf,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}