//package com.example.sharingapp;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.StaggeredGridLayoutManager;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.util.Log;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class ListUI extends AppCompatActivity {
//
//    oilistAdapter adapter;
//    RecyclerView recyclerView;
//
//    oispecificlistAdapter specificlistAdapter;
//    RecyclerView oispecificRecyclerView;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.list_oi);
//
//        //getIncomingIntent();
//
//        List<oi> oi_list = new ArrayList<>();
//        oi_list = getData();
//
//        recyclerView = (RecyclerView) findViewById(R.id.horizontalrecyclerView);
//        adapter = new oilistAdapter(oi_list, this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setLayoutManager(new LinearLayoutManager(ListUI.this, LinearLayoutManager.HORIZONTAL, false));
//
//        Log.d("ListUI", "Running end");
//
//    }
//
//    protected void onNewIntent(Intent intent) {
////        System.out.println("OnNewIntent running!");
////        if (intent.hasExtra("oi_type")) {
////            String oi_type = intent.getStringExtra("oi_type");
////
////            System.out.println("Intent received!" + oi_type);
////
////            Bundle bundle = new Bundle();
////            bundle.putString("oi_type", oi_type);
////            oispecificlistFragment fragobj = new oispecificlistFragment();
////            fragobj.setArguments(bundle);
////
////            System.out.println("MainActivity bundle received!" + oi_type);
////
////            FragmentManager fragmentManager = getSupportFragmentManager();
////            FragmentTransaction transaction = fragmentManager.beginTransaction();
////            transaction.replace(R.id.emptyfragment, fragobj);
////            transaction.commit();
////
////        }
//    }
//
///*    private void getIncomingIntent() {
//        if (getIntent().hasExtra("oi_type")) {
//            String oi_type = getIntent().getStringExtra("oi_type");
//            setoispecificRecyclerView(oi_type);
//        }
//    }*/
//
///*    private void setoispecificRecyclerView(String oi_type) {
//        Log.d("ListUI", "setoispecificRecyclerView START ");
//
//        List<oispecific> oi_specific_list = new ArrayList<>();
//        oi_specific_list = getSpecificData(oi_type);
//
//        oispecificRecyclerView = (RecyclerView) findViewById(R.id.specificoirecyclerView);
//        specificlistAdapter = new oispecificlistAdapter(oi_specific_list, this);
//        oispecificRecyclerView.setAdapter(specificlistAdapter);
//        oispecificRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//
//        Log.d("ListUI", "setoispecificRecyclerView END ");
//
//    }*/
//
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
//
//    private List<oi> getData() {
//        List<oi> oilist = new ArrayList<>();
//        oilist.add(new oi("HT", false, "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//        oilist.add(new oi("BBQ", true, "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052"));
//        oilist.add(new oi("Heritage Trees", false, "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        //GET ALL OI DATA HERE. above is dummy data. add to the oilist.
//        return oilist;
//    }
//
//
//
//
//}
///*
//
//    private List<oispecific> getSpecificData(String oi_type) {
//        Log.d("getSpecificData", "oi passed" + oi_type);
//        List<oispecific> oispecificlist = new ArrayList<>();
//        oispecificlist.add(new oispecific("Tree1", "Heritage Trees", "Bishan Park", "(100m away)",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        oispecificlist.add(new oispecific("Tree2", "Heritage Trees", "Punggol Park", "(150m away)",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        oispecificlist.add(new oispecific("Tree3", "Heritage Trees", "Punggol Park", "(150m away)",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        oispecificlist.add(new oispecific("Tree4", "Heritage Trees", "Punggol Park", "(150m away)",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        oispecificlist.add(new oispecific("Tree5", "Heritage Trees", "Punggol Park", "(150m away)",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        oispecificlist.add(new oispecific("Conservation1", "Tree Conservation Area", "Punggol Park", "(190m away)",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        List<oispecific> oifinal = new ArrayList<>();
//
//        for (int i = 0; i < oispecificlist.size(); i++) {
//            if (oispecificlist.get(i).oitype.equals(oi_type))
//                oifinal.add(oispecificlist.get(i));
//        }
//
//        Log.d("oifinallist", "final size: " + oifinal.size());
//        //GET ALL OI DATA HERE. above is dummy data. add to the oilist.
//        return oifinal;
//    }
//}
//
//*/
//
///*
//
//public class MainActivity extends AppCompatActivity {
//
//    oilistAdapter adapter;
//    RecyclerView recyclerView;
//
//    oispecificlistAdapter specificlistAdapter;
//    RecyclerView oispecificRecyclerView;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.list_oi);
//
//        getIncomingIntent();
//
//        List<oi> oi_list = new ArrayList<>();
//        oi_list = getData();
//
//        recyclerView = (RecyclerView) findViewById(R.id.horizontalrecyclerView);
//        adapter = new oilistAdapter(oi_list, this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
//
//        Log.d("ListUI", "Running end");
//    }
//
//    private void getIncomingIntent() {
//        if (getIntent().hasExtra("oi_type")) {
//            String oi_type = getIntent().getStringExtra("oi_type");
//            setoispecificRecyclerView(oi_type);
//        }
//    }
//
//    private void setoispecificRecyclerView(String oi_type) {
//        Log.d("ListUI", "setoispecificRecyclerView START ");
//
//        List<oispecific> oi_specific_list = new ArrayList<>();
//        oi_specific_list = getSpecificData(oi_type);
//
//        oispecificRecyclerView = (RecyclerView) findViewById(R.id.specificoirecyclerView);
//        specificlistAdapter = new oispecificlistAdapter(oi_specific_list, this);
//        oispecificRecyclerView.setAdapter(specificlistAdapter);
//        oispecificRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//
//        Log.d("ListUI", "setoispecificRecyclerView END ");
//
//    }
//
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
//
//    private List<oi> getData() {
//        List<oi> oilist = new ArrayList<>();
//        oilist.add(new oi("Heritage Trees", false, "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//        oilist.add(new oi("Tree Conservation Area", true, "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052"));
//        oilist.add(new oi("Heritage Trees", false, "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        //GET ALL OI DATA HERE. above is dummy data. add to the oilist.
//        return oilist;
//    }
//
//    private List<oispecific> getSpecificData(String oi_type) {
//        Log.d("getSpecificData", "oi passed" + oi_type);
//        List<oispecific> oispecificlist = new ArrayList<>();
//        oispecificlist.add(new oispecific("Tree1", "Heritage Trees", "Bishan Park", "(100m away)",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        oispecificlist.add(new oispecific("Tree2", "Heritage Trees", "Punggol Park", "(150m away)",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        oispecificlist.add(new oispecific("Tree3", "Heritage Trees", "Punggol Park", "(150m away)",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        oispecificlist.add(new oispecific("Tree4", "Heritage Trees", "Punggol Park", "(150m away)",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        oispecificlist.add(new oispecific("Tree5", "Heritage Trees", "Punggol Park", "(150m away)",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        oispecificlist.add(new oispecific("Conservation1", "Tree Conservation Area", "Punggol Park", "(190m away)",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052",
//                "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//
//        List<oispecific> oifinal = new ArrayList<>();
//
//        for (int i = 0; i < oispecificlist.size(); i++) {
//            if (oispecificlist.get(i).oitype.equals(oi_type))
//                oifinal.add(oispecificlist.get(i));
//        }
//
//        Log.d("oifinallist", "final size: " + oifinal.size());
//        //GET ALL OI DATA HERE. above is dummy data. add to the oilist.
//        return oifinal;
//    }
//
//
//*/
//
//    /* private ArrayList<String> oiNames = new ArrayList<>();
//    private ArrayList<String> oiImages = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initRecyclerView();
//    }
//
//    //create your array list of oinames and oiimages
//
//    private void initNamesImages(){
//
//        oiNames.add("Heritage Trees");
//        oiImages.add("https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg");
//
//        oiNames.add("Barbeque Pits");
//        oiImages.add("https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052");
//    }
//
//    private void initRecyclerView(){
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        RecyclerView recyclerView = findViewById(R.id.oiRecycler);
//        recyclerView.setLayoutManager(layoutManager);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, oiNames, oiImages);
//        recyclerView.setAdapter(adapter);
//    }*/
////}
