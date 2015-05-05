package com.limeng.entries;

import java.math.BigDecimal;

public class Budget 
{
	private  int id;
	private  BigDecimal total;
	private  int year;
	private  int month;
	
	public Budget()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public BigDecimal getTotal()
	{
		return total;
	}
	public void setTotal(BigDecimal total)
	{
		this.total = total;
	}
	public int getYear()
	{
		return year;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public int getMonth()
	{
		return month;
	}
	public void setMonth(int month)
	{
		this.month = month;
	}
}
