package com.imageprocessor.imageuploadprocessor.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.imageprocessor.imageuploadprocessor.datastore.UserProfile;

@Repository
public class UserProfileDataAccessService {

	private final UserData userData;

	@Autowired
	public UserProfileDataAccessService(UserData userData) {
		this.userData = userData;
	}
	
	List<UserProfile> getUserProfiles() {
		return userData.getUserProfile();
	}
}
