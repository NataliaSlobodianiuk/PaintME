package com.paintme.services;

import com.paintme.PaintMEException;

public interface UserService {
	boolean changePassword(String oldPassword, String newPassword)
			throws PaintMEException;

	void loadUser() throws PaintMEException;
}
