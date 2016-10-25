package com.example.crazyyalarm;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Question extends Activity implements OnClickListener {
Button buttonAnswerOk,buttonShowQuestion;
EditText editTextAnswer;
TextView textviewQuestion;
float c = 0 ;
float ans ;
int num1;
int num2;
AlertDemoAlarm ringtoneobject = new AlertDemoAlarm();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		
		buttonAnswerOk = (Button) findViewById(R.id.buttonAnswerOk);
		editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);
		textviewQuestion = (TextView) findViewById(R.id.textViewQuestion);
		
		buttonAnswerOk.setOnClickListener(this);
		//buttonShowQuestion.setOnClickListener(this);
			
		String operatorSwitch;
		 
		  Random randomGenerator = new Random();
		  randomGenerator.nextInt(100);
		  //for(int i = 0;i<1;i++)
		  //{
		   num1 = randomGenerator.nextInt(50);
		   num2 = randomGenerator.nextInt(10);
		    Random operatorChoice = new Random();
		         
		    int operator = operatorChoice.nextInt(4);

		          switch (operator){

		              case 0: operatorSwitch= "+";
		                  c = num1+num2;
		                  break;
		              case 1: operatorSwitch= "-";
		                  c = num1-num2;
		                  break;
		              case 2: operatorSwitch= "*";
		                  c = num1*num2;
		                  break;
		              case 3: operatorSwitch= "/";
		                c = num1/num2;
		              break;
		              default: operatorSwitch= "";
		    }
		 
		          String question = "what is:"+ num1 + operatorSwitch + num2;
		          textviewQuestion.setText(question);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		  case R.id.buttonAnswerOk: 
				  String answer = editTextAnswer.getText().toString();
		          ans = Float.parseFloat(answer);
			          if(ans == c)
			          {		
			        	  ringtoneobject.onPause();
			           	  Intent intent = new Intent(getApplicationContext(),SoonzeTime.class);
			           	  
			           	  startActivity(intent);
			           	  onDestroy();
			           	  
			           	  finish();
			           	  
			          }
			          else
			          {
			        	  Toast.makeText(Question.this, "INCORRECT ANSWER!!!!!!\n TRY AGAIN", Toast.LENGTH_SHORT).show();
			               
			          }
			          
			          break;
			  }
		}
		//question(v);

	
		
		
	}


	//public void question(View view)
	//{
		