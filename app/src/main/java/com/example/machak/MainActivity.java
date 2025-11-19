package com.example.machak;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;



/*
 main activity will have
 - terminal
 - input
 - this month's data

 */

public class MainActivity extends AppCompatActivity {

    TextView terminal_window;
    EditText location_input; // POS (Point Of Sale)
    EditText amount_input; // How much was spent?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Appoint variable access.


        // Check month.
        // if new month, save monthdata into oldmonthdata, make new monthdata (popup maybe w/ budget)


        setContentView(R.layout.activity_main);
    }


    // -- ONCLICK METHODS


    // Associated with the 'submit' button.
    public void submitTransaction(View view) {

    }

    public void openMonthLog(View view) {

    }


}    