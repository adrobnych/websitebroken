package com.ganttzilla.apps;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

public class Configure extends Activity {
	
	private Configure context;
	private int widgetId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configure);
		setResult(RESULT_CANCELED);
		
		context = this;
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, 
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
		final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main);
		
		final EditText et = (EditText) findViewById(R.id.editText1);
		Button b = (Button) findViewById(R.id.button1);
		

		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(et.getText().toString()));
				 PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
				 views.setOnClickPendingIntent(R.id.imageButton1, pending);
				 appWidgetManager.updateAppWidget(widgetId, views);
				 
				 Intent resultIntent = new Intent();
				 resultIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
				 setResult(RESULT_OK, resultIntent);
				 
				 finish();
			}
		});
		
		
	}

}
