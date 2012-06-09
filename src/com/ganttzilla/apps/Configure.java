package com.ganttzilla.apps;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

public class Configure extends Activity implements TextWatcher {
	
	private Configure context;
	private int widgetId;
	private RemoteViews views;
	private EditText refresh_time_et;
	private Button b;

	private static final String LOG = "com.ganttzilla.apps.websitebroken";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
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
		final EditText et2 = (EditText) findViewById(R.id.editText2);
		refresh_time_et = (EditText) findViewById(R.id.editText3);
		refresh_time_et.addTextChangedListener(this);
		
		b = (Button) findViewById(R.id.button1);
		

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
			    globals.setRefresh_period((new Integer(refresh_time_et.getText().toString())).intValue());
			    globals.getWidgetUrls().put(widgetId, et2.getText().toString());
			    
			    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(et2.getText().toString()));
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

	@Override
	public void afterTextChanged(Editable arg0) {
		b.setEnabled(true);
		
		String text = refresh_time_et.getText().toString();
		Integer refresh_time;
		try {
		    refresh_time = new Integer(text);
		    if (refresh_time < 5)
		    	update_refresh_time_et();
		  } catch (NumberFormatException e) {
			  update_refresh_time_et();
		  }
	}

	private void update_refresh_time_et(){
		b.setEnabled(false);
		
		Context context = getApplicationContext();
		CharSequence text = "refresh period has to be an integer >= 5";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}
	
}
