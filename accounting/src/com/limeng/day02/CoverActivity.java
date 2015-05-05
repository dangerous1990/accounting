package com.limeng.day02;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class CoverActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cover);
		Timer timer = new Timer();
		timer.schedule(new TimerTask()
		{
			
			@Override
			public void run()
			{
				Intent intent = new Intent(CoverActivity.this,FragmentTabs.class);
				startActivity(intent);
				CoverActivity.this.finish();
			}
		}, 3000);
		
	}

	
}
