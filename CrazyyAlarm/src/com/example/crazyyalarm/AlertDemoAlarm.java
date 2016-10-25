package com.example.crazyyalarm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.OpenableColumns;
import android.sax.StartElementListener;
import android.support.v4.app.DialogFragment;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;

public class AlertDemoAlarm extends DialogFragment {
	

	Ringtone r;
	String messagetodisplay ="hello alarm";
	
	
	Uri notification;
	
	MediaPlayer mMediaPlayer;
	RingtoneManager mRingtoneManager;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	//	Intent newintent = new Intent();
		//String abc = newintent.getStringExtra("remindername");	
        /** Turn Screen On and Unlock the keypad when this alert dialog is displayed */
		
		getActivity().getWindow().addFlags(LayoutParams.FLAG_TURN_SCREEN_ON | LayoutParams.FLAG_DISMISS_KEYGUARD);
         /** Creating a alert dialog builder */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
         /** Setting title for the alert dialog */
        builder.setTitle("Alarm");
        // uri = alert.Mringtone.getParcelableExtra(alert.mRingtoneManager.EXTRA_RINGTONE_PICKED_URI);
       // alert.rt.play();
        /** Setting the content for the alert dialog */
        builder.setMessage(messagetodisplay);
        
       //  Intent alertintent = new Intent();
        //Uri uri =  alertintent.getData();
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL);
      // r = RingtoneManager.getRingtone(getActivity(), notification);//(, notification);
       mMediaPlayer = MediaPlayer.create(getActivity(), notification);//(, notification);
       mMediaPlayer.start();
       mMediaPlayer.setLooping(true);
       //r.play();
       //alert.play(notification);
        
        
        /*mMediaPlayer.setDataSource(this, notification);
		  final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		 if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
		 mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
		 mMediaPlayer.setLooping(true);
		 mMediaPlayer.prepare();
		 mMediaPlayer.start();*/
        
        /** Defining an OK button event listener */
        builder.setNegativeButton("Disable", new OnClickListener() {
            
            public void onClick(DialogInterface dialog, int which) {
                /** Exit application on click OK */
            	          		
            	//alert.onDestroy();	
                getActivity().finish();
                
              //  rt.stop();
            }
        });
        
        builder.setPositiveButton("Snooze", new OnClickListener() {
            
            public void onClick(DialogInterface dialog, int which) {
                /** Exit application on click OK */
            	mMediaPlayer.stop();
                Intent intent = new Intent();//"com.example.crazyyalarm.Question");
                intent.setClass(getActivity(),Question.class);
                startActivity(intent);
               // intent.setClass(this, Question.class);
                
            }
        });
        setCancelable(false);
      
        /** Creating the alert dialog window */
        return builder.create();
        
        
    }
	/*public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }*/
	
	
	public void stopping()
    {	
		//mMediaPlayer.setLooping(false);
    	mMediaPlayer.stop();
    }

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mMediaPlayer.start();
	}


	@Override
	public void onCancel(DialogInterface dialog) {
		// TODO Auto-generated method stub
		super.onCancel(dialog);
		setCancelable(false);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
}
