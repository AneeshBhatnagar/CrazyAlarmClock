package com.example.crazyyalarm;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ReminderSetting extends Activity {
Button buttonSetReminder;
Button buttonremindertone;
DatePicker datePickerReminder;
TimePicker timePickerReminder;
EditText editTextReminder;


Uri ringtone;
Ringtone rt;
RingtoneManager mRingtoneManager;
Cursor mcursor;
Intent Mringtone;
TextView text;
String title;
MediaPlayer mMediaPlayer1 = new MediaPlayer();





	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder_setting);
		buttonSetReminder = (Button) findViewById(R.id.buttonSetReminder);
		buttonremindertone= (Button) findViewById(R.id.setremindertone);
		datePickerReminder = (DatePicker) findViewById(R.id.datePickerReminderr);
		timePickerReminder = (TimePicker) findViewById(R.id.timePickerReminder);
		editTextReminder = (EditText) findViewById(R.id.editTextReminder);
		//NotificationManager notificationmanager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		//notificationmanager.cancelAll();
		
		buttonSetReminder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String messageReminder = editTextReminder.getText().toString();
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH,datePickerReminder.getMonth());
			calendar.set(Calendar.DAY_OF_MONTH,datePickerReminder.getDayOfMonth());
			calendar.set(Calendar.YEAR,datePickerReminder.getYear());
			calendar.set(Calendar.HOUR_OF_DAY,timePickerReminder.getCurrentHour());
			calendar.set(Calendar.MINUTE,timePickerReminder.getCurrentMinute());
			calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);
			
			AlarmManager alarmmanagerReminder = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
			//Intent intent1 = new Intent(ReminderSetting.this,AlertDemo.class);				
			Intent intent = new Intent("com.example.crazyyalarm.MyService");
			//intent.setClass(ReminderSetting.this, MyService.class);
						
			intent.putExtra("remindername",messageReminder);
			
			//PendingIntent pendingintentReminder = PendingIntent.getService(ReminderSetting.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
			PendingIntent pendingintentReminder = PendingIntent.getActivity(getBaseContext(), 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
			 
			alarmmanagerReminder.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingintentReminder);	
			Toast.makeText(ReminderSetting.this, "Reminder Set", Toast.LENGTH_SHORT).show();
			finish();
			//startService(intent);
			}
		});
		
		buttonremindertone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
Mringtone = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
				
				
			Mringtone.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALL);
			
				startActivityForResult(Mringtone, 1);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reminder_setting, menu);
		return true;
	}

	
	public void onActivityResult(int requestCode, int resultCode, Intent Mringtone) {
		switch (requestCode) {
		
		case 1:
		
		Uri uri = Mringtone.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
				
		try
		{
		RingtoneManager.setActualDefaultRingtoneUri(this, resultCode, uri);
		}
		catch (Exception localException)
		{		}
		break;
		}
		}

	
}
