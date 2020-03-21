package com.example.sharingapp;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * OIList class
 */
public class OIList extends Observable {

    private static ArrayList<OI> OIs;
    private String FILENAME = "OIs.sav";

    public OIList() {
        OIs = new ArrayList<OI>();
    }

    public void setOIs(ArrayList<OI> OI_list) {
        OIs = OI_list;
        notifyObservers();
    }

    public ArrayList<OI> getOIs() {
        return OIs;
    }

    public void addOI(OI OI) {
        OIs.add(OI);
        notifyObservers();
    }

    public void deleteOI(OI OI) {
        OIs.remove(OI);
        notifyObservers();
    }

    public OI getOI(int index) {
        return OIs.get(index);
    }

    public int getIndex(OI OI) {
        int pos = 0;
        for (OI i : OIs) {
            if (OI.getId().equals(i.getId())) {
                return pos;
            }
            pos = pos + 1;
        }
        return -1;
    }

    public int getSize() {
        return OIs.size();
    }

    // Used by AvailableOIsFragment, BorrowedOIsFragment, and BiddedOIsFragment
    //
    //distance from OI to user location
    public ArrayList<OI> filterUserDistOI(String user_id) {
        ArrayList<OI> selected_OIs = new ArrayList<>();
        for (OI i: OIs) {
            if (i.getDistance(User user) <= 5) {
                selected_OIs.add(i);
            }
        }
        return selected_OIs;
    }

    //distance from OI to specified (pinned) location
    public ArrayList<OI> filterLocationDistOI(double lat, double lon) {
        ArrayList<OI> selected_OIs = new ArrayList<>();
        for (OI i: OIs) {
            if (i.calculateDistance(lat,lon,i.latitude,i.longitude) <= 5) {
                selected_OIs.add(i);
            }
        }
        return selected_OIs;
    }

    public ArrayList<OI> filterHashtagsOI(String user_id, int BBQ, int toilet, int pavilion, int tree) {
        selected_OIs = this.filterUserDistOI(user_id);
        ArrayList<OI> selected_OIs2 = new ArrayList<>();

        for (OI i: selected_OIs) {
            if ((i.hashtag == "BBQ" && BBQ == 1)||(i.hashtag == "toilet" && toilet == 1)||(i.hashtag == "pavilion" && pavilion == 1)||(i.hashtag == "tree" && tree == 1)){
                selected_OIs2.add(i);
            }
        }
        return selected_OIs2;
    }

    // Used by AllOIsFragment
    public ArrayList<OI> getMyOIs(String user_id) {
        ArrayList<OI> selected_OIs = new ArrayList<>();
        for (OI i: OIs) {
            if (i.getOwnerId().equals(user_id)) {
                selected_OIs.add(i);
            }
        }
        return selected_OIs;
    }

    // Used by SearchOIsActivity
    public ArrayList<OI> getSearchOIs(String user_id) {
        ArrayList<OI> selected_OIs = new ArrayList<>();
        for (OI i: OIs) {
            if (!i.getOwnerId().equals(user_id) && !i.getStatus().equals("Borrowed")) {
                selected_OIs.add(i);
            }
        }
        return selected_OIs;
    }

    // Used by BorrowedOIsActivity
    public ArrayList<OI> getBorrowedOIsByUsername(String username) {
        ArrayList<OI> selected_OIs = new ArrayList<>();
        for (OI i: OIs) {
            if (i != null && i.getBorrower() != null) {
                if (i.getBorrowerUsername().equals(username)) {
                    selected_OIs.add(i);
                }
            }
        }
        return selected_OIs;
    }

    public OI getOIById(String id){
        for (OI i: OIs) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public void loadOIs(Context context) {

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<OI>>() {
            }.getType();
            OIs = gson.fromJson(isr, listType); // temporary
            fis.close();
        } catch (FileNotFoundException e) {
            OIs = new ArrayList<OI>();
        } catch (IOException e) {
            OIs = new ArrayList<OI>();
        }
        notifyObservers();
    }

    public boolean saveOIs(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(OIs, osw);
            osw.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

//    //update OIList
//    public ArrayList<Contact> getActiveBorrowers() {
//        ArrayList<Contact> active_borrowers = new ArrayList<Contact>();
//        for (OI i : OIs ) {
//            Contact borrower = i.getBorrower();
//            if (borrower != null ) {
//                active_borrowers.add(borrower);
//            }
//        }
//        return active_borrowers;
//    }
//    public ArrayList<OI> filterOIsByStatus(String status){
//        ArrayList<OI> selected_OIs = new ArrayList<>();
//        for (OI i: OIs ) {
//            if (i.getStatus().equals(status)) {
//                selected_OIs.add(i);
//            }
//        }
//        return selected_OIs;
//    }
}
