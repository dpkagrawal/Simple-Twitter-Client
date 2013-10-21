package com.androidlearning.twitter.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String location;
	private String userProfileImage;
	String screenName;

	public User() {}

	public User(String name, String location) {
		this.name = name;
		this.location = location;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProfileUserImage() {
		return userProfileImage;
	}

	public void setProfileUserImage(String userProfileImage) {
		this.userProfileImage = userProfileImage;
	}
	
	public static User parse(JSONObject json) {
		User user = new User();
		try {
			user.name = json.getString("name");
			user.location = json.getString("location");
			user.userProfileImage = json.getString("profile_image_url");
			user.screenName = json.getString("screen_name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}
}