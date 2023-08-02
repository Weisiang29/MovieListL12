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

public class MainActivity extends AppCompatActivity {
    EditText edt1,edt2,edt3;
    TextView test;
    Button btn1 ,btn3;
    ArrayList<String> ratingList ;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test=findViewById(R.id.test);
        edt1=findViewById(R.id.edtSong);
        edt2=findViewById(R.id.edtSinger);
        edt3=findViewById(R.id.edtYear);
        btn1=findViewById(R.id.btnINSERT);
        btn3=findViewById(R.id.btnSHOW);
        spinner=findViewById(R.id.spinnerRating1);
        ratingList =new ArrayList<String>();
        ratingList.add("G");
        ratingList.add("PG");
        ratingList.add("PG-13");
        ratingList.add("NC16");
        ratingList.add("M18");
        ratingList.add("R21");
        ArrayAdapter<String> bb = new ArrayAdapter(this,android.R.layout.simple_list_item_1,ratingList );
        spinner.setAdapter(bb);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edit1= edt1.getText().toString();
                String edit2=edt2.getText().toString();
                String edit3 =edt3.getText().toString();
                if(edit1.isEmpty()||edit2.isEmpty()||edit3.isEmpty()){

                    Toast.makeText(MainActivity.this, "Please enter all of the input fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    String selectedRating = spinner.getSelectedItem().toString();
                    dbHelper db = new dbHelper(MainActivity.this);
                    String year = edt3.getText().toString();
                    int yearOK = Integer.parseInt(year);
                    db.insertMovie(edit1,edit2,yearOK,selectedRating);
                    Toast.makeText(MainActivity.this, "Insert sucessfully", Toast.LENGTH_SHORT).show();
                }







            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,ShowList.class);
                startActivity(intent);







            }
        });


    }@Override
    public void onResume(){
        super.onResume();
        edt1.setText("");
        edt2.setText("");
        edt3.setText("");

    }
    }
