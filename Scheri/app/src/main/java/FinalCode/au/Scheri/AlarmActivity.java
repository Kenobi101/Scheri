package FinalCode.au.Scheri;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;


import androidx.appcompat.app.AppCompatActivity;


public class AlarmActivity extends AppCompatActivity{


    Button setAlarmButton;
    Button cancelAlarmButton;
    int hourOfDayAlarm, minutesAlarm;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_view);

        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        hourOfDayAlarm = timePicker.getHour();
        minutesAlarm = timePicker.getMinute();
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                hourOfDayAlarm = i;
                minutesAlarm = i1;
            }
        });

        setAlarmButton  = (Button) findViewById(R.id.setAlarm);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("hourOfDayAlarm", hourOfDayAlarm);
                returnIntent.putExtra("minutesAlarm", minutesAlarm);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        cancelAlarmButton = (Button) findViewById(R.id.cancelAlarm);
        cancelAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });

        }

    }
