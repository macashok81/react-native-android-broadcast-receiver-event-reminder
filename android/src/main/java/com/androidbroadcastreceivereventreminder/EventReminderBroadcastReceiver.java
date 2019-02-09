package com.androidbroadcastreceivereventreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import android.net.Uri;
import android.provider.CalendarContract;
import android.database.Cursor;

/**
 * Created by freedomson on 08-01-2018.
 */

public class EventReminderBroadcastReceiver extends BroadcastReceiver {

    public static final String EVENT_ACTION = "android.intent.action.EVENT_REMINDER";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("ReactNativeJS", "EventReminderBroadcastReceiver event fired: " + intent.getAction());

        if (!EVENT_ACTION.equals(intent.getAction())) {
            return;
        }

        Uri uri = intent.getData();

        String alertTime = uri.getLastPathSegment();

        String selection = CalendarContract.CalendarAlerts.ALARM_TIME + "=?";

        Cursor cursor = context.getContentResolver().query(
            CalendarContract.CalendarAlerts.CONTENT_URI_BY_INSTANCE
            , new String[]{CalendarContract.CalendarAlerts.EVENT_ID}
            , selection,new String[]{alertTime}
            , null);
        
        if ( cursor.moveToNext() ) {
            Integer eventId = cursor.getInt(0);
            Log.d("ReactNativeJS", "EventReminderBroadcastReceiver eventid: " + eventId);
            sendEvent("GEventReminderBroadcastReceiver", eventId);
        } else {
            Log.d("ReactNativeJS", "EventReminderBroadcastReceiver no id found");
        }

    }

    private void sendEvent(String eventName, Integer eventId) {
        try{
            ReactContext reactContext = RNAndroidBroadcastReceiverEventReminderModule.reactContext;

            reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, eventId);
        }
        catch(Exception e){
            Log.d("ReactNativeJS","Exception in sendEvent in EventReminderBroadcastReceiver is:"+e.toString());
        }

    }
}
