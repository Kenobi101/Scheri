package FinalCode.au.Scheri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;

import FinalCode.au.Scheri.R;

public class AlarmActivity extends AppCompatActivity {

    Button setAlarmButton;
    Button cancelAlarmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_view);

        setAlarmButton  = (Button) findViewById(R.id.setAlarm);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmView();
            }
        });




    }

    public void alarmView(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result","");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
