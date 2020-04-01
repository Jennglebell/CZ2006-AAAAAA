package UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sharingapp.R;

import java.util.ArrayList;
import java.util.List;

import Model.oi;

public class Filter extends AppCompatActivity {

    List<oi> oilist;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_ui);

        oilist = getData();
        FilterListAdapter adapter = new FilterListAdapter(oilist,this);

        listView = (ListView) findViewById(R.id.filterlist);
        listView.setAdapter(adapter);

    }

    public void onBackPressed(){
        ArrayList<String> status = new ArrayList<>();
        for (oi i: oilist){
            if (i.applied.equals(true)){
                status.add(i.name);
            }
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("status",status);
        startActivity(intent);
    }

    public void filter(View v){
        ArrayList<String> status = new ArrayList<>();
        for (oi i: oilist){
            if (i.applied.equals(true)){
                status.add(i.name);
            }
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("status",status);
        startActivity(intent);
    }


    private List<oi> getData(){
        ArrayList<oi> oilist = new ArrayList<>();
        oilist.add(new oi("HT",  "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
        oilist.add(new oi("BBQ",  "https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052"));
        oilist.add(new oi("Heritage Trees",  "https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));


        //GET ALL OI DATA HERE. above is dummy data. add to the oilist.
        return oilist;
    }

}
