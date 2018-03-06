package com.pranavya.ican;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class ReadData extends AppCompatActivity  {
    TextToSpeech t1;
    final static String fileName = "genres.txt";
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
    final static String TAG = ReadData.class.getName();
    ListView simpleList;
    ArrayList<String> arr = new ArrayList<String>();

    public static  String ReadFile( Context context){
        String line = null;

        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return line;
    }

    public static boolean saveToFile( String data){
        try {
            new File(path  ).mkdir();
            File file = new File(path+ fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());

            return true;
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return  false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            final ArrayList<String> arr = new ArrayList<String>();

            String sCurrentLine;
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                arr.add(sCurrentLine);
            }

            simpleList = (ListView)findViewById(R.id.glist);
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
            simpleList.setAdapter(arrayAdapter);


            simpleList.setOnItemClickListener(new OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    String toSpeak = arr.get(position);
                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    TextToSpeech t1;
    final static String fileName = "restaurant.txt";
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
    final static String TAG = ReadData.class.getName();
    ListView simpleList;
    ArrayList<String> arr = new ArrayList<String>();

    public static  String ReadFile(Context context){
        String line = null;

        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return line;
    }

    public static boolean saveToFile( String data){
        try {
            new File(path  ).mkdir();
            File file = new File(path+ fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());
            return true;
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return  false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            final ArrayList<String> arr = new ArrayList<String>();

            String sCurrentLine;
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                arr.add(sCurrentLine);
            }

            simpleList = (ListView)findViewById(R.id.restaurantlist);
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
            simpleList.setAdapter(arrayAdapter);


            simpleList.setOnItemClickListener(new OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    String toSpeak = arr.get(position);
                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
