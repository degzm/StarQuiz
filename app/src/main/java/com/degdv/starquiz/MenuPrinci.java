package com.degdv.starquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrinci extends AppCompatActivity {
    private Button btn_malts;
    private Button btn_Chems;
    private Button btn_Front;
    private Button btn_Temps;
    private Button btn_Dining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        btn_malts = findViewById(R.id.btnMalteada);
        btn_malts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MaltsActivity.class);
                startActivityForResult(intent, 0);
            }
        });


        btn_Chems = findViewById(R.id.btnChem);
        btn_Chems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChemsActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btn_Front = findViewById(R.id.btnFront);
        btn_Front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CenterActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btn_Temps = findViewById(R.id.btnTemps);
        btn_Temps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TempsActivity.class);
                startActivityForResult(intent, 0);
            }
        });
/*
        btn_Dining = findViewById(R.id.btnDining);
        btn_Dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DiningActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        */
    }
}
