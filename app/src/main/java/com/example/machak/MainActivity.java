package com.example.machak;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;


/*
 main activity will have
 - terminal
 - input
 - this month's data

 */


public class MainActivity extends AppCompatActivity {

    private TextView terminal_window;
    private EditText location_input; // POS (Point Of Sale)
    private EditText amount_input; // How much was spent?

    private ArrayList<MonthData> month_list;
    private MonthData current_month;

    private File data_file;
    private final String DATA_FILE_PATH = "monthdata.json";

    final Type MONTH_LIST_ARRAY_TYPE = new TypeToken<ArrayList<MonthData>>() {
    }.getType();

    private Gson gson = new Gson();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //
        data_file = new File(this.getFilesDir(), DATA_FILE_PATH);

        // Appoint variable access.
        terminal_window = findViewById(R.id.terminal_textview);
        location_input = findViewById(R.id.locationTest);
        amount_input = findViewById(R.id.amountTest);

        // test
        loadMonthData();
        updateTerminalDisplay();
//        String formatTime = (new Timestamp()).toString();
//        terminal_window.setText(formatTime);
//        Log.d("testtime", formatTime);


        // Load month data.


        // if new month, save monthdata into oldmonthdata, make new monthdata (popup maybe w/ budget)


    }


    // -- UTILITY FUNCTIONS

    @SuppressLint("SetTextI18n")
    private void loadMonthData() {

        try {

            if (data_file.exists()) {

                // read
                String contents = new String(Files.readAllBytes(data_file.toPath()));

//                terminal_window.setText("file exists -> " + contents);

                // have a list of monthdata obj --> [monthdata1, monthdata2]
                month_list = gson.fromJson(contents, MONTH_LIST_ARRAY_TYPE); // typetoken forces monthdata recognition in runtime

                // create condition for creating new month data object
//                if (!month_list.isEmpty()) {

                    current_month = month_list.get(month_list.size() - 1); // get last without possibly using getLast which may be depreciated

                    // if it is a new month, set current month to new m
                    if (current_month.getMonth() != Timestamp.getCurrentMonth() && current_month.getYear() != Timestamp.getCurrentYear()) {
                        current_month = new MonthData();
                        month_list.add(current_month);
//                        terminal_window.setText("new month ,and exists!");

                    }

//                }
//                else {
//
//                    // file initialized, not empty (kinda lame?)
//
//                    current_month = new MonthData();
//                    month_list.add(current_month);
//                    updateFileContents();
//
//                }
            } else {
                terminal_window.setText("[WELCOME TO MACHAK!]");
                month_list = new ArrayList<>();
                current_month = new MonthData();
                month_list.add(current_month);

                updateFileContents();
            }
        } catch (Exception e) {
            terminal_window.setText("[FAILED TO LOAD]");
        }
    }


    private void updateFileContents() {

        //serialize data
        String serializedMonthData = gson.toJson(month_list); // no type needed O.O (wow) // assuming latest month has been updated (yes should be updated)

        // write to file

        try {
            Files.write(data_file.toPath(), serializedMonthData.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            Log.d("err", "couldnt locate file, or sum issue like that");
        }
    }

    private void updateTerminalDisplay() {
        String text = "";

        for (Transaction transaction : current_month.getTransactionLog()) {

            text += transaction.getSummary() + "\n\n";
        }

        terminal_window.setText(text);

    }



    // -- ONCLICK METHODS


    // Associated with the 'submit' button.
    public void submitTransaction(View view) {
//      // read entered parameters

//      (check that none are empty) <-- might make button just disabled until those are entered


        // read vals
        double amount = Double.parseDouble(amount_input.getText().toString());
        String location = location_input.getText().toString();


        // add new transaction object to monthly data
        current_month.appendTransaction(new Transaction(location, amount, new Timestamp()));

        // write to file
        updateFileContents();

        // clear input fields, update gui
        amount_input.getText().clear();
        location_input.getText().clear();

        updateTerminalDisplay();
        Log.d("test", "transaction append successful");

    }

    public void openMonthLog(View view) {

    }


}    