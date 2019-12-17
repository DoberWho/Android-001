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

        String msg = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        msg = msg + msg;
        msg = msg + msg;
        msg = msg + msg;
        msg = msg + msg;

        Notification n  = new Notification.Builder(ctx)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText(msg)
                .setSmallIcon(icon)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setStyle(new Notification.BigTextStyle().bigText(msg))
                //.addAction(R.drawable.icon, "Call", pIntent)
                //.addAction(R.drawable.icon, "More", pIntent)
                //.addAction(R.drawable.icon, "And more", pIntent)
                .build();


        NotificationManager manager = (NotificationManager) ctx.getSystemService(ctx.NOTIFICATION_SERVICE);
        manager.notify(REQUEST_CODE, n);

    }
}
