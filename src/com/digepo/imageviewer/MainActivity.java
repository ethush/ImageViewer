package com.digepo.imageviewer;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TouchImageView imagen;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imagen = (TouchImageView) findViewById(R.id.imageView);
		
		
		imgRemota img = new imgRemota();
		//img.execute("http://192.168.2.116/digepo_SIG/imagenes/2.png");
		img.execute("http://10.0.3.2/digepo_SIG/imagenes/2.png");
		Toast.makeText(this, "prueba touchImage", Toast.LENGTH_LONG).show();
		try {
			//Drawable drawable = img.get();
			DataHandler.graph1 = img.get();
			imagen.setImageDrawable(DataHandler.graph1);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//imagen.setImageResource(R.drawable.penguin);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
		
		
	}
	
	private class imgRemota extends AsyncTask<String, Integer, Drawable>{

		@Override
		protected Drawable doInBackground(String... params) {
			try{
				InputStream is = (InputStream) new URL(params[0]).getContent();
				Drawable draw = Drawable.createFromStream(is, "recurso");
				Log.e("imagen", draw.toString());
				return draw;
			}
	    	catch (Exception ex){
	    		Log.d("Error carga remota", ex.toString());
	    		return null;
	    	}
		}
    }


	
}
