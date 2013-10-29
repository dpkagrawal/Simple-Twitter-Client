package com.androidlearning.twitter.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.Profile;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.androidlearning.twitter.ProfileActivity;
import com.androidlearning.twitter.R;
import com.androidlearning.twitter.fragment.HomeFeedFragment;
import com.androidlearning.twitter.fragment.MentionsFragment;
import com.androidlearning.twitter.fragment.TweetsFragment;
import com.androidlearning.twitter.models.Tweet;

public class HomeFeedActivity extends FragmentActivity implements TabListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_feed);
		setupNavigationBar();
	}

	private void setupNavigationBar() {
		ActionBar ab = getActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ab.setDisplayShowTitleEnabled(true);
		Tab tabHome = ab.newTab().setText("Home")
				.setTag("HomeTimelineFragment").setTabListener(this);
		Tab tabMention = ab.newTab().setText("Mention")
				.setTag("MentionFragment").setTabListener(this);
		ab.addTab(tabHome);
		ab.addTab(tabMention);
		ab.selectTab(tabHome);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home_feed, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_compose:
			startComposeActivity();
			return true;
		case R.id.action_profile:
			startProfileActivity();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void startProfileActivity() {
		Intent i = new Intent(this, ProfileActivity.class);
		i.putExtra("current_user", true );
		startActivity(i);
	}

	public void startComposeActivity() {
		Intent i = new Intent(this, ComposeActivity.class);
		startActivityForResult(i, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 //tweetFragment.getTweetAdapter().insert((Tweet)
		 //data.getSerializableExtra("tweet"),0);
	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction fts = fm.beginTransaction();
		if (tab.getTag() == "HomeTimelineFragment") {
			fts.replace(R.id.frame_container, new HomeFeedFragment());
		} else {
			fts.replace(R.id.frame_container, new MentionsFragment());
		}
		fts.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
	
	public void onProfileImageClick(View v) {
		Intent i = new Intent(this, ProfileActivity.class);
		//ImageView imgView = (ImageView) v;
		i.putExtra("current_user", false );
		i.putExtra("screen_name", v.getTag().toString());
		startActivity(i);
	}
}