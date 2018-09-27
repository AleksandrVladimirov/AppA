package com.example.asvladimirov.appa;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class historyFragment extends Fragment {

    private String title;
    private int page;
//    public static String sort;

    private ArrayList<Image> imageArrayList = new ArrayList<Image>();
    private RecyclerViewAdapter adapter;

    final Uri CONTACT_URI = Uri
            .parse("content://com.example.asvladimirov.provider.ImageURL/images");
//    private ListView listView;
    private RecyclerView historyList;

    public static historyFragment newInstance(int page, String title) {
        historyFragment historyFragment = new historyFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        historyFragment.setArguments(args);
        return historyFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, null);

        historyList = view.findViewById(R.id.historyList);

        Cursor cursor = getActivity().getContentResolver().query(CONTACT_URI, null, null,
                null, null);

        if(cursor != null) {
            if(cursor.moveToFirst()){
                do{
                    Image image = new Image();
                    image.setStatus(cursor.getInt(cursor.getColumnIndex("Status")));
                    image.setUri(cursor.getString(cursor.getColumnIndex("Uri")));
                    image.setTime(cursor.getString(cursor.getColumnIndex("Time")));
                    imageArrayList.add(image);

                } while (cursor.moveToNext());
            }
            adapter = new RecyclerViewAdapter(getActivity(), imageArrayList);

            adapter.setOnImageSelectedListener(new OnImageSelectedListener() {
                @Override
                public void onImageSelectedListener(String uri, int status) {
                    if(uri != null) {
                        Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.example.asvladimirov.appb");
                        if (intent != null) {
                            intent.putExtra("URI_2", uri);
                            intent.putExtra("INT_URI_2", status);
                            startActivity(intent);
                        }
                    }
                }
            });

            adapter.notifyDataSetChanged();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

            historyList.setLayoutManager(linearLayoutManager);
            historyList.setAdapter(adapter);

        }

        return view;
    }

}