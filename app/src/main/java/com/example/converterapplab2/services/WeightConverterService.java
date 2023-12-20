package com.example.converterapplab2.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class WeightConverterService extends Service {
    private static final String POUND = "POUND";
    private static final String KILOGRAM = "KILOGRAM";
    private static final String GRAM = "GRAM";
    private static final String PUD = "PUD";
    private static final String CARAT = "CARAT";
    private static final String TONNE = "TONNE";

    private SharedPreferences sharedPreferences;

    public WeightConverterService(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public WeightConverterService() {
    }

    public String convert(double value, String fromValue, String toValue) {
        double result = 0.0;
        switch (fromValue) {
            case POUND:
                switch (toValue) {
                    case KILOGRAM:
                        result = value * sharedPreferences.getFloat("POUND_KILOGRAM",0f);
                        break;
                    case GRAM:
                        result = value * sharedPreferences.getFloat("POUND_GRAM",0f);
                        break;
                    case PUD:
                        result = value * sharedPreferences.getFloat("POUND_PUD",0f);
                        break;
                    case CARAT:
                        result = value * sharedPreferences.getFloat("POUND_CARAT",0f);
                        break;
                    case TONNE:
                        result = value * sharedPreferences.getFloat("POUND_TON",0f);
                        break;
                    default:
                        result=value;
                        break;
                }
                break;
            case KILOGRAM:
                switch (toValue) {
                    case POUND:
                        result = value * sharedPreferences.getFloat("KILOGRAM_POUND",0f);
                        break;
                    case GRAM:
                        result = value * sharedPreferences.getFloat("KILOGRAM_GRAM",0f);
                        break;
                    case PUD:
                        result = value * sharedPreferences.getFloat("KILOGRAM_PUD",0f);
                        break;
                    case CARAT:
                        result = value * sharedPreferences.getFloat("KILOGRAM_CARAT",0f);
                        break;
                    case TONNE:
                        result = value * sharedPreferences.getFloat("KILOGRAM_TONNE",0f);
                        break;
                    default:
                        result=value;
                        break;
                }
                break;
            case GRAM:
                switch (toValue) {
                    case POUND:
                        result = value * sharedPreferences.getFloat("GRAM_POUND",0f);
                        break;
                    case KILOGRAM:
                        result = value * sharedPreferences.getFloat("GRAM_KILOGRAM",0f);
                        break;
                    case PUD:
                        result = value * sharedPreferences.getFloat("GRAM_PUD",0f);
                        break;
                    case CARAT:
                        result = value * sharedPreferences.getFloat("GRAM_CARAT",0f);
                        break;
                    case TONNE:
                        result = value * sharedPreferences.getFloat("GRAM_TONNE",0f);
                        break;
                    default:
                        result=value;
                        break;
                }
                break;
            case PUD:
                switch (toValue) {
                    case POUND:
                        result = value * sharedPreferences.getFloat("PUD_POUND",0f);
                        break;
                    case KILOGRAM:
                        result = value * sharedPreferences.getFloat("PUD_KILOGRAM",0f);
                        break;
                    case GRAM:
                        result = value * sharedPreferences.getFloat("PUD_GRAM",0f);
                        break;
                    case CARAT:
                        result = value * sharedPreferences.getFloat("PUD_CARAT",0f);
                        break;
                    case TONNE:
                        result = value * sharedPreferences.getFloat("PUD_TONNE",0f);
                        break;
                    default:
                        result=value;
                        break;
                }
                break;
            case CARAT:
                switch (toValue) {
                    case POUND:
                        result = value * sharedPreferences.getFloat("CARAT_POUND",0f);
                        break;
                    case KILOGRAM:
                        result = value * sharedPreferences.getFloat("CARAT_KILOGRAM",0f);
                        break;
                    case GRAM:
                        result = value * sharedPreferences.getFloat("CARAT_GRAM",0f);
                        break;
                    case PUD:
                        result = value * sharedPreferences.getFloat("CARAT_PUD",0f);
                        break;
                    case TONNE:
                        result = value * sharedPreferences.getFloat("CARAT_TONNE",0f);
                        break;
                    default:
                        result=value;
                        break;
                }
                break;
            case TONNE:
                switch (toValue) {
                    case POUND:
                        result = value * sharedPreferences.getFloat("TONNE_POUND",0f);
                        break;
                    case KILOGRAM:
                        result = value * sharedPreferences.getFloat("TONNE_KILOGRAM",0f);
                        break;
                    case GRAM:
                        result = value * sharedPreferences.getFloat("TONNE_GRAM",0f);
                        break;
                    case PUD:
                        result = value * sharedPreferences.getFloat("TONNE_PUD",0f);
                        break;
                    case CARAT:
                        result = value * sharedPreferences.getFloat("TONNE_CARAT",0f);
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

    public class WeightConverterBinder extends Binder {
        public WeightConverterService getService() {
            return WeightConverterService.this;
        }
    }

}