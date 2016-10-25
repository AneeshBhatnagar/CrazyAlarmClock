package com.example.crazyyalarm;

import java.util.Random;

import android.app.Activity;
import android.widget.Toast;

public class PendingIntentID extends Activity {

	int p_id;
	 public int randomid()
	 {
		 Random randomGenerator = new Random();
		 p_id = randomGenerator.nextInt(100);
		 
		 return(p_id);
	 }
}
