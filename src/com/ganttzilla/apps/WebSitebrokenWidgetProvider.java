package com.ganttzilla.apps;

import java.util.Random;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class WebSitebrokenWidgetProvider extends AppWidgetProvider {
	private static final String LOG = "com.ganttzilla.apps.websitebroken";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		Log.w(LOG, "onUpdate method called!!!!!!!"); 
		
//		onUpdateHelper(context, appWidgetManager, appWidgetIds);
		
		if(!isMyServiceRunning(context)){
			// Get all ids
			ComponentName thisWidget = new ComponentName(context,
					WebSitebrokenWidgetProvider.class);
			int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

			// put widgetids to appscop
			AppScope globals = (AppScope)context.getApplicationContext();   
		    globals.setAllWidgetIds(allWidgetIds);
			
			// Build the intent to call the service
			Intent intent = new Intent(context.getApplicationContext(),
					UpdateWidgetService.class);
//			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
			
			// Update the widgets via the service
			context.startService(intent);
		}
		
		
	}
	
/*	public void onUpdateHelper(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		// Get all ids
		ComponentName thisWidget = new ComponentName(context,
				WebSitebrokenWidgetProvider.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		for (int widgetId : allWidgetIds) {
			// Create some random data
			int number = (new Random().nextInt(100));

			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.main);
			Log.w("WidgetExample", String.valueOf(number));
			// Set the text
			remoteViews.setTextViewText(R.id.textView1, String.valueOf(number));

			
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
	}*/
	
	private boolean isMyServiceRunning(Context context) {
	    ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if ("com.ganttzilla.apps.UpdateWidgetService".equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
	
}