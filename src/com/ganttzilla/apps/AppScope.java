package com.ganttzilla.apps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import android.app.Application;

public class AppScope extends Application {
	
	private int[] allWidgetIds = {};
	
	private int refresh_period = 5;
	
	private Map<Integer, String> widgetUrls = new HashMap<Integer, String>();

	public int[] getAllWidgetIds() {
		return allWidgetIds;
	}

	public void setAllWidgetIds(int[] allWidgetIds) {
		this.allWidgetIds = allWidgetIds;
	}

	public int getRefresh_period() {
		return refresh_period;
	}

	public void setRefresh_period(int refresh_period) {
		this.refresh_period = refresh_period;
	}

	public Map<Integer, String> getWidgetUrls() {
		return widgetUrls;
	}

	public void setWidgetUrls(Map<Integer, String> widgetUrls) {
		this.widgetUrls = widgetUrls;
	}
	
	
}
