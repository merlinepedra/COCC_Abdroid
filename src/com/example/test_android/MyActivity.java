/*************************************************************************
 *****               Author: Gage Heinrich                             ***
 ****   Description: This program is an app that is made for COCC      ***
 ****   There is a main page that will have all the buttons            ***
 ****   or links to other "activity pages" or web URl's.               ***
 ****   It is supossed to be more convinient for those with phones     ***
 ****   to access the bobcat web account, blackbord etc.               ***
 ****                                                                  ***
 * ***********************************************************************
**/

package com.example.test_android;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        // button that is 2nd from the top that goes to the map_page


        // button that will be on top of the homepage app
        ImageButton myButton = (ImageButton) findViewById(R.id.button3);
        ImageButton google_button = (ImageButton) findViewById(R.id.button2);
        ImageButton fBButton = (ImageButton) findViewById(R.id.fbButton);
        ImageButton LibButton = (ImageButton) findViewById(R.id.LibraryButton);
        ImageButton YoutubeButton = (ImageButton) findViewById(R.id.YtButton);
        ImageButton DirectButton = (ImageButton) findViewById(R.id.DirectoryButton);

        final String mTag = "tag";

        DirectButton.setOnClickListener(myhandler);
        myButton.setOnClickListener(myhandler);
        google_button.setOnClickListener(myhandler);
        fBButton.setOnClickListener(myhandler);
        LibButton.setOnClickListener(myhandler);
        YoutubeButton.setOnClickListener(myhandler);
        class getWeather extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {

                String result = null;
                try {
                  Document doc = Jsoup.connect("http://weather.weatherbug.com/OR/Bend-weather.html?zcode=z6286").get();


                    Elements tblElements = doc.select(".entry-title");

                    result = tblElements.text();

                    Log.d(mTag, result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
            @Override
            protected void onPostExecute(String result) {

                ((TextView) findViewById(R.id.setweather)).setText(result);
            }

        }
        getWeather weather = new getWeather();
        weather.execute();


    }

    public View.OnClickListener myhandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.fbButton) {


                Intent fbintent = new Intent(MyActivity.this, facebook_cocc.class);
                startActivity(fbintent);
            }


            if (v.getId() == R.id.button3) {

                Intent web_view_login = new Intent(MyActivity.this, web_view_login.class);
                startActivity(web_view_login);


            }


            if (v.getId() == R.id.button2) {
                Intent clickmap = new Intent(MyActivity.this, google_map.class);
                startActivity(clickmap);

            }

            if (v.getId() == R.id.LibraryButton) {
                Intent LibraryPageIntent = new Intent(MyActivity.this, library.class);
                startActivity(LibraryPageIntent);

            }

            if (v.getId() == R.id.YtButton) {
                Intent YtubeIntent = new Intent(MyActivity.this, YouTube.class);
                startActivity(YtubeIntent);

            }

            if (v.getId() == R.id.DirectoryButton) {
                Intent DirectoryIntent = new Intent(MyActivity.this, Directory.class);
                startActivity(DirectoryIntent);
            }





                /*sLoginView = (WebView)findViewById(R.id.button3);
                sLoginView.getSettings().setJavaScriptEnabled(true);
                sLoginView.loadUrl("http://www.cocc.edu/student-login/");
                */

        }


    };






    }




