package com.androidlearning.twitter.fragment;

import org.json.JSONArray;

import android.os.Bundle;

import com.androidlearning.twitter.MyTwitterApp;
import com.androidlearning.twitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MentionsFragment extends TweetsFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		RequestParams params = new RequestParams();
		params.put("count", "10");
		getMentions(params);
	}
	
	private void getMentions(RequestParams parameters) {
		MyTwitterApp.getRestClient().getMentionsTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				getTweetAdapter().addAll(Tweet.parseJsonArray(jsonTweets));
			}
		}, parameters);
	}
}
