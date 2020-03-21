package com.example.sharingapp;

import android.content.Context;

import java.util.ArrayList;

/**
 * OIListController is responsible for all communication between views and OIList model
 */

public class OIListController {

    private OIList OI_list;

    public OIListController(OIList OI_list){
        this.OI_list = OI_list;
    }

    public void setOIs(ArrayList<OI> OI_list) {
        this.OI_list.setOIs(OI_list);
    }

    public ArrayList<OI> getOIs() {
        return OI_list.getOIs();
    }

    public ArrayList<OI> getMyOIs(String user_id) {
        return OI_list.getMyOIs(user_id);
    }

    public boolean addOI(OI OI, Context context){
        AddOICommand add_OI_command = new AddOICommand(OI_list, OI, context);
        add_OI_command.execute();
        return add_OI_command.isExecuted();
    }

    public boolean deleteOI(OI OI, Context context) {
        DeleteOICommand delete_OI_command = new DeleteOICommand(OI_list, OI, context);
        delete_OI_command.execute();
        return delete_OI_command.isExecuted();
    }

    public boolean editOI(OI OI, OI updated_OI, Context context){
        EditOICommand edit_OI_command = new EditOICommand(OI_list, OI, updated_OI, context);
        edit_OI_command.execute();
        return edit_OI_command.isExecuted();
    }

    public OI getOI(int index) {
        return OI_list.getOI(index);
    }

    public int getIndex(OI OI) {
        return OI_list.getIndex(OI);
    }

    public int getSize() {
        return OI_list.getSize();
    }

    public ArrayList<OI> filterOIs(String user_id, String status) {
        return OI_list.filterOIs(user_id, status);
    }

    public ArrayList<OI> getSearchOIs(String user_id) {
        return OI_list.getSearchOIs(user_id);
    }

    public ArrayList<OI> getBorrowedOIsByUsername(String username) {
        return OI_list.getBorrowedOIsByUsername(username);
    }

    public OI getOIById(String id){
        return OI_list.getOIById(id);
    }

    public void loadOIs(Context context) {
        OI_list.loadOIs(context);
    }

    public void addObserver(Observer observer) {
        OI_list.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        OI_list.removeObserver(observer);
    }
}
