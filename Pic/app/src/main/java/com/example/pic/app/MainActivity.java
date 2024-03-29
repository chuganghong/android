package com.example.pic.app;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {
	
	private EditText pathText;
	private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		pathText = (EditText)this.findViewById(R.id.imagepath);
		imageView = (ImageView)this.findViewById(R.id.imageView);
		Button button = (Button)this.findViewById(R.id.button);
		
		button.setOnClickListener(new ButtonClickListener());
    }
	
	private final class ButtonClickListener implements View.OnClickListener{
		@Ovrride
		public void onClick(View v)
		{
			String path = pathText.getText().toString();
			try
			{
				byte[] data = ImageService.getImage(path);
				Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
				imageView.setImageBitmap(bitmap);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Toast.makeText(getApplicationContext(),R.string.error,1).show();
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
