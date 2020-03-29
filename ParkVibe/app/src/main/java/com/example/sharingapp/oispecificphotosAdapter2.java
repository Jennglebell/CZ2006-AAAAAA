package com.example.sharingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collections;
import java.util.List;

public class oispecificphotosAdapter2 extends RecyclerView.Adapter<oispecificphotosViewHolder> {

    List<oiphoto> oiphotolist = Collections.emptyList();
    Context context;


    public oispecificphotosAdapter2(List<oiphoto> oiphotolist, Context context) {
        this.oiphotolist = oiphotolist;
        this.context = context;
    }

    public oispecificphotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //inflate the layout
        View photoView = inflater.inflate(R.layout.specific_oi_photos_item, parent, false);
        oispecificphotosViewHolder viewHolder = new oispecificphotosViewHolder(photoView);

        return viewHolder;
    }

    public void onBindViewHolder(final oispecificphotosViewHolder viewHolder, final int position) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);
        Glide.with(context).asBitmap().load(oiphotolist.get(position).imgurl).apply(requestOptions).into(viewHolder.oiphotosimage);
         System.out.println("Photo owner:"+oiphotolist.get(position).getOwner());
<<<<<<< HEAD:ParkVibe/app/src/main/java/com/example/sharingapp/oispecificphotosAdapter2.java
        System.out.println("user:"+LoginActivity.getName());
         //if(oiphotolist.get(position).getOwner()!=null) {
             if (oiphotolist.get(position).getOwner() == LoginActivity.getName()) {
                 viewHolder.deleteimage.setVisibility(View.VISIBLE);
             }
       //  }
=======
         if(oiphotolist.get(position).getOwner()!=null) {
             if (oiphotolist.get(position).getOwner() == LoginActivity.getName()) {
                 viewHolder.deleteimageButton.setVisibility(View.VISIBLE);
             }
         }
>>>>>>> e1396f3bd08f7218e2d6c967d7f04c4b7f3add8f:SharingApp_/app/src/main/java/com/example/sharingapp/oispecificphotosAdapter.java

    }

    public int getItemCount() {
        if(oiphotolist!=null) {
            return oiphotolist.size();
        }
        else
            return 0;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}