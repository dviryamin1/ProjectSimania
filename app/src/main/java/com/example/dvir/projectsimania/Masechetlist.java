package com.example.dvir.projectsimania;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
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
    private final static String STORETEXT = "Saved_List2.txt";
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

        try {
            arrayBookmarks = stringToArrayList(readFileInEditor());
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }

        if (savedInstanceState != null) {
            String[] values = savedInstanceState.getStringArray("myKey");
            if (values != null) {
                arrayBookmarks = ListBookmarkAdapter.bmListFromArray(values);
            }
        } else if (arrayBookmarks == null) {
            arrayBookmarks = new ArrayList<Bookmark>();
        }

        listBookmarkAdapter = new ListBookmarkAdapter(this, arrayBookmarks);
        listV.setAdapter(listBookmarkAdapter);

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Masechetlist.this, ChangeBookmark.class);
                intent.putExtra(EXTRA_CHANGE_BOOKMARK, listBookmarkAdapter.getItem(position).getLabel() + "|" + listBookmarkAdapter.getItem(position).getMasechet() + "|" + listBookmarkAdapter.getItem(position).getPage() + "|" + position);
                startActivityForResult(intent, 2);
            }
        });
        listBookmarkAdapter.notifyDataSetChanged();
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
                Bookmark newBookmark = new Bookmark(res[0], res[1], res[2]);
                arrayBookmarks.add(newBookmark);
                listBookmarkAdapter.notifyDataSetChanged(); //remember: this fixed the problem that the listview wasn't updating
            }
        } else if (requestCode == 2) {
            if (resultCode == SetNewBookmark.RESULT_OK) {
                String txt = data.getStringExtra(EXTRA_CHANGE_BOOKMARK);
                String[] res = txt.split(Pattern.quote("|"));
                if (res[res.length - 1].equals("ADD")) {
                    arrayBookmarks.get(Integer.parseInt(res[1])).setPage(res[0]);
                    arrayBookmarks.get(Integer.parseInt(res[1])).setLabel(res[2]);
                    listBookmarkAdapter.notifyDataSetChanged();
                } else if (res[res.length - 1].equals("DEL")) {
                    arrayBookmarks.remove(Integer.parseInt(res[0]));
                    listBookmarkAdapter.notifyDataSetChanged();
                }
            }
        }
    }

//    public void saveList(View view) {
//        try {
//
//            OutputStreamWriter out = new OutputStreamWriter(openFileOutput("TextFile", MODE_APPEND));
//            //EditText ET = (EditText)findViewById(R.id.editText);
//            String text = "hello world";
//            out.write(text);
//            out.write('\n');
//            out.close();
//            Toast.makeText(this, "The contents are saved in the file.", Toast.LENGTH_LONG).show();
//
//        } catch (Throwable t) {
//
//            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
//
//        }
//        //Toast.makeText(this, "Save not implemented yet.", Toast.LENGTH_SHORT).show();
//    }

    public String readFileInEditor() {
        try {
            InputStream in = openFileInput(STORETEXT);
            if (in != null) {
                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(tmp);
                String str;
                StringBuilder buf = new StringBuilder();
                while ((str = reader.readLine()) != null) {
                    buf.append(str);
                }

                if (!buf.toString().equals("")) {
                    Toast.makeText(this, "File loaded", Toast.LENGTH_LONG).show(); //to be changed
                    //Toast.makeText(this, buf.toString(), Toast.LENGTH_LONG).show();
                    return buf.toString();
                }
                in.close();
            }

            return "";
        } catch (java.io.FileNotFoundException e) {
            Toast.makeText(this, "There is no file to load", Toast.LENGTH_LONG).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
        return "";
    }

    public ArrayList<Bookmark> stringToArrayList(String str) {
        ArrayList<Bookmark> loadedList = new ArrayList<Bookmark>();
        if ((!str.equals("")) && !str.equals(null)) {
            String[] strList = str.split(Pattern.quote("||"));
            String[] strBookmark;
            for (int i = 0; i < strList.length; i++) {
                strBookmark = strList[i].split(Pattern.quote("|"));
                //Bookmark bookmark = new Bookmark(strBookmark[0],strBookmark[1]);
                loadedList.add(new Bookmark(strBookmark[0], strBookmark[1], strBookmark[2]));
            }
        }
        return loadedList;
    }

    public void saveList(View v) {
        String strBMList = "";
        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(STORETEXT, 0));
            for (int i = 0; i < arrayBookmarks.size(); i++) {
                //TODO: remember to add line number and bookmark label when ready
                strBMList += arrayBookmarks.get(i).getLabel() + "|" + arrayBookmarks.get(i).getMasechet() + "|" + arrayBookmarks.get(i).getPage();
                if (i < arrayBookmarks.size() - 1) {
                    strBMList += "||";
                }
            }
            out.write(strBMList);
            out.close();
            Toast.makeText(this, "The contents are saved in the file.", Toast.LENGTH_LONG).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();

        }

    }
}

