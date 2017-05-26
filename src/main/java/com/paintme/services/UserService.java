package com.paintme.services;

import com.paintme.PaintMEException;
import com.paintme.domain.models.User;

public interface UserService {
	boolean changePassword(User user, String oldPassword, String newPassword)
			throws PaintMEException;

	User loadUser() throws PaintMEException;
}
