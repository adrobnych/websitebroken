package com.ganttzilla.apps;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;



import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {

	private static final String LOG = "com.ganttzilla.apps.websitebroken";
	
	private Context context;
	private Intent intent;
	private RemoteViews remoteViews;
	
	Timer timer	= new Timer();
	MyTimerTask timerTask = new MyTimerTask();
	
	@Override
	public void onCreate() {
		context = this;
		 
	}
	
	@Override
	public void onStart(Intent _intent, int startId) {
		intent = _intent;
		remoteViews = new RemoteViews(context
				.getApplicationContext().getPackageName(),
				R.layout.main);
		AppScope globals = (AppScope)context.getApplicationContext();   
		int refresh_period = globals.getRefresh_period();
		timer.schedule(timerTask, 60*1000, refresh_period*60*1000);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	private class MyTimerTask extends TimerTask {
		  @Override
		  public void run() {
			  		  
			  Log.i(LOG, "Called");
				// Create some random data

				AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
			
				AppScope globals = (AppScope)context.getApplicationContext();   
				int[] allWidgetIds = globals.getAllWidgetIds();

				ComponentName thisWidget = new ComponentName(getApplicationContext(),
						WebSitebrokenWidgetProvider.class);
				int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
				Log.w(LOG, "From Intent" + String.valueOf(allWidgetIds.length));
				Log.w(LOG, "Direct" + String.valueOf(allWidgetIds2.length));
				
					for (int widgetId : allWidgetIds) {
						// Create some random data
						int number = (new Random().nextInt(100));

						Log.w("WidgetExample number", (new Integer(number)).toString() );
						// Set the text
						remoteViews.setTextViewText(R.id.textView1,
								"Random: " + (new Integer(number)).toString() );
						
						//testUpdateImageButton(number);
						
						appWidgetManager.updateAppWidget(widgetId, remoteViews);

					}
				}

	}
	
	private void testUpdateImageButton(int random_number){
		switch(random_number%3){
		case 0:
			remoteViews.setImageViewResource(R.id.imageButton1, android.R.drawable.presence_away);
			break;
		case 1:
			remoteViews.setImageViewResource(R.id.imageButton1, android.R.drawable.presence_busy);
			break;
		case 2:
			remoteViews.setImageViewResource(R.id.imageButton1, android.R.drawable.presence_online);
		}
	}	

}
