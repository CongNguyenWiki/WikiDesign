package com.example.wikidesign.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wikidesign.R;

public class FragmentDiscover extends Fragment {

    public static FragmentDiscover newInstance(){
        FragmentDiscover fragmentDiscover = new FragmentDiscover();
        return fragmentDiscover;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
