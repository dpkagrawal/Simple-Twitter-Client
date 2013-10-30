package com.androidlearning.twitter.activity;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidlearning.twitter.MyTwitterApp;
import com.androidlearning.twitter.R;
import com.androidlearning.twitter.fragment.UserTimelineFragment;
import com.androidlearning.twitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity {
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		Intent i = getIntent();
		String screenName = i.getStringExtra("screen_name");
		UserTimelineFragment uf = (UserTimelineFragment)getSupportFragmentManager().findFragmentById(R.id.userTimelineFragment);
		uf.setScreenName(screenName);
		getUserInfoByScreenName(screenName);
	}

	private void populateProfileFields() {
		getActionBar().setTitle("@" + user.getScreenName());

		ImageView imgView = (ImageView) findViewById(R.id.profileImage);
		TextView userName = (TextView) findViewById(R.id.userName);
		TextView following = (TextView) findViewById(R.id.userFollowing);
		TextView follower = (TextView) findViewById(R.id.userFollower);
		TextView tagline = (TextView) findViewById(R.id.userTagline);

		ImageLoader.getInstance().displayImage(user.getProfileUserImage(),
				imgView);
		userName.setText(user.getName());

		following.setText(user.getFollowing() + " Following");
		follower.setText(user.getFollowersCount() + " Follower");
		tagline.setText(user.getTagLine());
	}

	public void getUserInfo() {
		MyTwitterApp.getRestClient().getUserInfo(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				user = User.parse(json);
				populateProfileFields();
			}
		});
	}

	public void getUserInfoByScreenName(String screenName) {
		MyTwitterApp.getRestClient().getUserInfoByScreenName(
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject json) {
						user = User.parse(json);
						populateProfileFields();
					}
				}, screenName);
	}
	
	public User getUser() {
		return user;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
