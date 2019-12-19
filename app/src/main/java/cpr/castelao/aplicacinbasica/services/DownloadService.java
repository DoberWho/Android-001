package cpr.castelao.aplicacinbasica.services;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import cpr.castelao.aplicacinbasica.DetailsActivity;
import cpr.castelao.aplicacinbasica.common.NotifController;

public class DownloadService extends Service {


    public static final String URL = "download_service_url_from_my_balls";
    private int result = Activity.RESULT_CANCELED;


    public DownloadService() {}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


       String urlPath = intent.getStringExtra(URL);
       String fileName = "fichero.jpg";

       // getDataDirectory() // <- Directorio interno de la aplicación
        //getExternalStorageDirectory() // <- SDCARD
        //getDownloadCacheDirectory() // <- Directorio de Cache de Aplicación
        // .getRootDirectory() // <- Directorio Raiz de la memoria interna del dispositivo
        File dirPath = Environment.getDataDirectory();
        File dirRoot = new File(dirPath, "misFicheros");
        dirRoot.mkdirs();
        File output = new File(dirRoot, fileName);
        InputStream stream = null;
        FileOutputStream fos = null;
        try {

            java.net.URL url = new URL(urlPath);
            stream = url.openConnection().getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            fos = new FileOutputStream(output.getPath());
            int next = -1;
            while ((next = reader.read()) != -1) {
                fos.write(next);
            }
            // successfully finished
            result = Activity.RESULT_OK;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        publishResults(output.getAbsolutePath(), result);


        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        toast("SERVICE CREADO");
    }


    public void toast(String msg) {

        Context ctx = this;
        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);

        toast.show();
    }


    private void publishResults(String outputPath, int result) {
        /**
         * Intent intent = new Intent(NOTIFICATION);
         *         intent.putExtra(FILEPATH, outputPath);
         *         intent.putExtra(RESULT, result);
         *         sendBroadcast(intent);
         */
        if (result == Activity.RESULT_OK){
            NotifController.init(this).showNotif(DetailsActivity.class, outputPath);
        }else{
            toast("Algo Fue Mal");
            toast("oh Dios Mio.");
            toast("Vamos a morir");
        }

    }
}
