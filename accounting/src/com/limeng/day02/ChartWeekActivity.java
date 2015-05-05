package com.limeng.day02;



import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.limeng.common.DateHelper;
import com.limeng.day02.adapter.DayChartAdapter;
import com.limeng.day02.adapter.WeekChartAdapter;
import com.limeng.service.RecordService;
import com.viewpagerindicator.TitlePageIndicator;

public class ChartWeekActivity extends FragmentActivity
{
	private WeekChartAdapter dla;
	private ViewPager pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.day_ledger_tabs);
		TextView tv_title = (TextView) this.findViewById(R.id.tv_title_text);
		
		Button btn1 = (Button) this.findViewById(R.id.tv_tlback);
		btn1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				ChartWeekActivity.this.finish();
				
			}
		});
		tv_title.setText("按周统计分类");
		
		
		dla = new WeekChartAdapter(getSupportFragmentManager());
		pager = (ViewPager)this.findViewById(R.id.pager);
		
		TitlePageIndicator indicator =(TitlePageIndicator)this.findViewById(R.id.indicator);
		
		pager.setAdapter(dla);
		
		indicator.setViewPager(pager);

		indicator.setOnPageChangeListener(new OnPageChangeListener() {
			
		@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
			   if(dla.getCount()-1 == arg0)
			   {
				   dla.addTabs();
			   }
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	public static final class DayChart extends android.support.v4.app.Fragment{
		private RecordService recordService;
		private static final String CREATE_KEY ="c";
		private Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();
		
		private Calendar c;
		private BigDecimal bd ;
		
		
		
		public static DayChart newInstance(Calendar c){
			DayChart dl= new DayChart();
			dl.c =c;
			return dl;
			
		}

		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			if(savedInstanceState!=null && savedInstanceState.containsKey(CREATE_KEY) ) 
			{
				c = (Calendar) savedInstanceState.getSerializable(CREATE_KEY);
			}
			super.onCreate(savedInstanceState);
			recordService = new RecordService(this.getActivity());
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState)
		{
			LinearLayout v=(LinearLayout) inflater.inflate(R.layout.testchart, null);
			
			v.addView(initView());
			
			return v;
		}
		
		public View initView()
		{
			int[] COLORS = new int[]
					{ Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN ,Color.BLUE,Color.LTGRAY,Color.DKGRAY};
			DefaultRenderer mRenderer = new DefaultRenderer();
			CategorySeries mSeries = new CategorySeries("");
			mRenderer.setApplyBackgroundColor(true);
		   //mRenderer.setBackgroundColor(Color.GRAY);
		   // mRenderer.setChartTitleTextSize(20);
		    mRenderer.setLabelsTextSize(15);
		    mRenderer.setLegendTextSize(15);
		    mRenderer.setMargins(new int[] { 20, 30, 15, 0 });
		  //  mRenderer.setZoomButtonsVisible(true);
		  //  mRenderer.setStartAngle(90);
		    map = recordService.find_typeAndCost(DateHelper.weekFirst(c), DateHelper.weekLast(c));
		    bd = recordService.total(DateHelper.start(DateHelper.weekFirst(c)), DateHelper.end(DateHelper.weekLast(c)));
		    for(Entry<String, BigDecimal> entry : map.entrySet())
		    
		    {
		    	Double d = entry.getValue().doubleValue()/bd.doubleValue();
		    	NumberFormat nt = NumberFormat.getPercentInstance();
		    	mSeries.add(entry.getKey()+nt.format(d) ,entry.getValue().doubleValue());
		    }
		    
		    for (int i= 0;i<map.size();i++) {
	               SimpleSeriesRenderer r = new SimpleSeriesRenderer();
	                r.setColor(COLORS[i]);
	                mRenderer.addSeriesRenderer(r);
	             }
		    View v = ChartFactory.getPieChartView(getActivity(), mSeries, mRenderer);
		    
		    return v;
		}
		
/**
 * 缓存数据，一个Title的时间 ，一个datas
 */
		@Override
		public void onSaveInstanceState(Bundle outState)
		{
			outState.putSerializable(CREATE_KEY, c);
			
			super.onSaveInstanceState(outState);
		}
		
		
	}
	
	
}
