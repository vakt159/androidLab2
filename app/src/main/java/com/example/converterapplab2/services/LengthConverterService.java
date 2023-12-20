package com.example.converterapplab2.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Map;

public class LengthConverterService extends Service {

    private static final String METER="METER";
    private static final String CENTIMETER="CENTIMETER";
    private static final String YARD="YARD";
    private static final String FOOT="FOOT";
    private static final String KILOMETER="KILOMETER";
    private static final String INCH="INCH";
    private static final String MILE="MILE";
    public LengthConverterService() {
    }
    private SharedPreferences sharedPreferences;

    public LengthConverterService(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public String convert(double value, String fromValue, String toValue) {
        double result = 0.0;
        switch (fromValue) {
            case METER:
                switch (toValue) {
                    case CENTIMETER:
                        result = value * sharedPreferences.getFloat("METER_CENTIMETER",0f);
                        break;
                    case FOOT:
                        result = value * sharedPreferences.getFloat("METER_FOOT",0f);
                        break;
                    case KILOMETER:
                        result = value / sharedPreferences.getFloat("METER_KILOMETER",0f);
                        break;
                    case MILE:
                        result = value / sharedPreferences.getFloat("METER_MILE",0f);
                        break;
                    case YARD:
                        result = value * sharedPreferences.getFloat("METER_YARD",0f);
                        break;
                    case INCH:
                        result = value * sharedPreferences.getFloat("METER_INCH",0f);
                        break;
                    default:
                        result=value;
                        break;
                }
                break;
            case KILOMETER:
                switch (toValue) {
                    case CENTIMETER:
                        result = value / sharedPreferences.getFloat("KILOMETER_CENTIMETER",0f);
                        break;
                    case FOOT:
                        result = value * sharedPreferences.getFloat("KILOMETER_FOOT",0f);
                        break;
                    case MILE:
                        result = value / sharedPreferences.getFloat("KILOMETER_MILE",0f);
                        break;
                    case YARD:
                        result = value * sharedPreferences.getFloat("KILOMETER_YARD",0f);
                        break;
                    case INCH:
                        result = value * sharedPreferences.getFloat("KILOMETER_INCH",0f);
                        break;
                    case METER:
                        result = value / sharedPreferences.getFloat("KILOMETER_METER",0f);
                        break;
                    default:
                        result=value;
                        break;
                }
                break;
            case CENTIMETER:
                switch (toValue) {
                    case FOOT:
                        result = value / sharedPreferences.getFloat("CENTIMETER_FOOT",0f);
                        break;
                    case MILE:
                        result = value / sharedPreferences.getFloat("CENTIMETER_MILE",0f);
                        break;
                    case YARD:
                        result = value / sharedPreferences.getFloat("CENTIMETER_YARD",0f);
                        break;
                    case INCH:
                        result = value / sharedPreferences.getFloat("CENTIMETER_INCH",0f);
                        break;
                    case METER:
                        result = value * sharedPreferences.getFloat("CENTIMETER_METER",0f);
                        break;
                    case KILOMETER:
                        result = value / sharedPreferences.getFloat("CENTIMETER_KILOMETER",0f);
                        break;
                    default:
                        result=value;
                        break;
                }
                break;
            case INCH:
                switch (toValue) {
                    case FOOT:
                        result = value * sharedPreferences.getFloat("INCH_FOOT",0f);
                        break;
                    case MILE:
                        result = value * sharedPreferences.getFloat("INCH_MILE",0f);
                        break;
                    case YARD:
                        result = value * sharedPreferences.getFloat("INCH_YARD",0f);
                        break;
                    case CENTIMETER:
                        result = value * sharedPreferences.getFloat("INCH_CENTIMETER",0f);
                        break;
                    case METER:
                        result = value * sharedPreferences.getFloat("INCH_METER",0f);
                        break;
                    case KILOMETER:
                        result = value * sharedPreferences.getFloat("INCH_KILOMETER",0f);
                        break;
                    default:
                        result=value;
                        break;
                }
                break;
            case YARD:
                switch (toValue) {
                    case FOOT:
                        result = value * sharedPreferences.getFloat("YARD_FOOT",0f);
                        break;
                    case MILE:
                        result = value / sharedPreferences.getFloat("YARD_MILE",0f);
                        break;
                    case CENTIMETER:
                        result = value * sharedPreferences.getFloat("YARD_CENTIMETER",0f);
                        break;
                    case METER:
                        result = value / sharedPreferences.getFloat("YARD_METER",0f);
                        break;
                    case KILOMETER:
                        result = value / sharedPreferences.getFloat("YARD_KILOMETER",0f);
                        break;
                    case INCH:
                        result = value * sharedPreferences.getFloat("YARD_INCH",0f);
                        break;

                    default:
                        result=value;
                        break;
                }
                break;
            case FOOT:
                switch (toValue) {
                    case MILE:
                        result = value / sharedPreferences.getFloat("FOOT_MILE",0f);
                        break;
                    case CENTIMETER:
                        result = value * sharedPreferences.getFloat("FOOT_CENTIMETER",0f);
                        break;
                    case INCH:
                        result = value * sharedPreferences.getFloat("FOOT_INCH",0f);
                        break;
                    case METER:
                        result = value / sharedPreferences.getFloat("FOOT_METER",0f);
                        break;
                    case YARD:
                        result = value / sharedPreferences.getFloat("FOOT_YARD",0f);
                        break;
                    case KILOMETER:
                        result = value / sharedPreferences.getFloat("FOOT_KILOMETER",0f);
                        break;
                    default:
                        result=value;
                        break;
                }
                break;
        }
        return formatter(result);
    }
    public static String formatter(Double number) {
        if (number == null) {
            return null;
        }

        // Використовуємо метод String.format для форматування числа з точністю до двох десяткових знаків
        String formattedNumber = String.format("%.10f", number);

        // Видаляємо нулі, що знаходяться після двох десяткових знаків
        formattedNumber = formattedNumber.replaceAll("0*$", "");

        // Видаляємо крапку, якщо вона залишається на кінці
        formattedNumber = formattedNumber.replaceAll("\\.$", "");

        return formattedNumber;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class LengthConverterBinder extends Binder {
        public LengthConverterService getService() {
            return LengthConverterService.this;
        }
    }



}
