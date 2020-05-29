package com.degdv.starquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginAct extends AppCompatActivity {
    private EditText pasword;
    int correct_passs = 132498;
    int pwr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Contraseña correcta

        Button btn_enter = findViewById(R.id.btnEnter);
        pasword = findViewById(R.id.pasword);
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    CheckPasword(v, builder);
                }catch(Exception ex) {
                    builder.setTitle("Contraseña vacia.");
                    builder.setMessage("Necesitas contraseña para entrar. .\nNo es posible iniciar los examenes!");
                    builder.setIcon(R.drawable.error);
                    builder.setNegativeButton(R.string.tryagain, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                }
            }
        });
    }

    private void CheckPasword(View v, AlertDialog.Builder builder) {
        pwr = Integer.parseInt(pasword.getText().toString());
        if(pwr == correct_passs) {
            pasword.setText("");

            Intent intent = new Intent(v.getContext(), MenuPrinci.class);
            startActivityForResult(intent, 0);
        } else{
            pasword.setText("");
            builder.setTitle("Contraseña incorrecta.");
            builder.setMessage("Usuario no autorizado.\nNo es posible iniciar los examenes!");
            builder.setIcon(R.drawable.no64);
            builder.setNegativeButton(R.string.tryagain, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
        }
    }
}
