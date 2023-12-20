package com.example.converterapplab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addCoefficients();
        findViewById(R.id.lengthButton).setOnClickListener(view -> {
            Intent intent = new Intent(this, LengthConverterActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.weightButton).setOnClickListener(view -> {
            Intent intent = new Intent(this, WeightConverterActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.temperatureButton).setOnClickListener(view -> {
            Intent intent = new Intent(this, TemperatureConverterActivity.class);
            startActivity(intent);
        });
    }

    private void addCoefficients() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

// Length
        editor.putFloat("METER_KILOMETER", 0.001f);
        editor.putFloat("METER_CENTIMETER", 100f);
        editor.putFloat("METER_MILE", 0.0006f);
        editor.putFloat("METER_YARD", 1.094f);
        editor.putFloat("METER_INCH", 39.37f);
        editor.putFloat("METER_FOOT", 3.281f);


        editor.putFloat("KILOMETER_METER", 1000f);
        editor.putFloat("KILOMETER_CENTIMETER", 100000f);
        editor.putFloat("KILOMETER_MILE", 0.621371f);
        editor.putFloat("KILOMETER_YARD", 1094f);
        editor.putFloat("KILOMETER_INCH", 39370f);
        editor.putFloat("KILOMETER_FOOT", 3281f);


        editor.putFloat("CENTIMETER_METER", 0.01f);
        editor.putFloat("CENTIMETER_KILOMETER", 0.00001f);
        editor.putFloat("CENTIMETER_MILE", 0.000062f);
        editor.putFloat("CENTIMETER_YARD", 0.01f);
        editor.putFloat("CENTIMETER_INCH", 0.393f);
        editor.putFloat("CENTIMETER_FOOT", 0.032f);


        editor.putFloat("MILE_METER", 0.000621371f);
        editor.putFloat("MILE_KILOMETER", 1.609344f);
        editor.putFloat("MILE_CENTIMETER", 160934.4f);
        editor.putFloat("MILE_YARD", 1760f);
        editor.putFloat("MILE_INCH", 63360f);
        editor.putFloat("MILE_FOOT", 5280f);

        editor.putFloat("YARD_METER", 0.9144f);
        editor.putFloat("YARD_KILOMETER", 0.0009144f);
        editor.putFloat("YARD_CENTIMETER", 91.44f);
        editor.putFloat("YARD_MILE", 0.000568182f);
        editor.putFloat("YARD_INCH", 36f);
        editor.putFloat("YARD_FOOT", 3f);

        editor.putFloat("INCH_METER", 0.0254f);
        editor.putFloat("INCH_KILOMETER", 2.54e-5f);
        editor.putFloat("INCH_CENTIMETER", 2.54f);
        editor.putFloat("INCH_MILE", 1.5783e-5f);
        editor.putFloat("INCH_YARD", 0.0277778f);
        editor.putFloat("INCH_FOOT", 0.0833333f);


        editor.putFloat("GRAM_KILOGRAM",0.001f);
        editor.putFloat("GRAM_POUND",0.00220462f);
        editor.putFloat("GRAM_TONNE",1e-6f);
        editor.putFloat("GRAM_PUD",0.0005f);
        editor.putFloat("GRAM_CARAT",5.0f);

        editor.putFloat("KILOGRAM_GRAM",1000.0f);
        editor.putFloat("KILOGRAM_TONNE",0.001f);
        editor.putFloat("KILOGRAM_POUND",2.20462f);
        editor.putFloat("KILOGRAM_PUD",3.75f);
        editor.putFloat("KILOGRAM_CARAT",5000.0f);

        editor.putFloat("TONNE_GRAM",1000000.0f);
        editor.putFloat("TONNE_KILOGRAM",1000.0f);
        editor.putFloat("TONNE_POUND",2204.62f);
        editor.putFloat("TONNE_PUD",40000.0f);
        editor.putFloat("TONNE_CARAT",5000000.0f);

        editor.putFloat("POUND_GRAM",53.59237f);
        editor.putFloat("POUND_TONNE",0.00045359237f);
        editor.putFloat("POUND_KILOGRAM",0.45359237f);
        editor.putFloat("POUND_PUD",0.06104712004738f);
        editor.putFloat("POUND_CARAT",2267.96185f);

        editor.putFloat("PUD_GRAM", 119826.427f);
        editor.putFloat("PUD_TONNE",0.119826427f);
        editor.putFloat("PUD_POUND",0.119826427f);
        editor.putFloat("PUD_KILOGRAM",119.826427f);
        editor.putFloat("PUD_CARAT",599136.001f);

        editor.putFloat("CARAT_GRAM", 0.2f);
        editor.putFloat("CARAT_TONNE", 2e-7f);
        editor.putFloat("CARAT_POUND",0.0004409245244f);
        editor.putFloat("CARAT_PUD",2e-5f);
        editor.putFloat("CARAT_KILOGRAM",0.0002f);




        editor.putString("KELVIN_CELSIUS","value-273.15");
        editor.putString("CELSIUS_KELVIN","value+273.15");
        editor.putString("KELVIN_FAHRENHEIT","(value-273.15)*9/5+32");
        editor.putString("FAHRENHEIT_KELVIN","(value-32)*5/9+273.15");
        editor.putString("CELSIUS_FAHRENHEIT","value*9/5+32");
        editor.putString("FAHRENHEIT_CELSIUS","(value-32)*5/9");

        editor.apply(); // або editor.commit(), щоб зберегти зміни
    }

}
/*




    editor.putFloat("KELVIN_CELSIUS",%1$d-273.15);
    editor.putFloat("CELSIUS_KELVIN",%1$d+273.15);
    editor.putFloat("KELVIN_FAHRENHEIT",(%1$d-273.15)*9/5+32);
    editor.putFloat("FAHRENHEIT_KELVIN",(%1$d-32)*5/9+273.15);
    editor.putFloat("CELSIUS_FAHRENHEIT",%1$d*9/5+32);
    editor.putFloat("FAHRENHEIT_CELSIUS",(%1$d-32)*5/9);
 */