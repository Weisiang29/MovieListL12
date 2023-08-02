package sg.edu.rp.c346.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowList extends AppCompatActivity {
    ListView ShowList;
    boolean buttonActivated=false;
    CustomAdapter adapterV2;
    Button btnshowRating;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        ShowList=findViewById(R.id.showallmovie);
        spinner=findViewById(R.id.spinnerRating2);
        btnshowRating=findViewById(R.id.btnRating);

        ArrayList<Movie> movieAL = new ArrayList<>();
        ArrayList<String> ratingList= new ArrayList<String>();


        dbHelper db = new dbHelper(ShowList.this);
        movieAL.addAll(db.getMovies());
        ratingList.add("G");
        ratingList.add("PG");
        ratingList.add("PG-13");
        ratingList.add("NC16");
        ratingList.add("M18");
        ratingList.add("R21");
        final boolean[] isUserSelection = { false };


        adapterV2 = new CustomAdapter(this,R.layout.row,movieAL);
        ShowList.setAdapter(adapterV2);

        ArrayAdapter<String> ratingAdapter =new ArrayAdapter(this, android.R.layout.simple_list_item_1,ratingList);
        spinner.setAdapter(ratingAdapter);
        btnshowRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedRating = spinner.getSelectedItem().toString();
                movieAL.clear();
                movieAL.addAll(db.getSelectedMovies(selectedRating));
                adapterV2.notifyDataSetChanged();
            }
        });
        ShowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(buttonActivated==true){

                    Movie data = movieAL.get(position);
                    Toast.makeText(ShowList.this, data.getTitle(), Toast.LENGTH_SHORT).show();
                    Intent pass = new Intent(ShowList.this,MovieEdit.class);
                    pass.putExtra("data",data);
                    startActivity(pass);
                }
                else{

                    Movie data = movieAL.get(position);
                    Intent pass = new Intent(ShowList.this,MovieEdit.class);
                    pass.putExtra("data", data);
                    startActivity(pass);
                }
            }
        });

    }@Override
    public void onResume() {
        super.onResume();
        ArrayList<Movie> movieAL = new ArrayList<>();
        dbHelper db = new dbHelper(ShowList.this);
        movieAL.addAll(db.getMovies());

        // Update the data source of the adapter and notify the adapter of the data change
        adapterV2.clear();
        adapterV2.addAll(movieAL);
        adapterV2.notifyDataSetChanged();
    }



}