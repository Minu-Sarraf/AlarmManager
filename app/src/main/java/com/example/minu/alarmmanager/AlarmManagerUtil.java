package com.example.minu.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import static android.content.Context.ALARM_SERVICE;

import java.util.Calendar;
public class AlarmManagerUtil {
    private final int requestCode = 123;
    private final Context context;
    //declare at other places


    private long date;

    public AlarmManagerUtil(Context context) {
        this.context = context;
    }

    private void setTime() {
        startAlarm();
    }

    private void startAlarm() {

        String currentFormat = "yyyy-MM-dd'T'HH:mm:ssZ";
        String desiredFormat = "yyyy-MMM-dd-HH:mm";
        String formattedDate = DateUtil.getFormatedDate(date, desiredFormat, currentFormat);
        String[] parts = formattedDate.split("-");
        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)(context.getSystemService(ALARM_SERVICE));
        Calendar cal = Calendar.getInstance();
        cal.set(parser(parts[0]), parser(parts[1]), parser(parts[2]), parser(parts[3]), parser(parts[4]));

        if (alarmManager != null) {
            alarmManager.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);
        }
    }

    public int parser(String parts) {
        int part = Integer.parseInt(parts);
        return part;
    }

    private void cancelAlarm() {
        /* Request the AlarmManager object */
        android.app.AlarmManager manager = (android.app.AlarmManager) context.getSystemService(ALARM_SERVICE);

        /* Create the PendingIntent that would have launched the BroadcastReceiver */
        PendingIntent pending = PendingIntent.getBroadcast(context, requestCode, new Intent(context, MainActivity.class), 0);

        /* Cancel the alarm associated with that PendingIntent */
        manager.cancel(pending);
    }

}
