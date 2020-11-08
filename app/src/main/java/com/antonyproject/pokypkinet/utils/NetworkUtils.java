package com.antonyproject.pokypkinet.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String URL_ADRESS="https://api.vk.com";
    private static final String USER_ADRESS ="/method/user.get";
    public  static URL generateURL(String typeText,int type){
        Uri uri=null;
        switch (type){
            case 0:
                uri = Uri.parse("https://pokypki.net/forum/android/api.php")
                        .buildUpon()
                        //.appendQueryParameter("user_ids",userId)
                        //.appendQueryParameter("v","5.124")
                        .build();
                break;
            case 1:
                uri = Uri.parse("https://pokypki.net/forum/android/api.php")
                        .buildUpon()
                        .appendQueryParameter("type",typeText)
                        //.appendQueryParameter("v","5.124")
                        .build();
                Log.d("ddd3",uri.toString());
                break;
        }

        URL url = null;
        try {
            url= new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return  url;
    }

    public static String getResponseFromUrl(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            InputStream in = connection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");// Без него сканнер будет делить по пробелу(A-в регулярных выражениях новая строка)
            if(scanner.hasNext()){
                return scanner.next();
            }else {
                return null;
            }
        }finally {
            connection.disconnect();
        }
    }
}
