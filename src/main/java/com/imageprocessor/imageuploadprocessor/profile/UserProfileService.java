package com.imageprocessor.imageuploadprocessor.profile;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imageprocessor.imageuploadprocessor.datastore.UserProfile;

@Service
public class UserProfileService {
	
	private final UserProfileDataAccessService userProfileDataAccessService;

	@Autowired
	public UserProfileService(UserProfileDataAccessService userProfileDataAccessService) {
		this.userProfileDataAccessService = userProfileDataAccessService;
	}
	
	List<UserProfile> getUserProfiles() {
		return userProfileDataAccessService.getUserProfiles();
	}

	public void uploadImage(UUID userProfileId, MultipartFile file) {
		
	}
}
