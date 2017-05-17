package com.paintme.security;

import com.paintme.PaintMEException;
import com.paintme.domain.models.User;
import com.paintme.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean changePassword(User user, String oldPassword, String newPassword)
			throws PaintMEException {
		try {
			if (!(SHA256.getSHA256SecurePassword(
					oldPassword, user.getPasswordSalt(), "SHA-256") ==
					user.getPasswordHash())) {
				return false;
			}
		} catch (NoSuchAlgorithmException exception) {
			throw new PaintMEException(
					"Unknown algorithm for password encoding chosen. " +
							exception.getMessage(), exception);
		}

		byte[] newSalt;
		try {
			newSalt = SHA256.getSalt("SHA1PRNG");
		} catch (NoSuchAlgorithmException exception) {
			throw new PaintMEException(
					"Unknown algorithm for salt generation chosen. " +
							exception.getMessage(), exception);
		}

		try {
			user.setPasswordHash(SHA256.getSHA256SecurePassword(
					newPassword, newSalt, "SHA-256"));
		} catch (NoSuchAlgorithmException exception) {
			throw new PaintMEException(
					"Unknown algorithm for password encoding chosen. " +
							exception.getMessage(), exception);
		}
		user.setPasswordSalt(newSalt);

		this.userRepository.save(user);
		return true;
	}
}
