package com.limeng.day02.adapter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.limeng.day02.R;
import com.limeng.entries.Record;

public class RecordLsitViewAdapter extends BaseAdapter
{
	private List<Record> datas;
	private DecimalFormat df = new DecimalFormat("-￥#,###.00");
	private SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private LayoutInflater inflater;
	private Context ctx;
	private int i ;
	public void setI(int i)
	{
		this.i = i;
	}

	private ViewHolder vh;

	
	//定义构造方法
	public RecordLsitViewAdapter(Context ctx)
	{
		this.ctx=ctx;
		inflater = LayoutInflater.from(ctx);
	}
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position)
	{

		return datas.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.ledger_day_relative, null);
			vh = new ViewHolder();
			vh.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			vh.tv_mintime = (TextView) convertView
					.findViewById(R.id.tv_mintime);
			vh.tv_sum = (TextView) convertView.findViewById(R.id.tv_sum);
			vh.tv_types = (TextView) convertView.findViewById(R.id.tv_types);

			convertView.setTag(vh);
		}
		
		vh = (ViewHolder) convertView.getTag();
		Record curr = datas.get(position);
		vh.iv_icon.setImageResource(curr.getRource(ctx));
		vh.tv_mintime.setText(df2.format(curr.getRecord_time()));
		vh.tv_sum.setText(df.format(curr.getCost()));
		vh.tv_types.setText(curr.getType());
		//datas.remove(i);
		notifyDataSetChanged();

		return convertView;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		return super.getDropDownView(position, convertView, parent);
	}
	public void setDatas(List<Record> datas)
	{
		this.datas = datas;
	}

	static class ViewHolder
	{
		ImageView iv_icon;
		TextView tv_types;
		TextView tv_sum;
		TextView tv_mintime;
		
	}
}
