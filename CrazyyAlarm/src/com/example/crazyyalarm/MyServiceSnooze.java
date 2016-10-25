package com.example.crazyyalarm;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class MyServiceSnooze extends Service {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		Intent alarmintent = new Intent();
		PendingIntent pendingintent = PendingIntent.getActivity(this, 0, alarmintent, 0);
		//alarmintent.putExtra("alarmringname",messagefromalarm);
		 
			
		
		NotificationManager notificationmanageralarm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		
		int icon = R.drawable.ic_launcher;
		String tickertext = "Snooze Ringing";
		long when = System.currentTimeMillis();
		//@SuppressWarnings("deprecation")
		Notification notification = new Notification(icon,tickertext,when);
		String contentTitle="ALARM";
		//String contentText="Reminder Information";
		
		String contentText= "Snooze";
		notification.setLatestEventInfo(this, contentTitle, contentText, pendingintent);
		notificationmanageralarm.notify(123, notification);
		
		
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
