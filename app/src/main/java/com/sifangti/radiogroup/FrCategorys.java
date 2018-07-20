package com.sifangti.radiogroup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FrCategorys extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_categorys,container,false);
        return view;
    }
}
