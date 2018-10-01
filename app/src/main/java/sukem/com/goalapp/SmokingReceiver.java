package sukem.com.goalapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class SmokingReceiver extends BroadcastReceiver {
    private static final String TAG = NotificationReceiver.class.getSimpleName();
    String id = "channgelid";
    CharSequence name = "user new";
    String description = "alar,";

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int importance = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            importance = NotificationManager.IMPORTANCE_LOW;
        }

        Intent intent1 = new Intent(context, HomeActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(mChannel);
            }

        }

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentTitle("Do not Smoke Reminder")
                .setContentText("Save Your Life")
                .setVibrate(new long[] { 1000, 1000})
                .setChannelId(id)
                .setAutoCancel(true);

        Log.e(TAG, "onReceive: ");

        if (intent.getAction().equals("MY_NOTIFICATION_MESSAGE")) {
            notificationManager.notify(100, builder.build());
            Log.e(TAG, "Alarm"); //Optional, used for debuging.
        }

    }
}
