package com.example.dvir.projectsimania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ChangeBookmark extends AppCompatActivity {
    public final static String EXTRA_CHANGE_BOOKMARK_MASSECHET = "com.example.dvir.CHANGE_BOOKMARK_MASSECHET";
    protected String data;
    private String[] someVariable;
    private TextView textView;
    private TextView textView2;
    private Spinner spinnerP;
    protected ArrayAdapter<String> ArrayAdapter;
    protected ArrayList<String> arrayList;
    protected Intent resultIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bookmark);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            someVariable = extras.getString(EXTRA_CHANGE_BOOKMARK_MASSECHET).split(Pattern.quote("|"));
        }
        textView = (TextView) findViewById(R.id.masechetNameTV);
        textView2 = (TextView) findViewById(R.id.sheetNumTV);
        spinnerP = (Spinner) findViewById(R.id.sheetSpinner);

        arrayList = Utils.getSheetList(someVariable[0]);
        ArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinnerP.setAdapter(ArrayAdapter);
        spinnerP.setSelection(arrayList.indexOf(someVariable[1]));
        textView.setText(someVariable[0]);
        textView2.setText(someVariable[1]);
    }

    public void changeBookmark(View view) {
        resultIntent = new Intent();
        data = spinnerP.getSelectedItem().toString() + "|" + someVariable[2];
        resultIntent.putExtra(EXTRA_CHANGE_BOOKMARK_MASSECHET, data);
        setResult(SetNewBookmark.RESULT_OK, resultIntent);
        finish();
    }
}
