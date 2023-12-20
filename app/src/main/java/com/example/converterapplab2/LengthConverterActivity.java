package com.example.converterapplab2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.converterapplab2.services.LengthConverterService;

import java.util.HashMap;
import java.util.Map;

public class LengthConverterActivity extends AppCompatActivity {

    private LengthConverterService lengthConverterService;
    String[] valueTypes = {"Centimeter", "Meter", "Kilometer", "Inch", "Mile", "Yard", "Foot"};
    private SharedPreferences sharedPreferences;

    Spinner spinnerFrom, spinnerTo;
    EditText from, to;
    ArrayAdapter<String> adapterItems;
    public static final String SPINNER_FROM = "SPINNER_FROM";
    public static final String SPINNER_TO = "SPINNER_TO";
    public static final String FROM = "FROM";
    public static final String TO = "TO";

    @Override

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SPINNER_FROM, spinnerFrom.getId());
        outState.putInt(SPINNER_TO, spinnerTo.getId());
        outState.putString(FROM, from.getText().toString());
        outState.putString(TO, to.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length_converter);

        sharedPreferences= getApplicationContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Intent intent = new Intent(this, LengthConverterService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

        spinnerFrom = findViewById(R.id.fromValue);
        spinnerTo = findViewById(R.id.toValue);

        from = findViewById(R.id.fromInput);
        to = findViewById(R.id.toOutput);

        if (savedInstanceState != null) {
            spinnerFrom.setId(savedInstanceState.getInt(SPINNER_FROM));
            spinnerTo.setId(savedInstanceState.getInt(SPINNER_TO));
            from.setText(savedInstanceState.getString(FROM));
            to.setText(savedInstanceState.getString(TO));
        }

        adapterItems = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, valueTypes);
        spinnerFrom.setAdapter(adapterItems);
        spinnerTo.setAdapter(adapterItems);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                update();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        from.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                update();
            }
        });
        findViewById(R.id.weightButton).setOnClickListener(view -> {
            Intent intent1 = new Intent(this, WeightConverterActivity.class);
            startActivity(intent1);
        });
        findViewById(R.id.temperatureButton).setOnClickListener(view -> {
            Intent intent1 = new Intent(this, TemperatureConverterActivity.class);
            startActivity(intent1);
        });
    }

    private void update() {
        if (!from.getText().toString().equals("") && !spinnerFrom.getSelectedItem().toString().equals("")) {
            String input = from.getText().toString();
            String fromValue = spinnerFrom.getSelectedItem().toString().toUpperCase();
            String toValue = spinnerTo.getSelectedItem().toString().toUpperCase();
            lengthConverterService = new LengthConverterService(sharedPreferences);
            to.setText(String.valueOf(lengthConverterService.convert(Double.parseDouble(input),
                    fromValue,
                    toValue)));
        } else
            to.setText("");
    }

    private ServiceConnection serviceConnection =
            new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    lengthConverterService = ((LengthConverterService.LengthConverterBinder) iBinder)
                            .getService();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    lengthConverterService = null;
                }
            };
}