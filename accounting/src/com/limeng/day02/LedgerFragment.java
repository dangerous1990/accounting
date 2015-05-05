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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.limeng.common.DateHelper;
import com.limeng.service.RecordService;

/**
 * 流水单界面
 * @author zhsys
 *
 */


public class LedgerFragment extends Fragment {
    int mNum;
    TextView day;
    TextView week;
    TextView month;
    LinearLayout ledger_ll1;
    LinearLayout ledger_ll2;
    LinearLayout ledger_ll3;
    
    private RecordService recordService;
    /**
     * Create a new instance of CountingFragment, providing "num"
     * as an argument.
     */
    public static LedgerFragment newInstance(int num) {
    	LedgerFragment f = new LedgerFragment();

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
        recordService = new RecordService(this.getActivity());
    }

    /**
     * The Fragment's UI is just a simple text view showing its
     * instance number.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
       View v = inflater.inflate(R.layout.fragment_ledger, null);
       //设置title标题
      ((TextView) v.findViewById(R.id.tv_title_text)).setText("流水单");
      v.findViewById(R.id.tv_tlback).setVisibility(View.GONE);
      //取出3个 属性
       day = (TextView) v.findViewById(R.id.tv_ledger_day);
      week = (TextView) v.findViewById(R.id.tv_ledger_week);
       month = (TextView) v.findViewById(R.id.tv_ledger_month);
       //取出linearlayout
       ledger_ll1=( LinearLayout) v.findViewById(R.id.ledger_ll1);
       ledger_ll2=( LinearLayout) v.findViewById(R.id.ledger_ll2);
       ledger_ll3=( LinearLayout) v.findViewById(R.id.ledger_ll3);
       
          
    		
    		
    		
    		ledger_ll1.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					Intent intent = new Intent(getActivity(),LedgerDayActivity.class);
					LedgerFragment.this.startActivity(intent);
					
					
				}
			});
    		ledger_ll2.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					Intent intent = new Intent(getActivity(),LedgerWeekActivity.class);
					LedgerFragment.this.startActivity(intent);
					
					
				}
			});
    		ledger_ll3.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
				Intent intent = new Intent(getActivity(),LedgerMonthActivity.class);
					LedgerFragment.this.startActivity(intent);
					
					
				}
			});
      
      
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
		
		day.setText(df.format(today.doubleValue()));
		
		// ///设置本周的消费总额（怎么获取本周第一天和最后一天的日历）
		
		BigDecimal ledger_week = recordService.total(
				DateHelper.start(DateHelper.weekFirst(c)),
				DateHelper.end(DateHelper.weekLast(c)));
		week.setText(df.format(ledger_week));

		// /////设置本月的消费总额(怎么获取本月第一天和最后一天的日历)
		BigDecimal ledger_month = recordService.total(
				DateHelper.start(DateHelper.monthFirst(c)),
				DateHelper.end(DateHelper.monthLast(c)));
		month.setText(df.format(ledger_month));
    	super.onResume();
    }
}
