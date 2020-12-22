package com.imageprocessor.imageuploadprocessor.datastore;


import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {
	
	private UUID userProfileId;
	private String userName;
	private String userProfileImage;
	
	public UserProfile(UUID userProfileId, String userName, String userProfileImage) {
		this.userProfileId = userProfileId;
		this.userName = userName;
		this.userProfileImage = userProfileImage;
	}

	public UUID getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(UUID userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Optional<String> getUserProfileImage() {
		return Optional.ofNullable(userProfileImage);
	}

	public void setUserProfileImage(String userProfileImage) {
		this.userProfileImage = userProfileImage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userProfileId, userName, userProfileImage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null || getClass() != obj.getClass())
			return false;
		UserProfile that = (UserProfile) obj;
		return Objects.equals(userProfileId, that.userProfileId) && 
				Objects.equals(userName, that.userName) &&
				Objects.equals(userProfileImage, that.userProfileImage);
	}
}
