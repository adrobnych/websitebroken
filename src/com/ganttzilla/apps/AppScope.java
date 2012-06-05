package com.ganttzilla.apps;

import java.util.ArrayList;
import java.util.HashMap;



import android.app.Application;

public class AppScope extends Application {
	
	private int[] allWidgetIds = {};

	public int[] getAllWidgetIds() {
		return allWidgetIds;
	}

	public void setAllWidgetIds(int[] allWidgetIds) {
		this.allWidgetIds = allWidgetIds;
	}
	
	
}
