package team4.hci.simplenotetaker;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static team4.hci.simplenotetaker.R.color.colorPink;
import static team4.hci.simplenotetaker.R.id.button_pink;

public class ChangeColor2 extends AppCompatActivity {

    Button pink;
    Button orange;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color2);


        pink = (Button)findViewById(button_pink);
        orange = (Button)findViewById(R.id.button_orange);




        pink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {


                int color = colorPink;

                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(color)));

                SharedPreferences preferences = getSharedPreferences("prefKey", MODE_PRIVATE);

                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("colorValue", color);

                editor.apply();


                /** Intent i = new Intent(getApplicationContext(), MainActivity.class);
                 i.putExtra("color", colorPink);
                 startActivity(i);*/


            }

        });


        orange.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){



                int color = R.color.colorOrange;

                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(color)));

                SharedPreferences preferences = getSharedPreferences ("prefKey", MODE_PRIVATE);

                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt ("colorValue", color);

                editor.apply ();


            }



        });

        SharedPreferences preferences = getSharedPreferences ("prefKey", MODE_PRIVATE);
        int color = preferences.getInt ("colorValue", 0);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(color)));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }





    //for back icon
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }



}
