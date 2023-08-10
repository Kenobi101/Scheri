package FinalCode.au.Scheri;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;


public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String eventName = "Event";
        if(intent.getExtras() != null && intent.getExtras().containsKey("eventName")){
            eventName = intent.getExtras().getString("eventName", "event");
        }

        Log.i("TAG", "Alarm Time" + eventName);

        String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Alarm"+eventName)
                .setContentText("Alert")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = manager.getNotificationChannel(channelID);
            if(notificationChannel == null){
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelID, "Event Notice", importance);
                notificationChannel.enableVibration(true);
                manager.createNotificationChannel(notificationChannel);
            }

        }

        manager.notify(0, builder.build());

    }
}
