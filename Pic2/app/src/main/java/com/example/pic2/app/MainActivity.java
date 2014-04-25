package com.example.pic2.app;


/*必需引用apache.http相關類別來建立HTTP連線*/
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils; 
/*必需引用java.io 與java.util相關類別來讀寫檔案*/
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.net.HttpURLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.StrictMode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class MainActivity extends Activity
{
    /*宣告兩個Button物件,與一個TextView物件*/
    private Button mButton2 = null;
    private TextView mTextView1 = null;
	private Handler handler = null;
	
	private Button mButton1 = null;
	private TextView mTextView2 = null;
	private ImageView mImageView1 = null;
	
	String uriPic = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
        .detectDiskReads()  
        .detectDiskWrites()  
        .detectNetwork()   // or .detectAll() for all detectable problems  
        .penaltyLog()  
        .build());  
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
        .detectLeakedSqlLiteObjects()  
        .detectLeakedClosableObjects()  
        .penaltyLog()  
        .penaltyDeath()  
        .build());  
		
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		handler = new Handler();
		
    /*透過findViewById建構子建立TextView與Button物件*/

        mButton2 =(Button)super.findViewById(R.id.myButton2);
        mTextView1 = (TextView)super.findViewById(R.id.myTextView1);
		
		mButton2.setOnClickListener(new Listener());
		
		mButton1 = (Button) findViewById(R.id.myButton1);
		mTextView2 = (TextView) findViewById(R.id.myTextView2);
		mImageView1 = (ImageView) findViewById(R.id.myImageView1);

		mButton1.setOnClickListener(new Listener1());
		
    }
	
	private class Listener implements OnClickListener
	{		
		public void onClick(View v)
		{
			new Thread()
			{
				public void run()
				{
					handler.post(runnableUi);
				}
			}.start();
		}
		
		Runnable runnableUi = new Runnable()
		{
			@Override
			public void run()
			{
				/*宣告網址字串*/
				String uriAPI = "http://10.0.2.2/app/testAPI.php"; 
				/*建立HTTP Get連線*/
				HttpGet httpRequest = new HttpGet(uriAPI); 
				try 
				{ 
					/*發出HTTP request*/
					HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest); 
					/*若狀態碼為200 ok*/
					if(httpResponse.getStatusLine().getStatusCode() == 200)  
					{ 
						/*取出回應字串*/
						String strResult = EntityUtils.toString(httpResponse.getEntity());
						uriPic = strResult;
						mTextView1.setText("");
					} 
					else 
					{ 
						mTextView1.setText("Error Response: "+httpResponse.getStatusLine().toString()); 
					} 
				} 
				catch (ClientProtocolException e) 
				{
					mTextView1.setText("c:" + e.toString());            
					e.printStackTrace(); 
				} 
				catch (IOException e) 
				{            
					mTextView1.setText("d:" + e.toString());
					e.printStackTrace(); 
				} 
				catch (Exception e) 
				{           
					mTextView1.setText("The content is:" + e.toString());
					e.printStackTrace();  
				} 
			}
		};		
	} 
	
	private class Listener1 implements OnClickListener
	{		
		public void onClick(View v)
		{
			new Thread()
			{
				public void run()
				{
					handler.post(runnableUi);
				}
			}.start();
		}
		
		Runnable runnableUi = new Runnable()
		{
			@Override
			public void run()
			{
				/*宣告網址字串*/
				String uriAPI = "http://10.0.2.2/app/testAPI.php"; 
				/*建立HTTP Get連線*/
				HttpGet httpRequest = new HttpGet(uriAPI); 
				try 
				{ 
					/*發出HTTP request*/
					HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest); 
					/*若狀態碼為200 ok*/
					if(httpResponse.getStatusLine().getStatusCode() == 200)  
					{ 
						/*取出回應字串*/
						String strResult = EntityUtils.toString(httpResponse.getEntity());
						uriPic = strResult;
						mTextView1.setText("");
						mImageView1.setImageBitmap(getURLBitmap());
					} 
					else 
					{ 
						mTextView1.setText("Error Response: "+httpResponse.getStatusLine().toString()); 
					} 
				} 
				catch (ClientProtocolException e) 
				{
					mTextView1.setText("c:" + e.toString());            
					e.printStackTrace(); 
				} 
				catch (IOException e) 
				{            
					mTextView1.setText("d:" + e.toString());
					e.printStackTrace(); 
				} 
				catch (Exception e) 
				{           
					mTextView1.setText("The content is:" + e.toString());
					e.printStackTrace();  
				} 
			}
		};		
	}

	public Bitmap getURLBitmap()
	{
		URL imageUrl = null;
		Bitmap bitmap = null;
		try
		{
			/* new URL物件將網址傳入 */
			imageUrl = new URL(uriPic);
		} 
		catch (MalformedURLException e)
		{
            mTextView2.setText("a:" + e.toString());
            e.printStackTrace();
		}
		try
		{
			/* 取得連線 */
			HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
			conn.connect();
			/* 取得回傳的InputStream */
			InputStream is = conn.getInputStream();
			/* 將InputStream變成Bitmap */
			bitmap = BitmapFactory.decodeStream(is);
			/* 關閉InputStream */
			is.close();
      
		}
		catch (IOException e)
		{
            mTextView2.setText("b:" + e.toString());
            e.printStackTrace();
		}
		return bitmap;
	}
} 
