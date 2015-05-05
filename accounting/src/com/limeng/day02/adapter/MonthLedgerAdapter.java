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
import com.limeng.day02.LedgerMonthActivity;
import com.viewpagerindicator.IconPagerAdapter;

/**
 * @author limeng
 * 
 */
public class MonthLedgerAdapter extends FragmentPagerAdapter implements
		IconPagerAdapter
{

	private final List<Calendar> lists = new LinkedList<Calendar>();
	

	public MonthLedgerAdapter(FragmentManager fm)
	{
		super(fm);
		Calendar c = Calendar.getInstance();
		lists.add(c);
		Calendar c1 = DateHelper.prevMonth(c);
		lists.add(c1);
		Calendar c2 = DateHelper.prevMonth(c1);
		lists.add(c2);

	}

	

	@Override
	public Fragment getItem(int arg0)
	{
		// TODO Auto-generated method stub
		return LedgerMonthActivity.DayLedger.newInstance(lists.get(arg0));
	}

	@Override
	public int getCount()
	{

		return lists.size();
	}

	@Override
	public CharSequence getPageTitle(int position)
	{	SimpleDateFormat df = new SimpleDateFormat("MM月");
		Calendar monthStart = DateHelper.monthFirst(lists.get(position));
		//Calendar weekEnd = DateHelper.weekFirst(lists.get(position));
		//String str=df.format(weekStart.getTime());
		//String str2= df.format(weekEnd.getTime());
		//String str3= str+"-"+str2; 
		String str = df.format(monthStart.getTime());
		return str;
	}
	
	
	
	
	public void addTabs()
	{
		if(lists.size()<31)
		{	 Calendar c=DateHelper.prevMonth(lists.get(lists.size()-1));
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
