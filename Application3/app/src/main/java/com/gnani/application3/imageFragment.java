package com.gnani.application3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class imageFragment extends Fragment {

    private ImageView seriesImage;
    private int imgResource = -1;
    int position;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for fragment
        return inflater.inflate(R.layout.imagelayout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // To restore the previous selection
        seriesImage = (ImageView) getActivity().findViewById(R.id.seriesImage);
        if (imgResource != -1)
            seriesImage.setImageResource(imgResource);
    }

    // Returns the current position of the image .
    int getImage() {
        return position;
    }

    // Show the Image based on position
    void showImage(int index) {
        position = index;
        seriesImage.setImageResource(MainActivity.img.get(position));
    }

}