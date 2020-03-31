package com.example.sharingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Displays a list of all items
 */
public class AllItemsFragment extends ItemsFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        super.setUserId(this.getArguments());
        String oitype = getArguments().getString("oitype");
        System.out.println("AllFragemnt oitype:" + oitype);
        super.setVariables(R.layout.all_items_fragment, R.id.my_items);
        super.loadItems(AllItemsFragment.this);
        super.setFragmentOnItemLongClickListener();

        return rootView;
    }

    public ArrayList<Item> filterItems() {
        return item_list_controller.getItems();
    }
    public ArrayList<Item> filterItems(String oitype) {
        return item_list_controller.getItems(oitype);
    }
}
