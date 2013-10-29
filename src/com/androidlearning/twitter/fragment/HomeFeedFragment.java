package com.androidlearning.twitter.fragment;

import org.json.JSONArray;

import com.androidlearning.twitter.EndlessScrollListener;
import com.androidlearning.twitter.MyTwitterApp;
import com.androidlearning.twitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeFeedFragment extends TweetsFragment {

	private String TWEET_COUNT = "20";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RequestParams params = new RequestParams();
		params.put("count", TWEET_COUNT);
		getMoreTweets(params);
		
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setupOnScroll();
	}

	private void setupOnScroll() {
		lvTweets.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				RequestParams params = new RequestParams();
				params.put("count", TWEET_COUNT);
				Tweet t = tweetAdapter.getItem(tweetAdapter.getCount() - 1);
				params.put("max_id", t.getId());
				getMoreTweets(params);
			}
		});
	}

	private void getMoreTweets(RequestParams parameters) {
		MyTwitterApp.getRestClient().getTweets(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				getTweetAdapter().addAll(Tweet.parseJsonArray(jsonTweets));
			}
		}, parameters);
	}
}
