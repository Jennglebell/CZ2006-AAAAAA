package com.example.sharingapp;


import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

public class oispecificphotosViewHolder extends RecyclerView.ViewHolder{
    ImageView oiphotosimage; //for each image of that specific oi, create a viewHolder object
<<<<<<< HEAD:ParkVibe/app/src/main/java/com/example/sharingapp/oispecificphotosViewHolder.java
    ImageButton deleteimage;
=======
    ImageButton deleteimageButton;
>>>>>>> e1396f3bd08f7218e2d6c967d7f04c4b7f3add8f:SharingApp_/app/src/main/java/com/example/sharingapp/oispecificphotosViewHolder.java

    public oispecificphotosViewHolder(View itemView){
        super(itemView);
        oiphotosimage = (ImageView)itemView.findViewById(R.id.oiphotos_specificphoto);
<<<<<<< HEAD:ParkVibe/app/src/main/java/com/example/sharingapp/oispecificphotosViewHolder.java
        deleteimage = (ImageButton)itemView.findViewById(R.id.deleteImage);
=======
        deleteimageButton = (ImageButton)itemView.findViewById(R.id.deleteImageButton);
>>>>>>> e1396f3bd08f7218e2d6c967d7f04c4b7f3add8f:SharingApp_/app/src/main/java/com/example/sharingapp/oispecificphotosViewHolder.java
    }

}

