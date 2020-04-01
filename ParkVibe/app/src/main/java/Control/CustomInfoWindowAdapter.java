package Control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.sharingapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context mContext;

    public CustomInfoWindowAdapter(Context context){
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.activity_edit_item1, null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return mWindow;
    }
}
