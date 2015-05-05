package com.limeng.day02;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.limeng.entries.Record;
import com.limeng.service.RecordService;

public class RecordActivity extends Activity
{

	private TextView et_curr_date;
	
	
	private Spinner spinner;
	private EditText record_money;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_record);
		
		 record_money = (EditText)this.findViewById(R.id.et_record_money);
		 
		 spinner = (Spinner)this.findViewById(R.id.record_spinner);
		//显示时间
		et_curr_date = (TextView) this.findViewById(R.id.et_cur_date);
		et_curr_date.setText(new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date()));
		// 按钮事件
				
		Button btn = (Button) this.findViewById(R.id.btn_record);
		Button btn1 = (Button) this.findViewById(R.id.tv_tlback);
		btn1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				RecordActivity.this.finish();
				
			}
		});
		btn.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{	
				String type = (String)spinner.getSelectedItem();
				String str = String.valueOf(record_money.getText());		
			if(null != str && !"".equals(str))
			{Record record = new Record();
			record.setType(type);
			record.setCost(new BigDecimal(str));
			record.setRecord_time(new Date());
			
			RecordService service = new RecordService(RecordActivity.this);
			try {
				service.add(record);
				Toast.makeText(RecordActivity.this, "新增成功，你可继续添加一下笔", Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				e.printStackTrace();
				
				Toast.makeText(RecordActivity.this, "新增失败，请重试", Toast.LENGTH_SHORT).show();
			}
							
			}
			else{
				Toast.makeText(RecordActivity.this, "输入的金额不能为空", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
				
		
		
	}
}
