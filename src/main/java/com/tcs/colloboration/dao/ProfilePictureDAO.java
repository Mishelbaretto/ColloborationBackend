package com.tcs.colloboration.dao;

import com.tcs.colloboration.model.ProfilePicture;

public interface ProfilePictureDAO {

	public boolean save(ProfilePicture profilePicture );
	public ProfilePicture getProfilePicture(String emailID);
}
