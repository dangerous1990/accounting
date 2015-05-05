package com.limeng.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.limeng.common.DBHelper;
import com.limeng.entries.Record;

public class RecordService
{
	private DBHelper db;
	private ArrayList<Record> list = new ArrayList<Record>();

	public RecordService(Context context)
	{
		db = new DBHelper(context);
	}

	public void add(Record record)
	{
		SQLiteDatabase sdb = null;
		try
		{

			sdb = db.getSQLiteDatabase();

			String sql = "insert into record(type,cost,record_time) values(?,?,?)";

			DecimalFormat df = new DecimalFormat("###.00");

			Object[] paramValues =
			{ record.getType(), df.format(record.getCost().doubleValue()),
					record.getRecord_time().getTime() };

			sdb.execSQL(sql, paramValues);
		} catch (SQLException e)
		{
			throw e;

		} finally
		{
			if (null != sdb)
				sdb.close();

		}

	}
	public void delete(Date date)
	{
		SQLiteDatabase sdb = null;
		try
		{

			sdb = db.getSQLiteDatabase();

			String sql = "delete from record where record_time=?";
		

			String[] paramValues =
			{String.valueOf(date.getTime()) };

			sdb.execSQL(sql, paramValues);
		} catch (SQLException e)
		{
			throw e;

		} finally
		{
			if (null != sdb)
				sdb.close();

		}

	}

	public BigDecimal total(Calendar start, Calendar end)
	{
		BigDecimal bd = BigDecimal.ZERO;
		SQLiteDatabase sdb = null;
		Cursor c = null;// 游标，用于获取结果集中的获取的一个对象
		try
		{
			sdb = db.getSQLiteDatabase(); // 获取数据库的一个连接

			String sql = "select sum(cost) from record where record_time>=? and record_time<=?";
			String[] paramValues =
			{ String.valueOf(start.getTimeInMillis()),
					String.valueOf(end.getTimeInMillis()) };

			c = sdb.rawQuery(sql, paramValues);// 执行查询操作
			if (c.moveToNext())
			{
				String temp = c.getString(0);

				if (null != temp && !"".equals(temp))
				{
					bd = new BigDecimal(temp);
				}
			}
		} catch (SQLiteException e)
		{
			throw e;
		} finally
		{
			if (null != sdb)
			{ // 关闭连接
				sdb.close();
			}
			if (null != c)
			{ // 关闭连接
				c.close();
			}
		}

		return bd;
	}

	public ArrayList<Record> getRecord(Calendar start, Calendar end)
	{
		SQLiteDatabase sd = null;
		Cursor cursor = null;
		String sql = "select * from record where record_time>=? and record_time<=?";
		try
		{
			sd = db.getSQLiteDatabase();
			cursor = sd.rawQuery(
					sql,
					new String[]
					{ String.valueOf(start.getTimeInMillis()),
							String.valueOf(end.getTimeInMillis()) });
			while (cursor.moveToNext())
			{
				Record record = new Record();
				record.setId(cursor.getInt(0));
				record.setType(cursor.getString(1));
				String str = cursor.getString(2);
				record.setCost(new BigDecimal(str));
				Long l = cursor.getLong(3);
				record.setRecord_time(new Date(l));						
				list.add(record);
			}

		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			if (null != sd)
			{
				sd.close();
			}
			if (null != cursor)
			{
				cursor.close();
			}
		}

		return list;

	}
	
	public Map<String,BigDecimal> find_typeAndCost(Calendar s,Calendar e)
	{	String sql = "select type,sum(cost) sc from record where record_time>=? and record_time<=? group by type";
		SQLiteDatabase sd = null;
		Cursor cursor = null;
		Map<String,BigDecimal> map = new HashMap<String, BigDecimal>();
		try
		{
			sd = db.getSQLiteDatabase();
			cursor = sd.rawQuery(
					sql,
					new String[]
					{ String.valueOf(s.getTimeInMillis()),
							String.valueOf(e.getTimeInMillis()) });
			while (cursor.moveToNext())
			{	String s1 = cursor.getString(cursor.getColumnIndex("type"));
				String s2 = cursor.getString(cursor.getColumnIndex("sc"));
				BigDecimal b =new BigDecimal(s2);
				map.put(s1,b);
			}

		} catch (SQLException se)
		{
			throw se;
		} finally
		{
			if (null != sd)
			{
				sd.close();
			}
			if (null != cursor)
			{
				cursor.close();
			}
		}

		
		return map;
	}

	
}
