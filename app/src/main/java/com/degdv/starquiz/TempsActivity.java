package com.degdv.starquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TempsActivity extends AppCompatActivity {
    public static final String CORRECT_ANSWER = "correct_answer";
    public static final String CURRENT_CUESTION = "current_cuestion";
    public static final String ANSWER_IS_CORRECT = "answer_is_correct";
    public static final String ANSWER = "answer";
    /**
     *
     */
    private int[] ids_answer = {R.id.answer_1,R.id.answer_2,R.id.answer_3,R.id.answer_4};
    private int correct_answer;
    private int current_cuestion;
    private String[] temps_questions;
    private boolean [] answer_is_correct;
    private int[] answer;
    private TextView text_question;
    private RadioGroup group;
    private Button btn_next;
    private Button btn_prev;
    private Button btn_menu;

    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CORRECT_ANSWER,correct_answer);
        outState.putInt(CURRENT_CUESTION,current_cuestion);
        outState.putBooleanArray(ANSWER_IS_CORRECT,answer_is_correct);
        outState.putIntArray(ANSWER, answer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_question = findViewById(R.id.text_question);
        group = findViewById(R.id.answer_group);
        btn_next = findViewById(R.id.btn_check);
        btn_prev = findViewById(R.id.btn_prev);
        btn_menu = findViewById(R.id.btnMenu);
        btn_menu.setVisibility(View.GONE);
        temps_questions = getResources().getStringArray(R.array.temps_questions);
        answer_is_correct = new boolean[temps_questions.length];
        answer = new int[temps_questions.length];
        //Rellenamos con -1 de momento
        for(int i = 0; i < answer.length; i++){
            answer[i] = -1;
        }
        current_cuestion = 0;
        showQuestion();

        if(savedInstanceState == null){

        }else{
            Bundle state = savedInstanceState;
            correct_answer = state.getInt(CORRECT_ANSWER);
            current_cuestion = state.getInt(CURRENT_CUESTION);
            answer_is_correct = state.getBooleanArray(ANSWER_IS_CORRECT);
            answer = state.getIntArray(ANSWER);
            showQuestion();
        }

        //Grupo de incisos
        final RadioGroup rg = findViewById(R.id.answer_group);

        //Listener de boton checar
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chechAnswer();
                if(current_cuestion < temps_questions.length-1){
                    current_cuestion++;
                    showQuestion();
                }else{
                    checkResult();
                }
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chechAnswer();
                if(current_cuestion > 0){
                    current_cuestion--;
                    showQuestion();
                }
            }
        });
    }

    private void startOver() {
        btn_menu.setVisibility(View.GONE);
        answer_is_correct = new boolean[temps_questions.length];
        answer = new int[temps_questions.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = -1;
        }
        current_cuestion = 0;
        showQuestion();
    }


    private void checkResult() {
        int correctas = 0, incorrectas = 0, noCont = 0, cont = 0    ;

        for(int i = 0; i < temps_questions.length; i++){
            cont++;
            if(answer_is_correct[i]) correctas++;
            else if (answer[i] == -1) noCont++;
            else incorrectas++;
        }

        String message = String.format("Correctas: %d" + "\nIncorrectas: %d" + "\nNo contestadas: %d\n" ,correctas,incorrectas, noCont);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        int porc = (correctas*100)/cont;
        builder.setTitle("Sacaste: " + porc);

        builder.setMessage(message + "\nEspera a que el supervisor guarde tus resultados!");
        btn_prev.setVisibility(View.GONE);
        btn_menu.setVisibility(View.VISIBLE);

        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), MenuPrinci.class);
                        startActivityForResult(intent, 0);
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.start_over, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startOver();
            }
        });
        builder.create().show();
    }

    private void chechAnswer() {
        int id = group.getCheckedRadioButtonId();
        int ans = -1;
        for(int i = 0; i < ids_answer.length; i++){
            if(ids_answer[i] == id){
                ans = i;
            }
        }
        //Array de booleanos que regresa true o false
        answer_is_correct[current_cuestion] = (ans == correct_answer);
        answer[current_cuestion] = ans;
    }


    private void showQuestion() {
        String q = temps_questions[current_cuestion];
        String [] parts = q.split(";");

        group.clearCheck();
        text_question.setText(parts[0]);
        for(int i = 0; i<ids_answer.length;i++) {
            RadioButton rb = findViewById(ids_answer[i]);
            String ans = parts[i+1];
            if(ans.charAt(0) == '*'){
                correct_answer = i;
                ans = ans.substring(1);
            }
            //
            rb.setText(ans);
            if(answer[current_cuestion] == i){
                rb.setChecked(true);
            }
        }
        if(current_cuestion == 0){
            btn_prev.setVisibility(View.GONE);
        }else{
            btn_prev.setVisibility(View.VISIBLE);
        }
        if(current_cuestion == temps_questions.length-1){
            btn_next.setText(R.string.finish);
        }else{
            btn_next.setText(R.string.next);
        }
    }
}