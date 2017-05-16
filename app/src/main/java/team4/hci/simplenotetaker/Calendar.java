package team4.hci.simplenotetaker;

import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.Locale;

public class Calendar extends AppCompatActivity {



    TextView thedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        thedate = (TextView) findViewById(R.id.list_note_date);


    }
}
