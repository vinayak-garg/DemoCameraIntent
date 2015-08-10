package com.example.vinayak.democameraintent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by vingarg on 8/10/15.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCamera(final View view) {
        int intentRequestCode = 100009;

        Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File captureFile = getCapturedMediaFile();
        Uri captureFileUri = Uri.fromFile(captureFile);

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, captureFileUri);
        cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        startActivityForResult(cameraIntent, intentRequestCode);
    }


    /*
     * Helper Functions
     */

    public static String getMediaPath() {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                "Demo");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        return mediaStorageDir.getPath();
    }

    public static File getCapturedMediaFile() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            String mediaStorageDirPath = getMediaPath();

            // Create a media file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
            return new File(mediaStorageDirPath + File.separator +
                        "VID_"+ timeStamp + ".mp4");

        }
        return null;

    }
}
