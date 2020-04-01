package UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import Model.ItemList;

import java.util.ArrayList;

import Control.ItemListController;
import Model.Item;
import Model.Observer;

/**
 * Superclass of AvailableItemsFragment, BorrowedItemsFragment, BiddedItemsFragment and AllItemsFragment
 */
public class ItemsFragment extends Fragment implements Observer {

    private static String oi_type;
    private ItemList item_list = new ItemList();

    ItemListController item_list_controller = new ItemListController(item_list);
    View rootView;
    String user_id;

    private ListView list_view;
    private ArrayAdapter<Item> adapter;
    private ArrayList<Item> selected_items;
    private LayoutInflater inflater;
    private ViewGroup container;
    private Context context;
    private Fragment fragment;
    private boolean update = false;
    //String oi_type;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = getContext();
        Intent intent = new Intent();
      //  oi_type = intent.getStringExtra("oi_type");
    //    oi_type="BBQ";
//        if (getArguments() != null) {
//            oi_type = getArguments().getString("oi_type");
//        }
        System.out.println("oitype in itemFragment" + oi_type);
        item_list_controller.loadItems(context); // Call to update() suppressed
        update = true; // Future calls to update() permitted

        this.inflater = inflater;
        this.container = container;


        return rootView;
    }

    public static void setOitype(String oitype) {
        oi_type = oitype;
    }


    public void setVariables(int resource, int id ) {
        rootView = inflater.inflate(resource, container, false);
        list_view = (ListView) rootView.findViewById(id);
        selected_items = filterItems(oi_type);

    }

    public void setUserId(Bundle b) {
        this.user_id = b.getString("user_id", user_id);
    }

    public void loadItems(Fragment fragment){
        this.fragment = fragment;
        item_list_controller.addObserver(this);
        item_list_controller.loadItems(context);
    }

    public void setFragmentOnItemLongClickListener(){
        // When item is long clicked, this starts EditItemActivity
        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                Item item = adapter.getItem(pos);
                String item_id = item.getId();
                int meta_pos = item_list_controller.getIndex(item);
                if (meta_pos >= 0) {

                    Intent edit = new Intent(context, EditItemActivity.class);
                    edit.putExtra("id", item_id);
                    edit.putExtra("position", meta_pos);
                    startActivity(edit);
                }
                return true;
            }
        });
    }



    /**
     * filterItems is implemented independently by AvailableItemsFragment, BorrowedItemsFragment
     * BiddedItemsFragment and AllItemsFragment
     * @return selected_items
     */
    //public abstract ArrayList<Item> filterItems();
    public  ArrayList<Item> filterItems(String oitype){
        return item_list_controller.getItems(oitype);
    };

    /**
     * Called when the activity is destroyed, thus we remove this fragment as an observer
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        item_list_controller.removeObserver(this);
    }

    /**
     * Update the view
     */
    public void update(){
        if (update) {
            selected_items = filterItems(oi_type); // Ensure items are filtered
            adapter = new ItemFragmentAdapter(context, selected_items, fragment);
            list_view.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
