package com.limeng.day02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 图表统计界面
 * @author zhsys
 *
 */
public class ChartFragment extends Fragment {
    int mNum;
    LinearLayout chart_ll1;
    LinearLayout chart_ll2;
    LinearLayout chart_ll3;
    /**
     * Create a new instance of CountingFragment, providing "num"
     * as an argument.
     */
    public static ChartFragment newInstance(int num) {
    	ChartFragment f = new ChartFragment();

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    /**
     * The Fragment's UI is just a simple text view showing its
     * instance number.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	 View v = inflater.inflate(R.layout.fragment_chart, null);
         //设置title标题
        ((TextView) v.findViewById(R.id.tv_title_text)).setText("分类图标统计");
        v.findViewById(R.id.tv_tlback).setVisibility(View.GONE);
        //取出3个 属性
        TextView day = (TextView) v.findViewById(R.id.tv_chart_day);
        TextView week = (TextView) v.findViewById(R.id.tv_chart_week);
        TextView month = (TextView) v.findViewById(R.id.tv_chart_month);
         chart_ll1 =(LinearLayout)v.findViewById(R.id.chart_ll1);
         chart_ll2 =(LinearLayout)v.findViewById(R.id.chart_ll2);
         chart_ll3 =(LinearLayout)v.findViewById(R.id.chart_ll3);
        chart_ll1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getActivity(),ChartDayActivity.class);
				ChartFragment.this.startActivity(intent);
				
			}
		});
        chart_ll2.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent2 = new Intent(getActivity(),ChartWeekActivity.class);
				ChartFragment.this.startActivity(intent2);
				
			}
		});
        chart_ll3.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
			Intent intent = new Intent(getActivity(),ChartMonthActivity.class);
				startActivity(intent);
				
			}
		});
        
        day.setVisibility(View.INVISIBLE);
        week.setVisibility(View.INVISIBLE);
        month.setVisibility(View.INVISIBLE);
        return v;
    }
}
