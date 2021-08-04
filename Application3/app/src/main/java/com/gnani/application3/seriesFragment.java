package com.gnani.application3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;


public class seriesFragment extends ListFragment {

    ListAdapter adapter;
    private String[] series_name;
    private Integer position = null;

    // Interface to notify the main activity once selected .
    public interface OnListItemSelectedListener {
        void onSeriesListItemSelected(int selected);
        public String[] getSeriesTitle();
    }

    private int selection = -1;
    private ListView seriesList;
    private OnListItemSelectedListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) throws ClassCastException {
        super.onAttach(context);
        this.listener = (OnListItemSelectedListener) context;

        if(series_name == null) {
            series_name = listener.getSeriesTitle();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for fragment
        View view =  super.onCreateView(inflater, container, savedInstanceState) ;
        if (savedInstanceState != null) {
            int previousPosition = savedInstanceState.getInt("savedIndex") ;
            position = previousPosition ;
        }
        else
            position = null ;

        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Can set mode to either single or multiple
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // Set list adapter
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.serieslayout, MainActivity.series_name));

//        seriesList = getView().findViewById(R.id.title);
//        seriesList.setAdapter(adapter);
//        seriesList.setOnItemClickListener((parent, view, position, id) -> {
//            selection = position;
//            this.listener.onSeriesListItemSelected(selection);
//        });

//        if (selection > 0) {
//            seriesList.setItemChecked(selection, true);
//            this.listener.onSeriesListItemSelected(selection);
//        }
    }

    // Called once the series name is selected by the user .
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        getListView().setItemChecked(pos, true);
        // To inform main Activity once the item is selected .
        listener.onSeriesListItemSelected(pos);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (position != null) {
            getListView().setSelection(position) ;
            // To inform main activity about the position selected
            listener.onSeriesListItemSelected(position);
        }
    }

}

