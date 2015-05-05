package com.limeng.entries;

import java.math.BigDecimal;
import java.util.Date;

import android.content.Context;

import com.limeng.day02.R;

public class Record
{
	private int id; //编号
	private String type; //消费类型
	private BigDecimal cost; //消费金额
	private Date record_time; //记录时间
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public BigDecimal getCost()
	{
		return cost;
	}
	public void setCost(BigDecimal cost)
	{
		this.cost = cost;
	}
	public Date getRecord_time()
	{
		return record_time;
	}
	public void setRecord_time(Date record_time)
	{
		this.record_time = record_time;
	}
	public static int[] images ={
		R.drawable.icon_1,
		R.drawable.icon_2,
		R.drawable.icon_3,
		R.drawable.icon_4,
		R.drawable.icon_5,
		R.drawable.icon_6,
		R.drawable.icon_7,
		R.drawable.icon_8,
		R.drawable.icon_9,}; 
	
	public int getRource(Context ctx){
		String[] str = ctx.getResources().getStringArray(R.array.styles);
		int pos = 0 ;
		for(int i = 0; i<str.length;i++)
		{
			if(str[i].equalsIgnoreCase(type))
			{
				pos=i;
			}
		}
		return images[pos];
	}
	
}


