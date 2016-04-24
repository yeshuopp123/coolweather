package com.laocaixw.coolweather.activity;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;

import com.laocaixw.coolweather.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;

public class AboutActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about_layout);
		
		AdView adView = new AdView(this, AdSize.FIT_SCREEN);//实例化广告条
		LinearLayout adLayout = (LinearLayout) findViewById(R.id.adLayout);//获取要嵌入广告条的布局
		adLayout.addView(adView);//将广告条加入到布局中
	}
}
