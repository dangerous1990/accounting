package com.limeng.day02;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.limeng.entries.Budget;
import com.limeng.service.BudgetService;

/**
 * 预算界面
 * 
 * @author zhsys
 * 
 */
public class BudgetFragment extends Fragment
{
	int mNum;
	private TextView budget;
	private LinearLayout linearlayout;
	public BudgetService budgetService;
	EditText et;
	BigDecimal bg;
	public static BudgetFragment newInstance(int num)
	{
		BudgetFragment f = new BudgetFragment();

		Bundle args = new Bundle();
		args.putInt("num", num);
		f.setArguments(args);

		return f;
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mNum = getArguments() != null ? getArguments().getInt("num") : 1;
		budgetService = new BudgetService(this.getActivity());
	}

		@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		View v = inflater.inflate(R.layout.fragment_budget, null);
		// 设置title标题
		((TextView) v.findViewById(R.id.tv_title_text)).setText("本月预算");
		v.findViewById(R.id.tv_tlback).setVisibility(View.GONE);
		
		budget = (TextView) v.findViewById(R.id.budget_all);

		DecimalFormat df = new DecimalFormat("+￥#,###.00");
		bg = budgetService.getCurMonthTotal();
		Log.d("bg", ""+bg);
		budget.setText(df.format(bg));

		
		linearlayout = (LinearLayout) v.findViewById(R.id.budget_ll);
		linearlayout.setOnClickListener(new View.OnClickListener()
		{
			

			@Override
			public void onClick(View v)
			{   LayoutInflater inflater = getActivity().getLayoutInflater();  
				final View view = inflater.inflate(R.layout.dialog_edittext,null);
				final EditText et = (EditText) view.findViewById(R.id.et_dialog);
				et.setText(new DecimalFormat("####.00").format(budgetService.getCurMonthTotal()));
				
				AlertDialog dialog = new AlertDialog.Builder(getActivity())
						.setIcon(android.R.drawable.ic_dialog_info)
						.setTitle("输入/更改预算")
						.setView(view)
						.setPositiveButton("确认", new OnClickListener()
						{					
							@Override
							public void onClick(DialogInterface dialog,
									int which)
							{
								String str =String.valueOf(et.getText());
								if(null != str && !"".equals(str))
								{
								BigDecimal bd2 = new BigDecimal(str);
								Budget  bud = new Budget();
								Calendar c = Calendar.getInstance();
								//封装BUDGET
								bud.setMonth(c.get(Calendar.MONTH)+1);
								bud.setYear(c.get(Calendar.YEAR));
								bud.setTotal(bd2);							
								try {
									budgetService.addOrUpdateTotal(bud);
									Toast.makeText(getActivity(), "新增成功", Toast.LENGTH_SHORT).show();
								} catch (Exception e) {
									e.printStackTrace();
									
									Toast.makeText(getActivity(), "新增失败", Toast.LENGTH_SHORT).show();
								}
								}
								else
								{
									Toast.makeText(getActivity(), "输入的金额不能为空", Toast.LENGTH_SHORT).show();
								}							
								DecimalFormat df = new DecimalFormat("+￥#,###.00");
								BigDecimal bg2 = budgetService.getCurMonthTotal();
								budget.setText(df.format(bg2));
								dialog.dismiss();
							}
						}).setNegativeButton("取消", new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog,
									int which)
							{

								dialog.dismiss();
							}
						}).create();
				dialog.show();

			}
		});
		/**
		 * 获取总预算金额
		 */

		return v;

	}

}
