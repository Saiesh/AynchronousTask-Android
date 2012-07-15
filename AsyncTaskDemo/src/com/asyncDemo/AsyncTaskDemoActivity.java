package com.asyncDemo;

import java.util.Random;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AsyncTaskDemoActivity extends Activity 
{
	ProgressDialog dialog;
	Button bttn;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bttn = (Button)findViewById(R.id.bttn);
        bttn.setOnClickListener(new View.OnClickListener()
        {
			
			public void onClick(View arg0) 
			{
				try
				{
				  new execute().execute();
				  //execute();
				}
				catch(Exception e)
				{
					Toast.makeText(getBaseContext(),"Error :"+e.toString(),Toast.LENGTH_LONG).show();
				}
			}
		});
       
    }
    
    public void execute()
    {
    	int i=0;
		Random g = new Random();
		
		while(i<=100)
		{
			int tym = g.nextInt(3001);
			try 
			{
				Thread.sleep(tym);
			} 
			catch (InterruptedException e) 
			{
				
			}
			
			int v = g.nextInt(11);
			i=i+v;
		}
		
		Toast.makeText(getBaseContext(),"Data Loaded Successfully",Toast.LENGTH_LONG).show();
		
    }
    
    class execute extends AsyncTask<Void,String,Void>
    {

		@Override
		protected void onPreExecute() 
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(AsyncTaskDemoActivity.this);
			dialog.setMessage("Please wait ... Loading ...");
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			//dialog.setMax(100);
			dialog.show();
			//dialog.setProgress(5);
		}

		@Override
		protected Void doInBackground(Void... params) 
		{
			// TODO Auto-generated method stub
			int i=0;
			Random g = new Random();
			while(i<=100)
			{
				int tym = g.nextInt(3001);
				try 
				{
					Thread.sleep(tym);
				} 
				catch (InterruptedException e) 
				{
					
				}
				
				int v = g.nextInt(11);
				i=i+v;
				
				if(i>100)
					publishProgress("Process Completed");
				else
					publishProgress(i+"% done");
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(String... values) 
		{
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			//dialog.setProgress(values[0]);
			dialog.setMessage(values[0]);
		}
		
		@Override
		protected void onPostExecute(Void result) 
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			Toast.makeText(getBaseContext(),"Data Loaded Successfully",Toast.LENGTH_LONG).show();
		}
    	
    }
}