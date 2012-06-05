package com.ganttzilla.apps;


import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

public class Configure extends Activity {
	
	private Configure context;
	private int widgetId;
	private RemoteViews views;

	private static final String LOG = "de.vogella.android.widget.example";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configure);
		
		Log.w(LOG, "configure method called0");
		
		setResult(RESULT_CANCELED);
		
		context = this;
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, 
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
		final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
	    views = new RemoteViews(context.getPackageName(), R.layout.main);
		
		final EditText et = (EditText) findViewById(R.id.editText1);
		Button b = (Button) findViewById(R.id.button1);
		

		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 
				
				
				

				Log.w(LOG, "configure method called1");
				// Get all ids
				ComponentName thisWidget = new ComponentName(context,
						WebSitebrokenWidgetProvider.class);
				int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
                 
				// put widgetids to appscop
				AppScope globals = (AppScope)context.getApplicationContext();   
			    globals.setAllWidgetIds(allWidgetIds);
			    

				
/*
				// Build the intent to call the service
				Intent intent = new Intent(context.getApplicationContext(),
						UpdateWidgetService.class);
				intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

				// Update the widgets via the service
				context.startService(intent);*/
				
				
				
				
				
				
				
				/*
				//store data to sharedprefs
				 SharedPreferences prefs = getSharedPreferences("WSBPREFS", 0);
				 SharedPreferences.Editor editor = prefs.edit();
				 editor.putString("widgetId" + widgetId , et.getText().toString());
				 editor.commit();*/
				 
				
				 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(et.getText().toString()));
				 PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
				 views.setOnClickPendingIntent(R.id.imageButton1, pending);
				 views.setTextViewText(R.id.textView1, et.getText().toString());
				 
				
				 appWidgetManager.updateAppWidget(widgetId, views);  
				 
				 Intent resultIntent = new Intent();
				 resultIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId); 
				 setResult(RESULT_OK, resultIntent); 
				 
				
				 
				 
				 finish();
			}
		});
		
		
	}

}
