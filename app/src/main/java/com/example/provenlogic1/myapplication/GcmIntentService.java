package com.example.provenlogic1.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import de.greenrobot.event.EventBus;

public class GcmIntentService extends GcmListenerService {
    public static final int notifyID = 9001;
    public static EventBus bus = EventBus.getDefault();

    @Override
    public void onMessageReceived(String from, Bundle data) {

        Log.e("notification -- " + data.getString("message") + "   from    " + from, "");
        Log.e("Bundle value  -- " + data.toString() + "   from    " + from, "");
        if(ActivityStatus.ChatActivity_isOpen==true){
           ChatManager.receiveNewRowInChatTable("second person", "second mail ", "" + data.getString("price"), "receive");
//            Intent in = new Intent("message_recieved");
//            in.putExtra("message" , ""+data.getString("price"));
//            GcmBroadcastReceiver.completeWakefulIntent(in);


            updateMyActivity(getApplicationContext() , ""+data.getString("price"));


        }else if (ActivityStatus.ChatActivity_isOpen == false){
            sendNotification(""+data.getString("price"));
        }

    }


    private void sendNotification(String message) {
        Intent resultIntent = new Intent(this, ChatActivity.class);
        resultIntent.putExtra("msg", message);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
                resultIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mNotifyBuilder;
        NotificationManager mNotificationManager;

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Notification title")
                .setContentText("" + message)
                .setSmallIcon(R.drawable.logo);
        // Set pending intent
        mNotifyBuilder.setContentIntent(resultPendingIntent);

        // Set Vibrate, Sound and Light
        int defaults = 0;
        defaults = defaults | Notification.DEFAULT_LIGHTS;
        defaults = defaults | Notification.DEFAULT_VIBRATE;
        defaults = defaults | Notification.DEFAULT_SOUND;

        mNotifyBuilder.setDefaults(defaults);
        mNotifyBuilder.setContentText(""+message);
        mNotifyBuilder.setAutoCancel(true);
        mNotificationManager.notify(notifyID, mNotifyBuilder.build());

    }





    static void updateMyActivity(Context context, String message) {

        Intent intent = new Intent("unique_name");

        //put whatever data you want to send, if any
        intent.putExtra("message", message);

        //send broadcast
        context.sendBroadcast(intent);
    }






}
