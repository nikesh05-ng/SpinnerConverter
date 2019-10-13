package com.example.spinnerconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    private EditText fromEditText = null, toEditText = null;
    private Spinner fromSpinner = null, toSpinner = null;
    private Button clearButton = null, convertButton =null;
    private ArrayAdapter<String> adaptor = null;

    private String [] currencyNames = {"EUR","USD","UKP","INR","ROL"};
    private double [] currencyRates = {1.0, 1.23, 0.89, 81.2, 4.78};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //wire widgets with objects
        fromEditText = findViewById(R.id.editText);
        toEditText = findViewById(R.id.editText1);

        fromSpinner = findViewById(R.id.spinner);
        toSpinner = findViewById(R.id.spinner1);

        clearButton = findViewById(R.id.button);
        convertButton = findViewById(R.id.button2);

        //make the spinners
        adaptor = new ArrayAdapter<String>( getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,currencyNames);
        fromSpinner.setAdapter(adaptor);
        toSpinner.setAdapter(adaptor);

        //deal with button events
        clearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                fromEditText.setText("");
                toEditText.setText("");
                fromSpinner.setSelection(0);
                toSpinner.setSelection(0);
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //get the value to convert
                double fromValue = Double.parseDouble(fromEditText.getText().toString());

                //get the selection and find the convert rate
                int fromSpinnerIndex = fromSpinner.getSelectedItemPosition();
                int toSpinnerIndex = toSpinner.getSelectedItemPosition();
                double rate = currencyRates[toSpinnerIndex] / currencyRates[fromSpinnerIndex];

                //calculate the converted amount

                double toAmount = (int)(100*fromValue*rate)/100;
                toEditText.setText(""+toAmount);

            }
        });

    }
}
