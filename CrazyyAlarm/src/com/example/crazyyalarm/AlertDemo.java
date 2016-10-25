package com.example.crazyyalarm;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.WindowManager.LayoutParams;

public class AlertDemo extends DialogFragment {
	String messagetodisplay;
	
	Uri notification1;
	
	MediaPlayer mMediaPlayer2;
	RingtoneManager mRingtoneManager2;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		 setCancelable(false);
	//	Intent newintent = new Intent();
		//String abc = newintent.getStringExtra("remindername");	
        /** Turn Screen On and Unlock the keypad when this alert dialog is displayed */
        getActivity().getWindow().addFlags(LayoutParams.FLAG_TURN_SCREEN_ON | LayoutParams.FLAG_DISMISS_KEYGUARD);
 
        /** Creating a alert dialog builder */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
 
        /** Setting title for the alert dialog */
        builder.setTitle("REMINDER");
 
        /** Setting the content for the alert dialog */
        builder.setMessage(messagetodisplay);
        notification1 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL);
        // r = RingtoneManager.getRingtone(getActivity(), notification);//(, notification);
         mMediaPlayer2 = MediaPlayer.create(getActivity(), notification1);//(, notification);
         mMediaPlayer2.start();
         mMediaPlayer2.setLooping(true);
        
        
        
        /** Defining an OK button event listener */
        builder.setPositiveButton("OK", new OnClickListener() {
            
            public void onClick(DialogInterface dialog, int which) {
                /** Exit application on click OK */
            	mMediaPlayer2.stop();
                getActivity().finish();
            }
        });
 
        /** Creating the alert dialog window */
        return builder.create();
    }
 
    @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
    	if(requestCode==1)
    	{
    		messagetodisplay = data.getStringExtra("abc");	
    	}
    	
		super.onActivityResult(requestCode, resultCode, data);
	}

	/** The application should be exit, if the user presses the back button */
    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }


}
