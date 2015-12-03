package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Calculator extends Activity implements OnClickListener{
	Button btn_0;//0数字按钮
	Button btn_1;//1数字按钮
	Button btn_2;//2数字按钮
	Button btn_3;//3数字按钮
	Button btn_4;//4数字按钮
	Button btn_5;//5数字按钮
	Button btn_6;//6数字按钮
	Button btn_7;//7数字按钮
	Button btn_8;//8数字按钮
	Button btn_9;//9数字按钮
	Button btn_point;//point小数点按钮
	Button btn_clear;//clear清除按钮
	Button btn_del;//del删除按钮
	Button btn_plus;//plus加按钮
	Button btn_minus;//minus减按钮
	Button btn_multiply;//乘按钮
	Button btn_divide;//除按钮
	Button btn_equal;//等号按钮
	boolean clearflag;//清空标示
	EditText et_input;//显示输入内容的显示屏

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		
		//实例化上述的按钮
		btn_0=(Button)findViewById(R.id.btn_0);
		btn_1=(Button)findViewById(R.id.btn_1);
		btn_2=(Button)findViewById(R.id.btn_2);
		btn_3=(Button)findViewById(R.id.btn_3);
		btn_4=(Button)findViewById(R.id.btn_4);
		btn_5=(Button)findViewById(R.id.btn_5);
		btn_6=(Button)findViewById(R.id.btn_6);
		btn_7=(Button)findViewById(R.id.btn_7);
		btn_8=(Button)findViewById(R.id.btn_8);
		btn_9=(Button)findViewById(R.id.btn_9);
		btn_point=(Button)findViewById(R.id.btn_point);
		btn_clear=(Button)findViewById(R.id.btn_clear);
		btn_del=(Button)findViewById(R.id.btn_del);
		btn_plus=(Button)findViewById(R.id.btn_plus);
		btn_minus=(Button)findViewById(R.id.btn_minus);
		btn_multiply=(Button)findViewById(R.id.btn_multiply);
		btn_divide=(Button)findViewById(R.id.btn_divide);
		btn_equal=(Button)findViewById(R.id.btn_equal);
		et_input=(EditText)findViewById(R.id.et_input);

		//设置点击事件
		btn_0.setOnClickListener(this);
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);
		btn_8.setOnClickListener(this);
		btn_9.setOnClickListener(this);
		btn_point.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
		btn_del.setOnClickListener(this);
		btn_plus.setOnClickListener(this);
		btn_minus.setOnClickListener(this);
		btn_multiply.setOnClickListener(this);
		btn_divide.setOnClickListener(this);
		btn_equal.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// 判断点的是哪个按钮
		String str=et_input.getText().toString();
		switch(v.getId()){
		case R.id.btn_0:
		case R.id.btn_1:
		case R.id.btn_2:
		case R.id.btn_3:
		case R.id.btn_4:
		case R.id.btn_5:
		case R.id.btn_6:
		case R.id.btn_7:
		case R.id.btn_8:
		case R.id.btn_9:
		case R.id.btn_point:
			if(clearflag){
				clearflag=false;
				str="";
				et_input.setText("");
			}
			et_input.setText(str+((Button)v).getText());//只要点击数字按钮和小数点按钮，就会把数字添加在输入框中。在原来输入框的内容上面直接累加
			break;
		
		case R.id.btn_plus: //运算符是一类
		case R.id.btn_minus:
		case R.id.btn_multiply:
		case R.id.btn_divide:
			if(clearflag){
				clearflag=false;
				str="";
				et_input.setText("");
			}
			et_input.setText(str+" "+((Button)v).getText()+" ");
			break;
			
		case R.id.btn_clear:
			clearflag=false;
			str="";
			et_input.setText("");
			break;
		case R.id.btn_del://删除是一个一个的删除
			if(clearflag){
				clearflag=false;
				str="";
				et_input.setText("");
			}
			if(str != null && !str.equals("")){
				et_input.setText(str.substring(0,str.length()-1));
			}
			break;
		case R.id.btn_equal:
			getResult();
			break;
			default:
				break;
		}
	}
	//单独来运算最后的结果
	private void getResult(){
		String exp=et_input.getText().toString();
		if(exp == null || exp.equals("")){
			return;
		}
		//判断当前内容中有没有空格，如果没有空格说明没点运算符，则直接显示，不需要运算
		if(!exp.contains(" ")){
			return;
		}
		if(clearflag){
			clearflag=false;
			return;
		}
		clearflag=true;//当点击“＝”时，清空标示设置为true
		double result=0;
		String s1=exp.substring(0,exp.indexOf(" "));//截取运算符前面的字符，也就是第一个参与运算的数字
		String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);//截取的运算符
		String s2=exp.substring(exp.indexOf(" ")+3);//截取运算符后面的字符，也就是第二个参与运算的数字
		if(!s1.equals("") && !s2.equals("")){//s1,s2都不为空
			double d1=Double.parseDouble(s1);
			double d2=Double.parseDouble(s2);
			if(op.equals("+")){
				result=d1+d2;
			}
			else if(op.equals("-")){
				result=d1-d2;
			}
			else if(op.equals("×")){
				result=d1*d2;
			}
			else if(op.equals("÷")){
				if(d2 == 0){
					result=0;
				}
				else
					result=d1/d2;
			}
			if(!s1.contains(".") && !s2.contains(".") && !op.equals("÷")){
				int r=(int)result;
				et_input.setText(r+"");
			}
			else{
				et_input.setText(result+"");
			}
		}
		else if(!s1.equals("") && s2.equals("")){//s1不为空，s2为空
			et_input.setText(exp);
		}
		else if(s1.equals("")&& !s2.equals("")){//s1为空，s2不为空
			double d2=Double.parseDouble(s2);
			if(op.equals("+")){
				result=0+d2;
			}
			else if(op.equals("-")){
				result=0-d2;
			}
			else if(op.equals("×")){
				result=0*d2;
			}
			else if(op.equals("÷")){
				result=0;
			}
			if(!s1.contains(".") && !s2.contains(".")){
				int r=(int)result;
				et_input.setText(r+"");
			}
			else{
				et_input.setText(result+"");
			}
		}
		else{
			et_input.setText("");
		}
	}
}
