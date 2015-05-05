package com.limeng.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class MoreAboutUs extends FragmentActivity
{
	private WebView webView;
	@Override
	protected void onCreate(Bundle arg0)
	{	super.onCreate(arg0);
		this.setContentView(R.layout.more_about_us);
		TextView tv_title=(TextView)this.findViewById(R.id.tv_title_text);
		tv_title.setText("关于我们");
		Button btn = (Button)this.findViewById(R.id.tv_tlback);
		btn.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				MoreAboutUs.this.finish();
				
			}
		});
		
		 webView = (WebView) this.findViewById(R.id.aboutus);
		 
		 String str= readHtml("aboutus.html").toString();
		 webView.loadDataWithBaseURL("file:///android_assets", str, "text/html", "UTF-8", null);
		
	}
	public StringBuffer readHtml(String path)
	{ 	StringBuffer sb = new StringBuffer();
		BufferedReader bufer=null;
		try
		{
			 bufer = new BufferedReader(new InputStreamReader(this.getAssets().open(path)));
			 String str =bufer.readLine();
			 while(str!=null)
			 {
				 sb.append(bufer.readLine());
				 str=bufer.readLine();
			 }
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bufer.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sb;
	}
	
	
}
