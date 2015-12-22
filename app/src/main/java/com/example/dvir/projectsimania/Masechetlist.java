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
import android.widget.AdapterView;
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
    public final static String EXTRA_MASSECHET = "com.example.dvir.MASSECHET";
    public final static String EXTRA_CHANGE_BOOKMARK = "com.example.dvir.CHANGE_BOOKMARK_MASSECHET";
    protected Button button;
    protected ListView listV; //For sub text
    protected ArrayList<Bookmark> arrayBookmarks; //For sub text
    protected ListBookmarkAdapter listBookmarkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masechetlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button = (Button) findViewById(R.id.button);
        listV = (ListView) findViewById(R.id.listView);

        if (savedInstanceState != null) {
            String[] values = savedInstanceState.getStringArray("myKey");
            if (values != null) {
                arrayBookmarks = ListBookmarkAdapter.bmListFromArray(values);
                listBookmarkAdapter = new ListBookmarkAdapter(this, arrayBookmarks);
            }
        } else {
            arrayBookmarks = new ArrayList<Bookmark>();
            listBookmarkAdapter = new ListBookmarkAdapter(this, arrayBookmarks);
        }

        listV.setAdapter(listBookmarkAdapter);

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Masechetlist.this, ChangeBookmark.class);
                intent.putExtra(EXTRA_CHANGE_BOOKMARK, listBookmarkAdapter.getItem(position).getMasechet() + "|" + listBookmarkAdapter.getItem(position).getPage() + "|" + position);
                startActivityForResult(intent, 2);
            }
        });
    }

    public void onSaveInstanceState(Bundle savedState) {

        super.onSaveInstanceState(savedState);

        // Note: getValues() is a method in your ArrayAdaptor subclass
        String[] values = listBookmarkAdapter.getValues();
        savedState.putStringArray("myKey", values);
    }

    public void setBookmark(View view) {
        Intent intent = new Intent(this, SetNewBookmark.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == SetNewBookmark.RESULT_OK) {
                String txt = data.getStringExtra(EXTRA_MASSECHET);
                String[] res = txt.split(Pattern.quote("|"));
                Bookmark newBookmark = new Bookmark(res[0], res[1]);
                arrayBookmarks.add(newBookmark);
                listBookmarkAdapter.notifyDataSetChanged(); //remember: this fixed the problem that the listview wasn't updating
            }
        } else if (requestCode == 2) {
            if (resultCode == SetNewBookmark.RESULT_OK) {
                String txt = data.getStringExtra(EXTRA_CHANGE_BOOKMARK);
                String[] res = txt.split(Pattern.quote("|"));
                arrayBookmarks.get(Integer.parseInt(res[1])).setPage(res[0]);
                listBookmarkAdapter.notifyDataSetChanged();
            }
        }
    }


//    private void populateListView() {
//
//        ArrayList<Bookmark> arrayPeople = new ArrayList<Bookmark>();
//
//        Bookmark person1 = new Bookmark("Alex", 1);
//        Bookmark person2 = new Bookmark("Laura", 3);
//        Bookmark person3 = new Bookmark("John", 2);
//        Bookmark person4 = new Bookmark("Tom", 5);
//
//        arrayPeople.add(person1);
//        arrayPeople.add(person2);
//        arrayPeople.add(person3);
//        arrayPeople.add(person4);
//
//        ListView listV = (ListView) findViewById(R.id.listView);
//        ListBookmarkAdapter adapter = new ListBookmarkAdapter(this, arrayPeople);
//        listV.setAdapter(adapter);
//    }
}

