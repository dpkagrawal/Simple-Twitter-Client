package com.androidlearning.twitter.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.androidlearning.twitter.R;
import com.androidlearning.twitter.adapter.TweetAdapter;
import com.androidlearning.twitter.models.Tweet;

public class TweetsFragment extends Fragment {
	ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	TweetAdapter tweetAdapter;
	ListView lvTweets;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tweet_list, container,
				false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
		tweetAdapter = new TweetAdapter(getActivity(), tweets);
		lvTweets.setAdapter(tweetAdapter);
	}

	public TweetAdapter getTweetAdapter() {
		return tweetAdapter;
	}
}
