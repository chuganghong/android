package com.example.hello.app;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

//imageview
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity {
	
	private ImageView ivwPicture = null;
	private Button ibtnPre = null;
	private Button ibtnNext = null;
	private Button button = null;
    private Button button2 = null;
	
	private Integer[] iImages = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		ivwPicture = (ImageView)super.findViewById(R.id.picture);
		ibtnPre = (Button)super.findViewById(R.id.pre);
		ibtnNext = (Button)super.findViewById(R.id.next);
		ibtnPre.setOnClickListener(new PreOnClickListener());
		ibtnNext.setOnClickListener(new NextOnClickListener());
		
		button = (Button)super.findViewById(R.id.go);
		button.setOnClickListener(new Listener());

        button2 = (Button)super.findViewById(R.id.go2);
        button2.setOnClickListener(new Listener2());
	}
    
	private class Listener implements OnClickListener
	{		
		public void onClick(View v)
		{
			Intent intent = new Intent();
			intent.setClass(MainActivity.this,OtherActivity.class);
			startActivity(intent);
		}
	}

    private class Listener2 implements OnClickListener
    {
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,Other2Activity.class);
            startActivity(intent);
        }
    }
	
	
	private class PreOnClickListener implements OnClickListener
	{
		private int i = 3;
		public void onClick(View view)
		{
			if(i > 0)
			{
				ivwPicture.setImageResource(iImages[--i]);
			}
			else if(i == 0)
			{
				i = 3;
				ivwPicture.setImageResource(iImages[3]);
			}
		}
	}
	
	private class NextOnClickListener implements OnClickListener
	{
		private int i = 0;
		public void onClick(View view)
		{
			if(i < 3)
			{
				ivwPicture.setImageResource(iImages[i++]);
			}
			else if(i == 3)
			{
				i = 0;
				ivwPicture.setImageResource(iImages[i]);
			}
		}
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


