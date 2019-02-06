package com.androidbroadcastreceivereventreminder;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNAndroidBroadcastReceiverEventReminderModule extends ReactContextBaseJavaModule {

    public static ReactApplicationContext reactContext;

    public RNAndroidBroadcastReceiverEventReminderModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNAndroidBroadcastReceiverEventReminder";
    }

    @ReactMethod
    public void getReferrerData(Promise promise) {
        Log.d("ReactNativeJS", "Inside getReferrerData in RNAndroidBroadcastReceiverEventReminderModule");

        String referrerValue = "NOT AVAILABLE";

        if (EventReminderBroadcastReceiver.referrer != null) {
            Log.d("ReactNativeJS", "EventReminderBroadcastReceiver.referrer is not null. It is:" + EventReminderBroadcastReceiver.referrer);
            referrerValue = EventReminderBroadcastReceiver.referrer;
        }

        Log.d("ReactNativeJS", "Returning from getReferrerData in RNAndroidBroadcastReceiverEventReminderModule. referrer is:" + referrerValue);
        promise.resolve(referrerValue);
    }
}