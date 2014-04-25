package com.example.hello.app;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

//imageview
import android.widget.Button;

import android.view.View;
import android.view.View.OnClickListener;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;


public class OtherActivity extends ActionBarActivity {
	
	private ImageView imageView;
    private Button button = null;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);

        button = (Button)super.findViewById(R.id.b);
        button.setOnClickListener(new Listener());


		}

    private class Listener implements OnClickListener
    {
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(OtherActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
	


}


