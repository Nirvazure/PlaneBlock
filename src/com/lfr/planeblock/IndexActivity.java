package com.lfr.planeblock;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IndexActivity extends Activity{
	Button btnPlay;
	Button btnExit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		
		btnPlay = (Button) findViewById(R.id.btnPlay);
		btnPlay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(IndexActivity.this, GameActivity.class);
				IndexActivity.this.startActivity(intent);
			}
		});
		
		
		btnExit=(Button)findViewById(R.id.btnExit);
		btnExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				IndexActivity.this.finish();
			}
		});
		
	}
	
	
	
}
