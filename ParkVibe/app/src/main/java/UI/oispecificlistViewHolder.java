package UI;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingapp.R;

public class oispecificlistViewHolder extends RecyclerView.ViewHolder {
    TextView oispecificName;
    TextView oispecificDistance;
    TextView oispecificLocation;
    ImageView oispecificImage1;
    ImageView oispecificImage2;
    ImageView oispecificImage3;
    ImageView oispecificImage4;
    ImageView oispecificImage5;

    public oispecificlistViewHolder(View itemView){
        super(itemView);
        oispecificName = (TextView)itemView.findViewById(R.id.oi_specific_name);
        oispecificDistance = (TextView)itemView.findViewById(R.id.oi_specific_distance);
        oispecificLocation = (TextView)itemView.findViewById(R.id.oi_specific_location);
        oispecificImage1 = (ImageView)itemView.findViewById(R.id.oi_specific_image1);
        oispecificImage2 = (ImageView)itemView.findViewById(R.id.oi_specific_image2);
        oispecificImage3 = (ImageView)itemView.findViewById(R.id.oi_specific_image3);
        oispecificImage4 = (ImageView)itemView.findViewById(R.id.oi_specific_image4);
        oispecificImage5 = (ImageView)itemView.findViewById(R.id.oi_specific_image5);
    }

}
