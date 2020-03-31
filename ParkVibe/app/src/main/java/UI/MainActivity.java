package UI;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sharingapp.R;
import Model.oi;

import java.util.ArrayList;
import java.util.List;


/**
 * Home Activity of the App
 */
public class MainActivity extends AppCompatActivity {

    private String user_id;
    private String oitype;
    oilistAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent(); // Get intent from LoginActivity
        user_id = intent.getStringExtra("user_id");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), user_id);

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);

        mViewPager.setAdapter(mSectionsPagerAdapter);
//        mViewPager.setOffscreenPageLimit(0);

       // TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
       // tabLayout.setupWithViewPager(mViewPager);


        oitype = intent.getStringExtra("oi_type");
        System.out.println("oitype in main" + oitype);
        Bundle bundle = new Bundle();
        bundle.putString("oi_type", oitype);
// set Fragmentclass Arguments
        AllItemsFragment fragobj = new AllItemsFragment();
        fragobj.setArguments(bundle);


        List<oi> oi_list;
        oi_list = getData();
        recyclerView = findViewById(R.id.horizontalrecyclerView);
        adapter = new oilistAdapter(oi_list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }


//    String oitype;
//
//    public String getOitype() {
//        return oitype;
//    }
//
//    public void setOitype(String oitype) {
//        this.oitype = oitype;
//    }



    private List<oi> getData() {
        List<oi> oilist = new ArrayList<>();
        oilist.add(new oi("HT", false, "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
        oilist.add(new oi("BBQ", true, "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052"));
        oilist.add(new oi("Heritage Trees", false, "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));

        //GET ALL OI DATA HERE. above is dummy data. add to the oilist.
        return oilist;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Intent search_intent = new Intent(this, SearchActivity.class);
                search_intent.putExtra("user_id", user_id);
                startActivity(search_intent);
                return true;
            case R.id.borrowed_items:
                Intent b_intent = new Intent(this, MapDisplay.class);
                b_intent.putExtra("user_id", user_id);
                startActivity(b_intent);
                return true;
            case R.id.edit_profile:
                Intent profile_intent = new Intent(this, AddItemActivity.class);
                profile_intent.putExtra("user_id", user_id);
                startActivity(profile_intent);
                return true;
            case R.id.logout:
                Intent logout_intent = new Intent(this, LoginActivity.class);
                Toast.makeText(getApplicationContext(), "Goodbye", Toast.LENGTH_SHORT).show();
                startActivity(logout_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent logout_intent = new Intent(this, LoginActivity.class);
        startActivity(logout_intent);
    }

    public void addItemActivity(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }

    public void displayMap(View view){
        Intent intent = new Intent(this, MapDisplay.class);
        startActivity(intent);
    }

//    public void listView(View view){
//        Intent intent = new Intent(this, ListUI.class);
//        startActivity(intent);
//    }
}