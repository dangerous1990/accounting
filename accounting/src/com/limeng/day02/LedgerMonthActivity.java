package com.limeng.day02;



import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

import com.limeng.common.DateHelper;
import com.limeng.day02.LedgerWeekActivity.DayLedger;
import com.limeng.day02.adapter.MonthLedgerAdapter;
import com.limeng.day02.adapter.RecordLsitViewAdapter;
import com.limeng.entries.Record;
import com.limeng.service.RecordService;
import com.viewpagerindicator.TitlePageIndicator;

public class LedgerMonthActivity extends FragmentActivity
{
	private MonthLedgerAdapter dla;
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
				LedgerMonthActivity.this.finish();
				
			}
		});
		tv_title.setText("按月流水");
		
		
		dla = new MonthLedgerAdapter(getSupportFragmentManager());
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
	
	public static final class DayLedger extends android.support.v4.app.Fragment{
		private RecordService recordService;
		private static final String CREATE_KEY ="monthledgerfragment";
		private static final String CREATE_KEY2 ="datas";
		private ListView listview;
		private Calendar c;
		private ArrayList<Record> datas;
		private RecordLsitViewAdapter  listViewAdapter;
		private Record r;
		
		
		public static DayLedger newInstance(Calendar c){
			DayLedger dl= new DayLedger();
			dl.c =c;
			return dl;
			
		}

		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			if(savedInstanceState!=null && savedInstanceState.containsKey(CREATE_KEY2) ) 
			{
				c = (Calendar) savedInstanceState.getSerializable(CREATE_KEY2);
			}
			super.onCreate(savedInstanceState);
			recordService = new RecordService(this.getActivity());
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState)
		{
			View v=  inflater.inflate(R.layout.day_ledger_listview, null);
			listview = (ListView) v.findViewById(R.id.lv_day_ledger);
			listViewAdapter = new RecordLsitViewAdapter(this.getActivity());
			listview.setAdapter(listViewAdapter);
			if(savedInstanceState!=null && savedInstanceState.containsKey(CREATE_KEY) ) 
			{
				datas =  (ArrayList<Record>) savedInstanceState.getSerializable(CREATE_KEY);
			}
			else {
				datas =  (ArrayList<Record>)recordService.getRecord(DateHelper.monthFirst(c), DateHelper.monthLast(c));
			}
			listViewAdapter.setDatas(datas);
			listview.setOnItemLongClickListener(new OnItemLongClickListener()
			{
			
			
				
				@Override
				public boolean onItemLongClick(AdapterView<?> parent,  View view,
						int position, long id)
				{	
					listview.setTag(position);
					r = datas.get(position);
					final int i = position;
					//listViewAdapter.setI(position);
					
					
					AlertDialog dialog = new AlertDialog.Builder(DayLedger.this.getActivity())
				.setIcon(android.R.drawable.ic_dialog_info)
				.setTitle("确定删除").setMessage("您真的要删除此条记录吗？")
				.setPositiveButton("确认", new DialogInterface.OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						
						
						 recordService.delete(r.getRecord_time());
						// datas =  (ArrayList<Record>)recordService.getRecord(DateHelper.start(c), DateHelper.end(c));
					//	listViewAdapter.setDatas(datas);
						 datas.remove(i);
						listViewAdapter.notifyDataSetChanged();

					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which)
					{

						dialog.dismiss();
					}
				}).create();
				dialog.show();
					
			return true;
					
				}
				
			});
			
			
			return v;
		}

		@Override
		public void onSaveInstanceState(Bundle outState)
		{
			outState.putSerializable(CREATE_KEY2, c);
			outState.putSerializable(CREATE_KEY, datas);
			super.onSaveInstanceState(outState);
		}
		
		
	}
	
	
}
