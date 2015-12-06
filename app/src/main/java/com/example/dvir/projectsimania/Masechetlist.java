package com.example.dvir.projectsimania;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

public class Masechetlist extends AppCompatActivity {
    protected Button button;
    protected ListView listView;
    protected ListView listV; //For sub text
    protected ArrayList<String> list;
    protected ArrayList<Bookmark> arrayBookmarks; //For sub text
    protected ArrayAdapter<String> adapter;
    public final static String EXTRA_MASSECHET = "com.example.dvir.MASSECHET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masechetlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = (Button) findViewById(R.id.button);


            arrayBookmarks = new ArrayList<Bookmark>();

//            Bookmark person1 = new Bookmark("Alex", 1);
//            Bookmark person2 = new Bookmark("Laura", 3);
//            Bookmark person3 = new Bookmark("John", 2);
//            Bookmark person4 = new Bookmark("Tom", 5);
//
//            arrayBookmarks.add(person1);
//            arrayBookmarks.add(person2);
//            arrayBookmarks.add(person3);
//            arrayBookmarks.add(person4);

            listV = (ListView) findViewById(R.id.listView);
            ListBookmarkAdapter adapter = new ListBookmarkAdapter(this, arrayBookmarks);
            listV.setAdapter(adapter);
//        listView = (ListView) findViewById(R.id.listView);
//        String[] masechet = {"Sanhedrin", "BaBa Meziha", "Makot"};
//        list = new ArrayList<>(Arrays.asList(masechet));
//        adapter = new ArrayAdapter<String>(this, R.layout.list_items,R.id.txtitem,list);
//        listView.setAdapter(adapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_masechetlist, menu);
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


    public void setBookmark(View view) {
        Intent intent = new Intent(this, SetNewBookmark.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case (1):{
                if (resultCode == SetNewBookmark.RESULT_OK){
                    String txt = data.getStringExtra(EXTRA_MASSECHET);
                    String[] res = txt.split(Pattern.quote("|"));
                    Bookmark newBookmark = new Bookmark(res[0],Integer.valueOf(res[1]));
                    arrayBookmarks.add(newBookmark);
                    }
                }
            }
        }
    private void populateListView() {

        ArrayList<Bookmark> arrayPeople = new ArrayList<Bookmark>();

        Bookmark person1 = new Bookmark("Alex", 1);
        Bookmark person2 = new Bookmark("Laura", 3);
        Bookmark person3 = new Bookmark("John", 2);
        Bookmark person4 = new Bookmark("Tom", 5);

        arrayPeople.add(person1);
        arrayPeople.add(person2);
        arrayPeople.add(person3);
        arrayPeople.add(person4);

        ListView listV = (ListView) findViewById(R.id.listView);
        ListBookmarkAdapter adapter = new ListBookmarkAdapter(this, arrayPeople);
        listV.setAdapter(adapter);


    }
    }

