package cpr.castelao.aplicacinbasica;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class NotifController {

    private int REQUEST_CODE = 1234;
    private int icon = R.mipmap.ic_launcher;
    private String nameApp;
    
    private static NotifController instance = new NotifController();
    private Context ctx;
    private long time;

    private NotifController(){}

    public static NotifController init(Context ctx){
        instance.ctx = ctx;
        instance.time = System.currentTimeMillis();
        instance.initData();
        return instance;
    }

    private void initData() {
        if (ctx == null){
            return;
        }
        this.nameApp = ctx.getString(R.string.app_name);
    }

    public void showNotif(Class activity, String item){

        Intent intent = new Intent(ctx, activity);
        intent.putExtra(DetailsActivity.ITEM_CLICKADO, item);
        PendingIntent pIntent = PendingIntent.getActivity(ctx, REQUEST_CODE, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification n  = new Notification.Builder(ctx)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject")
                .setSmallIcon(icon)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                //.addAction(R.drawable.icon, "Call", pIntent)
                //.addAction(R.drawable.icon, "More", pIntent)
                //.addAction(R.drawable.icon, "And more", pIntent)
                .build();


        NotificationManager manager = (NotificationManager) ctx.getSystemService(ctx.NOTIFICATION_SERVICE);
        manager.notify(REQUEST_CODE, n);

    }
}
