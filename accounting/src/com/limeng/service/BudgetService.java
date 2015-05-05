package com.limeng.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.limeng.common.DBHelper;
import com.limeng.entries.Budget;

public class BudgetService
{
	private DBHelper db;

	public BudgetService(Context context)
	{
		db = new DBHelper(context);
	}

	public BigDecimal getCurMonthTotal()
	{
		BigDecimal bd = BigDecimal.ZERO;
		SQLiteDatabase sdb = null;
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		Log.d("budgetservice", ""+year+month);
		// DecimalFormat df = new DecimalFormat("+￥###.000");
		Cursor cursor = null;
		try
		{
			sdb = db.getSQLiteDatabase();
			String sql = "select total from budget where year=? and month=?";
			cursor = sdb.rawQuery(sql, new String[]{ String.valueOf(year), String.valueOf(month) });

			if (cursor.moveToNext())
			{
				String temp = cursor.getString(0);
				Log.d("budgetservicetemp", ""+temp);

				if (null != temp && !"".equals(temp))
				{
					bd = new BigDecimal(temp);
					
					Log.d("budgetservicebd", ""+bd);
				}
			}
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			if (null != sdb)
			{
				sdb.close();
			}
			if (null != cursor)
			{
				cursor.close();
			}
		}
		return bd;
	}

	public void addOrUpdateTotal(Budget budget)
	{
		SQLiteDatabase sdb = null;
		int id = 0;
		Cursor cursor = null;

		try
		{
			sdb = db.getSQLiteDatabase();
			String sql1 = "select id from budget where year=? and month=? ";
			String sql2 = "update budget set total=? where id=?";
			String sql3 = "insert into budget(year,total,month) values(?,?,?)";
			int year = budget.getYear();
			int month = budget.getMonth();
			cursor = sdb.rawQuery(sql1,new String[]{ String.valueOf(year),String.valueOf(month)});
			BigDecimal total = budget.getTotal();
			if (cursor.moveToNext())
			{
				id = cursor.getInt(0);
			}
			if (id > 0)
			{
				sdb.execSQL(sql2, new String[]
				{ String.valueOf(total), String.valueOf(id) });
				
			} else
			{
				sdb.execSQL(sql3, new String[]
				{ String.valueOf(year), String.valueOf(total),String.valueOf(month) });
			}

		} catch (SQLException e)
		{
			throw e;

		} finally
		{
			if (null != sdb)
			{ // 关闭连接
				sdb.close();
			}
			if (null != cursor)
			{ // 关闭连接
				cursor.close();
			}
		}

	}

}
