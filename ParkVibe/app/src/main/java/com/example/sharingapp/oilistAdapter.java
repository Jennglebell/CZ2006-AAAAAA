package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

public class oilistAdapter extends RecyclerView.Adapter<oilistViewHolder> {

    List<oi> oilist = Collections.emptyList();
    Context context;

    public oilistAdapter(List<oi> oilist, Context context){
        this.oilist = oilist;
        this.context = context;
    }

    public oilistViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //inflate the layout
        View photoView = inflater.inflate(R.layout.list_oi_item, parent, false);
        oilistViewHolder viewHolder = new oilistViewHolder(photoView);

        return viewHolder;
    }

    public void onBindViewHolder(final oilistViewHolder viewHolder, final int position) {
        System.out.println("oilistAdapter starting onBindViewHolder");
        viewHolder.oiName.setText(oilist.get(position).name);
        Glide.with(context).asBitmap().load(oilist.get(position).imgurl).into(viewHolder.oiImage);

        viewHolder.oiparentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("onBindViewHolder clicked: " + oilist.get(position).name);

                Intent intent = new Intent(context, ListUI.class);
                //intent.getExtras("")
                intent.putExtra("oi_type", oilist.get(position).name);
               // intent.putExtra("oi_type", oilist.get(position).name);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); //this will probably cause issues when navigating from other pages. research alternatives.
                context.startActivity(intent);


            }
        });
    }
    /*private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.specificoilistfragment, fragment);
        fragmentTransaction.commit();

    }*/

    public int getItemCount(){
        return oilist.size();
    }
/*
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }*/

}
