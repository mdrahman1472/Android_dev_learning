package com.example.rahman.guesscelebrity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.MalformedURLException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> celebImgArray = new ArrayList<String>(); // hold url for celeb image
    ArrayList<String> celebNameArray = new ArrayList<String>(); // hold celeb name
    ImageView imageView;
    int selectedCelebIndex = 0;
    int locationOfCorrectAnswer = 0;
    String[] answers = new String[4];
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    public void buttonClick(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            Toast.makeText(getApplicationContext(),"Correct!",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Incorrect! it is "+answers[locationOfCorrectAnswer],Toast.LENGTH_LONG).show();
        }
        questions();
    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            try {
                url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();
                while (data != -1){
                    char current = (char) data;

                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);


        DownloadTask task = new DownloadTask();
        try {
            String result = task.execute("http://www.posh24.com/celebrities").get();
            String[] splitResult = result.split("<div class=\"sidebarContainer\">");

            Pattern p = Pattern.compile("<img src=\"(.*?)\"");
            Matcher m = p.matcher(splitResult[0]);
            while(m.find()){
                celebImgArray.add(m.group(1));
            }

            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResult[0]);
            while(m.find()){
                celebNameArray.add(m.group(1));
            }

            questions();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void questions(){
        try {
            Random rand = new Random();
            selectedCelebIndex = rand.nextInt(celebImgArray.size());// randomly choice 1 less array size
            DownloadImage imgDownloadTask = new DownloadImage();
            Bitmap resultImg = imgDownloadTask.execute(celebImgArray.get(selectedCelebIndex)).get();

            // set image on screen
            imageView.setImageBitmap(resultImg);

            // place for correct answer in 4 buttons
            locationOfCorrectAnswer = rand.nextInt(4); // becasue of 4 buttons
            System.out.println("ans loc: " + locationOfCorrectAnswer + "    size img: " + celebImgArray.size() + "    size Name: " + celebNameArray.size());

            int indexOfInCorrectAnswer = 0;
            for (int i = 0; i < 4; i++) {
                if (i == locationOfCorrectAnswer) {
                    System.out.println("iter: " + i + " cor indx: " + locationOfCorrectAnswer);
                    answers[i] = celebNameArray.get(selectedCelebIndex);
                } else {
                    indexOfInCorrectAnswer = rand.nextInt(celebImgArray.size());
                    while (indexOfInCorrectAnswer == selectedCelebIndex)
                        indexOfInCorrectAnswer = rand.nextInt(celebImgArray.size());

                    System.out.println("iter: " + i + " incor indx: " + indexOfInCorrectAnswer);
                    answers[i] = celebNameArray.get(indexOfInCorrectAnswer);
                }


            }
            button1.setText(answers[0]);
            button2.setText(answers[1]);
            button3.setText(answers[2]);
            button4.setText(answers[3]);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
