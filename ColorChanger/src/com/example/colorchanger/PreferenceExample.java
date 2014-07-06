package com.example.colorchanger;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class PreferenceExample extends PreferenceActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		PreferenceManager.setDefaultValues(PreferenceExample.this, R.xml.preferences, false);

		ListPreference lp1 = (ListPreference) findPreference("BC");
	
		  
		lp1.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				
				finish();
				return true;
			}
		});
		
		
		

	}




}