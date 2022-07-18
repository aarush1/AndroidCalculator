 package com.example.basic_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView resultTv, solutionTv;
    MaterialButton button_c,button_bracketopen,button_bracketclose;
    MaterialButton button_divide,button_multiply,button_plus,button_minus,button_equals;
    MaterialButton button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;
    MaterialButton button_ac,button_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignid(button_c, R.id.button_c);
        assignid(button_ac, R.id.button_ac);
        assignid(button_0, R.id.button_0);
        assignid(button_1, R.id.button_1);
        assignid(button_2, R.id.button_2);
        assignid(button_3, R.id.button_3);
        assignid(button_4, R.id.button_4);
        assignid(button_5, R.id.button_5);
        assignid(button_6, R.id.button_6);
        assignid(button_7, R.id.button_7);
        assignid(button_8, R.id.button_8);
        assignid(button_9, R.id.button_9);
        assignid(button_divide, R.id.button_divide);
        assignid(button_multiply, R.id.button_multiply);
        assignid(button_equals, R.id.button_equals);
        assignid(button_minus, R.id.button_minus);
        assignid(button_plus, R.id.button_plus);
        assignid(button_dot, R.id.button_dot);
        assignid(button_bracketopen, R.id.button_open_bracket);
        assignid(button_bracketclose, R.id.button_close_bracket);
    }


    void assignid(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }
     @Override
     public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText  = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }

        solutionTv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("err")){
            resultTv.setText(finalResult);
        }
     }

     String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e){
            return "err";

        }

     }
 }