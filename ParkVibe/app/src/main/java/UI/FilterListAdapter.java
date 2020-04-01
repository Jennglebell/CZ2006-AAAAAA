package UI;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sharingapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Model.oi;

public class FilterListAdapter extends ArrayAdapter {
    private final Activity context;
    List<oi> oilist = Collections.emptyList();

    public FilterListAdapter(List<oi> oilist, Activity context){
        super(context, R.layout.filter_oi_item , oilist);
        this.oilist = oilist;
        this.context = context;
    }

    public View getView(final int position, View view, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.filter_oi_item, null,true);

        TextView OIname = (TextView) rowView.findViewById(R.id.oi_name);
        final Switch OIswitch = (Switch) rowView.findViewById(R.id.oi_switch);
        ImageView OIimage = (ImageView) rowView.findViewById(R.id.oi_image);

        OIname.setText(oilist.get(position).name);
        OIswitch.setChecked(oilist.get(position).applied);
        Glide.with(context).asBitmap().load(oilist.get(position).imgurl).into(OIimage);

        OIswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (OIswitch.isChecked()) {
                    oilist.get(position).applied = true;
                    System.out.println("name of oi: " + oilist.get(position).name);
                    System.out.println("applied state = " + oilist.get(position).applied);
                }
                else{
                    oilist.get(position).applied = false;
                    System.out.println("name of oi: " + oilist.get(position).name);
                    System.out.println("applied state = " + oilist.get(position).applied);
                }
            }
        });

        return rowView;

    }




}
