package com.zybooks.simplecalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public TextView calculateMainText;
    public TextView calculateFunctionText;
    private Button zeroButton;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button divideButton;
    private Button addButton;
    private Button multiplyButton;
    private Button subtractButton;
    private Button enterButton;
    private Button decimalButton;

    private boolean hasDecimal;
    private boolean operating;
    private boolean reset;
    private double num1;
    private double num2;

    private double result;
    private String currentOperator;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button Objects
         zeroButton = findViewById(R.id.zeroButton);
            zeroButton.setOnClickListener(this);
         oneButton = findViewById(R.id.oneButton);
            oneButton.setOnClickListener(this);
         twoButton = findViewById(R.id.twoButton);
            twoButton.setOnClickListener(this);
         threeButton = findViewById(R.id.threeButton);
            threeButton.setOnClickListener(this);
         fourButton = findViewById(R.id.fourButton);
            fourButton.setOnClickListener(this);
         fiveButton = findViewById(R.id.fiveButton);
            fiveButton.setOnClickListener(this);
         sixButton = findViewById(R.id.sixButton);
            sixButton.setOnClickListener(this);
         sevenButton = findViewById(R.id.sevenButton);
            sevenButton.setOnClickListener(this);
         eightButton = findViewById(R.id.eightButton);
            eightButton.setOnClickListener(this);
         nineButton = findViewById(R.id.nineButton);
            nineButton.setOnClickListener(this);
         divideButton = findViewById(R.id.divideButton);
            divideButton.setOnClickListener(this);
         addButton = findViewById(R.id.addButton);
            addButton.setOnClickListener(this);
        subtractButton = findViewById(R.id.subtractButton);
         subtractButton.setOnClickListener(this);
         multiplyButton = findViewById(R.id.multiplyButton);
            multiplyButton.setOnClickListener(this);
         enterButton = findViewById(R.id.enterButton);
            enterButton.setOnClickListener(this);
         decimalButton = findViewById(R.id.decimalButton);
            decimalButton.setOnClickListener(this);

        calculateMainText = findViewById(R.id.calculateMainText);
        calculateMainText.setText("0");
        calculateFunctionText= findViewById(R.id.calculateFunctionView);

        /*BottomNavigationView navView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();

            AppBarConfiguration appBarConfig = new AppBarConfiguration.Builder(
                    R.id.navigation_calculator, R.id.navigation_common, R.id.navigation_previous)
                    .build();

            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
            NavigationUI.setupWithNavController(navView, navController);
        }*/
    }
    @Override
    public void onClick(View v){

       if(v.getId() == R.id.zeroButton) {
           evaluate('0');
       } else if(v.getId() == R.id.oneButton) {
           evaluate('1');
       }else if(v.getId() == R.id.twoButton) {
           evaluate('2');
       }else if(v.getId() == R.id.threeButton) {
           evaluate('3');
       }else if(v.getId() == R.id.fourButton) {
           evaluate('4');
       }else if(v.getId() == R.id.fiveButton) {
           evaluate('5');
       }else if(v.getId() == R.id.sixButton) {
           evaluate('6');
       }else if(v.getId() == R.id.sevenButton) {
           evaluate('7');
       }else if(v.getId() == R.id.eightButton) {
           evaluate('8');
       }else if(v.getId() == R.id.nineButton) {
           evaluate('9');
       }else if(v.getId() == R.id.addButton) {
           evaluate('+');
       }else if(v.getId() == R.id.subtractButton) {
           evaluate('-');
       }else if(v.getId() == R.id.multiplyButton) {
           evaluate('*');
       }else if(v.getId() == R.id.divideButton) {
           evaluate('/');
       }else if(v.getId() == R.id.decimalButton) {
           evaluate('.');
       }else if(v.getId() == R.id.enterButton) {
           evaluate('=');
       }

    }

    public void evaluate(char c)
    {
        String token = String.valueOf(c);
        String currentMainField = calculateMainText.getText().toString();
        String currentMiniFiled = calculateFunctionText.getText().toString();
        if(token.equals(".") && !currentMainField.isEmpty()){
            if(!hasDecimal) {
                calculateMainText.setText(currentMainField + token);
            }
            hasDecimal = true;
            operating = false;
            return;
        }
        if(isNumeric(token)){
            if(reset){
                calculateMainText.setText(token);
                currentMainField = calculateMainText.getText().toString();
                reset = false;
                hasDecimal = false;
                return;
            }
            if((hasDecimal || !currentMainField.equals("0") )&& !operating){
                calculateMainText.setText(currentMainField + token);
            }else{
                calculateMainText.setText(token);
                operating = false;
                hasDecimal = false;
            }
            //calculateMainText.setText(token);
            //calcStack.push(token);
            return;
        }

        if(isOperator(token)){
            if(reset){
                 num1 = result;
                 reset = false;
            }else{
                num1 = Double.parseDouble(currentMainField);
            }
            currentOperator = token;

            operating = true;
        }

        if(token.equals("=")){
            num2 = Double.parseDouble(currentMainField);
            Log.d("num1", String.valueOf(num1));
            Log.d("current operator", currentOperator);
            Log.d("num2", String.valueOf(num2));
            switch(currentOperator){
                case ("+"):
                    result = num1 + num2;
                    break;
                case ("-"):
                    result = num1 - num2;
                    break;
                case ("*"):
                    result = num1 * num2;
                    break;
                case("/"):
                    result = num1 / num2;
                    break;
            }
            calculateFunctionText.setText(String.valueOf(num1)
                    + currentOperator + String.valueOf(num2));
            calculateMainText.setText(String.valueOf(result));

            reset = true;
            hasDecimal = true;
        }
        //Log.d("stackPush", "onClick: " + calcStack.peek() );
    }



    public boolean isNumeric(String s)
    {
        try{
            Double.parseDouble(s);
            return true;
        }catch (NumberFormatException nfe){
            return false;
        }
    }

    public boolean isOperator(String s){
        if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
            return true;
        }
        return false;
    }
    
}