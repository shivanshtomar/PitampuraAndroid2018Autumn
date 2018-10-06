package com.example.shivanshtomar.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view){
        TextView textView =findViewById(R.id.txt);

        EditText t1= (EditText)findViewById(R.id.input);
        EditText t2= (EditText)findViewById(R.id.input2);
        String ans1=t1.getText().toString();
        String ans2=t2.getText().toString();


        Log.e("TAG", "click: ----- ");

        if(ans1.length()!=0){
            x=Integer.parseInt(ans1);
        }

        if(ans2.length()!=0){
            y=Integer.parseInt(ans2);
        }




        Log.e("TAG", "click2: ----- ");


        double z =0.0;
        switch (view.getId()){

            case R.id.plus:
                z=x+y;
                break;
            case R.id.minus:
                z=x-y;
                break;
            case R.id.mod:
                z=x%y;
                break;
            case R.id.sqrt:
                z=Math.sqrt(x);
                break;
            case R.id.divide:
                double a=(double)y;
                z=x/a;

                break;
            case R.id.multiply:
                z=x*y;
                break;


        }


            textView.setText(" "+z);

    }
}
