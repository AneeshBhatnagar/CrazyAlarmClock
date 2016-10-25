package com.example.crazyyalarm;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class MyServiceAlarm extends FragmentActivity {
Intent intent;
Ringtone rt;
MediaPlayer mMediaPlayer;
RingtoneManager mRingtoneManager;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	  //   Uri uri =   intent.getData();
	    //    play(uri);
	       
	        /** Creating an Alert Dialog Window */
	               
	     //   AlertDemoAlarm alert = new AlertDemoAlarm();
	        AlertDemoAlarm alert = new AlertDemoAlarm();
	        
	        /** Opening the Alert Dialog Window. This will be opened when the alarm goes off */
	        alert.show(getSupportFragmentManager(), "AlertDemoAlarm");
	        //alert.onCreate(savedInstanceState);
	        //Toast.makeText(this, "oncreate", Toast.LENGTH_SHORT).show();
	       }
	 
	 
	/*
	
     void play(Uri uri) {
     	// TODO Auto-generated method stub
     	if (uri != null) {

     		
     		try {
     	//in order to play the ringtone, you need to create a new Ringtone with RingtoneManager and pass it to a variable
     	rt = mRingtoneManager.getRingtone(this, uri);
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
     	}*/
	 
	 
	 
	 
	 
	 
	 
	 
	
}
	
	
	
	
	/*
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Oncreate", Toast.LENGTH_SHORT).show();
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		Toast.makeText(this, "Onstart", Toast.LENGTH_SHORT).show();
		String messagefromalarm = intent.getStringExtra("alarmname");	
		Intent alarmintent = new Intent(this,AlarmRing.class);
		PendingIntent pendingintent = PendingIntent.getActivity(this, 0, alarmintent, 0);
		//alarmintent.putExtra("alarmringname",messagefromalarm);
		
		NotificationManager notificationmanageralarm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		
		int icon = R.drawable.ic_launcher;
		String tickertext = messagefromalarm;
		long when = System.currentTimeMillis();
		//@SuppressWarnings("deprecation")
		Notification notification = new Notification(icon,tickertext,when);
		String contentTitle="ALARM";
		//String contentText="Reminder Information";
		
		String contentText= "Alarm: "+ messagefromalarm;
		notification.setLatestEventInfo(this, contentTitle, contentText, pendingintent);
		notificationmanageralarm.notify(123, notification);
			
		//startActivity(alarmintent,null );
		//String AlarmRingName = messagefromreminder.getText().toString();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
*/