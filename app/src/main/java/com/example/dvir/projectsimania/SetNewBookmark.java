package com.example.dvir.projectsimania;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


import java.util.ArrayList;

public class SetNewBookmark extends AppCompatActivity {
    public final static String EXTRA_MASSECHET = "com.example.dvir.MASSECHET";
    protected Button button;
    protected Intent resultIntent;
    protected String data;
    protected ArrayList<String> arrayList;
    protected ArrayAdapter<String> ArrayAdapter;
    private Spinner spinnerM;
    private Spinner spinnerP;
    private EditText editText;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String str;

    @Override

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_bookmark);
        spinnerM = (Spinner) findViewById(R.id.spinner);
        spinnerP = (Spinner) findViewById(R.id.spinner2);
        editText = (EditText) findViewById(R.id.editText2);

        radioGroup = (RadioGroup) findViewById(R.id.rg);
        radioButton = (RadioButton) findViewById(R.id.radioButton2);
        radioButton.setChecked(true);
        str = radioButton.getText().toString();

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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup rGroup, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) rGroup.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    str = (checkedRadioButton.getText().toString());
                }
            }
        });
    }

    public void setBookmark(View view) {
        resultIntent = new Intent();
        data = editText.getText().toString() + "|" +
                spinnerM.getSelectedItem().toString() + "|" +
                spinnerP.getSelectedItem().toString() + " " + str;
        resultIntent.putExtra(EXTRA_MASSECHET, data);
        setResult(SetNewBookmark.RESULT_OK, resultIntent);
        finish();
    }

    public void cancel() {
        super.onBackPressed();
    }
}
