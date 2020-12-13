package com.example.android1lesson5homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private EditText editText;
    private Button btnSave;
    Title title;
    public static final String KEY2 = "key2";
    private CalendarView calendar;
    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        calendar = findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int mYear = year;
                int mMonth = month;
                int mDay = dayOfMonth;
                selectedDate = new StringBuilder().append(mMonth + 1)
                        .append("-").append(mDay).append("-").append(mYear)
                        .append(" ").toString();
                Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_LONG).show();
            }
        });

        editText = findViewById(R.id.editText);
        btnSave = findViewById(R.id.btnResult);
        getData();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                title = new Title(editText.getText().toString());
                title.data = selectedDate;
                intent.putExtra(KEY2, title);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void getData() {
        if (getIntent() != null) {
            title = (Title) getIntent().getSerializableExtra(MainActivity.KEY);
            editText.setText(title.name);
        }
    }
}