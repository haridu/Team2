package com.example.medicalbookingapp.test;

import com.example.medicalbookingapp.BookingPage;
import com.example.medicalbookingapp.Home;
import com.example.medicalbookingapp.MainActivity;
import com.example.medicalbookingapp.R;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

/*Class that tests the layout and button functionality of the home page */

public class HomeTests extends ActivityInstrumentationTestCase2<Home> {

	public HomeTests() {
		super(Home.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	/*
	 * Test to check if there is a textview of the home page, showing the users
	 * username.
	 */
	@SmallTest
	public void testTextView() {
		TextView testName = (TextView) getActivity().findViewById(R.id.wname);
		assertNotNull(testName);
	}

	/*
	 * Test to check if there is a button for making bookings in our home page.
	 */
	@SmallTest
	public void testMakeBookingBtn() {
		Button makeBook = (Button) getActivity().findViewById(R.id.button1);
		assertNotNull(makeBook);
	}

	/*
	 * Test to check if there is a button for view bookings in our home page.
	 */
	@SmallTest
	public void testViewBookingBtn() {
		Button viewBook = (Button) getActivity().findViewById(R.id.viewBtn);
		assertNotNull(viewBook);
	}

	/*
	 * Test to check if there is a button for about bookings in our home page.
	 */
	@SmallTest
	public void testAboutBtn() {
		Button about = (Button) getActivity().findViewById(R.id.aboutBtn);
		assertNotNull(about);
	}

	/*
	 * Test to check if there is a button for logging out of the app in our home
	 * page.
	 */
	@SmallTest
	public void testLogoutBtn() {
		Button lgOut = (Button) getActivity().findViewById(R.id.logoutBtn);
		assertNotNull(lgOut);
	}

	/* Test to see if the make bookings button opens the bookings page */
	@MediumTest
	public void testStartMakeBookings() {
		ActivityMonitor monitor = getInstrumentation().addMonitor(BookingPage.class.getName(), null, false);

		Button bookingLaunch = (Button) getActivity().findViewById(R.id.button1);
		TouchUtils.clickView(this, bookingLaunch);

		BookingPage bpTest = (BookingPage) monitor.waitForActivityWithTimeout(2000);
		assertNotNull(bpTest);

		// Press back to return to original activity
		this.sendKeys(KeyEvent.KEYCODE_BACK);
	}

	/* Test to see if the logout button takes user to main / login page */
	@MediumTest
	public void testLogout() {
		ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
		Button lgoutBtn = (Button) getActivity().findViewById(R.id.logoutBtn);
		TouchUtils.clickView(this, lgoutBtn);

		MainActivity logoutTest = (MainActivity) monitor.waitForActivityWithTimeout(2000);
		assertNotNull(logoutTest);

		// Press back to return to original activity
		this.sendKeys(KeyEvent.KEYCODE_BACK);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
}
