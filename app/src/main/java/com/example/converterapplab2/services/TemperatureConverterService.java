package com.example.converterapplab2.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Stack;

public class TemperatureConverterService extends Service {

    private static final String CELSIUS = "CELSIUS";
    private static final String FAHRENHEIT = "FAHRENHEIT";
    private static final String KELVIN = "KELVIN";

    private SharedPreferences sharedPreferences;

    public TemperatureConverterService() {
    }

    public TemperatureConverterService(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }
    public String convert(double value, String fromValue, String toValue) {
        double result = 0.0;
        switch (fromValue) {
            case CELSIUS:
                switch (toValue) {
                    case FAHRENHEIT:
                        result = evaluateExpression(value,sharedPreferences.getString("CELSIUS_FAHRENHEIT",""));
                        break;
                    case KELVIN:
                        result = evaluateExpression(value,sharedPreferences.getString("CELSIUS_KELVIN",""));
                        break;
                    default:
                        result=value;
                        break;
                }
                break;
            case FAHRENHEIT:
                switch (toValue) {
                    case CELSIUS:
                        result = evaluateExpression(value,sharedPreferences.getString("FAHRENHEIT_CELSIUS",""));
                        break;
                    case KELVIN:
                        result = evaluateExpression(value,sharedPreferences.getString("FAHRENHEIT_KELVIN",""));
                        break;
                    default:
                        result=value;
                        break;
                }
                break;
            case KELVIN:
                switch (toValue) {
                    case CELSIUS:
                        result = evaluateExpression(value,sharedPreferences.getString("KELVIN_CELSIUS",""));
                        break;
                    case FAHRENHEIT:
                        result = evaluateExpression(value,sharedPreferences.getString("KELVIN_FAHRENHEIT",""));
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

        private static int precedence(char operator) {
            switch (operator) {
                case '+':
                case '-':
                    return 1;
                case '*':
                case '/':
                    return 2;
            }
            return -1;
        }

        private static float applyOp(char op, float b, float a) {
            switch (op) {
                case '+':
                    return a + b;
                case '-':
                    return a - b;
                case '*':
                    return a * b;
                case '/':
                    if (b == 0) {
                        throw new UnsupportedOperationException("Cannot divide by zero");
                    }
                    return a / b;
            }
            return 0;
        }

        public static float evaluateExpression(double value,String expression) {
        expression=expression.replace("value",String.valueOf(value));
        char[] tokens = expression.toCharArray();


            Stack<Float> values = new Stack<>();
            Stack<Character> ops = new Stack<>();

            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i] == ' ')
                    continue;

                if (tokens[i] >= '0' && tokens[i] <= '9') {
                    StringBuilder sb = new StringBuilder();
                    while (i < tokens.length && (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.')) {
                        sb.append(tokens[i++]);
                    }
                    values.push(Float.parseFloat(sb.toString()));
                    i--;
                } else if (tokens[i] == '(') {
                    ops.push(tokens[i]);
                } else if (tokens[i] == ')') {
                    while (ops.peek() != '(')
                        values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                    ops.pop();
                } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                    while (!ops.empty() && precedence(tokens[i]) <= precedence(ops.peek())) {
                        values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                    }
                    ops.push(tokens[i]);
                }
            }

            while (!ops.empty()) {
                values.push(applyOp(ops.pop(), values.pop(), values.pop()));
            }

            return values.pop();
        }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class TemperatureConverterBinder extends Binder {
        public TemperatureConverterService getService() {
            return TemperatureConverterService.this;
        }
    }
}