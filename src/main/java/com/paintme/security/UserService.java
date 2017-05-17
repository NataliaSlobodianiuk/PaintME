package com.paintme.security;

import com.paintme.PaintMEException;
import com.paintme.domain.models.User;

import java.security.NoSuchAlgorithmException;

public interface UserService {
	boolean changePassword(User user, String oldPassword, String newPassword) throws NoSuchAlgorithmException, PaintMEException;
}
