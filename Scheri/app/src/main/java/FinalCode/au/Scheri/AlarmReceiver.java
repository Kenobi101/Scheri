package FinalCode.au.Scheri;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Scheri Application")
        .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Schedule Notice")
                .setContentText("You have a Schedule coming")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123,builder.build());
        
    }
}
