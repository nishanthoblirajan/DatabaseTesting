package com.zaptrapp.nishanth.databasetesting;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.zaptrapp.nishanth.databasetesting.Database.DbHelperProvider;
import com.zaptrapp.nishanth.databasetesting.Database.DbHelperProviderClient;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class UpdateNumberService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_UPDATE = "com.zaptrapp.nishanth.databasetesting.action.UPDATE";
    public static final String ACTION_DOWNGRADE = "com.zaptrapp.nishanth.databasetesting.action.DOWNGRADE";
    public static final String ACTION_GET = "com.zaptrapp.nishanth.databasetesting.action.GET";


    public UpdateNumberService() {
        super("UpdateNumberService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE.equals(action)) {
                handleActionUpdate();
            }else if(ACTION_DOWNGRADE.equals(action)){
                handleActionDowngrade();
            }else if(ACTION_GET.equals(action)){
                handleActionGet();
            }
            startActionUpdateWidget(this);
        }
    }

    private void handleActionGet() {
        Cursor cursor = DbHelperProviderClient.getDetails(34,this);
        //TODO 1 Get the correct amount
                double amount = 0;
        try {
            if(cursor!=null) {
                if (cursor.moveToFirst()) {

                    amount = cursor.getDouble(DbHelperProvider.DETAILS_AMOUNT_COLUMN_POSITION);
                    //no need to look up column index since you already know it due to the projection used
                }
            }
        } finally {
            cursor.close();
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, NewAppWidget.class));
        //Now update all widgets
        NewAppWidget.updateWidget(this, appWidgetManager, amount, appWidgetIds);
    }

    /**
     * Handle action Update in the provided background thread with the provided
     * parameters.
     */
    private void handleActionUpdate() {
        Log.d("Update","d");
        DbHelperProviderClient.updateDetails(34,
                "Nishanth Oblirajan",
                Double.parseDouble(String.valueOf(900)),
                "Updated from UpdateNumberService ", this);
    }

    private void handleActionDowngrade() {
        Log.d("Downgrade","d");
        DbHelperProviderClient.updateDetails(34,
                "Nishanth Oblirajan",
                Double.parseDouble(String.valueOf(123)),
                "Downgraded from UpdateNumberService ", this);
    }

    public static void startActionUpdateWidget(Context context) {
        Intent intent = new Intent(context, UpdateNumberService.class);
        intent.setAction(ACTION_GET);
        context.startService(intent);
    }
}
