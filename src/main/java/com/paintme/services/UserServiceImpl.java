package com.paintme.services;

import com.paintme.PaintMEException;
import com.paintme.domain.models.User;
import com.paintme.domain.repositories.UserRepository;
import com.paintme.security.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Properties;

@Service
public class UserServiceImpl implements UserService {

	private User sessionUser = null;

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean changePassword(String oldPassword, String newPassword)
			throws PaintMEException {
		try {
			if (!(Objects.equals(Hashing.getSecurePassword(
					oldPassword, sessionUser.getPasswordSalt(), "SHA-256"),
					sessionUser.getPasswordHash()))) {
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
	public void loadUser() throws PaintMEException {
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

		if (login != null && passwordHash != null) {
			sessionUser = this.userRepository.findByLoginAndPasswordHash(login, passwordHash);
			if (sessionUser == null) {
				throw new PaintMEException(
						"User with login " + login +
								" and password hash " + passwordHash +
								" doesn't exist.");
			}
		}
	}
}
