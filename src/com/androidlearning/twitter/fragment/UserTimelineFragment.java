package com.androidlearning.twitter.fragment;

import org.json.JSONArray;

import android.os.Bundle;

import com.androidlearning.twitter.MyTwitterApp;
import com.androidlearning.twitter.activity.ProfileActivity;
import com.androidlearning.twitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TweetsFragment {
	String screenName;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getUserTime();
	}
	
	private void getUserTime() {
		//Intent i = (Intent) getActivity().getIntent();
		ProfileActivity prof = (ProfileActivity) getActivity();
		MyTwitterApp.getRestClient().getUserTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				getTweetAdapter().addAll(Tweet.parseJsonArray(jsonTweets));
			}
		}, getScreenName());
	}
	
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getScreenName() {
		return this.screenName;
	}

}
