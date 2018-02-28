package com.pranavya.ican;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Genres extends Fragment {
    String[] usedGestures = {"Restaurant", "Travel", "Work", "Others"};
   // ListView listView;
   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fused, container, false);

       //String[] usedGestures = {"Hello", "How are you?", "Excuse me!!", "Could you like to help me!!", "Sorry"};
       ListView listView = (ListView) view.findViewById(R.id.rUsed);

       ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String> (
               getActivity(),android.R.layout.simple_list_item_1,usedGestures
       );

       listView.setAdapter(listViewAdapter);


       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view,
                                   int position, long id) {
               String genre = usedGestures[position];
               if (genre == "Restaurant") {
                   Intent i = new Intent(Genres.this.getActivity(), SaveGenres.class);
                   startActivity(i);
               }
               else if (genre == "Travel") {
                   Intent i = new Intent(Genres.this.getActivity(), Travel.class);
                   startActivity(i);
               }
               else if (genre == "Work") {
                   Intent i = new Intent(Genres.this.getActivity(), Work.class);
                   startActivity(i);
               }
               else {
                   Intent i = new Intent(Genres.this.getActivity(), Others.class);
                   startActivity(i);
               }
           }
       });


       return view;        //return inflater.inflate(R.layout.fused, container, false);
   }


/*        View view = inflater.inflate(R.layout.genres, container, false);

        listView = (ListView) view.findViewById(R.id.genlist);
        ArrayAdapter<String> listViewAdapter;
        listViewAdapter = new ArrayAdapter<String> (
                getActivity(),android.R.layout.simple_list_item_1,usedGestures
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String genre = usedGestures[position];
                if (genre == "SaveGenres") {
                    Intent i = new Intent(Genres.this.getActivity(), SaveGenres.class);
                    startActivity(i);
                }
                else if (genre == "Travel") {
                    Intent i = new Intent(Genres.this.getActivity(), Travel.class);
                    startActivity(i);
                }
                else if (genre == "Work") {
                    Intent i = new Intent(Genres.this.getActivity(), Work.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(Genres.this.getActivity(), Others.class);
                    startActivity(i);
                }
            }
        });
        listView.setAdapter(listViewAdapter);
        return view;


    }*/

}
