package com.paintme.services;

import com.paintme.PaintMEException;
import com.paintme.domain.models.User;

public interface UserService {
	boolean changePassword(User user, String oldPassword, String newPassword)
			throws PaintMEException;

	User getSessionUser() throws PaintMEException;

	void setSessionUser(String login, String password) throws PaintMEException;

	void removeSessionUser() throws PaintMEException;
}
