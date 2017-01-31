package com.example.rahman.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    public void downloadImage(View view){

        //https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Bangladesh_physical_map.svg/324px-Bangladesh_physical_map.svg.png
        Bitmap myImage;
        DownloadImage task = new DownloadImage();
        try{

            myImage = task.execute("https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Bangladesh_physical_map.svg/324px-Bangladesh_physical_map.svg.png").get();
            image.setImageBitmap(myImage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.imageView);
    }

    public class DownloadImage extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url = null;
            try {
                url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();

                Bitmap  myBitMap = BitmapFactory.decodeStream(inputStream);
                return myBitMap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }
    }
}
