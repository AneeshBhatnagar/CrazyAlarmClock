package com.example.crazyyalarm;

import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
//import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateAlarm extends Activity implements OnClickListener, OnCheckedChangeListener {

 
MediaPlayer mediaplayer;
Button buttonAlarmTone , buttonSetAlarm;
RadioButton radioButtonOneAlarm;
RadioButton radioButtonRepeatingAlarm;
RadioGroup radiogroup;
CheckBox SelectAll , Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
EditText edittextAlarmName;
TimePicker timePicker;
Calendar calendar1 = Calendar.getInstance();
Calendar calendar = (Calendar) calendar1.clone();
DatePicker datePickerAlarm;
Intent intent;
Boolean all=false,onealarm=false, repeatingalarm = false;
Boolean monday=false,tuesday=false,wednesday=false,thursday=false,friday=false,saturday=false,sunday=false,once=false;

AlarmManager alarmmanageralarm;
LinearLayout linearlayoutdays;

Uri ringtone;
Ringtone rt;
RingtoneManager mRingtoneManager;
Cursor mcursor;
Intent Mringtone;
//TextView text;
String title;
MediaPlayer mMediaPlayer = new MediaPlayer();
DBAdapter helper = new DBAdapter(this);
//RegistrationAdapter adapter;
PendingIntent pendingintentalarm;
PendingIntentID pid = new PendingIntentID();

public final static String EXTRA_MESSAGE = "User's Alarms";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		
		openDB();
		
		title = RingtoneManager.EXTRA_RINGTONE_TITLE;
		mRingtoneManager = new RingtoneManager(this);
		mcursor = mRingtoneManager.getCursor();
		//text = (TextView) findViewById(R.id.textadd);
		timePicker = (TimePicker)findViewById(R.id.timePicker);
		edittextAlarmName = (EditText)findViewById(R.id.edittextAlarmName);
		buttonAlarmTone = (Button)findViewById(R.id.buttonAlarmTone);
		buttonSetAlarm = (Button)findViewById(R.id.buttonSetAlarm);
		radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
		radiogroup.setOnCheckedChangeListener(this);
		
	//	adapter = new RegistrationAdapter(this);
		
		
		radioButtonOneAlarm = (RadioButton) findViewById(R.id.radioButtonOneAlarm);
		radioButtonRepeatingAlarm = (RadioButton) findViewById(R.id.radioButtonRepeatingAlarm);
		linearlayoutdays = (LinearLayout) findViewById(R.id.linearlayoutdays);
		
		SelectAll = (CheckBox)findViewById(R.id.SelectAll);
		Monday = (CheckBox)findViewById(R.id.Monday);
		Tuesday = (CheckBox)findViewById(R.id.Tuesday);
		Wednesday = (CheckBox)findViewById(R.id.Wednesday);
		Thursday = (CheckBox)findViewById(R.id.thursday);
		Friday = (CheckBox)findViewById(R.id.friday);
		Saturday = (CheckBox)findViewById(R.id.saturday);
		Sunday = (CheckBox)findViewById(R.id.sunday);
		
		SelectAll.setOnClickListener(this);
		Monday.setOnClickListener(this);
		Tuesday.setOnClickListener(this);
		Wednesday.setOnClickListener(this);
		Thursday.setOnClickListener(this);
		Friday.setOnClickListener(this);
		Saturday.setOnClickListener(this);
		Sunday.setOnClickListener(this);
		
		buttonSetAlarm.setOnClickListener(this);
		
		buttonAlarmTone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Mringtone = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
			Mringtone.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
			Mringtone.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_RINGTONE);
			
			startActivityForResult(Mringtone, 0);	
			}
		});
					
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm, menu);
		return true;
	}

			
	@Override
	public void onClick(View src) {
		// TODO Auto-generated method stub

		switch(src.getId())
		{
				
		case R.id.SelectAll: 
			if(SelectAll.isChecked())
		{	
			Monday.setChecked(true); 
			Tuesday.setChecked(true);
			Wednesday.setChecked(true);
			Thursday.setChecked(true);
			Friday.setChecked(true);
			Saturday.setChecked(true);
			Sunday.setChecked(true);
			
			if(Monday.isChecked() && Tuesday.isChecked() && Wednesday.isChecked() && Thursday.isChecked() && Friday.isChecked() && Saturday.isChecked() && Sunday.isChecked())
				{all = true;}
			Toast.makeText(CreateAlarm.this, "All Days Selected", Toast.LENGTH_SHORT).show();
		}
		else 
		{	all = false;
			Monday.setChecked(false);
			Tuesday.setChecked(false);
			Wednesday.setChecked(false);
			Thursday.setChecked(false);
			Friday.setChecked(false);
			Saturday.setChecked(false);
			Sunday.setChecked(false);
			Toast.makeText(CreateAlarm.this, "Select days", Toast.LENGTH_SHORT).show();
		}
		break;
		case R.id.Monday:
			if(Monday.isChecked())
			{	monday = true;
				
				Toast.makeText(CreateAlarm.this, "Monday Selected", Toast.LENGTH_SHORT).show();
			}
			else
			{	monday = false;
				
				Toast.makeText(CreateAlarm.this, "Select days", Toast.LENGTH_SHORT).show();
			}
			
		break;
		case R.id.Tuesday:
			if(Tuesday.isChecked())
			{	tuesday = true;
				Toast.makeText(CreateAlarm.this, "Tuesday Selected", Toast.LENGTH_SHORT).show();
			}
			else
			{	tuesday = false;
				Toast.makeText(CreateAlarm.this, "Select days", Toast.LENGTH_SHORT).show();
			}
		break;
		case R.id.Wednesday:
			if(Wednesday.isChecked())
			{	wednesday= true;
				Toast.makeText(CreateAlarm.this, "Wednesday Selected", Toast.LENGTH_SHORT).show();
			}
			else 
			{	wednesday= false;
				Toast.makeText(CreateAlarm.this, "Select days", Toast.LENGTH_SHORT).show();
			}
		break;
		case R.id.thursday:
			if(Thursday.isChecked())
			{	thursday= true;
				Toast.makeText(CreateAlarm.this, "Thursday Selected", Toast.LENGTH_SHORT).show();
			}
			else
			{	thursday= false;
				Toast.makeText(CreateAlarm.this, "Select days", Toast.LENGTH_SHORT).show();
			}
		break;
		case R.id.friday:
			if(Friday.isChecked())
			{	friday= true;
				Toast.makeText(CreateAlarm.this, "Friday Selected", Toast.LENGTH_SHORT).show();
			}
			else
			{	friday= false;
				Toast.makeText(CreateAlarm.this, "Select days", Toast.LENGTH_SHORT).show();
			}
		break;
		case R.id.saturday:
			if(Saturday.isChecked())
			{	saturday= true;
				Toast.makeText(CreateAlarm.this, "Saturday Selected", Toast.LENGTH_SHORT).show();
			}
			else
			{
				saturday= false;
				Toast.makeText(CreateAlarm.this, "Select days", Toast.LENGTH_SHORT).show();
			}
		break;
		case R.id.sunday:
			if(Sunday.isChecked())
			{	sunday= true;
				Toast.makeText(CreateAlarm.this, "Sunday Selected", Toast.LENGTH_SHORT).show();
			}
			else
			{
				sunday= false;
				Toast.makeText(CreateAlarm.this, "Select days", Toast.LENGTH_SHORT).show();
			}
		break;
		
case R.id.buttonSetAlarm:
			String pi;
			String Time;
			String AlarmName = edittextAlarmName.getText().toString();	
			String hour = timePicker.getCurrentHour().toString();
			String min = timePicker.getCurrentMinute().toString();
			if(min.length()<2)
			{
			Time = hour +":"+"0"+ min;
			}
			else
			{
			Time = hour +":"+ min;
			}
			String days = " ";
			
			helper.open();
			//long id = helper.insertRow(AlarmName,Time);
			//long id1 = helper.insertPendingIntent(null, null, null, null, null, null, null, null);
			
			/*if(id<0)
			{
				Message.message(this, "Alarm Not Inserted");
			}
			else
			{
				Message.message(this, "Alarm Inserted");
			}
			*/
			/*ContentValues contentvalues = new ContentValues();
			contentvalues.put(helper.NAME, AlarmName);
			contentvalues.put(helper.TIME, Time);*/
			//	long val = adapter.insertDetails(AlarmName, Time);
			//helper = new DatabaseHandler(getBaseContext());
			//helper.open();
			//long id = helper.insertData(AlarmName, Time);
			//helper.close();
			
			//Toast.makeText(Alarm.this,AlarmName, Toast.LENGTH_SHORT).show();
			if(onealarm == true)
			{
				onealarm();
			}
			else if(repeatingalarm == true)
			{
				if(all == true||(monday==true && tuesday==true && wednesday==true && thursday==true && friday==true && saturday==true && sunday==true))
				{	alldaysalarm();
					monday=false;
					tuesday=false;
					wednesday=false;
					thursday=false;
					friday=false;
					saturday=false;
					sunday=false;
					}
						
				else if(monday==false && tuesday==false && wednesday==false && thursday==false && friday==false && saturday==false && sunday==false && all==false)
				 {Toast.makeText(CreateAlarm.this, "Alarm Cannot Set", Toast.LENGTH_SHORT).show();}
							
			else
			{
			if(monday == true)// && tuesday==false && wednesday==false && thursday==false && friday==false && saturday==false && sunday==false)
			{mondayalarm();
				days = days+" Monday ";}
			
			if(tuesday == true)// && monday==false && wednesday==false && thursday==false && friday==false && saturday==false && sunday==false)
			{tuesdayalarm();
				days = days+"Tuesday ";}
			
			if(wednesday == true)// && monday==false && tuesday==false && thursday==false && friday==false && saturday==false && sunday==false)
			{wednesdayalarm();
			 days = days+"Wednesday ";}
			
			if(thursday == true)// && monday==false && tuesday==false && wednesday==false && friday==false && saturday==false && sunday==false)
			{thursdayalarm();
			days = days+"Thursday ";}
			
			if(friday == true)// && monday==false && tuesday==false && wednesday==false && thursday==false &&saturday==false && sunday==false)
			{fridayalarm();
			days = days+"Friday ";}
			
			if(saturday == true)// && monday==false && tuesday==false && wednesday==false && thursday==false && friday==false&& sunday==false)
			{saturdayalarm();
			days = days+"Saturday ";}
			
			if(sunday == true)// && monday==false && tuesday==false && wednesday==false && thursday==false && friday==false && saturday==false )
			{sundayalarm();
			days = days+"Sunday ";}
			
			}
			}
			if(radioButtonOneAlarm.isChecked()||radioButtonRepeatingAlarm.isChecked())
			{
			long id = helper.insertRow(AlarmName,Time);
			finish();
			}
			else
			{
			Toast.makeText(CreateAlarm.this,"Please Set One Type Of Alarm \n Or Press Back To Go To Previous Menu"  , Toast.LENGTH_LONG).show();
			}
			
			//Toast.makeText(Alarm.this,"Alarm Set For"+days, Toast.LENGTH_SHORT).show();
			//finish();
			break;
		}	
			}
		
	
	
	public void alldaysalarm()
	{	//Calendar calendar1 = Calendar.getInstance();
		//Calendar calendar = (Calendar) calendar1.clone();
		alarmmanageralarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
		calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
		calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (calendar.compareTo(calendar1) <= 0) {
            // Today Set time passed, count to tomorrow
            calendar.add(Calendar.DATE, 1);
        }
        Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
        int abc = pid.randomid();
       
		pendingintentalarm = PendingIntent.getActivity(getBaseContext(), abc, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),86400000 ,pendingintentalarm);
		String pi = pendingintentalarm.toString();
		Toast.makeText(CreateAlarm.this, "Alarm Set For All Days ", Toast.LENGTH_SHORT).show();
		
	}
	
	public void mondayalarm() 
	{	calendar.set(Calendar.DAY_OF_WEEK,2);
	/*repeating();
		pendingintentalarm = PendingIntent.getActivity(getBaseContext(), 2, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000,pendingintentalarm);
		*/
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar = (Calendar) calendar1.clone();
		alarmmanageralarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
		calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
		calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);
		calendar.set(Calendar.DAY_OF_WEEK,2);
		if(calendar1.get(Calendar.DAY_OF_WEEK)>calendar.get(Calendar.DAY_OF_WEEK))
		{
			calendar.add(Calendar.DATE, 7);
		}
		if (calendar.compareTo(calendar1) <= 0) {
            // Today Set time passed, count to tomorrow
            calendar.add(Calendar.DATE, 7);
        }
		int abc = pid.randomid();
		
		Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		pendingintentalarm = PendingIntent.getActivity(getBaseContext(), abc, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000,pendingintentalarm);
		Toast.makeText(CreateAlarm.this,"Alarm Set For Monday ", Toast.LENGTH_SHORT).show();
		//String pi = pendingintentalarm2.toString();
	}
	
	public void tuesdayalarm()
	{	calendar.set(Calendar.DAY_OF_WEEK, 3);
	/*repeating();
		pendingintentalarm = PendingIntent.getActivity(getBaseContext(), 3, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000 ,pendingintentalarm);/*Calendar calendar1 = Calendar.getInstance();
		*/
		Calendar calendar = (Calendar) calendar1.clone();
		alarmmanageralarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
		calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
		calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_WEEK, 3);
		if(calendar1.get(Calendar.DAY_OF_WEEK)>calendar.get(Calendar.DAY_OF_WEEK))
		{
			calendar.add(Calendar.DATE, 7);
		}
		if (calendar.compareTo(calendar1) <= 0) {
            // Today Set time passed, count to tomorrow
            calendar.add(Calendar.DATE, 7);
        }
		int abc = pid.randomid();
		
		Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		pendingintentalarm = PendingIntent.getActivity(getBaseContext(), abc, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000 ,pendingintentalarm);
		Toast.makeText(CreateAlarm.this,"Alarm Set For Tuesday ", Toast.LENGTH_SHORT).show();
		//String pi = pendingintentalarm3.toString();
	}
	
	public void wednesdayalarm()
	{	calendar.set(Calendar.DAY_OF_WEEK, 4);
	/*repeating();
		pendingintentalarm = PendingIntent.getActivity(getBaseContext(), 4, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000 ,pendingintentalarm);
		*/
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar = (Calendar) calendar1.clone();
		alarmmanageralarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
		calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
		calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_WEEK, 4);	
		if(calendar1.get(Calendar.DAY_OF_WEEK)>calendar.get(Calendar.DAY_OF_WEEK))
		{
			calendar.add(Calendar.DATE, 7);
		}
		if (calendar.compareTo(calendar1) <= 0) {
            // Today Set time passed, count to tomorrow
            calendar.add(Calendar.DATE, 7);
        }
		int abc = pid.randomid();
		
		Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		PendingIntent pendingintentalarm = PendingIntent.getActivity(getBaseContext(), abc, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000 ,pendingintentalarm);
		Toast.makeText(CreateAlarm.this, "Alarm Set For Wednesday.", Toast.LENGTH_SHORT).show();
		//String pi = pendingintentalarm4.toString();
	}
	
	public void thursdayalarm()
	{	calendar.set(Calendar.DAY_OF_WEEK, 5);
	/*repeating();
		pendingintentalarm = PendingIntent.getActivity(getBaseContext(), 5, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000 ,pendingintentalarm);
		*/
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar = (Calendar) calendar1.clone();
		alarmmanageralarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
		calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
		calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_WEEK, 5);	
		if(calendar1.get(Calendar.DAY_OF_WEEK)>calendar.get(Calendar.DAY_OF_WEEK))
		{
			calendar.add(Calendar.DATE, 7);
		}
		if (calendar.compareTo(calendar1) <= 0) {
            // Today Set time passed, count to tomorrow
            calendar.add(Calendar.DATE, 7);
        }
		int abc = pid.randomid();
		
		Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		PendingIntent pendingintentalarm = PendingIntent.getActivity(getBaseContext(), abc, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000 ,pendingintentalarm);
		Toast.makeText(CreateAlarm.this, "Alarm Set For Thursday.", Toast.LENGTH_SHORT).show();
		//String pi = pendingintentalarm5.toString();
		}
	
	public void fridayalarm()
	{	calendar.set(Calendar.DAY_OF_WEEK, 6);
	/*repeating();
		pendingintentalarm = PendingIntent.getActivity(getBaseContext(), 6, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000 ,pendingintentalarm);
		*/
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar = (Calendar) calendar1.clone();
		alarmmanageralarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
		calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
		calendar.set(Calendar.DAY_OF_WEEK, 6);
		calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if(calendar1.get(Calendar.DAY_OF_WEEK)>calendar.get(Calendar.DAY_OF_WEEK))
		{
			calendar.add(Calendar.DATE, 7);
		}
        if (calendar.compareTo(calendar1) <= 0) {
            // Today Set time passed, count to tomorrow
            calendar.add(Calendar.DATE, 7);
        }
        int abc = pid.randomid();
        
		Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		PendingIntent pendingintentalarm = PendingIntent.getActivity(getBaseContext(), abc, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000 ,pendingintentalarm);
		Toast.makeText(CreateAlarm.this, "Alarm Set For Friday.", Toast.LENGTH_SHORT).show();
		//String pi = pendingintentalarm6.toString();
		}
	
	public void saturdayalarm()
	{	calendar.set(Calendar.DAY_OF_WEEK, 7);
	/*repeating();
		pendingintentalarm = PendingIntent.getActivity(getBaseContext(), 7, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000 ,pendingintentalarm);
		*/
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar = (Calendar) calendar1.clone();
		alarmmanageralarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
		calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
		calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_WEEK, 7);		
		if(calendar1.get(Calendar.DAY_OF_WEEK)>calendar.get(Calendar.DAY_OF_WEEK))
		{
			calendar.add(Calendar.DATE, 7);
		}
		if (calendar.compareTo(calendar1) <= 0) {
            // Today Set time passed, count to tomorrow
            calendar.add(Calendar.DATE, 7);
        }
		int abc = pid.randomid();
		
		Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		PendingIntent pendingintentalarm = PendingIntent.getActivity(getBaseContext(), abc, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000 ,pendingintentalarm);
		Toast.makeText(CreateAlarm.this, "Alarm Set For Saturday.", Toast.LENGTH_SHORT).show();
		//String pi = pendingintentalarm7.toString();
		}
	
	public void sundayalarm()
		{	calendar.set(Calendar.DAY_OF_WEEK, 1);
			/*repeating();
			pendingintentalarm = PendingIntent.getActivity(getBaseContext(), 1, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
			alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000 ,pendingintentalarm);
			*/
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar = (Calendar) calendar1.clone();
		alarmmanageralarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
		calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
		calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_WEEK, 1);		
		if (calendar.compareTo(calendar1) <= 0) {
            // Today Set time passed, count to tomorrow
            calendar.add(Calendar.DATE, 7);
        }
		if (calendar.compareTo(calendar1) <= 0) {
            // Today Set time passed, count to tomorrow
            calendar.add(Calendar.DATE, 7);
        }
		int abc = pid.randomid();
		Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		PendingIntent pendingintentalarm = PendingIntent.getActivity(getBaseContext(), abc, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000 ,pendingintentalarm);
		Toast.makeText(CreateAlarm.this, "Alarm Set For Sunday.", Toast.LENGTH_SHORT).show();
		//String pi = pendingintentalarm1.toString();
		}
	
	public void onealarm()
	{
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar = (Calendar) calendar1.clone();
		alarmmanageralarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
		calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
		calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
		//calendar.set(Calendar.DAY_OF_WEEK, 1);		
        if (calendar.compareTo(calendar1) <= 0) {
                // Today Set time passed, count to tomorrow
                calendar.add(Calendar.DATE, 1);
            }
       int abc = pid.randomid();
       
        Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		pendingintentalarm = PendingIntent.getActivity(getBaseContext(), abc, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingintentalarm);
		Toast.makeText(CreateAlarm.this, "Alarm Set", Toast.LENGTH_SHORT).show();
		//String pi = pendingintentalarm.toString();
		}
	
	/*
	public void repeating()
	{
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar = (Calendar) calendar1.clone();
		alarmmanageralarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
		calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
		calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
		//calendar.set(Calendar.DAY_OF_WEEK, 7);		
		if(calendar1.get(Calendar.DAY_OF_WEEK)>calendar.get(Calendar.DAY_OF_WEEK))
		{
			calendar.add(Calendar.DATE, 7);
		}
		if (calendar.compareTo(calendar1) <= 0) {
            // Today Set time passed, count to tomorrow
            calendar.add(Calendar.DATE, 7);
        }
		Intent intent = new Intent("com.example.crazyyalarm.MyServiceAlarm");
		/*PendingIntent pendingintentalarm7 = PendingIntent.getActivity(getBaseContext(), 7, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmmanageralarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),604800000 ,pendingintentalarm7);
		//Toast.makeText(Alarm.this, "Alarm Set For Saturday.", Toast.LENGTH_SHORT).show();
		//String pi = pendingintentalarm7.toString();
		}
	*/
		/*
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode == RESULT_OK) {
	        switch (requestCode) {
	        case 1:
	            ringtone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
	           play(ringtone);
	            
	            // Toast.makeText(getBaseContext(),RingtoneManager.URI_COLUMN_INDEX,
	            // Toast.LENGTH_SHORT).show();
	            break;

	        default:
	            break;
	        }
	    }
	}
	*/
	/*
	public void playing()
	{
		 Ringtone r = RingtoneManager.getRingtone(this, ringtone);
         r.play();
     	}
	*/
	
	public void onActivityResult(int requestCode, int resultCode, Intent Mringtone) {
		switch (requestCode) {
		case 0:
		//sents the ringtone that is picked in the Ringtone Picker Dialog
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

	//this method captures the ringtone from the selection and plays it in the main activity
	public void play(Uri uri) {
		
	if (uri != null) {
		
		try {
	//in order to play the ringtone, you need to create a new Ringtone with RingtoneManager and pass it to a variable
	rt = mRingtoneManager.getRingtone(getApplicationContext(), uri);
	//Ringtone r = RingtoneManager.getRingtone(getActivity(), notification);
	rt.play();

		  // Uri alert =  RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
		
		  mMediaPlayer.setDataSource(this, uri);
		  final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		 if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
		 mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
		 mMediaPlayer.setLooping(true);
		 mMediaPlayer.prepare();
		 mMediaPlayer.start();
		}
		} catch(Exception e) {
		}   
	}
	} 
	
	public void stop()
	{
		rt.stop();
	}
			
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			
			switch(checkedId)
			{
						case R.id.radioButtonOneAlarm:
				
				if(radioButtonOneAlarm.isChecked())
				{	onealarm = true;
					//radioButtonRepeatingAlarm.setSelected(false);//setActivated(false);
					linearlayoutdays.setVisibility(View.INVISIBLE);
					/*Sunday.setVisibility(1);Monday.setVisibility(1);Tuesday.setVisibility(1);Wednesday.setVisibility(1);Thursday.setVisibility(1);Friday.setVisibility(1);Saturday.setVisibility(1);SelectAll.setVisibility(1);*/
					repeatingalarm=false;
				}
				break;
				
			case R.id.radioButtonRepeatingAlarm:
				if(radioButtonRepeatingAlarm.isChecked())
				{	repeatingalarm = true;
				
					//radioButtonOneAlarm.setSelected(false);
					linearlayoutdays.setVisibility(View.VISIBLE);
					onealarm = false;
				}	
				
				break;
		}
		
		/*Toast.makeText(Alarm.this, "Alarm Set", Toast.LENGTH_SHORT).show();
		
		ArrayList<String> alarmname = new ArrayList<String>();
		alarmname.add(edittextAlarmName.getText().toString());
				
		Intent intent = new Intent(Alarm.this, MainActivity.class);
		intent.putExtra("AlarmName",alarmname);
		setResult(100,intent);
		finish();
		intent.setClass(Alarm.this, MyService.class);
		startService(intent);*/
		
			
	
		}
				
		@Override
		protected void onDestroy() {
				super.onDestroy();	
				closeDB();
			}

		private void openDB() {
				
				helper.open();
			}
		private void closeDB() {
				helper.close();
			}

		}
	


