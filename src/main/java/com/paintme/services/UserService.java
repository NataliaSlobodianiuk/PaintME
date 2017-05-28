package com.paintme.services;

import com.paintme.PaintMEException;
import com.paintme.domain.models.User;

public interface UserService {
	boolean changePassword(User user, String oldPassword, String newPassword)
			throws PaintMEException;

	User getSessionUser() throws PaintMEException;

	boolean uploadUser(User user) throws PaintMEException;

	boolean unloadUser(User user) throws PaintMEException;
}
