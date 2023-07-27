package FinalCode.au.Scheri;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.media.audiofx.BassBoost;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
        MediaPlayer mediaPlayer = MediaPlayer(context, Settings.System.DEFAULT_NOTIFICATION_URI);

    }
}
