package com.zaptrapp.nishanth.databasetesting;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                double amount, int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        views.setTextViewText(R.id.appwidget_text,String.valueOf(amount));
        Intent intent = new Intent(context, UpdateNumberService.class);
        intent.setAction(UpdateNumberService.ACTION_UPDATE);
        PendingIntent wateringPendingIntent = PendingIntent.getService(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.appwidget_text, wateringPendingIntent);

        Intent intent1 = new Intent(context, UpdateNumberService.class);
        intent1.setAction(UpdateNumberService.ACTION_DOWNGRADE);
        PendingIntent wateringPendingIntent1 = PendingIntent.getService(
                context,
                0,
                intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.appwidget_text1, wateringPendingIntent1);

        Intent openIntent = new Intent(context,MainActivity.class);

        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        UpdateNumberService.startActionUpdateWidget(context);
    }

    public static void updateWidget(Context context, AppWidgetManager appWidgetManager, double amount, int[] appWidgetIds){
        for(int appWidgetId : appWidgetIds){
            updateAppWidget(context,appWidgetManager,amount,appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

