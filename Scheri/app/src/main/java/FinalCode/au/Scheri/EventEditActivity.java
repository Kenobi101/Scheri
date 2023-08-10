package FinalCode.au.Scheri;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class EventEditActivity extends AppCompatActivity
{
    private EditText eventNameET;
    private TextView eventDateTV;
    private TextView eventTime;
    int hourOfDayAlarm = -1;
    int minutesAlarm = -1;
    LocalDateTime dateTime;


    Button scheduleButton;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_event_edit);
        initWidgets();
        eventTime = (TextView) findViewById(R.id.eventTimeTV);

        dateTime = CalendarUtils.selectedDate.atTime(0, 0, 0);

        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(dateTime.toLocalDate()));


        scheduleButton  = (Button) findViewById(R.id.scheduleAlarm);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmView();
            }
        });

    }





    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


    private void initWidgets() {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);

    }

    public void saveEventAction(View view) {

        if(hourOfDayAlarm != -1 && minutesAlarm != -1){
            dateTime = dateTime
                    .withHour(hourOfDayAlarm)
                    .withMinute(minutesAlarm)
                    .withSecond(0);

            Log.i("TAG", "Save event hour" + dateTime.getHour() + "minute" + dateTime.getMinute());

            triggerAlarm();
        }


        String eventName = eventNameET.getText().toString();
        Event newEvent = new Event(eventName, dateTime.toLocalDate(), dateTime.toLocalTime());
        Event.eventsList.add(newEvent);
        finish();
    }

    private void triggerAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(AlarmManager.class);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("eventName", eventNameET.getText().toString());

        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP, dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                PendingIntent.getBroadcast(this, 1, intent,PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE)
        );
    }




    public void alarmView(){
        int alarm = 1;
        Intent i = new Intent(this, AlarmActivity.class);
        startActivityForResult(i, 1);
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK && data.getExtras() !=null){
                hourOfDayAlarm = data.getIntExtra("hourOfDayAlarm", -1);
                minutesAlarm = data.getIntExtra("minutesAlarm", -1);

                eventTime.setText("Alarm on = " + hourOfDayAlarm + " : " + minutesAlarm);
            } else {
                hourOfDayAlarm = -1;
                minutesAlarm = -1;

                eventTime.setText("No Alarm");
            }
        }
    }
    }

