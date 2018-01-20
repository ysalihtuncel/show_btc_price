package com.srtyazilim.cash;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class MainActivity extends AppCompatActivity {
    Button btn, btn2;
    ImageView img1, img2, img3, img4;
    int i=0;
    String URL = "https://www.coinmarketcap.com/currencies/bitcoin/";
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.kirbac);
        img1 = (ImageView) findViewById(R.id.imageView);
        img2 = (ImageView) findViewById(R.id.imageView2);
        img3 = (ImageView) findViewById(R.id.imageView3);
        img4 = (ImageView) findViewById(R.id.imageView4);

        img1.setVisibility(View.INVISIBLE);
        img2.setVisibility(View.INVISIBLE);
        img3.setVisibility(View.INVISIBLE);
        img4.setVisibility(View.INVISIBLE);


        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.cash);
        final MediaPlayer mp1 = MediaPlayer.create(getApplicationContext(), R.raw.cash);
        final MediaPlayer mp2 = MediaPlayer.create(getApplicationContext(), R.raw.cash);
        final MediaPlayer mp3 = MediaPlayer.create(getApplicationContext(), R.raw.cash);
        final MediaPlayer mp4 = MediaPlayer.create(getApplicationContext(), R.raw.cash);

        final MediaPlayer m = MediaPlayer.create(getApplicationContext(), R.raw.crack);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m.start();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                i++;

                if(i==1){
                    img1.setVisibility(View.VISIBLE);
                    mp.start();

                }
                else if(i==2){
                    img2.setVisibility(View.VISIBLE);
                    mp1.start();
                }
                else if(i==3){
                    img3.setVisibility(View.VISIBLE);
                    mp2.start();
                }
                else if(i==4){
                    img4.setVisibility(View.VISIBLE);
                    mp3.start();
                }
                else{
                    mp4.start();
                    img1.setVisibility(View.INVISIBLE);
                    img2.setVisibility(View.INVISIBLE);
                    img3.setVisibility(View.INVISIBLE);
                    img4.setVisibility(View.INVISIBLE);
                    i=0;
                }

            }
        });

        new getData().execute();

        Handler myHandler = new Handler();
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new getData().execute();
                //call the method which is schedule to call after 20 sec
            }
        }, 600000);  //the time is in miliseconds



    }




    private class getData extends AsyncTask<Object, Object, Void> {


        String para;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Object... objects) {
            try{
                Document doc  = Jsoup.connect(URL).get();    // web siteye bağlantıyı gerçeleştirme
                Elements info = doc.select("span[id=quote_price");
                para = info.text();
            }catch (Exception e){

                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            txt = (TextView) findViewById(R.id.textView);
            txt.setText("1 BTC: " + "" + para);
        }
    }
}


