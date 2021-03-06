package com.example.medicalbookingapp;

import java.util.HashMap;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Login page of the app
 *
 */
public class MainActivity extends Activity {
	// components reference
	private Button Register, login, about;
	private EditText usernamein, passwordin;
	private TextView notice;

	/**
	 * Sets up all elements on page
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Register = (Button) findViewById(R.id.Signup);
		login = (Button) findViewById(R.id.login);
		about = (Button) findViewById(R.id.About);

		// About button
		about.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				view();
			}

			public void view() {
				StringBuffer buffer = new StringBuffer();
				buffer.append("This is the first New Zealand medical application  " + "\n");
				buffer.append("which allows New Zealand residents to book  " + "\n");
				buffer.append("doctor�s appointment based on the area.  " + "\n");
				Display("About This Application", buffer.toString());
				return;
			}
		});

		Register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// links patients register and starts the activity
				Intent Intent = new Intent(MainActivity.this, PatientRegister.class);
				startActivity(Intent);
			}
		});

		/*
		 * gets login details from filled textfields
		 */
		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				usernamein = (EditText) findViewById(R.id.logusernamein);
				passwordin = (EditText) findViewById(R.id.logpasswordin);
				notice = (TextView) findViewById(R.id.notice);

				String username = String.valueOf(usernamein.getText().toString());
				String password = String.valueOf(passwordin.getText().toString());

				// creates a new object to get access to the database
				Logindatabase ldb = new Logindatabase(getBaseContext());
				// hashmap for username and passwords
				HashMap uandp = new HashMap<String, String>();
				// assign all the usernames and passwords from database
				uandp = ldb.getdata();

				// check if database is empty
				if (uandp.isEmpty()) {
					notice.setText("If you don't have an account, sign up");
				}

				for (Object key : uandp.keySet()) {

					if (key.equals(username)) {
						String pass = (String) uandp.get(username);

						if (password.equals(pass)) {

							// closes login database
							ldb.close();
							Intent Intent = new Intent(MainActivity.this, Home.class);
							// puts username into the next intent
							Intent.putExtra("username", username);

							// clears username and password fields
							usernamein.getText().clear();
							passwordin.getText().clear();

							startActivity(Intent);
							notice.setText(" ");

							break;

						} else {
							shownoticepass();
						}

					} else {
						notice.setText("Invalid account details! If you don't have an account, sign up");
					}

				}
				// checks for username
				if (username.equals("") || password.equals("")) {
					notice.setText("please enter username and password!");
				}

			}

		});

	}

	/** shows error */
	public void shownoticepass() {
		notice.setText("Wrong Password!");

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
	 * Method that builds an alert dialog that shows the About application to
	 * the user.
	 */
	public void Display(String title, String string) {
		AlertDialog.Builder build = new AlertDialog.Builder(this);
		build.setCancelable(true);
		build.setTitle(title);
		build.setMessage(string);
		build.show();
	}

}