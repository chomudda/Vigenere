package today.duma.vigenere;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import java.util.Random;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //Widgets
    public EditText message;
    public EditText key;
    public RadioGroup radioGroup;

    //Global arrays
    public ArrayList config = new ArrayList();
    public ArrayList message_no = new ArrayList();
    public ArrayList key_no = new ArrayList();
    public ArrayList wrap = new ArrayList();
    public ArrayList total = new ArrayList();
    public ArrayList fin = new ArrayList();
    public  ArrayList key_array = new ArrayList();
    public Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (EditText)findViewById(R.id.message);
        key = (EditText)findViewById(R.id.key);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }

    //ENCRYPT
    public void encrypt(View view){
        //Clear arrays
        message_no.clear();
        key_no.clear();
        wrap.clear();
        total.clear();
        fin.clear();

        if(radioGroup.getCheckedRadioButtonId()==-1){
            Toast.makeText(getApplicationContext(), "Method not selected", Toast.LENGTH_SHORT).show();
            return;
        }
        if(message.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(), "Please enter a message", Toast.LENGTH_SHORT).show();
            return;
        }
        if(key.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(), "Please enter a key", Toast.LENGTH_SHORT).show();
            return;
        }
        //Message to values
        for(int i=0; i<message.getText().length(); i++){
            message_no.add(config.indexOf(message.getText().charAt(i)));
        }
        //Key to values
        for(int i=0; i<key.getText().length(); i++){
            key_no.add(config.indexOf(key.getText().charAt(i)));
        }
        //Wrap key to message
        int c=0;
        for(int i=0; i<message_no.size(); i++){
            wrap.add(key_no.get(c));
            c++;
            if(c>key_no.size()-1){
                c=0;
            }
        }
        //Sum wrap values with message values
        for(int i=0; i<message_no.size(); i++){
            total.add(Integer.parseInt(String.valueOf(message_no.get(i)))+Integer.parseInt(String.valueOf(wrap.get(i))));
        }
        //Error handle values > config length
        for(int i=0; i<total.size(); i++){
            if(Integer.parseInt(String.valueOf(total.get(i)))>config.size()-1){
                total.set(i, Integer.parseInt(String.valueOf(total.get(i)))-(config.size()-1)-1);
            }
        }
        //Get message from value
        for(int i=0; i<total.size(); i++){
            fin.add(config.get(Integer.parseInt(String.valueOf(total.get(i)))));
        }
        //Array to string
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < fin.size(); i++) {
            strBuilder.append(fin.get(i));
        }
        String newString = strBuilder.toString();
        System.out.println(newString);
        message.setText(newString);
    }

    //DECRYPT
    public void decrypt(View view){
        //Clear arrays
        message_no.clear();
        key_no.clear();
        wrap.clear();
        total.clear();
        fin.clear();

        if(radioGroup.getCheckedRadioButtonId()==-1){
            Toast.makeText(getApplicationContext(), "Method not selected", Toast.LENGTH_SHORT).show();
            return;
        }
        if(message.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(), "Please enter a message", Toast.LENGTH_SHORT).show();
            return;
        }
        if(key.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(), "Please enter a key", Toast.LENGTH_SHORT).show();
            return;
        }
        //Message to values
        for(int i=0; i<message.getText().length(); i++){
            message_no.add(config.indexOf(message.getText().charAt(i)));
        }
        //Key to values
        for(int i=0; i<key.getText().length(); i++){
            key_no.add(config.indexOf(key.getText().charAt(i)));
        }
        //Wrap key to message
        int c=0;
        for(int i=0; i<message_no.size(); i++){
            wrap.add(key_no.get(c));
            c++;
            if(c>key_no.size()-1){
                c=0;
            }
        }
        //Sum wrap values with message values
        for(int i=0; i<message_no.size(); i++){
            total.add(Integer.parseInt(String.valueOf(message_no.get(i)))-Integer.parseInt(String.valueOf(wrap.get(i))));
        }
        //If total < 0, make positive
        for(int i=0; i<total.size(); i++){
            if(Integer.parseInt(String.valueOf(total.get(i)))<0){
                total.set(i, Integer.parseInt(String.valueOf(total.get(i)))+(config.size()));
            }
        }
        //Get message from value
        for(int i=0; i<total.size(); i++){
            fin.add(config.get(Integer.parseInt(String.valueOf(total.get(i)))));
        }
        //Array to string
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < fin.size(); i++) {
            strBuilder.append(fin.get(i));
        }
        String newString = strBuilder.toString();
        System.out.println(newString);
        message.setText(newString);
    }

    //RADIO
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.simple:
                if (checked)
                    //Toast.makeText(getApplicationContext(), "Simple", Toast.LENGTH_SHORT).show();
                    config.clear();
                config.addAll(Arrays.asList(' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
                //System.out.println(config);
                break;
            case R.id.standard:
                if (checked)
                    //Toast.makeText(getApplicationContext(), "Standard", Toast.LENGTH_SHORT).show();
                    config.clear();
                config.addAll(Arrays.asList(' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'H', 'E', 'Q', 'U', 'I', 'C', 'K', 'B', 'R', 'O', 'W', 'N', 'F', 'X', 'J', 'M', 'P', 'S', 'V', 'L', 'A', 'Z', 'Y', 'D', 'G', 't', 'h', 'e', 'q', 'u', 'i', 'c', 'k', 'b', 'r', 'o', 'w', 'n', 'f', 'x', 'j', 'm', 'p', 's', 'v', 'l', 'a', 'z', 'y', 'd', 'g'));
                //System.out.println(config);
                break;
            case R.id.ascii:
                if (checked)
                    //Toast.makeText(getApplicationContext(), "ASCII", Toast.LENGTH_SHORT).show();
                    config.clear();
                config.addAll(Arrays.asList(' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~'));
                //System.out.println(config);
                break;
        }
    }

    //KEY GEN
    public void key_gen(View view){
        key_array.clear();

        if(radioGroup.getCheckedRadioButtonId()==-1){
            Toast.makeText(getApplicationContext(), "Method not selected", Toast.LENGTH_SHORT).show();
            return;
        }

        if(message.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(), "Please enter a message", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < message.getText().toString().length(); i++) {
            int  j = rand.nextInt(config.size());
            key_array.add(config.get(j));
        }

        //Array to string
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < key_array.size(); i++) {
            strBuilder.append(key_array.get(i));
        }
        String newString = strBuilder.toString();
        System.out.println(newString);
        key.setText(newString);
    }

    //FLOATING ACTION BUTTON
    public void fab(View view){

    }
}