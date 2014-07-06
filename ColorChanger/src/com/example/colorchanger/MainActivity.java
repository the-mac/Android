package com.example.colorchanger;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;



public class MainActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main_menu);
		Button color_button = (Button) findViewById(R.id.color_button);
		
		color_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * Bluetooth starting connection here
				 */

				startActivity(new Intent(getBaseContext(), PreferenceExample.class));

			}

		});

	}


	@Override
	public void onStart() {
		super.onStart();

		getBG();

	}

	
	void getBG() {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		String colorText = sharedPrefs.getString("BC", "#808080");

		((ViewGroup) findViewById(android.R.id.content)).getChildAt(0)
				.setBackgroundColor(Color.parseColor(colorText));

	}


}