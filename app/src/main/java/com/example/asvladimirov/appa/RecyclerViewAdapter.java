package com.example.asvladimirov.appa;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

class ImageViewHolder extends RecyclerView.ViewHolder{

    public TextView uri;
    public TextView data;
    public int status;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        uri = itemView.findViewById(R.id.uriImage);
        data = itemView.findViewById(R.id.timeImage);
    }
}

public class RecyclerViewAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private Activity activity;
    private List<Image> images = Collections.EMPTY_LIST;
    private OnImageSelectedListener listener;

    RecyclerViewAdapter(Activity activity, List<Image> images) {
        this.activity = activity;
        this.images = images;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_item, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        ImageViewHolder viewHolder = imageViewHolder;
        viewHolder.uri.setText(images.get(i).getUri());
        final String uri = images.get(i).getUri();
        viewHolder.data.setText(images.get(i).getTime());
        final int status = images.get(i).getStatus();
        if(status == 1){
            viewHolder.itemView.setBackgroundColor(Color.GREEN);
        } else if(status == 2){
            viewHolder.itemView.setBackgroundColor(Color.RED);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onImageSelectedListener(uri, status);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void setOnImageSelectedListener(OnImageSelectedListener listener){
        this.listener = listener;
    }
}
