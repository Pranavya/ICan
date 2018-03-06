package com.pranavya.ican;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GenresList extends Fragment {
    String[] usedGestures = {"Restaurant", "Travel", "Work", "Others"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.genres, container, false);

        ListView listView = (ListView) view.findViewById(R.id.genlist);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String> (
                getActivity(),android.R.layout.simple_list_item_1,usedGestures
        );
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    Intent i = new Intent(GenresList.this.getActivity(), Restaurant.class);
                    startActivity(i);
                }
                else if (position == 1) {
                    Intent i = new Intent(GenresList.this.getActivity(), Travel.class);
                    startActivity(i);
                }
                else if (position == 2) {
                    Intent i = new Intent(GenresList.this.getActivity(), Work.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(GenresList.this.getActivity(), Others.class);
                    startActivity(i);
                }
            }
        });


        return view;
    }
    //@Override
    //protected void OnListItemClick(ListView l, View v, int position, long id) {
    //    super.OnListItemClick(l, v, position, id);
    //}
}

