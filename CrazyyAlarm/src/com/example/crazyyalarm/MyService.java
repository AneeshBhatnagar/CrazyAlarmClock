package com.example.crazyyalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;


public class MyService extends FragmentActivity {
	String messagefromreminder;
	String messageforAlert;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	       // Intent intent = new Intent();
	        
	     //  Toast.makeText(this, "oncreate", Toast.LENGTH_SHORT).show();
	        /** Creating an Alert Dialog Window */
	        AlertDemo alert = new AlertDemo();
	        
	        
	      //  Intent intent = new Intent();
	        //String messagefromreminder = intent.getStringExtra("remindername");	
	       // Intent reminderintent1 = new Intent(getBaseContext(),AlertDemo.class);
	       // reminderintent1.putExtra("abc", messagefromreminder);
	        
	     //   Intent newintent = new Intent();
		//	newintent.setClass(this, AlertDemo.class);
		//	newintent.putExtra("remindername",messagefromreminder);    
			//startActivity(newintent);
	        
	        
	        /** Opening the Alert Dialog Window. This will be opened when the alarm goes off */
	        alert.show(getSupportFragmentManager(), "AlertDemo");
	       
	        
	        
	        //String ABC = "hello";
	        
	        //reminderintent1.putExtra("remindername1",ABC);
	    }

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent intent) {
		// TODO Auto-generated method stub
		messagefromreminder = intent.getStringExtra("remindername");
		super.onActivityResult(arg0, arg1, intent);
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
		intent.setClass(this, AlertDemo.class);
		messageforAlert = messagefromreminder;
		requestCode=1;
		intent.putExtra("abc",messageforAlert);
		super.startActivityForResult(intent, requestCode);
	}
	}
	
	
	

	
/*	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	

	@SuppressWarnings("deprecation")
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		//Toast.makeText(MyService.this, "OnStart", Toast.LENGTH_SHORT).show();
		Intent reminderintent = new Intent(this,ReminderSetting.class);
		PendingIntent pendingintent = PendingIntent.getActivity(this, 0, reminderintent, 0);
		String messagefromreminder = intent.getStringExtra("remindername");	
		
		AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
		//dialogbuilder.setTitle("REMINDER!");
		//dialogbuilder.setMessage(messagefromreminder);
		dialogbuilder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			System.exit(0);	
			}
		});
		
		String contentTitle = "REMINDER";
		String contentText = messagefromreminder;

		AlertDialog dialog = dialogbuilder.create();
		dialog.setLatestEventInfo(this, contentTitle, contentText, pendingintent);
		dialog.show();
		
		
		/*NotificationManager notificationmanager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		Intent reminderintent = new Intent(this,ReminderSetting.class);
		
				
		
		PendingIntent pendingintent = PendingIntent.getActivity(this, 0, reminderintent, 0);
		int icon = R.drawable.ic_launcher;
		String tickertext = "You Have A Reminder";
		long when = System.currentTimeMillis();
		//@SuppressWarnings("deprecation")
		Notification notification = new Notification(icon,tickertext,when);
		String contentTitle="Reminder";
		//String contentText="Reminder Information";
		String contentText= messagefromreminder;
		notification.setLatestEventInfo(this, contentTitle, contentText, pendingintent);
		notificationmanager.notify(123, notification);
			
		
		
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
*/