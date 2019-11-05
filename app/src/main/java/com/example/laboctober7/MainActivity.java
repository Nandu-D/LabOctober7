package com.example.laboctober7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int incrementValue = 1;

    private int score1 = 0;
    private int score2 = 0;

    TextView score1_tv, score2_tv;
    Button score1_minus_bt, score2_minus_bt, score1_plus_bt, score2_plus_bt;

    RadioGroup incrementSelector;
    RadioButton incrementBy1, incrementBy5, incrementBy10;

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
                score1= score1 + incrementValue;

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


}

