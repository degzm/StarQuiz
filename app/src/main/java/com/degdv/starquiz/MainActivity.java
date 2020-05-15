package com.degdv.starquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /**
     *
     */
    private int ids_answer[] = {R.id.answer_1,R.id.answer_2,R.id.answer_3,R.id.answer_4};
    private int correct_answer;
    private int current_cuestion;
    private String[] all_questions;
    private boolean [] answer_is_correct;
    private int[] answer;
    private TextView text_question;
    private RadioGroup group;
    private Button btn_next;
    private Button btn_prev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * @param text_cuestion es el text view de la pregunta
         * @param group es el grupo donde se muestran las preguntas
         * @param btn_check boton que valida las respuestas
         * @param btn_prev boton que checa las respuestas
         * @param all_questions es el array donde se guardan las preguntas
         * @param answer_is_correct asigna la dimension de las preguntas
         * @param answer sabemos cual es la respuesta
         * @param current_cuestion es la pregunta actual, va aumentando
         * @method showQuestion
         */
        text_question = (TextView) findViewById(R.id.text_question);
        group = (RadioGroup) findViewById(R.id.answer_group);
        btn_next = (Button) findViewById(R.id.btn_check);
        btn_prev = (Button) findViewById(R.id.btn_prev);
        all_questions = getResources().getStringArray(R.array.all_questions);
        answer_is_correct = new boolean[all_questions.length];
        answer = new int[all_questions.length];
        //Rellenamos con -1 de momento
        for(int i = 0; i < answer.length; i++){
            answer[i] = -1;
        }
        current_cuestion = 0;
        showQuestion();


        //Grupo de incisos
        final RadioGroup rg = (RadioGroup) findViewById(R.id.answer_group);

        //Listener de boton checar
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chechAnswer();

                if(current_cuestion < all_questions.length-1){
                    current_cuestion++;
                    showQuestion();
                }else{
                    int correctas = 0, incorrectas = 0;
                    for(boolean b : answer_is_correct){
                        if(b) correctas++;
                        else incorrectas ++;
                    }
                    String resultado = String.format("Correctas: %d-- Incorrectas: %d",correctas,incorrectas );
                    Toast.makeText(MainActivity.this,resultado,Toast.LENGTH_LONG).show();
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
        String q = all_questions[current_cuestion];
        String [] parts = q.split(";");

        group.clearCheck();
        text_question.setText(parts[0]);
        for(int i = 0; i<ids_answer.length;i++) {
            RadioButton rb = (RadioButton) findViewById(ids_answer[i]);
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
        if(current_cuestion == all_questions.length-1){
            btn_next.setText(R.string.finish);
        }else{
            btn_next.setText(R.string.next);
        }
    }
}
