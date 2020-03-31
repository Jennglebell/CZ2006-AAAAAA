package UI;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sharingapp.R;

import java.util.ArrayList;

import Control.ItemController;
import Model.Item;

/**
 * ItemFragmentAdapter is responsible for what information is displayed in ListView entries.
 */
public class ItemFragmentAdapter extends ArrayAdapter<Item> {
    private LayoutInflater inflater;
    private Fragment fragment;
    private Context context;

    public ItemFragmentAdapter(Context context, ArrayList<Item> items, Fragment fragment) {
        super(context, 0, items);
        this.inflater = LayoutInflater.from(context);
        this.fragment = fragment;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);
        ItemController item_controller = new ItemController(item);

        String title = "Name: " + item_controller.getName();
        String id = "Hashtag: " + item_controller.getHashtag();
        Bitmap thumbnail = item_controller.getImage();
        String description = "Description: " + item_controller.getDescription();

        // Check if an existing view is being reused, otherwise inflate the view.
        if (convertView == null) {
            convertView = inflater.from(context).inflate(R.layout.itemlist_item, parent, false);
        }

        TextView title_tv = (TextView) convertView.findViewById(R.id.title_tv);
        TextView id_tv = (TextView) convertView.findViewById(R.id.status_tv);
        TextView description_tv = (TextView) convertView.findViewById(R.id.description_tv);
        ImageView photo = (ImageView) convertView.findViewById(R.id.image_view);

        if (thumbnail != null) {
            photo.setImageBitmap(thumbnail);
        } else {
            photo.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        title_tv.setText(title);
        description_tv.setText(description);

        // AllItemFragments: itemlist_item shows title, description and status
        if (fragment instanceof AllItemsFragment) {
            id_tv.setText(id);
        }

        return convertView;
    }
}
