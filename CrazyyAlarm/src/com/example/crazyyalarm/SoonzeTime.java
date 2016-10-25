package com.example.crazyyalarm;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;


public class SoonzeTime extends Activity implements OnClickListener, OnCheckedChangeListener {
Button buttonSnoozeOk;
RadioGroup radioGroupSnooze;
RadioButton radio2min;
RadioButton radio3min;
RadioButton radio5min;
RadioButton radio7min;
RadioButton radio10min;
Boolean twomin=false,threemin=false,fivemin=false,sevenmin=false,tenmin=false;
AlarmManager alarmmanagersnooze;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soonze_time);
		buttonSnoozeOk = (Button)findViewById(R.id.buttonSnoozeOk);
		radioGroupSnooze = (RadioGroup) findViewById(R.id.radioGroupSnooze);
		radio2min = (RadioButton) findViewById(R.id.radio2min);
		radio3min = (RadioButton) findViewById(R.id.radio3min);
		radio5min = (RadioButton) findViewById(R.id.radio5min);
		radio7min = (RadioButton) findViewById(R.id.radio7min);
		radio10min = (RadioButton) findViewById(R.id.radio10min);
		
		buttonSnoozeOk.setOnClickListener(this);
		
		radioGroupSnooze.setOnCheckedChangeListener(this);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.soonze_time, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(twomin==true)
		{
			snoozetwo();
		}
		
		else if(threemin==true)
		{
			snoozethree();
		}
		else if(fivemin==true)
		{
			snoozefive();
		}
		else if(sevenmin==true)
		{
			snoozeseven();
		}
		else if(twomin==true)
		{
			snoozeten();
		}
		
		else
		Toast.makeText(SoonzeTime.this, "Select Snooze Time",Toast.LENGTH_SHORT ).show();
		finish();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch(checkedId)
		{
		case R.id.radio2min:
			twomin=true;
			fivemin=false;
			threemin=false;
			sevenmin=false;
			tenmin=false;
			break;

		case R.id.radio3min:
			twomin=false;
			threemin=true;
			fivemin=false;
			sevenmin=false;
			tenmin=false;
			break;

		case R.id.radio5min:
			twomin=false;
			fivemin=true;
			threemin=false;
			sevenmin=false;
			tenmin=false;
			break;

		case R.id.radio7min:
			twomin=false;
			fivemin=false;
			threemin=false;
			sevenmin=true;
			tenmin=false;
			break;

		case R.id.radio10min:
			twomin=false;
			fivemin=false;
			threemin=false;
			sevenmin=false;
			tenmin=true;
			break;
				
		}
	}

	public void snoozetwo()
	{
		Calendar calendar = Calendar.getInstance();
		long time = calendar.getTimeInMillis();//(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
		//calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
		//calendar.set(Calendar.DAY_OF_WEEK, 1);
		alarmmanagersnooze = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		PendingIntent pendingintentsnooze2 = PendingIntent.getActivity(getBaseContext(), 102, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanagersnooze.set(AlarmManager.RTC_WAKEUP,time + 120000,pendingintentsnooze2);
		Toast.makeText(SoonzeTime.this, "Snooze Set", Toast.LENGTH_SHORT).show();
	}
	
	public void snoozethree()
	{	Calendar calendar = Calendar.getInstance();
		long time = calendar.getTimeInMillis();
		alarmmanagersnooze = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		PendingIntent pendingintentsnooze3 = PendingIntent.getActivity(getBaseContext(), 103, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanagersnooze.set(AlarmManager.RTC_WAKEUP,time+180000,pendingintentsnooze3);
		Toast.makeText(SoonzeTime.this, "Snooze Set", Toast.LENGTH_SHORT).show();
	}
	
	public void snoozefive()
	{	Calendar calendar = Calendar.getInstance();
		long time = calendar.getTimeInMillis();
		alarmmanagersnooze = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		PendingIntent pendingintentsnooze5 = PendingIntent.getActivity(getBaseContext(), 105, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanagersnooze.set(AlarmManager.RTC_WAKEUP,time+300000,pendingintentsnooze5);
		Toast.makeText(SoonzeTime.this, "Snooze Set", Toast.LENGTH_SHORT).show();
	}
	
	public void snoozeseven()
	{	Calendar calendar = Calendar.getInstance();
		long time = calendar.getTimeInMillis();
		alarmmanagersnooze = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		PendingIntent pendingintentsnooze7 = PendingIntent.getActivity(getBaseContext(), 107, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanagersnooze.set(AlarmManager.RTC_WAKEUP,time+420000,pendingintentsnooze7);
		Toast.makeText(SoonzeTime.this, "Snooze Set", Toast.LENGTH_SHORT).show();
	}
	
	public void snoozeten()
	{	Calendar calendar = Calendar.getInstance();
		long time = calendar.getTimeInMillis();
		alarmmanagersnooze = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		PendingIntent pendingintentsnooze10 = PendingIntent.getActivity(getBaseContext(), 110, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanagersnooze.set(AlarmManager.RTC_WAKEUP,time+600000,pendingintentsnooze10);
		Toast.makeText(SoonzeTime.this, "Snooze Set", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		
	}

	
}
/*
		AlarmManager alarmmanageralarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		
		Intent intent = new Intent();
		intent.setClass(this, MyServiceAlarm.class);
		intent.putExtra("alarmname",AlarmName);
		PendingIntent pendingintentalarm = PendingIntent.getService(Alarm.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
		alarmmanageralarm.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingintentalarm);
		Toast.makeText(Alarm.this, "Alarm Set", Toast.LENGTH_SHORT).show();
		
		*/

