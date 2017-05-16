package team4.hci.simplenotetaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;

public class ActivityMode extends AppCompatActivity {
    private Button btnAutoMode,btnNightMode,btnDayMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        //Default Night Mode as Auto Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        btnAutoMode= (Button) findViewById(R.id.buttonAuto);
        btnNightMode= (Button) findViewById(R.id.buttonNight);
        btnDayMode= (Button) findViewById(R.id.buttonDay);


        btnAutoMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set Auto Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                Intent i=new Intent(ActivityMode.this,MainActivity.class);
                startActivity(i);
            }
        });

        btnNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set Default Night Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Intent i=new Intent(ActivityMode.this,MainActivity.class);
                startActivity(i);
            }
        });

        btnDayMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set Default Day Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Intent i = new Intent(ActivityMode.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}