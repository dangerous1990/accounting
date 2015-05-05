package com.limeng.day02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.limeng.day02.adapter.MoreListViewAdapter;

/**
 * 更多界面
 * 
 * @author zhsys
 * 
 */
public class MoreFragment extends Fragment
{
	int mNum;

	/**
	 * Create a new instance of CountingFragment, providing "num" as an
	 * argument.
	 */
	public static MoreFragment newInstance(int num)
	{
		MoreFragment f = new MoreFragment();

		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putInt("num", num);
		f.setArguments(args);

		return f;
	}

	/**
	 * When creating, retrieve this instance's number from its arguments.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mNum = getArguments() != null ? getArguments().getInt("num") : 1;
	}

	/**
	 * The Fragment's UI is just a simple text view showing its instance number.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		View v = inflater.inflate(R.layout.fragment_more, null);
		//设置 更多标题
		v.findViewById(R.id.tv_tlback).setVisibility(View.GONE);
		((TextView) v.findViewById(R.id.tv_title_text)).setText("更多");
		
		//使用自定义的构造器 传值
		MoreListViewAdapter adapter = new MoreListViewAdapter(getActivity());
		ListView lv = (ListView) v.findViewById(R.id.lv);
		lv.setAdapter(adapter);
		//构造数据
		final List<Map<String, Object>> testDatas = new ArrayList<Map<String, Object>>();

		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("left", Integer.valueOf(R.drawable.more_setting));
		item1.put("title", "系统设置");
		item1.put("right", Boolean.TRUE);
		

		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("left", Integer.valueOf(R.drawable.more_feedback));
		item2.put("title", "意见反馈");
		item2.put("right", Boolean.TRUE);
		

		Map<String, Object> item3 = new HashMap<String, Object>();
		item3.put("left", Integer.valueOf(R.drawable.more_help));
		item3.put("title", "使用协议");
		item3.put("right", Boolean.TRUE);
		

		Map<String, Object> item4 = new HashMap<String, Object>();
		item4.put("left", Integer.valueOf(R.drawable.more_about));
		item4.put("title", "关于我们");
		item4.put("right", Boolean.TRUE);
		

		Map<String, Object> item5 = new HashMap<String, Object>();
		item5.put("left", Integer.valueOf(R.drawable.more_queryonline));
		item5.put("title", "检查更新");
		item5.put("right", Boolean.FALSE);
		//添加到List中
		testDatas.add(item1);
		testDatas.add(item2);
		testDatas.add(item3);
		testDatas.add(item4);
		testDatas.add(item5);
		
		adapter.setDatas(testDatas);
		adapter.notifyDataSetChanged();
		//添加Item监听器
		lv.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				switch (position)
				{
				case 0 :
					//启动“系统设置”
					break;
				case 1 :
					//启动“意见反馈”
					break;
				case 2 :
					//启动“使用协议”
					Intent intent2 = new  Intent(getActivity(),Morexieyi.class);
					startActivity(intent2);
					break;
				case 3 :
					//启动“关于我们”
					Intent intent3 = new Intent(getActivity(),MoreAboutUs.class);
					startActivity(intent3);
					break;
				case 4 :
					//启动“检查更新”
					break;

				
				}
				
			}
			
		});

		return v;
	}
}
