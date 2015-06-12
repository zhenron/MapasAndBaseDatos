package com.example.juliancamilomunoz.mapasandbasedatos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    private static DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        SetDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_database) {
            Intent i = new Intent(this, DatabaseActivity.class);
            startActivity(i);
        }
        if (id == R.id.menu_map){
            Intent i = new Intent(this, MapActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void SetDatabase(){
        Cursor res = myDb.getAllData();
        if (res.getCount() != 0){
            //show message nothing in the table
            return;
        }
        myDb.insertData("Laureles", "6.2503604", "-75.5884722");
        myDb.insertData("Lleras", "6.2132447", "-75.5734518");
        myDb.insertData("Centro", "6.248526", "-75.5722502");
        myDb.insertData("SanDiego", "6.2364535", "-75.5713733");
        myDb.insertData("Av. El Poblado", "6.229841", "-75.5768235");
        myDb.insertData("Las Palmas", "6.2137145", "-75.5656226");
    }

    public static DatabaseHelper getHelper() {
        return myDb;
    }
}
