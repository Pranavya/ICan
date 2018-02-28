package com.pranavya.ican;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Sucharita on 24-02-2018.
 */

public class FUsed  extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fused, container, false);

        String[] usedGestures = {"Hello", "How are you?", "Excuse me!!", "Could you like to help me!!", "Sorry"};
        ListView listView = (ListView) view.findViewById(R.id.rUsed);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String> (
                getActivity(),android.R.layout.simple_list_item_1,usedGestures
        );

        listView.setAdapter(listViewAdapter);

        return view;        //return inflater.inflate(R.layout.fused, container, false);
    }
}
