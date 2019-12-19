package cpr.castelao.aplicacinbasica.common;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;

public class DownloadTask extends AsyncTask<URL, Long, Integer> {

    private final Context ctx;
    private int result = Activity.RESULT_FIRST_USER;

    public DownloadTask(Context ctx){
        this.ctx = ctx;
    }
    
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        result = Activity.RESULT_CANCELED;
    }

    @Override
    protected Integer doInBackground(URL... urls) {
        int len = urls.length;
        long totalSize = 0;

        String fileName = "fichero-";
        String fileTail = ".jpg";

        // getDataDirectory() // <- Directorio interno de la aplicación
        // getExternalStorageDirectory() // <- SDCARD
        // getDownloadCacheDirectory() // <- Directorio de Cache de Aplicación
        // getRootDirectory() // <- Directorio Raiz de la memoria interna del dispositivo
        File dirPath = Environment.getExternalStorageDirectory();
        File dirRoot = new File(dirPath, "misFicheros");
        dirRoot.mkdirs();

        InputStream stream = null;
        FileOutputStream fos = null;

        try {

            for (URL url : urls) {

                String nombre = fileName + totalSize + fileTail;
                File output = new File(dirRoot, nombre);
                Log.d("ASYNTACK","FICHERO:"+output.getAbsolutePath());
                output.createNewFile();

                stream = url.openConnection().getInputStream();
                InputStreamReader reader = new InputStreamReader(stream);
                fos = new FileOutputStream(output.getPath());
                int next = -1;
                while ((next = reader.read()) != -1) {
                    fos.write(next);
                }
                totalSize++;
                publishProgress(totalSize);
            }
            // successfully finished
            result = Activity.RESULT_OK;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ASYNTASK","OSTIAS, Fue Mal => "+e.getLocalizedMessage());
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


        return len;
    }

    @Override
    protected void onProgressUpdate(Long... progress)  {
        super.onProgressUpdate(progress);
        toast(ctx, "Descargados: "+progress);
        Log.i("ASYNTASK","DESCARGADOS: "+progress);
    }


    protected void onPostExecute(Long result) {
        toast(ctx, "Descargas TOTALES: "+result);
        Log.i("ASYNTASK","DESCARGAS TOTALES: "+result);
    }


    public void toast(Context ctx, String msg) {

        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}
