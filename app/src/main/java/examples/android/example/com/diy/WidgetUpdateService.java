package examples.android.example.com.diy;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.ArrayList;

public class WidgetUpdateService extends IntentService  {

    public static final String WIDGET_UPDATE_ACTION = "com.example.actc.myapplication.update_widget";
    private String recentlyPlayed;

    public WidgetUpdateService() {
        super("WidgetServiceUpdate");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null && intent.getAction().equals(WIDGET_UPDATE_ACTION)) {
            Bundle bundle = intent.getBundleExtra(videosActivity.BUNDLE);

            recentlyPlayed=bundle.getString("videoName");

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,NewAppWidget.class));
            NewAppWidget.updateAppWidget(this, appWidgetManager, appWidgetIds, Constants.widget+"\n"+recentlyPlayed);
        }
    }
}
