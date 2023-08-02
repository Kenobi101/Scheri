package FinalCode.au.Scheri;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Calendar;

import FinalCode.au.Scheri.R;

public class AlarmActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TextView mTextView;

    Button setAlarmButton;
    Button cancelAlarmButton;
    AlarmManager alarmManager;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_view);

        mTextView = findViewById(R.id.textView);

        setAlarmButton  = (Button) findViewById(R.id.setAlarm);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                alarmView();
            }
        });

        cancelAlarmButton = (Button) findViewById(R.id.cancelAlarm);
        cancelAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAlarm();
            }
        });

    }

    public void alarmView(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);
        //alarmView();

    }

    private void updateTimeText(Calendar c){

        String timeText = "Alarm Set For:";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        mTextView.setText(timeText);
    }

    private void startAlarm(Calendar c){
        AlarmManager AlarmManager = (android.app.AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE);

        alarmManager.setExact(android.app.AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    @SuppressLint("SetTextI18n")
    private void cancelAlarm(){
        AlarmManager AlarmManager = (android.app.AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE);

        alarmManager.cancel(pendingIntent);
        mTextView.setText("Alarm Canceled");
    }

}
