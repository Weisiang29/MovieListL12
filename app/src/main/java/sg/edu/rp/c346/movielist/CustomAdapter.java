package sg.edu.rp.c346.movielist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter  extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }

    public CustomAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        View rowView = inflater.inflate(layout_id, parent, false);
        TextView tv_first= rowView.findViewById(R.id.textMovietitle);
        TextView tv_second= rowView.findViewById(R.id.textGenre);
        TextView tv_third= rowView.findViewById(R.id.textMovieyear);
        ImageView image= rowView.findViewById(R.id.imageView);

        Movie movies =versionList.get(position);
        tv_first.setText(movies.getTitle());
        tv_second.setText(movies.getgenre());
        tv_third.setText(String.format("%d",movies.getYear()));
        String getImage= movies.getrating();
        if ("G".equals(getImage)) {
            String imageUrl = "https://i.imgur.com/tGbaZCY.jpg";
            image.setImageResource(R.drawable.imageg);
        } else if ("PG".equals(getImage)) {
            String imageUrl = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16278-28797ce.jpg?quality=90&webp=true&fit=584,471";
            Picasso.with(parent_context).load(imageUrl).into(image);
        } else if ("PG-13".equals(getImage)) {
            String imageUrl = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16280-8d5bdb7.jpg?quality=90&webp=true&fit=320,320";
            Picasso.with(parent_context).load(imageUrl).into(image);
        } else if ("NC16".equals(getImage)) {
            String imageUrl = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16281-8d5bdb7.jpg?quality=90&webp=true&fit=490,490";
            Picasso.with(parent_context).load(imageUrl).into(image);
        } else if ("M18".equals(getImage)) {
            String imageUrl = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16282-05127b2.jpg?quality=90&webp=true&fit=300,300";
            Picasso.with(parent_context).load(imageUrl).into(image);
        } else if ("R21".equals(getImage)) {
            String imageUrl = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16283-05127b2.jpg?quality=90&webp=true&fit=515,424";
            Picasso.with(parent_context).load(imageUrl).into(image);
        }

        return rowView;}

}


