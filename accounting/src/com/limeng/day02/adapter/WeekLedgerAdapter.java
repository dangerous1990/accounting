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
import com.limeng.day02.LedgerWeekActivity;
import com.viewpagerindicator.IconPagerAdapter;

/**
 * @author limeng
 * 
 */
public class WeekLedgerAdapter extends FragmentPagerAdapter implements
		IconPagerAdapter
{

	private final List<Calendar> lists = new LinkedList<Calendar>();
	

	public WeekLedgerAdapter(FragmentManager fm)
	{
		super(fm);
		Calendar c = Calendar.getInstance();
		lists.add(c);
		Calendar c1 = DateHelper.prevWeek(c);
		lists.add(c1);
		Calendar c2 = DateHelper.prevWeek(c1);
		lists.add(c2);

	}

	

	@Override
	public Fragment getItem(int arg0)
	{
		// TODO Auto-generated method stub
		return LedgerWeekActivity.DayLedger.newInstance(lists.get(arg0));
	}

	@Override
	public int getCount()
	{

		return lists.size();
	}

	@Override
	public CharSequence getPageTitle(int position)
	{	SimpleDateFormat df = new SimpleDateFormat("MM.dd");
		Calendar weekStart = DateHelper.weekFirst(lists.get(position));
		Calendar weekEnd = DateHelper.weekLast(lists.get(position));
		String str=df.format(weekStart.getTime());
		String str2= df.format(weekEnd.getTime());
		String str3= str+"-"+str2; 
		return str3;
	}
	
	
	
	
	public void addTabs()
	{
		if(lists.size()<12)
		{	 Calendar c=DateHelper.prevWeek(lists.get(lists.size()-1));
		     lists.add(c);
		     //自动刷新
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
