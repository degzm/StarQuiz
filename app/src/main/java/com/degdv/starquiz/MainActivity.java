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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text_question = (TextView) findViewById(R.id.text_question);
        text_question.setText(R.string.question_content);

        String[] answers = getResources().getStringArray(R.array.answers);

        for(int i = 0; i<ids_answer.length;i++) {
            RadioButton rb = (RadioButton) findViewById(ids_answer[i]);
            rb.setText(String.format(answers[i]));
        }

        final int correct_answer = getResources().getInteger(R.integer.correct_answer);
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
