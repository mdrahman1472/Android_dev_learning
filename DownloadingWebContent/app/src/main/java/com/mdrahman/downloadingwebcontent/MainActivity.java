package com.mdrahman.downloadingwebcontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

// we need permission from user to get access to internet which is defined in manifests --> AndroidManifest.xml
public class MainActivity extends AppCompatActivity {
    /* we are creating a class and extends AsyncTask. enerally we do everything inside
        onCreate() but it is wise to do separately when task take fair bit of time. In this
        case we are downloading from web which may take long time. So, this way it will work in the
        backgraound
         AsyncTask<String, void, String> : - first parameter, type of date we want send to DownloadTask
                                          - second parameter, name of method we may or may not use to show the
                                          progress of this task, we palced void since we are not gonna show progress here

                                          - third, type of variable that will be returned by DownloadTask
         */
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        // protected means this can be access from anywhere in the package, basically anywhere in the app
        // String...  : called Varargs, means kind of array but when not specifying how many is there
        protected String doInBackground(String... params) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try{
                url = new URL(params[0]); // params[0] since we know we are going to give only one one string url for doenload in this case but could be multiple
                urlConnection = (HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream(); // access content (source code of web in this case)
                InputStreamReader reader = new InputStreamReader(in); // read it

                int data = reader.read(); // from reader it read one character at time
                // now need to put in while loop to read whole character
                while(data != -1){ // when finish read all char the it read -1
                    char current = (char) data;
                    result += current; // appending result string
                    data = reader.read(); // for reading next char
                }

                return result;
            } catch (Exception e){
                e.printStackTrace();
                return "failed";
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // calling DownloadTask
        DownloadTask task = new DownloadTask();
        String result = null;
        try{
            result = task.execute("https://ecowebhosting.co.uk/").get();
        } catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }


        Log.i("Contents of URL", result);
    }
}
