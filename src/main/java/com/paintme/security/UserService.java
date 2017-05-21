package com.paintme.security;

import com.paintme.PaintMEException;
import com.paintme.domain.models.User;

public interface UserService {
	boolean changePassword(String oldPassword, String newPassword)
			throws PaintMEException;

	User loadUser() throws PaintMEException;
}
