package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     List<String> items;

    //gethandleoflogic
    Button addbtn;
    EditText editem;
    RecyclerView rvitems;
    itemsAdapter itemsAdapter;

    public MainActivity() throws IOException {
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defining member variable
        addbtn = findViewById(R.id.addbtn);
        editem = findViewById(R.id.editem);
        rvitems = findViewById(R.id.rvitems);
        //methods for the variable



        loadItems();

        itemsAdapter.OnLongClickListener onLongClickListener = new itemsAdapter.OnLongClickListener(){

            @Override
            public void onItemLongClicked(int position) {
                //delete the item from the model
                items.remove(position);
                //Notify the adapter
                itemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(),"Item was removed", Toast.LENGTH_SHORT).show();
                saveItems();

            }
        };

        itemsAdapter = new itemsAdapter(items, onLongClickListener);
        rvitems.setAdapter(itemsAdapter);
        rvitems.setLayoutManager(new LinearLayoutManager(this));

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = editem.getText().toString();
                //Add item to the model and notify that item is inserted
                items.add(todoItem);
                itemsAdapter.notifyItemInserted(items.size() -1);
                editem.setText("");
                Toast.makeText(getApplicationContext(),"Item was Added", Toast.LENGTH_SHORT).show();
                saveItems();


            }
        });

    }
    private File getDataFile(){
        return new File(getFilesDir(), "data.txt");

    }
    //This function will load by reading every line of txt
    private void loadItems(){
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e("MainActivity", "Error reading items", e);
            items = new ArrayList<>();
        }


    }
    //this writes them
    private void saveItems(){
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e){
            Log.e("MainActivity", "Error writing items", e);

        }
    }
}