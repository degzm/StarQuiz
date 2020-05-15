package com.degdv.starquiz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int ids_answer[] = {R.id.answer_1,R.id.answer_2,R.id.answer_3,R.id.answer_4};
    private int correct_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Text View donde muestra la pregunta
        TextView text_question = (TextView) findViewById(R.id.text_question);
        String [] all_questions = getResources().getStringArray(R.array.all_questions);
        String q0= all_questions[0];
        String [] parts = q0.split(";");

        text_question.setText(parts[0]);

        for(int i = 0; i<ids_answer.length;i++) {
            RadioButton rb = (RadioButton) findViewById(ids_answer[i]);
            String answer = parts[i+1];
            if(answer.charAt(0) == '*'){
                correct_answer = i;
                answer = answer.substring(1);
            }
            rb.setText(answer);
        }

        final RadioGroup rg = (RadioGroup) findViewById(R.id.answer_group);

        Button btn_check = (Button) findViewById(R.id.btn_check);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = rg.getCheckedRadioButtonId();
                int answer = -1;
                for(int i = 0; i<ids_answer.length;i++){
                    if(ids_answer[i] == id){
                        answer = i;
                    }
                }
                if(answer == correct_answer){
                    Toast.makeText(MainActivity.this,R.string.correct,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,R.string.icorrect,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
