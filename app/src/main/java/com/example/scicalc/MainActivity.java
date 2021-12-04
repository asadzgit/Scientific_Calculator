package com.example.scicalc;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.*;

public class MainActivity extends AppCompatActivity {

    private TextView previousCalculation;
    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previousCalculation = findViewById(R.id.previousCalculationView);
        display = findViewById(R.id.displayText);
        display.setShowSoftInputOnFocus(false);
    }

    public  void updateText(String strToAdd){
        String oldSting = display.getText().toString();
        int cursPosition = display.getSelectionStart();
        String leftString = oldSting.substring(0,cursPosition);
        String rightString = oldSting.substring(cursPosition);
        display.setText(String.format("%s%s%s" , leftString , strToAdd , rightString));
        display.setSelection(cursPosition + strToAdd.length());
    }

    public void equalButtonPush(View view){
        String userExpression = display.getText().toString();
        previousCalculation.setText(userExpression);
        userExpression = userExpression.replaceAll(getResources().getString(R.string.divideText), "/");
        userExpression = userExpression.replaceAll(getResources().getString(R.string.multiplyText), "*");
        String result;
        try{
            Expression exp = new ExpressionBuilder( userExpression).build();
            result = String.valueOf(exp.evaluate());
        }
        catch(Exception e){
            result ="NaN";
        }
        display.setText(result);
        display.setSelection(result.length());
    }
    public void clearTextButtonPush(View view){
        display.setText("");
        previousCalculation.setText("");
    }
    public void backSpaceButtonPush(View view){
        try{
            previousCalculation.setText("");
            int cursorPosition = display.getSelectionStart();
            int textLength = display.getText().length();
            if(cursorPosition != 0 && textLength != 0){
                SpannableStringBuilder  removeOneCharacter = (SpannableStringBuilder) display.getText();
                removeOneCharacter.replace(cursorPosition-1 , cursorPosition , "");
                display.setText(removeOneCharacter);
                display.setSelection(cursorPosition - 1);
            }

        }
        catch(Exception e){

        }
    }

    public void moveCursor(String result){
        display.setText(result);
        int cursPosition = display.getSelectionStart();
        display.setSelection(cursPosition + result.length());
    }

    public void sinButtonPush(View view){
        updateText("sin(");
    }
    public void cosButtonPush(View view){
        updateText("cos(");
    }
    public void tanButtonPush(View view){
        updateText("tan(");
    }
    public void sinInverseButtonPush(View view){
        updateText("arcsin(");
    }
    public void cosInverseButtonPush(View view){
        updateText("arccos(");
    }
    public void tanInverseButtonPush(View view){
        updateText("arctan(");
    }
    public void logButtonPush(View view){
        updateText("log10(");
    }
    public void naturallogButtonPush(View view){
        updateText("ln(");
    }
    public void squarerootButtonPush(View view){
        updateText("sqrt(");
    }
    public void zeroButtonPush(View view){        updateText(getResources().getString(R.string.zeroText));    }
    public void oneButtonPush(View view){        updateText(getResources().getString(R.string.oneText));    }
    public void twoButtonPush(View view){
        updateText(getResources().getString(R.string.twoText));
    }
    public void threeButtonPush(View view){        updateText(getResources().getString(R.string.threeText));    }
    public void fourButtonPush(View view){        updateText(getResources().getString(R.string.fourText));    }
    public void fiveButtonPush(View view){        updateText(getResources().getString(R.string.fiveText));    }
    public void sixButtonPush(View view){        updateText(getResources().getString(R.string.sixText));    }
    public void sevenButtonPush(View view){        updateText(getResources().getString(R.string.sevenText));    }
    public void eightButtonPush(View view){        updateText(getResources().getString(R.string.eightText));    }
    public void nineButtonPush(View view){        updateText(getResources().getString(R.string.nineText));    }
    public void addButtonPush(View view){
        updateText(getResources().getString(R.string.addText));
    }
    public void minusButtonPush(View view){        updateText(getResources().getString(R.string.subtractText));    }
    public void multiplyButtonPush(View view){        updateText(getResources().getString(R.string.multiplyText));    }
    public void divideButtonPush(View view){        updateText(getResources().getString(R.string.divideText));    }
    public void bracketOpenButtonPush(View view){        updateText(getResources().getString(R.string.parenthesesOpenText));    }
    public void bracketCloseButtonPush(View view){        updateText(getResources().getString(R.string.parenthesesCloseText));    }
    public void decimalButtonPush(View view){        updateText(getResources().getString(R.string.decimalText));    }
    public void modeButtonPush(View view){
        updateText("abs( ");
    }
    public void xSquareButtonPush(View view){
        updateText("^(2)");
    }
    public void xPowerYButtonPush(View view){
        updateText("^(");
    }
    public void percentButtonPush(View view){
        updateText("%");
    }
    public void cubeButtonPush(View view){
        updateText("^(3)");
    }
    public void yRootXButtonPush(View view){
        updateText("^(1/");
    }

    public void exponentionButtonPush(View view){        updateText("e^(");
    }
    public void pieButtonPush(View view){        updateText(getResources().getString(R.string.piText));    }

    public void factorialButtonPush(View view){

        String str = display.getText().toString();
        try{
            int d = Integer.parseInt(str);
            int i,fact=1;
            for(i=1;i<=d;i++){
                fact=fact*i;
            }
            String result = Integer.toString(fact);
            moveCursor(result);
        }catch (Exception ex){
            updateText("NaN");
        }
    }

    public void r0ButtonPush(View view){

        String str = display.getText().toString();
        try{
            double d = Double.parseDouble(str);
            String result = Integer.toString((int) Math.round(d));
            moveCursor(result);
        }catch (Exception ex){
            updateText("NaN");
        }
    }
    public void r2ButtonPush(View view){

        String str = display.getText().toString();
        try{
            double d = Double.parseDouble(str);
            String result = Double.toString(Math.round(d*100.00)/100.00);
            moveCursor(result);
        }catch (Exception ex){
            updateText("NaN");
        }
    }

}