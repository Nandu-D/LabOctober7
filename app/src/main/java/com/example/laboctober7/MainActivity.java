package com.example.laboctober7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String SCORE1STRING = "1";
    private final String SCORE2STRING = "2";
    private final String RADIOVALUE = "RADIOVALUE";
    private final String MYPREFERENCE = "mypreference";

    private int incrementValue = 1;

    private int score1 = 0;
    private int score2 = 0;

    TextView score1_tv, score2_tv;
    Button score1_minus_bt, score2_minus_bt, score1_plus_bt, score2_plus_bt;

    RadioGroup incrementSelector;
    RadioButton incrementBy1, incrementBy5, incrementBy10;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score1_tv = findViewById(R.id.tv_score1);
        score2_tv = findViewById(R.id.tv_score2);

        score1_minus_bt = findViewById(R.id.btn_score1Minus);
        score1_plus_bt = findViewById(R.id.btn_score1Plus);
        score2_plus_bt = findViewById(R.id.btn_score2Plus);
        score2_minus_bt = findViewById(R.id.btn_score2Minus);

        incrementSelector = findViewById(R.id.radio_group);
        incrementBy1 = findViewById(R.id.radio_1);
        incrementBy5 = findViewById(R.id.radio_5);
        incrementBy10 = findViewById(R.id.radio_10);

        incrementSelector.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_1:
                        incrementValue = 1;
                        break;
                    case R.id.radio_5:
                        incrementValue = 5;
                        break;
                    case R.id.radio_10:
                        incrementValue = 10;
                        break;
                }
            }
        });

        score1_minus_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score1 - incrementValue >= 0) {
                    score1 = score1 - incrementValue;

                    score1_tv.setText(Integer.toString(score1));
                }
            }
        });

        score1_plus_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score1 = score1 + incrementValue;

                score1_tv.setText(Integer.toString(score1));
            }
        });

        score2_plus_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score2 = score2 + incrementValue;

                score2_tv.setText(Integer.toString(score2));
            }
        });

        score2_minus_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score2 - incrementValue >= 0) {
                    score2 = score2 - incrementValue;

                    score2_tv.setText(Integer.toString(score2));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(this, "This is lab 8", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedpreferences = getSharedPreferences(MYPREFERENCE,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(SCORE1STRING)) {
            score1_tv.setText(Integer.toString(sharedpreferences.getInt(SCORE1STRING, 0)));
            score1 = sharedpreferences.getInt(SCORE1STRING, 0);
        }
        if (sharedpreferences.contains(SCORE2STRING)) {
            score2_tv.setText(Integer.toString(sharedpreferences.getInt(SCORE2STRING, 0)));
            score2 = sharedpreferences.getInt(SCORE2STRING, 0);
        }
        if (sharedpreferences.contains(RADIOVALUE)) {
            RadioButton radioButton;
            switch (sharedpreferences.getInt(RADIOVALUE, 1)) {
                case 1:
                    radioButton = findViewById(R.id.radio_1);
                    radioButton.setChecked(true);
                    break;
                case 5:
                    radioButton = findViewById(R.id.radio_5);
                    radioButton.setChecked(true);
                    break;
                case 10:
                    radioButton = findViewById(R.id.radio_10);
                    radioButton.setChecked(true);
                    break;
            }
        }
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(SCORE1STRING, score1);
        editor.putInt(SCORE2STRING, score2);
        editor.putInt(RADIOVALUE, incrementValue);
        editor.apply();
        super.onPause();
    }
}

