package UI;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingapp.R;

public class oilistViewHolder extends RecyclerView.ViewHolder {
    TextView oiName;
    ImageView oiImage;
    CardView oiparentCard;
    Fragment oifragment;

    public oilistViewHolder(@NonNull View itemView) {
        super(itemView);
        oiName = (TextView)itemView.findViewById(R.id.oilistname);
        oiImage = (ImageView)itemView.findViewById(R.id.oilistimage);
        oiparentCard = (CardView) itemView.findViewById(R.id.oilistcard);
    }

}
