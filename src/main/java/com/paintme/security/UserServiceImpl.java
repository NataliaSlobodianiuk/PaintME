package com.paintme.security;

import com.paintme.PaintMEException;
import com.paintme.domain.models.User;
import com.paintme.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

@Service
public class UserServiceImpl implements UserService {

	private User sessionUser = null;

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean changePassword(String oldPassword, String newPassword)
			throws PaintMEException {
		try {
			if (!(Hashing.getSecurePassword(
					oldPassword, sessionUser.getPasswordSalt(), "SHA-256") ==
					sessionUser.getPasswordHash())) {
				return false;
			}
		} catch (NoSuchAlgorithmException exception) {
			throw new PaintMEException(
					"Unknown algorithm for password encoding chosen. " +
							exception.getMessage(), exception);
		}

		byte[] newSalt;
		try {
			newSalt = Hashing.getSalt("SHA1PRNG");
		} catch (NoSuchAlgorithmException exception) {
			throw new PaintMEException(
					"Unknown algorithm for salt generation chosen. " +
							exception.getMessage(), exception);
		}

		try {
			sessionUser.setPasswordHash(Hashing.getSecurePassword(
					newPassword, newSalt, "SHA-256"));
		} catch (NoSuchAlgorithmException exception) {
			throw new PaintMEException(
					"Unknown algorithm for password encoding chosen. " +
							exception.getMessage(), exception);
		}
		sessionUser.setPasswordSalt(newSalt);

		this.userRepository.save(sessionUser);
		return true;
	}

	@Override
	public User loadUser() throws PaintMEException {
		Properties prop = new Properties();
		InputStream in = getClass().getResourceAsStream("application.properties");
		try {
			prop.load(in);
		} catch (IOException exception) {
			throw new PaintMEException(
					"Project properties couldn`t be loaded. " +
							exception.getMessage(), exception);
		}

		String login = prop.getProperty("security.user.name");
		String passwordHash = prop.getProperty("security.user.password");

		User user = null;

		if (login != null && passwordHash != null) {
			user = this.userRepository.findByLoginAndPasswordHash(login, passwordHash);
			if (user == null) {
				throw new PaintMEException(
						"User with login " + login +
								" and password hash " + passwordHash +
								" doesn't exist.");
			}
		}

		return user;
	}
}
