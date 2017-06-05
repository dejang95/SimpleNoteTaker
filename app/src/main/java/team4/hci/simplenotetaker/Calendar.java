package team4.hci.simplenotetaker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {

    TextView thedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        thedate = (TextView) findViewById(R.id.list_note_date);


    }
}
