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
   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.genres, container, false);

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
               switch(genre) {
                   case "Restaurant":
                       Intent i = new Intent(Genres.this.getActivity(), Restaurant.class);
                       startActivity(i);
                       break;
                   case "Travel":
                       Intent i1 = new Intent(Genres.this.getActivity(), Travel.class);
                       startActivity(i1);
                       break;
                   case "Work":
                       Intent i2 = new Intent(Genres.this.getActivity(), Work.class);
                       startActivity(i2);
                       break;
                   case "Others":
                       Intent i3 = new Intent(Genres.this.getActivity(), Others.class);
                       startActivity(i3);
                       break;
               }
           }
       });
       return view;
   }
}
