package com.example.viewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultField; // вывод рез-та
    EditText numberField;   // число (ввод)
    TextView operationField;    // операция +-/*

    Double result = null;  // результт операции
    String lastOperation = "="; // последняя операция
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // получаем все поля по id из activity_main.xml
        resultField =(TextView) findViewById(R.id.resultField);
        numberField = (EditText) findViewById(R.id.numberField);
        operationField = (TextView) findViewById(R.id.operationField);
    }

    public void onNumberClick(View view){
        Button button = (Button)view;
        numberField.append(button.getText()); //забираем цифру с кнопки и добавляем
        if(lastOperation.equals("=") && result!=null){
            result = null;
        }
    }
//     +-/*
    public void onOperationClick(View view){
        Button button = (Button)view;
        String operation = button.getText().toString();
        String number = numberField.getText().toString(); // берем готовую цифру (поле)
        // если введенно что-нибудь
        if(number.length()>0){
            number = number.replace(',', '.'); // нужная запись
            try{
                performOperation(Double.valueOf(number), operation);
            }catch (NumberFormatException ex){
                numberField.setText("");
            }
        }
        lastOperation = operation;
        operationField.setText(lastOperation);
    }

    private void performOperation(Double number, String operation){

        if(result ==null){
            result = number; //число с клавы
        }
        else{
            if(lastOperation.equals("=")){
                lastOperation = operation;
            }
            switch(lastOperation){
                case "=":
                    result =number;
                    break;
                case "/":
                    if(number==0){
                        result = null;
                        resultField.setText("ERROR. cannot be divided by zero");
                        numberField.setText("");
                        return;
                    }
                    else{
                        result /=number;
                    }
                    break;
                case "*":
                    result *=number;
                    break;
                case "+":
                    result +=number;
                    break;
                case "-":
                    result -=number;
                    break;
            }
        }
        resultField.setText(result.toString().replace('.', ','));
        numberField.setText("");
    }
}