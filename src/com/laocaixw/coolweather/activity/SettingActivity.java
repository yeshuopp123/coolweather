package com.laocaixw.coolweather.activity;

import com.laocaixw.coolweather.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingActivity extends Activity {
	private CheckBox isAutoUpdate;
	private RelativeLayout updateTimeLayout;
	private TextView time;
	private SharedPreferences prefs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting_layout);
		
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		isAutoUpdate = (CheckBox) findViewById(R.id.isAutoUpdate);
		updateTimeLayout = (RelativeLayout) findViewById(R.id.updateTimeLayout);
		time = (TextView) findViewById(R.id.timeText);
		
		Boolean b = prefs.getBoolean("isAutoUpdate", false);
		int timeset = prefs.getInt("autoUpdateTime", 24);
		
		isAutoUpdate.setChecked(b);
		isAutoUpdate.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SharedPreferences.Editor editor = prefs.edit();
	        	editor.putBoolean("isAutoUpdate", isChecked);
	        	editor.commit();
				if (isChecked) {
					updateTimeLayout.setVisibility(View.VISIBLE);
				} else {
					updateTimeLayout.setVisibility(View.GONE);
				}
			}
		});
		
		updateTimeLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				numberPickerShow();
			}
		});
		time.setText("" + timeset + "小时");
		if (b) updateTimeLayout.setVisibility(View.VISIBLE);
	}
	
	private void numberPickerShow(){
		final Dialog d = new Dialog(SettingActivity.this);
        d.setTitle("自动更新频率");
        d.setContentView(R.layout.number_dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker);
        np.setMaxValue(24);
        np.setMinValue(1);
        np.setWrapSelectorWheel(false);
        int timeset = prefs.getInt("autoUpdateTime", 0);
        np.setValue(timeset);
        b1.setOnClickListener(new OnClickListener()
        {
         @Override
         public void onClick(View v) {
        	 int timeInt = np.getValue();
        	 time.setText(String.valueOf(timeInt));
        	 SharedPreferences.Editor editor = prefs.edit();
        	 editor.putInt("autoUpdateTime", timeInt);
        	 editor.commit();
             d.dismiss();
          }    
         });
        b2.setOnClickListener(new OnClickListener()
        {
         @Override
         public void onClick(View v) {
             d.dismiss();
          }    
         });
        d.show();
	}
}
