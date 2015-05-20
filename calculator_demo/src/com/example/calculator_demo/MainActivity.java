package com.example.calculator_demo;

/*
 * 
 * <dl>
 * <dt>Main.java</dt>
 * <dd>Description: ������</dd>
 * <dd>Copyright: Copyright (C) 2011</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2013-6-14</dd>
 * </dl>
 * 
 * @author xiaojian
 */
import java.math.BigDecimal;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button NumBtn1, NumBtn2, NumBtn3, NumBtn4, NumBtn5, NumBtn6,
			NumBtn7, NumBtn8, NumBtn9, NumBtn0;

	private Button signPlusBtn;
	private Button signMinusBtn;
	private Button signMutiBtn;
	private Button signDivideBtn;
	private Button pointBtn;
	private Button signEqualsBtn;
	private Button clearBtn;
	private Button confirmBtn;

	private EditText num1InputText = null;
	private EditText operatorInputText = null;
	private EditText num2InputText = null;
	private EditText equalInputText = null;
	private EditText resultInputText = null;

	private boolean Num1HavePoint = false; // �ж������Ƿ���С����
	private boolean Num2HavePoint = false; // �ж������Ƿ���С����
	private boolean ErrorMark = false; // �ж��Ƿ��д�falseΪû�У�trueΪ��

	private String str1 = "";
	private String str2 = "";
	private String str3 = "";
	private String str5 = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ���ְ�ť
		NumBtn0 = (Button) findViewById(R.id.num0btn);
		NumBtn1 = (Button) findViewById(R.id.num1btn);
		NumBtn2 = (Button) findViewById(R.id.num2btn);
		NumBtn3 = (Button) findViewById(R.id.num3btn);
		NumBtn4 = (Button) findViewById(R.id.num4btn);
		NumBtn5 = (Button) findViewById(R.id.num5btn);
		NumBtn6 = (Button) findViewById(R.id.num6btn);
		NumBtn7 = (Button) findViewById(R.id.num7btn);
		NumBtn8 = (Button) findViewById(R.id.num8btn);
		NumBtn9 = (Button) findViewById(R.id.num9btn);

		NumBtn0.setOnClickListener(this);
		NumBtn1.setOnClickListener(this);
		NumBtn2.setOnClickListener(this);
		NumBtn3.setOnClickListener(this);
		NumBtn4.setOnClickListener(this);
		NumBtn5.setOnClickListener(this);
		NumBtn6.setOnClickListener(this);
		NumBtn7.setOnClickListener(this);
		NumBtn8.setOnClickListener(this);
		NumBtn9.setOnClickListener(this);

		// ������ť
		signPlusBtn = (Button) findViewById(R.id.signplusbtn);
		signMinusBtn = (Button) findViewById(R.id.signminusbtn);
		signMutiBtn = (Button) findViewById(R.id.signmutibtn);
		signDivideBtn = (Button) findViewById(R.id.signdividebtn);
		pointBtn = (Button) findViewById(R.id.pointbtn);
		signEqualsBtn = (Button) findViewById(R.id.signequalbtn);
		clearBtn = (Button) findViewById(R.id.clearbtn);
		confirmBtn = (Button) findViewById(R.id.confirmbtn);

		signPlusBtn.setOnClickListener(new OnOperateClickListener());
		signMinusBtn.setOnClickListener(new OnOperateClickListener());
		signMutiBtn.setOnClickListener(new OnOperateClickListener());
		signDivideBtn.setOnClickListener(new OnOperateClickListener());
		pointBtn.setOnClickListener(this);
		signEqualsBtn.setOnClickListener(new OnEqualclickListener());
		clearBtn.setOnClickListener(new OnClearclickListener());
		confirmBtn.setOnClickListener(new OnEqualclickListener());

		// ��ʾ��

		num1InputText = (EditText) findViewById(R.id.num1inputview);
		operatorInputText = (EditText) findViewById(R.id.operatorinputview);
		num2InputText = (EditText) findViewById(R.id.num2inputview);
		equalInputText = (EditText) findViewById(R.id.equalinputview);
		resultInputText = (EditText) findViewById(R.id.resultinputview);

		resultInputText.setInputType(InputType.TYPE_NULL);// ����ʾ�����
		num1InputText.setInputType(InputType.TYPE_NULL);
		num2InputText.setInputType(InputType.TYPE_NULL);
		operatorInputText.setInputType(InputType.TYPE_NULL);
		equalInputText.setInputType(InputType.TYPE_NULL);

		// �ı������
		num1InputText.addTextChangedListener(tw);
	}

	/**
	 * Description:�����������ť����
	 * 
	 * @author sqg
	 * 
	 */

	class OnOperateClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EditText et = (EditText) getCurrentFocus(); // ��ȡ������ͼ
			String operate = ((Button) v).getText().toString(); // ��ȡ�������
			getInputText(); // ��ȡ��������������
			if (et.equals(num1InputText)) {
				if (!str1.equals("")) { // ��һ�������Ϊ��
					operatorInputText.setText(operate);
					num1InputText.setEnabled(false); // �����1������
					num2InputText.setEnabled(true);
					num2InputText.requestFocus(); // �����2��ȡ����
				}

			}else if(et.equals(resultInputText)){
				if(!"ERROR".equals(str5)){
					String temp=str5;
					clear();
					if(temp.indexOf('.') != -1){   //��������С����
						Num1HavePoint = true;
					}
					/*if(temp.length()>12){			//������ȳ��ޣ����ֵ
						temp=temp.substring(0,12);
					}*/
					num1InputText.setText(temp);
					operatorInputText.setText(operate);
					num2InputText.setEnabled(true);
					num1InputText.setEnabled(false);
					num2InputText.requestFocus();
					
				}
			} else {    					//�����������2��
				if (!"".equals(str3)) {
					BigDecimal bigNumber = getResult(); // ʹ��BigDecimal���г�������ȷ����
					operatorInputText.setText(operate);
					if (!ErrorMark) {
						String temp = bigNumber.stripTrailingZeros()
								.toPlainString(); // ȥ��ĩβ��0
						if (temp.matches("\\d{13}.*")) { // ���ֵ����,����
							num2InputText.setEnabled(false);
							resultInputText.requestFocus();
							resultInputText.setText("ERROR");
						} else {
							str1 = temp;
							/*if (str1.length() > 12) { // ���ȳ��ޣ���ֵ
								str1 = str1.substring(0, 12);
							}*/
							Num1HavePoint = false;
							Num2HavePoint = false;
							if (str1.indexOf('.') != -1) {
								Num1HavePoint = true;
							}
							num1InputText.setText(str1);
							operatorInputText.setText(operate);
							num2InputText.setText("");
						}
					} else { // �������
						num2InputText.setEnabled(false);
						resultInputText.requestFocus();
						resultInputText.setText("ERROR");
					}
				} else {
					operatorInputText.setText(operate); // ���������2Ϊ�գ���ı��������
				}
			}

		}

	}

	/**
	 * Description:����ȷ����ť����
	 * 
	 * @author sqg
	 * 
	 */
	class OnEqualclickListener implements OnClickListener {

		public void onClick(View v) {
			getInputText(); // ��ȡ����
			if ("".equals(str1)) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"û�����ֲ��ܼ���^.^", Toast.LENGTH_SHORT);// û����������
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			} else if (!"".equals(str2)) {
				BigDecimal bigNumber = getResult();
				equalInputText.setText("=");
				num2InputText.setEnabled(false);
				resultInputText.requestFocus();
				if (!ErrorMark) {
					String temp = bigNumber.stripTrailingZeros()
							.toPlainString(); // ȥ��0��ʾ
					if (temp.matches("\\d{13}.*")) {
						str5 = "ERROR";
					} else {
						str5 = temp;
						if (str5.indexOf('.') != -1) { // ������������С����
							if (str5.length() - str5.indexOf('.') > 11) {
								str5 = str5
										.substring(0, str5.indexOf('.') + 11); // ��ȡ����
							}
						}
					}
				} else {
					str5 = "ERROR";
				}
				resultInputText.setText(str5);
			}
		}

	}

	/**
	 * Description:���������ť����
	 * 
	 * @author sqg
	 * 
	 */
	class OnClearclickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			getInputText();
			if ("".equals(str1)) {
				showSimpleDialog(MainActivity.this);
				//finish(); // �˳�����
			} else if (ErrorMark) {
				clear(); // ���
			} else {
				View v1 = getCurrentFocus();
				if (v1.getId() == num1InputText.getId()) {
					if (str1.charAt(str1.length() - 1) == '.') {
						Num1HavePoint = false;
					}
					if (str1.length() > 1) {
						str1 = str1.substring(0, str1.length() - 1);
					} else {
						str1 = "";
					}
					num1InputText.setText(str1);
				} else if (v1.getId() == num2InputText.getId()) { // ɾ��������2
					if (str3.length() > 1) {
						if (str3.charAt(str3.length() - 1) == '.') { // �ж�ɾ�����Ƿ�ΪС����
							Num1HavePoint = false;
						}
						str3 = str3.substring(0, str3.length() - 1);
					} else {
						if ("".equals(str3)) {
							num2InputText.setEnabled(false);
							operatorInputText.setText("");
							num1InputText.requestFocus();
						} else {
							str3 = "";
						}
					}
					num2InputText.setText(str3);
				} else { // ����м�������ֱ�ӳ�ʼ��
					clear();
				}
			}
		}

	}

	/**
	 * Description:�������ֺ�С���㰴ť����
	 */
	@Override
	public void onClick(View v) {
		EditText et = (EditText) getCurrentFocus(); // ��ȡ�������������
		String text = et.getText().toString(); // ��ȡ�����������
		if (text.length() >= 12 && !et.equals(resultInputText)) { // �ж��Ƿ񳤶ȳ���
			return;
		}
		String inputText = ((Button) v).getText().toString(); // ��ȡ��������
		if (et.equals(resultInputText)) { // ���Ѿ��н��������£���ʼ�����ڵ�һ�������������
			if (!"ERROR".equals(str5)) {
				clear();
				if (v.equals(pointBtn)) {
					inputText = "0.";
					Num1HavePoint = true;
				}
				num1InputText.setText(inputText);
				num1InputText.setSelection(inputText.length()); // ���̶�
			}
		} else {
			if (v.equals(pointBtn)) {
				if ("".equals(text)) {
					text += 0;
				}
				if (et.equals(num1InputText) && !Num1HavePoint) { // �ж�������1�Ƿ����С����
					text += ".";
					Num1HavePoint = true;
				} else if (et.equals(num2InputText) && !Num2HavePoint) { // �ж�������2�Ƿ����С����
					text += ".";
					Num2HavePoint = true;
				}
			} else {
				if ("0".equals(text)) { // ��������������Ϊ0����ֱ��������������
					text = inputText;
				} else {
					text += inputText;
				}
			}
			et.setText(text); // �����������ʾ
			et.setSelection(text.length()); // �̶���������һλ
		}
	}

	/**
	 * ����������
	 * 
	 * @return
	 */
	private BigDecimal getResult() {
		/*if (str1.matches("0\\.0*")) {
			str1 = 0 + "";
		}*/
		BigDecimal num1 = new BigDecimal(str1);
		BigDecimal num3 = null;
		if ("".equals(str3)) {
			num3 = num1;
		} else {
			/*if (str3.matches("0\\.0*")) {
				str3 = 0 + "";
			}*/
			if (str3.indexOf('.') == str3.length() - 1) { // ���С���������һλ,��ȥ���ٸ�ֵ
				str3 = str3.substring(0, str3.length() - 1);
				num2InputText.setText(str3);
			}
			BigDecimal num2 = new BigDecimal(str3);
			switch (str2.charAt(0)) {
			case '+':
				num3 = num1.add(num2);
				break;
			case '-':
				num3 = num1.subtract(num2);
				break;
			case '*': {
				if ("0".equals(str1) || "0".equals(str3)) { // ����Ϊ0
					num3 = new BigDecimal("0");
				} else {
					num3 = num1.multiply(num2);
				}
			}
				break;
			case '/': {
				if (str3.equals("0")) { // ����Ϊ0 ,����
					ErrorMark = true;
				} else if (str1.equals("0")) { // ������Ϊ0
					num3 = num1;

				} else {
					num3 = num1.divide(num2, 12, BigDecimal.ROUND_HALF_UP);
				}
			}
				break;
			default:
				break;
			}
		}
		return num3;
	}

	
	/**
	 * �˳���ʾ�Ի���
	 * @param context
	 */
		public void showSimpleDialog(final Context context) {
			String message = context.getResources().getString(
					R.string.common_back_button_confirm);
			AlertDialog dialog = new AlertDialog.Builder(context)
					.setTitle(R.string.alert_title)
					.setMessage(message)
					.setPositiveButton(R.string.confirm,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									((Activity) context).finish();
								}
							})
					.setNegativeButton(R.string.negative,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							}).create();
			dialog.show();

		}

	/**
	 * ��ʼ������
	 */
	private void clear() {
		Num1HavePoint = false;
		Num2HavePoint = false;
		ErrorMark = false;
		num1InputText.setEnabled(true);
		operatorInputText.setEnabled(false);
		num2InputText.setEnabled(false);
		num1InputText.setText("");
		operatorInputText.setText("");
		num2InputText.setText("");
		equalInputText.setText("");
		resultInputText.setText("");
		str1 = "";
		str2 = "";
		str3 = "";
		str5 = "";
		num1InputText.requestFocus();
	}

	/**
	 * ��ȡ���е�����
	 */
	private void getInputText() {
		str1 = num1InputText.getText().toString();// ���������1
		str2 = operatorInputText.getText().toString();// ����������
		str3 = num2InputText.getText().toString();// ���������2
		str5 = resultInputText.getText().toString();// �����
	}

	/**
	 * �ı������
	 */
	TextWatcher tw = new TextWatcher() {

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			if (s.length() == 0) {
				clearBtn.setText("�˳�");
			} else {
				clearBtn.setText("���");
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub

		}
	};

}
