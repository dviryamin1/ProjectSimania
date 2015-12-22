package com.example.dvir.projectsimania;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class SetNewBookmark extends AppCompatActivity {
    private Spinner spinnerM;
    private Spinner spinnerP;
    protected Button button;
    protected Intent resultIntent;
    protected String data;
    protected ArrayList<String> arrayList;
    protected ArrayAdapter<String> ArrayAdapter;
    public final static String EXTRA_MASSECHET = "com.example.dvir.MASSECHET";
    @Override

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_bookmark);
        spinnerM = (Spinner) findViewById(R.id.spinner);
        spinnerP = (Spinner) findViewById(R.id.spinner2);
        arrayList = Utils.getSheetList(spinnerM.getSelectedItem().toString());
        ArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayList);

        spinnerP.setAdapter(ArrayAdapter);

        button = (Button) findViewById(R.id.button2);
        spinnerM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                arrayList = Utils.getSheetList(spinnerM.getSelectedItem().toString());
                ArrayAdapter = new ArrayAdapter<String>(SetNewBookmark.this, android.R.layout.simple_spinner_dropdown_item, arrayList);
                spinnerP.setAdapter(ArrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerP.setSelection(0);
            }
        });
    }

    public void setBookmark(View view){
        resultIntent = new Intent();
        data = spinnerM.getSelectedItem().toString() + "|" + spinnerP.getSelectedItem().toString();
        resultIntent.putExtra(EXTRA_MASSECHET, data);
        setResult(SetNewBookmark.RESULT_OK, resultIntent);
        finish();
    }
}
