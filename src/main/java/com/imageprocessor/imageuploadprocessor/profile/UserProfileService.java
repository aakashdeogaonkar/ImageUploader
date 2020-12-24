package com.imageprocessor.imageuploadprocessor.profile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imageprocessor.imageuploadprocessor.bucket.BucketName;
import com.imageprocessor.imageuploadprocessor.datastore.UserProfile;
import com.imageprocessor.imageuploadprocessor.filestore.FileStore;

@Service
public class UserProfileService {
	
	private final UserProfileDataAccessService userProfileDataAccessService;
	private final FileStore fileStore;

	@Autowired
	public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
		this.userProfileDataAccessService = userProfileDataAccessService;
		this.fileStore = fileStore;
	}
	
	List<UserProfile> getUserProfiles() {
		return userProfileDataAccessService.getUserProfiles();
	}

	public void uploadImage(UUID userProfileId, MultipartFile file) {
		// 1. Check if image is not empty
		if(file.isEmpty()) {
			throw new IllegalStateException("Cannot upload empty image [ " + file.getSize() + "]");
		}
		
		// 2. If the file is an image
		if(!Arrays.asList(ContentType.IMAGE_GIF.getMimeType(), ContentType.IMAGE_JPEG.getMimeType(), 
				ContentType.IMAGE_PNG.getMimeType()).contains(file.getContentType())) {
			throw new IllegalStateException("File must be an image [ " + file.getContentType() + "]");
		}
		
		//3. The user exists in the database
		UserProfile user = userProfileDataAccessService
				.getUserProfiles()
				.stream()
				.filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
		
		//4. Grab some metadat from file if any
		Map<String, String> metadata = new HashMap<String, String>();
		metadata.put("Content-Type", file.getContentType());
		metadata.put("Content-Length", String.valueOf(file.getSize()));
		
		//5. Store the image in S3 and update database (userProfileImageLink) with S3 image link
		String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
		String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
		try {
			fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}












