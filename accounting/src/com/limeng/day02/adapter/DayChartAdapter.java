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
import android.view.View;
import android.view.ViewGroup;

import com.limeng.common.DateHelper;
import com.limeng.day02.ChartDayActivity;
import com.limeng.day02.LedgerDayActivity;
import com.viewpagerindicator.IconPagerAdapter;

/**
 * @author limeng
 * 
 */
public class DayChartAdapter extends FragmentPagerAdapter implements
		IconPagerAdapter
{

	private final List<Calendar> lists = new LinkedList<Calendar>();
	

	public DayChartAdapter(FragmentManager fm)
	{
		super(fm);
		Calendar c = Calendar.getInstance();
		lists.add(c);
		Calendar c1 = DateHelper.prevDay(c);
		lists.add(c1);
		Calendar c2 = DateHelper.prevDay(c1);
		lists.add(c2);

	}

	

	@Override
	public Fragment getItem(int arg0)
	{
		// TODO Auto-generated method stub
		return ChartDayActivity.DayChart.newInstance(lists.get(arg0));
	}

	@Override
	public int getCount()
	{

		return lists.size();
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		String df = new SimpleDateFormat("yyyy年MM月dd日").format(lists
				.get(position).getTime());
		return df;
	}
	
	
	
	
	public void addTabs()
	{
		if(lists.size()<31)
		{	 Calendar c=DateHelper.prevDay(lists.get(lists.size()-1));
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
