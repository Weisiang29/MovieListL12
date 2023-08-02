package sg.edu.rp.c346.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieEdit extends AppCompatActivity {
    TextView tvID;
    EditText et1 ,et2,et3 ,edtID;
    Button btnUpdate, btnDelete,btncancel;
    Spinner ratingspinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_edit);
        tvID=findViewById(R.id.tvID1);
        et1=findViewById(R.id.edit1);
        et2=findViewById(R.id.edit2);
        et3=findViewById(R.id.edit3);
        btncancel=findViewById(R.id.btncancel);
        btnDelete=findViewById(R.id.btndelete);
        btnUpdate=findViewById(R.id.btnupdate);
        edtID=findViewById(R.id.editID);
        ratingspinner=findViewById(R.id.spinnerRating3);

        Intent i= getIntent();
        Toast.makeText(MovieEdit.this, "Before retrieving intent extra", Toast.LENGTH_SHORT).show();
        Movie data = (Movie) i.getSerializableExtra("data");
        Toast.makeText(MovieEdit.this, "After retrieving intent extra", Toast.LENGTH_SHORT).show();

        String title=String.format("%s",data.getTitle());
        String genre=String.format("%s",data.getgenre());
        String year=String.format("%d",data.getYear());
        String id =String.format("%d",data.getId());


        edtID.setText(id);
        et1.setText(title);
        et2.setText(genre);
        et3.setText(year);
        edtID.setEnabled(false);
        String rating =String.format("%s",data.getrating());
        ArrayList<String> ratingList=new ArrayList<String>();
        ratingList.add("G");
        ratingList.add("PG");
        ratingList.add("PG-13");
        ratingList.add("NC16");
        ratingList.add("M18");
        ratingList.add("R21");
        ratingList.remove(rating);
        ratingList.add(0,rating);
        ArrayAdapter<String> ratingAdapter =new ArrayAdapter(this, android.R.layout.simple_list_item_1,ratingList);
        ratingspinner.setAdapter(ratingAdapter);

        dbHelper db = new dbHelper(MovieEdit.this);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteMovie(title);
                db.close();
                finish();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setTitle(et1.getText().toString());
                data.setGenre(et2.getText().toString());
                data.setYear(Integer.parseInt(et3.getText().toString()));
                data.setRating(ratingspinner.getSelectedItem().toString());
                db.updateMovie(data);
                db.close();
                finish();

            }
        });
    }
}