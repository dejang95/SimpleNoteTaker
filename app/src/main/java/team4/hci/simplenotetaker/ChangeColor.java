package team4.hci.simplenotetaker;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ChangeColor extends AppCompatActivity {

    Button pink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);


        pink = (Button)findViewById(R.id.button_pink);


    pink.setOnClickListener(new View.OnClickListener(){
    public void onClick(View view){
        getSupportActionBar().setTitle("Pink");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPink)));
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            Window window =getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));

        }
    }



});

    }
}
