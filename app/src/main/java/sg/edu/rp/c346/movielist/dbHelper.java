package sg.edu.rp.c346.movielist;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
public class dbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "Movie.db";
    private static final String TABLE_Movie = "Movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";
    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_Movie + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_GENRE + " TEXT, "
                + COLUMN_YEAR + " INTEGER, "
                + COLUMN_RATING + " TEXT)";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Movie);
        // Create table(s) again
        onCreate(db);
    }
    public void insertMovie(String title, String genre,int year,String rating){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_TITLE, title);
        // Store the column name as key and the date as value
        values.put(COLUMN_GENRE, genre);

        values.put(COLUMN_YEAR, year);
        // Store the column name as key and the date as value
        values.put(COLUMN_RATING, rating);



        // Insert the row into the TABLE_TASK
        db.insert(TABLE_Movie, null, values);
        // Close the database connection
        db.close();

    }
    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE,COLUMN_YEAR,COLUMN_RATING};
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_Movie, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year=cursor.getInt(3);
                String rating=cursor.getString(4);
                Movie obj = new Movie( id,title, genre,year,rating);
                movies.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }
    public ArrayList<Movie> getSelectedMovies(String selected) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE,COLUMN_YEAR,COLUMN_RATING};
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_Movie, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year=cursor.getInt(3);
                String rating=cursor.getString(4);
                Movie obj = new Movie( id,title, genre,year,rating);
                if(rating.equals(selected)){
                    movies.add(obj);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }
    public int updateMovie(Movie data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,data.getTitle());
        values.put(COLUMN_GENRE,data.getgenre());
        values.put(COLUMN_YEAR,data.getYear());
        values.put(COLUMN_RATING,data.getrating());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_Movie, values, condition, args);
        db.close();
        return result;
    }
    public int deleteMovie(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_TITLE + "= ?";
        String[] args = {String.valueOf(title)};
        int result = db.delete(TABLE_Movie, condition, args);
        db.close();
        return result;
    }
}
