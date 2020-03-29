package com.example.sharingapp;


import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

public class oispecificphotosViewHolder extends RecyclerView.ViewHolder{
    ImageView oiphotosimage; //for each image of that specific oi, create a viewHolder object
    ImageButton deleteimage;

    public oispecificphotosViewHolder(View itemView){
        super(itemView);
        oiphotosimage = (ImageView)itemView.findViewById(R.id.oiphotos_specificphoto);
        deleteimage = (ImageButton)itemView.findViewById(R.id.deleteImage);
    }

}

