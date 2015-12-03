package com.example.dvir.projectsimania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetNewBookmark extends AppCompatActivity {
    protected Button button;
    protected Intent resultIntent;
    protected EditText putExtra;
    public final static String EXTRA_MASSECHET = "com.example.dvir.MASSECHET";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_bookmark);
        button = (Button) findViewById(R.id.button2);
        putExtra = (EditText) findViewById(R.id.edit_message);
    }

    public void setBookmark(View view){
        resultIntent = new Intent();

        resultIntent.putExtra(EXTRA_MASSECHET, putExtra.getText().toString());
        setResult(SetNewBookmark.RESULT_OK, resultIntent);
        finish();

    }
}
