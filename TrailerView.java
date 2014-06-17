package us.the.mac.reel.life.views;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/** TrailerView loads a main trailer image and a back up trailer image for the next load.
 * This set up allows for a seamless transition between trailers, and it also has the 
 * ability open the trailer in the YouTube Android app.
 **/
public class TrailerView extends ImageView implements OnClickListener{
	Bitmap main;
	Bitmap backUp;

	public TrailerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener(this);
	}
	
	public void onClick(View v)
	{
		//YOUTUBE ID OF REEL
		String id = (String) v.getTag();

		//YOUTUBE APPLICATION REFERENCE
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + id));
		
		//INTENT TO OPEN YOUTUBE ID OF REEL
		getContext().startActivity(intent);
	}
	
	private boolean initialState() {
		return main == null;
	}

	private boolean normalBackUpState() {
		return backUp != null;
	}

	public void setUpWithBackUpUrl(String url, String backUpUrl)
	{
		if(initialState()) {
			//LOADS THE main BITMAP INTO THIS IMAGEVIEW
			new LoadTrailerView().execute(url);
			
			//SIMPLY LOADS THE backUp VARIABLE
			new LoadBackUpTrailerView().execute(backUpUrl);
		}
		else {
			//LOADS THE NEXT backUp BITMAP
			new LoadTrailerView().execute(backUpUrl);
		}
	}

	private Bitmap downloadBitmap(String... params) throws MalformedURLException, IOException {
		String trailer = params[0];
		URL url = new URL(trailer);
		return BitmapFactory.decodeStream(url.openConnection().getInputStream());
	}
	
	private class LoadTrailerView extends AsyncTask<String, Void, Bitmap>
	{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if(normalBackUpState()) {
				main = backUp;
				setImageBitmap(main);
			}
		}
		
		@Override
		protected Bitmap doInBackground(String... params) {

			try { return downloadBitmap(params); }
			catch (Exception e) { return null; }
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			if(main == null) {
				main = result;
				setImageBitmap(result);
			}
			else {
				backUp = result;
			}
		}
	}

	
	private class LoadBackUpTrailerView extends AsyncTask<String, Void, Bitmap>
	{
		@Override
		protected Bitmap doInBackground(String... params) {

			try { return downloadBitmap(params); }
			catch (Exception e) { return null; }
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			backUp = result;
		}
	}
}
