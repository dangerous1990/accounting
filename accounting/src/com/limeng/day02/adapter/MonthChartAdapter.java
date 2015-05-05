/**
 * 
 */
package com.limeng.day02.adapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.limeng.common.DateHelper;
import com.limeng.day02.ChartMonthActivity;
import com.viewpagerindicator.IconPagerAdapter;

/**
 * @author limeng
 * 
 */
public class MonthChartAdapter extends FragmentPagerAdapter implements
		IconPagerAdapter
{

	private final List<Calendar> lists = new LinkedList<Calendar>();
	

	public MonthChartAdapter(FragmentManager fm)
	{
		super(fm);
		Calendar c = Calendar.getInstance();
		lists.add(c);
		Calendar c1 = DateHelper.prevMonth(c);
		lists.add(c1);
		Calendar c2 = DateHelper.prevMonth(c);
		lists.add(c2);

	}

	

	@Override
	public Fragment getItem(int arg0)
	{
		// TODO Auto-generated method stub
		return ChartMonthActivity.DayChart.newInstance(lists.get(arg0));
	}

	@Override
	public int getCount()
	{

		return lists.size();
	}

	@Override
	public CharSequence getPageTitle(int position)
	{ SimpleDateFormat df = new SimpleDateFormat("MM月");
	 Calendar monthStart = DateHelper.monthFirst(lists.get(position));
	
	  String str = df.format(monthStart.getTime());
	   return str;
	}
	
	
	
	
	public void addTabs()
	{
		if(lists.size()<31)
		{	 Calendar c=DateHelper.prevMonth(lists.get(lists.size()-1));
		     lists.add(c);
			notifyDataSetChanged();
		}
	}



	@Override
	public int getIconResId(int index)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	
}
