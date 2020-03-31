package UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sharingapp.R;
import Model.oiphoto;

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
        System.out.println("user:"+ LoginActivity.getName());
         //if(oiphotolist.get(position).getOwner()!=null) {
             if (oiphotolist.get(position).getOwner() == LoginActivity.getName()) {
                 viewHolder.deleteimage.setVisibility(View.VISIBLE);
             }
       //  }

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