package com.ganttzilla.apps;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {

	private static final String LOG = "de.vogella.android.widget.example";

	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(LOG, "Called");
		// Create some random data

		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
				//.getApplicationContext());

		int[] allWidgetIds = intent
				.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

		ComponentName thisWidget = new ComponentName(getApplicationContext(),
				WebSitebrokenWidgetProvider.class);
		int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
		Log.w(LOG, "From Intent" + String.valueOf(allWidgetIds.length));
		Log.w(LOG, "Direct" + String.valueOf(allWidgetIds2.length));
		
		for(int i=0; i<5; i++){
			try {
				long numMillisecondsToSleep = 3000; // 3 seconds
				Thread.sleep(numMillisecondsToSleep);
			} catch (InterruptedException e) {
			}
			for (int widgetId : allWidgetIds) {
				// Create some random data
				int number = (new Random().nextInt(100));


				RemoteViews remoteViews = new RemoteViews(this
						.getApplicationContext().getPackageName(),
						R.layout.main);


				Log.w("WidgetExample number", (new Integer(number)).toString() );
				// Set the text
				remoteViews.setTextViewText(R.id.textView1,
						"Random: " + (new Integer(number)).toString() );
				appWidgetManager.updateAppWidget(widgetId, remoteViews);

				// Register an onClickListener
				Intent clickIntent = new Intent(this.getApplicationContext(),
						WebSitebrokenWidgetProvider.class);

				clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
				clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
						allWidgetIds);

				PendingIntent pendingIntent = PendingIntent.getBroadcast(
						getApplicationContext(), 0, clickIntent, 
						PendingIntent.FLAG_UPDATE_CURRENT);
				remoteViews.setOnClickPendingIntent(R.id.imageButton1, pendingIntent);

				appWidgetManager.updateAppWidget(widgetId, remoteViews);
			}
		}
		stopSelf();

		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
