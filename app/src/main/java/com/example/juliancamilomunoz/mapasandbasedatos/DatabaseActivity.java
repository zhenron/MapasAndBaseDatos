package com.example.juliancamilomunoz.mapasandbasedatos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.database.Cursor;
import android.view.View;

import android.widget.Toast;

public class DatabaseActivity extends Activity {

    EditText editName, editLatitude, editLongitude, editId;
    Button btnAddData, btnViewAll, btnUpdate, btnDelete;
    DatabaseHelper myDb = MainActivity.getHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        editName = (EditText) findViewById(R.id.editText_name);
        editLatitude = (EditText) findViewById(R.id.editText_lat);
        editLongitude = (EditText) findViewById(R.id.editText_long);
        editId = (EditText) findViewById(R.id.editText_id);
        btnAddData = (Button) findViewById(R.id.button_add);
        btnViewAll = (Button) findViewById(R.id.button_view);
        btnUpdate = (Button) findViewById(R.id.button_upd);
        btnDelete = (Button) findViewById(R.id.button_delete);

        AddData();
        viewAll();
        UpdateData();
        deleteData();
    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editLatitude.getText().toString(),
                                editLongitude.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(DatabaseActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DatabaseActivity.this, "Data NOT inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0){
                            //show message nothing in the table
                            showMessage("Error", "No data found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Latitude :" + res.getString(2) + "\n");
                            buffer.append("Longitude :" + res.getString(3) + "\n\n");
                        }

                        //show all the data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = myDb.updateData(editId.getText().toString(),
                                editName.getText().toString(),
                                editLatitude.getText().toString(),
                                editLongitude.getText().toString());
                        if (isUpdated == true)
                            Toast.makeText(DatabaseActivity.this, "Data updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DatabaseActivity.this, "Data NOT updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void deleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editId.getText().toString());
                        if (deletedRows >0)
                            Toast.makeText(DatabaseActivity.this, "Data deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DatabaseActivity.this, "Data NOT deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_database, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
