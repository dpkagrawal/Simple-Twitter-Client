package com.androidlearning.twitter.fragment;

import org.json.JSONArray;

import android.os.Bundle;

import com.androidlearning.twitter.MyTwitterApp;
import com.androidlearning.twitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TweetsFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getUserTime();
	}
	
	private void getUserTime() {
		MyTwitterApp.getRestClient().getUserTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				getTweetAdapter().addAll(Tweet.parseJsonArray(jsonTweets));
			}
		});
	}

}
