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

	private static final String LOG = "de.vogella.android.widget.example";
	
	Context context;
	Intent intent;
	
	Timer timer	= new Timer();
	MyTimerTask timerTask = new MyTimerTask();
	
	@Override
	public void onCreate() {
		context = this;
		 
	}
	
	@Override
	public void onStart(Intent _intent, int startId) {
		intent = _intent;
		timer.schedule(timerTask, 1000, 2000);
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
						//.getApplicationContext());

				int[] allWidgetIds = intent
						.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

				ComponentName thisWidget = new ComponentName(getApplicationContext(),
						WebSitebrokenWidgetProvider.class);
				int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
				Log.w(LOG, "From Intent" + String.valueOf(allWidgetIds.length));
				Log.w(LOG, "Direct" + String.valueOf(allWidgetIds2.length));
				
					for (int widgetId : allWidgetIds) {
						// Create some random data
						int number = (new Random().nextInt(100));


						RemoteViews remoteViews = new RemoteViews(context
								.getApplicationContext().getPackageName(),
								R.layout.main);


						Log.w("WidgetExample number", (new Integer(number)).toString() );
						// Set the text
						remoteViews.setTextViewText(R.id.textView1,
								"Random: " + (new Integer(number)).toString() );
						appWidgetManager.updateAppWidget(widgetId, remoteViews);

						// Register an onClickListener
//						Intent clickIntent = new Intent(context.getApplicationContext(),
//								WebSitebrokenWidgetProvider.class);
//
//						clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
//						clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
//								allWidgetIds);
//
//						PendingIntent pendingIntent = PendingIntent.getBroadcast(
//								getApplicationContext(), 0, clickIntent, 
//								PendingIntent.FLAG_UPDATE_CURRENT);
//						remoteViews.setOnClickPendingIntent(R.id.imageButton1, pendingIntent);
//
//						appWidgetManager.updateAppWidget(widgetId, remoteViews);
					}
				}
				//stopSelf();

				//super.onStart(intent, startId);

		  }


}
