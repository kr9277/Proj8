package com.example.proj8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/** @author Created by karin on 9/11/2023.
 * @version 1.0
 * @since 13/11/2023
 *On this Activity, there are three buttons, an edit text and a Text view, and a context menu which contains 2 options, stay on this screen or move to the credits screen.
 */

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText et;
    Button btn1, btn2, btn3;
    private final String FILENAME = "inttest.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        tv.setText(read());
    }
    /**
     * onCreateOptionsMenu method
     * <p> Creating the options menu
     * </p>
     *
     * @param menu the Menu object to pass to the inflater
     * @return true
     */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.tafrit, menu);
        return true;
    }
    /**
     * onOptionsItemSelected method
     * <p> Reacting the options menu
     * </p>
     *
     * @param item the MenuItem object that triggered by the listener
     * @return super.onOptionsItemSelected(item)
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        String str = item.getTitle().toString();
        if(str.equals("Credits_Activity")){
            Intent intent = new Intent(this, Credits_Activity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * save method
     * <p> Reacting the first button
     * </p>
     *
     * @param view the view that triggered the method
     * The method is writing the text to the text file & display
     */
    public void save(View view){
        try{
            String x = read();
            FileOutputStream fOS = openFileOutput(FILENAME, MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter (oSW);
            String y = et.getText().toString();
            bW.write(x+y);
            bW.close();
            oSW.close();
            fOS.close();
            tv.setText(read());
            et.setText("");
        }
        catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Failed to save text file", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * delete method
     * <p> Reacting the second button
     * </p>
     *
     * @param view the view that triggered the method
     * The method is deleting the text from the text file
     */
    public void delete(View view){
        try{
            String x = "";
            FileOutputStream fOS = openFileOutput(FILENAME, MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter (oSW);
            bW.write(x);
            bW.close();
            oSW.close();
            fOS.close();
            tv.setText(read());
            et.setText("");
        }
        catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Failed to save text file", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * out method
     * <p> Reacting the third button
     * </p>
     *
     * @param view the view that triggered the method
     * The method is saving the text to the text file & close the app
     */
    public void out(View view){
        save(view);
        finish();
    }
    /**
     * read() method
     *
     * The method is reading and returning the text from the text file
     */
    public String read(){
        try{
            FileInputStream fIS = openFileInput(FILENAME);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while(line!=null){
                sB.append(line+'\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            fIS.close();
            String z = sB.toString();
            if(z.length()>=1){
                z = z.substring(0, z.length()-1);
            }
            return z;
        }
        catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Failed to read text file", Toast.LENGTH_SHORT).show();
        }
        return "";
    }
}