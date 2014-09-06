package com.example.tipcalculator;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	DecimalFormat twoDForm = new DecimalFormat("#.##");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		/*
		 * if (savedInstanceState == null) {
		 * getFragmentManager().beginTransaction() .add(R.id.container, new
		 * PlaceholderFragment()).commit(); }
		 */
		// Add listener for amount edit text
		EditText etAmount = (EditText) findViewById(R.id.etBillAmount);
		etAmount.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				calculateAndDispTip(null);
			}
		});

		// Add listener for tip percentage
		EditText etTip = (EditText) findViewById(R.id.etTipPercent);
		etTip.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				calculateAndDispTip(null);
			}
		});

	}

	/**
	 * On click listener for 10% button
	 * 
	 * @param v
	 */
	public void addTip10(View v) {
		calculateAndDispTip("10");
		resetTipET();
	}

	/**
	 * On click listener for 15% button
	 * 
	 * @param v
	 */
	public void addTip15(View v) {
		calculateAndDispTip("15");
		resetTipET();
	}

	/**
	 * On click listener for 20% button
	 * 
	 * @param v
	 */
	public void addTip20(View v) {
		calculateAndDispTip("20");
		resetTipET();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	/**
	 * Calculate and display final amount.
	 * 
	 * @param tipStr
	 *            null if custom %age by user else, string value
	 */
	public void calculateAndDispTip(String tipStr) {

		// get amount text
		EditText etAmount = (EditText) findViewById(R.id.etBillAmount);
		String amountStr = etAmount.getText().toString();
		if (amountStr == null || amountStr.length() == 0
				|| amountStr.charAt(amountStr.length() - 1) == '.') {
			return;
		}

		// get tip text
		EditText etTip = (EditText) findViewById(R.id.etTipPercent);
		if (tipStr == null) {
			tipStr = etTip.getText().toString();
		}

		if (tipStr == null || tipStr.length() == 0
				|| tipStr.charAt(tipStr.length() - 1) == '.') {
			return;
		}

		// convert the amount to double
		double amount = 0.00;
		double tip = 0.00;
		try {
			amount = Double.parseDouble(amountStr);
			tip = (Double.parseDouble(tipStr) * 0.01) + 1.00;
		} catch (NumberFormatException e) {
			return;
		}

		double totalAmount = amount * tip;

		TextView tvAmount = (TextView) findViewById(R.id.tvTotalAmount);
		tvAmount.setText("$ "
				+ String.valueOf(Double.valueOf(twoDForm.format(totalAmount))));
	}

	/**
	 * Reset the tip percentage ET once the %age button is clicked.
	 */
	public void resetTipET() {
		EditText etTip = (EditText) findViewById(R.id.etTipPercent);
		etTip.setText("");
	}

}
