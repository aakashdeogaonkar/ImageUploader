package com.imageprocessor.imageuploadprocessor.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.imageprocessor.imageuploadprocessor.datastore.UserProfile;

@Repository
public class UserData {
	
	private static final List<UserProfile> USER_PROFILES = new ArrayList<>();
	
	static {
		USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Diksha", null));
		USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Dikshe", null));
	}
	
	public List<UserProfile> getUserProfile() {
		return USER_PROFILES;
	}
}
