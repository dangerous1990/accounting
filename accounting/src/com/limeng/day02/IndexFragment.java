package com.limeng.day02;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.limeng.common.DateHelper;
import com.limeng.service.BudgetService;
import com.limeng.service.RecordService;

/**
 * 首页界面
 * 
 * @author zhsys
 * 
 */
public class IndexFragment extends Fragment
{
	int mNum;
	private TextView tv_today_amount;
	private TextView tv_week_amount;
	private TextView tv_month_amount;
	
	private TextView tv_index_msg;
	private TextView tv_index_ze;
	private TextView tv_index_zc;
	private TextView tv_index_ye;
	
	private LinearLayout linear_1;
	private LinearLayout linear_2;
	private LinearLayout linear_3;
	
	RecordService recordService;
	BudgetService budgetService;
	

	/**
	 * Create a new instance of CountingFragment, providing "num" as an
	 * argument.
	 */
	public static IndexFragment newInstance(int num)
	{
		IndexFragment f = new IndexFragment();

		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putInt("num", num);
		f.setArguments(args);

		return f;
	}

	/**
	 * When creating, retrieve this instance's number from its arguments.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mNum = getArguments() != null ? getArguments().getInt("num") : 1;
		 recordService = new RecordService(getActivity());
		 budgetService = new BudgetService(getActivity());
	}

	/**
	 * The Fragment's UI is just a simple text view showing its instance number.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		View v = inflater.inflate(R.layout.fragment_index, null);
		//流水部分
		tv_today_amount = (TextView) v.findViewById(R.id.tv_today_amount);
		tv_week_amount = (TextView) v.findViewById(R.id.tv_week_amount);
		tv_month_amount = (TextView) v.findViewById(R.id.tv_month_amount);
		
		//预算部分
		 tv_index_msg = (TextView) v.findViewById(R.id.tv_index_msg);
		 tv_index_ze = (TextView) v.findViewById(R.id.tv_index_ze);
		 tv_index_zc = (TextView) v.findViewById(R.id.tv_index_zc);
		 tv_index_ye = (TextView) v.findViewById(R.id.tv_index_ye);
		 //linear
		 linear_1 = (LinearLayout)v.findViewById(R.id.linear_1);
		 linear_2 = (LinearLayout)v.findViewById(R.id.linear_2);
		 linear_3 = (LinearLayout)v.findViewById(R.id.linear_3);
		 linear_1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getActivity(),LedgerDayActivity.class);
				startActivity(intent);
				
			}
		});
		 linear_2.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					Intent intent = new Intent(getActivity(),LedgerWeekActivity.class);
					startActivity(intent);
					
				}
			});
		 linear_3.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					Intent intent = new Intent(getActivity(),LedgerMonthActivity.class);
					startActivity(intent);
					
				}
			});
		
		 
		 
		 //记一笔账
		Button btn = (Button) v.findViewById(R.id.btn_start_record);
		v.findViewById(R.id.tv_tlback).setVisibility(View.GONE);

		((TextView) v.findViewById(R.id.tv_title_text)).setText("首页");

		btn.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getActivity(), RecordActivity.class);
				startActivity(intent);

			}
		});
		((TextView) v.findViewById(R.id.tv_curr_year_month)).setText(DateHelper
				.getCurrYear() + "年" + DateHelper.getCurrMonth() + "月");
		((TextView) v.findViewById(R.id.tv_curr_day)).setText(DateHelper
				.getCurrDay() + "");

		return v;
	}

	@Override
	public void onResume()
	{
		
		// /////设置今天的消费总额
		Calendar c = Calendar.getInstance();
		BigDecimal today = recordService.total(DateHelper.start(c),
				DateHelper.end(c));
		DecimalFormat df = new DecimalFormat("-￥#,###.00");
		DecimalFormat df2 = new DecimalFormat("+￥#,###.00");
		DecimalFormat df3 = new DecimalFormat("￥#,###.00");
		tv_today_amount.setText(df.format(today.doubleValue()));
		
		// ///设置本周的消费总额（怎么获取本周第一天和最后一天的日历）
		
		BigDecimal week = recordService.total(
				DateHelper.start(DateHelper.weekFirst(c)),
				DateHelper.end(DateHelper.weekLast(c)));
		tv_week_amount.setText(df.format(week.doubleValue()));

		// /////设置本月的消费总额(怎么获取本月第一天和最后一天的日历)
		BigDecimal month = recordService.total(
				DateHelper.start(DateHelper.monthFirst(c)),
				DateHelper.end(DateHelper.monthLast(c)));
		tv_month_amount.setText(df.format(month.doubleValue()));
		//设置预算总额
		BigDecimal ze = budgetService.getCurMonthTotal();
		tv_index_ze.setText("预算总额:"+df2.format(ze));
		//设置支出
		tv_index_zc.setText("支出总额:"+df.format(month.doubleValue()));
		//设置余额
		BigDecimal ye=ze.subtract(month);
		if(ye.signum()<0){
			tv_index_ye.setText("预算余额:"+df3.format(ye));
			tv_index_msg.setText("本月已超支！！！");
			tv_index_msg.setVisibility(View.VISIBLE);
		}
		else{
			tv_index_ye.setText("预算余额:"+df2.format(ye));
		}
		super.onResume();
	}
}
