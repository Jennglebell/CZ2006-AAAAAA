package com.example.sharingapp;


import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class oispecificphotosViewHolder extends RecyclerView.ViewHolder{
    ImageView oiphotosimage; //for each image of that specific oi, create a viewHolder object
    ImageButton deleteimage;
    ImageButton likeimage;
    TextView like_num ;

    public oispecificphotosViewHolder(View itemView){
        super(itemView);
        oiphotosimage = (ImageView)itemView.findViewById(R.id.oiphotos_specificphoto);
        deleteimage = (ImageButton)itemView.findViewById(R.id.deleteImage);
        likeimage = (ImageButton)itemView.findViewById(R.id.like);
        likeimage.setImageResource(R.drawable.like);
        like_num = (TextView) itemView.findViewById(R.id.like_num);

    }

}

