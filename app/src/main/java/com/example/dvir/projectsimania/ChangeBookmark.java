package com.example.dvir.projectsimania;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ChangeBookmark extends AppCompatActivity {
    public final static String EXTRA_CHANGE_BOOKMARK_MASSECHET = "com.example.dvir.CHANGE_BOOKMARK_MASSECHET";
    protected String data;
    protected ArrayAdapter<String> ArrayAdapter;
    protected ArrayList<String> arrayList;
    protected Intent resultIntent;
    private String[] someVariable;
    private TextView textView;
    private TextView textView2;
    private EditText editText;
    private Spinner spinnerP;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String str;

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
        editText = (EditText) findViewById(R.id.editText);
        spinnerP = (Spinner) findViewById(R.id.sheetSpinner);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        str = someVariable[2].split(Pattern.quote(" "))[1];
        if (str.equals("עא")) {
            radioButton = (RadioButton) findViewById(R.id.pageA);
            radioButton.setChecked(true);
        } else if (str.equals("עב")) {
            radioButton = (RadioButton) findViewById(R.id.pageB);
            radioButton.setChecked(true);
        }

        arrayList = Utils.getSheetList(someVariable[1]);
        ArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinnerP.setAdapter(ArrayAdapter);
        spinnerP.setSelection(arrayList.indexOf(someVariable[1].split(Pattern.quote(" "))[0]));
        editText.setText(someVariable[0]);
        textView.setText(someVariable[1]);
        textView2.setText(someVariable[2]);

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

    public void changeBookmark(View view) {
        resultIntent = new Intent();
        data = spinnerP.getSelectedItem().toString() + " " + str + "|" +
                someVariable[3] + "|" +
                editText.getText().toString() +
                "|ADD";
        resultIntent.putExtra(EXTRA_CHANGE_BOOKMARK_MASSECHET, data);
        setResult(SetNewBookmark.RESULT_OK, resultIntent);
        finish();
    }

    public void cancel(View view) {
        super.onBackPressed();
    }

    public void deleteBookmark(View view) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        resultIntent = new Intent();
                        data = someVariable[3] + "|DEL";
                        resultIntent.putExtra(EXTRA_CHANGE_BOOKMARK_MASSECHET, data);
                        setResult(SetNewBookmark.RESULT_OK, resultIntent);
                        finish();

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("אתה בטוח שברצונך למחוק את הסמנייה").setPositiveButton("כן", dialogClickListener)
                .setNegativeButton("לא", dialogClickListener).show();
    }
}
