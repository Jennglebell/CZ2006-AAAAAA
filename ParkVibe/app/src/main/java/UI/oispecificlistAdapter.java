package UI;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharingapp.R;
import Model.oispecific;

import java.util.Collections;
import java.util.List;

public class oispecificlistAdapter extends RecyclerView.Adapter<oispecificlistViewHolder> {

    List<oispecific> oispecificlist = Collections.emptyList();
    Context context;

    public oispecificlistAdapter(List<oispecific> oispecificlist, Context context){
        Log.d("oispecificlistAdapter", "START");
        this.oispecificlist = oispecificlist;
        this.context = context;
    }

    public oispecificlistViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        Log.d("oispecificlistAdapter", "onCreateViewHolder START");
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //inflate the layout
        View photoView = inflater.inflate(R.layout.list_oi_specific_item, parent, false);
        oispecificlistViewHolder viewHolder = new oispecificlistViewHolder(photoView);

        Log.d("oispecificlistAdapter: ", "onCreateViewHolder END");

        return viewHolder;
    }

    public void onBindViewHolder(final oispecificlistViewHolder viewHolder, final int position){
        viewHolder.oispecificName.setText(oispecificlist.get(position).name);
        viewHolder.oispecificDistance.setText(oispecificlist.get(position).distanceFromUser);
        viewHolder.oispecificLocation.setText(oispecificlist.get(position).location);
        Glide.with(context).asBitmap().load(oispecificlist.get(position).imgurl1).into(viewHolder.oispecificImage1);
        Glide.with(context).asBitmap().load(oispecificlist.get(position).imgurl2).into(viewHolder.oispecificImage2);
        Glide.with(context).asBitmap().load(oispecificlist.get(position).imgurl3).into(viewHolder.oispecificImage3);
        Glide.with(context).asBitmap().load(oispecificlist.get(position).imgurl4).into(viewHolder.oispecificImage4);
        Glide.with(context).asBitmap().load(oispecificlist.get(position).imgurl5).into(viewHolder.oispecificImage5);

    }

    public int getItemCount(){
        return oispecificlist.size();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}