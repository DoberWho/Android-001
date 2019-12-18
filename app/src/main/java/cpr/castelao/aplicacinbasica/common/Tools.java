package cpr.castelao.aplicacinbasica.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import java.io.IOException;

public class Tools {

    private static Tools instance = new Tools();

    private Tools(){}
    public Tools init(){
        return instance;
    }

    public Bitmap getBitmapFromResID(@NonNull Context ctx, @DrawableRes int id){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                return ImageDecoder.decodeBitmap(ImageDecoder.createSource(ctx.getResources(), id));
            } else {
                String resourceScheme = "res";
                Uri uri = new Uri.Builder()
                        .scheme(resourceScheme)
                        .path(String.valueOf(id))
                        .build();
                return MediaStore.Images.Media.getBitmap(ctx.getContentResolver(), uri );
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
