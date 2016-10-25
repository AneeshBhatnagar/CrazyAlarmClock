package com.example.crazyyalarm;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity  {
	ImageButton buttonAlarm ;
	Button buttonViewDetail;
	Button buttonReminder ;
	ListView listAlarmName;
	TextView textViewAlarmName;
	int requestcode;
	DBAdapter helper_ob = new DBAdapter(this);
	//DatabaseAdapter helper = new DatabaseAdapter(this);
	//RegistrationAdapter adapter_ob;
	Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		requestcode = 2;
		
		buttonAlarm = (ImageButton)findViewById(R.id.buttonAlarm);
		buttonReminder = (Button)findViewById(R.id.buttonReminder);
		listAlarmName = (ListView) findViewById(R.id.ListAlarmName);
		//helper_ob = new DBAdapter(this);
		
		
		helper_ob.open();
		populateListViewFromDB();
		registerListClickCallback();
		
			
         buttonAlarm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CreateAlarm.class);
				startActivity(intent);
				//startActivityForResult(intent,requestcode);
			}
		});
		
		buttonReminder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent();
				intent1.setClass(MainActivity.this, ReminderSetting.class);
				startActivity(intent1);
			}
		});
		//textViewAlarmName=(TextView)findViewById(R.id.textViewAlarmName);
		
	}	
		
	
	private void registerListClickCallback() {
		// TODO Auto-generated method stub
		listAlarmName.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
					long id_DB) {
				Cursor cursor = helper_ob.getRow(id_DB);
				if(cursor.moveToFirst())
				{
					long idDB = cursor.getLong(DBAdapter.COL_ROWID);
					String name = cursor.getString(DBAdapter.COL_NAME);
					String time = cursor.getString(DBAdapter.COL_TIME);
				}
				cursor.close();
			}
		});
	}


	private void populateListViewFromDB() {
		// TODO Auto-generated method stub
		Cursor cursor = helper_ob.getAllRows();
		//displayRecordSet(cursor);
		startManagingCursor(cursor);
		String[] fromfieldname = new String[] {DBAdapter.KEY_NAME,DBAdapter.KEY_TIME};
		int[] toViewId = new int[] {R.id.DBalarmName, R.id.DBalarmTime};
		
		//String[] fromfieldtime = new String[] {DBAdapter.KEY_TIME};
		
		SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(
				this,R.layout.alarm_name,cursor,fromfieldname,toViewId);
		listAlarmName.setAdapter(myCursorAdapter);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	/*
public void viewDetails(View view)
{
	Cursor data = helper_ob.getAllRows();
	Message.message(this,data);
}
*/
/*
private void displayRecordSet(Cursor cursor) {
	String message = "";
	// populate the message from the cursor
	
	// Reset cursor to start, checking to see if there's data:
	if (cursor.moveToFirst()) {
		do {
			// Process the data:
			int id = cursor.getInt(DBAdapter.COL_ROWID);
			String name = cursor.getString(DBAdapter.COL_NAME);
			String time = cursor.getString(DBAdapter.COL_TIME);
			//String favColour = cursor.getString(DBAdapter.COL_FAVCOLOUR);
			
			// Append data to the message:
			message += "id=" + id
					   +", name=" + name
					   +", time=" + time
					   +"\n";
					   //+", Colour=" + favColour
					   //+"\n";
		} while(cursor.moveToNext());
	}
	// Close the cursor to avoid a resource leak.
			cursor.close();
			
			displayText(message);
}
/*
private void displayText(String message) {
    TextView textView = (TextView) findViewById(R.id.displayalarmtext);
    textView.setText(message);
}
*/
/*
@Override
protected void onRestart() {
	// TODO Auto-generated method stub
	super.onRestart();
	Cursor cursor = helper_ob.getAllRows();
	displayRecordSet(cursor);
}

*/

@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	helper_ob.close();
}
	
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	Cursor cursor = helper_ob.getAllRows();
	/*displayRecordSet(cursor);*/
	populateListViewFromDB();
}


}
