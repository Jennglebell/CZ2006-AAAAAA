package Control;

import android.content.Context;

import Model.Item;
import Model.ItemList;

/**
 * Command to edit pre-existing item
 */
public class EditItemCommand extends Command {
    private ItemList item_list;
    private Item old_item;
    private Item new_item;
    private Context context;

    public EditItemCommand(ItemList item_list, Item old_item, Item new_item, Context context) {
        this.item_list = item_list;
        this.old_item = old_item;
        this.new_item = new_item;
        this.context = context;
    }

    // Delete the old item locally, save the new item locally
    public void execute() {
        item_list.deleteItem(old_item);
        item_list.addItem(new_item);
        super.setIsExecuted(item_list.saveItems(context));
    }
}