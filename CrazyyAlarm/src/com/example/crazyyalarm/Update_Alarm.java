package com.example.crazyyalarm;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Update_Alarm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update__alarm);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_update__alarm, menu);
		return true;
	}

}
