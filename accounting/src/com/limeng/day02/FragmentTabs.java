package com.limeng.day02;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.limeng.common.TabManager;

/**
 * 主框架界面
 */
public class FragmentTabs extends FragmentActivity
{
	/** 选项卡组件对象 */
	private TabHost mTabHost;
	private long temp;
	/** 自定义的选项卡管理器 */
	private TabManager mTabManager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_tabs);

		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);

		// 添加选项
		mTabManager.addTab(
				mTabHost.newTabSpec("index")
						.setIndicator(
								createTabHeader("首页",
										R.drawable.tab_btn_home_selector)),
				IndexFragment.class, null);
		mTabManager.addTab(
				mTabHost.newTabSpec("ledger").setIndicator(
						createTabHeader("流水单",
								R.drawable.tab_btn_message_selector)),
				LedgerFragment.class, null);
		mTabManager.addTab(
				mTabHost.newTabSpec("chart").setIndicator(
						createTabHeader("图表统计",
								R.drawable.tab_btn_self_selector)),
				ChartFragment.class, null);
		mTabManager.addTab(
				mTabHost.newTabSpec("budget")
						.setIndicator(
								createTabHeader("预算",
										R.drawable.tab_btn_squre_selector)),
				BudgetFragment.class, null);
		mTabManager.addTab(
				mTabHost.newTabSpec("more")
						.setIndicator(
								createTabHeader("更多",
										R.drawable.tab_btn_more_selector)),
				MoreFragment.class, null);

	}

	private View createTabHeader(String str, int imgId)
	{
		// 获取一个布局文件加载器
		LayoutInflater inflater = LayoutInflater.from(this);
		View v = inflater.inflate(R.layout.tab_indicator, null);// 加载指定的布局文件成一个View对象

		ImageView iv = (ImageView) v.findViewById(R.id.iv_pic);
		iv.setImageResource(imgId);

		TextView tv = (TextView) v.findViewById(R.id.tv_tab);
		tv.setText(str);

		return v;
	}

	@Override//重写 退出键方法
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{   /**
			AlertDialog dialog = new AlertDialog.Builder(this)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setTitle("确认退出").setMessage("您真的要退出记账本吗？")
					.setPositiveButton("确认", new OnClickListener()
					{

						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
							FragmentTabs.this.finish();

						}
					}).setNegativeButton("取消", new OnClickListener()
					{

						@Override
						public void onClick(DialogInterface dialog, int which)
						{

							dialog.dismiss();
						}
					}).create();
			dialog.show();
			*/
			
			if(System.currentTimeMillis() - temp > 3000)
			{
				temp = System.currentTimeMillis();
				Toast.makeText(this, "在点击一下回退健退出", Toast.LENGTH_SHORT).show();
				
			}
			else
			{
				FragmentTabs.this.finish();
			}
			return true;
		} else
		{
			return super.onKeyDown(keyCode, event);
		}
	}
	//重写菜单键方法
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		this.getMenuInflater().inflate(R.menu.mian_menu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	//重写菜单键选中回调方法
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO Auto-generated method stub
		switch (item.getItemId())
		{
		case R.id.menu_exit : //退出菜单
			FragmentTabs.this.finish();
			break;
		case R.id.menu_info : //信息反馈
			
			break;
		case R.id.menu_settings : //系统设置
			
			break;
		case R.id.menu_aboutus : //关于我们
			Intent intent3 = new Intent(FragmentTabs.this,MoreAboutUs.class);
			startActivity(intent3);
			break;
		case R.id.menu_update : //检查更新
			
			break;
		case R.id.menu_use : //使用协议
			Intent intent2 = new  Intent(FragmentTabs.this,Morexieyi.class);
			startActivity(intent2);
			break;
		
		}
		return super.onOptionsItemSelected(item);
	}

}
