package com.androidbroadcastreceivereventreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * Created by freedomson on 08-01-2018.
 */

public class EventReminderBroadcastReceiver extends BroadcastReceiver {

    //public static final String EVENT_ACTION = "android.intent.action.EVENT_REMINDER";
    public static final String EVENT_ACTION = "android.intent.action.PACKAGE_FULLY_REMOVED";


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("ReactNativeJS111", "EventReminderBroadcastReceiver event fired: " + intent.getAction());

        if (!EVENT_ACTION.equals(intent.getAction())) {
            return;
        }
        Log.v("ReactNativeJS111", "EventReminderBroadcastReceiver event fired: " + intent.getData());
        sendEvent("GEventReminderBroadcastReceiver", intent.getData().toString());

    }

    private void sendEvent(String eventName, String eventId) {
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
