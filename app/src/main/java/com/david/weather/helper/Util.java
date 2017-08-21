package com.david.weather.helper;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by TechnoA on 03.08.2017.
 */

public class Util {
    public static void logInfo(String info){
        Log.d("MyLog", info);
//        appendLogToFile(info);
    }

    private static void appendLogToFile(String text){

        if(!isExternalStorageWritable()){
            return;
        }

        File logFile = new File("sdcard/WeatherMVP.file");
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
