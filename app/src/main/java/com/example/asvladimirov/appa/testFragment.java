package com.example.asvladimirov.appa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class testFragment extends Fragment {

    private String title;
    private int page;

    private EditText editText;
    private Button buttonOk;

    public static testFragment newInstance(int page, String title) {
        testFragment testFragment = new testFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        testFragment.setArguments(args);
        return testFragment;
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
        View view = inflater.inflate(R.layout.test_fragment, null);

        editText = view.findViewById(R.id.editText);
        buttonOk = view.findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entryText = editText.getText().toString();

                Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.example.asvladimirov.appb");
                if(!entryText.isEmpty()) {
                    intent.putExtra("URI", entryText);
                    startActivity(intent);
                }

            }
        });
        return view;
    }
}