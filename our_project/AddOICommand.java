package com.example.sharingapp;

import android.content.Context;

/**
 * Command to add an OI
 */
public class AddOICommand extends Command{

    private OIList OI_list;
    private OI OI;
    private Context context;

    public AddOICommand(OIList OI_list, OI OI, Context context) {
        this.OI_list = OI_list;
        this.OI = OI;
        this.context = context;
    }

    // Save the OI locally
    public void execute(){
        OI_list.addOI(OI);
        super.setIsExecuted(OI_list.saveOIs(context));
    }
}
