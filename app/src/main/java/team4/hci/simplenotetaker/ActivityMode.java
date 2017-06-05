package team4.hci.simplenotetaker;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

// This class is for the user so he can choose between the Day/Night/Auto Mode

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
        btnDayMode= (Button) findViewById(R.id.buttonAuto);


        btnAutoMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set Auto Mode - In 22:00 the App will automatically change itselft to Night Mode
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