package com.paintme.services;

import com.paintme.PaintMEException;
import com.paintme.domain.models.User;
import com.paintme.domain.models.statuses.UserStatuses;
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

	public static User sessionUser = null;

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
		String login = this.getProperty("security.user.name");
		String passwordHash = this.getProperty("security.user.password");

		if (login != null && passwordHash != null) {
			User user = this.userRepository.findByLoginAndPasswordHash(login, passwordHash);
			if (user == null) {
				throw new PaintMEException(
						"User with login " + login +
								" and password hash " + passwordHash +
								" doesn't exist.");
			}
			else if (user.getStatus() != UserStatuses.OFFLINE){
			}
		}
	}

	public String getProperty(String propertyName) throws PaintMEException {
		Properties prop;

		try {
			prop = this.getProperties();
		}  catch (IOException exception) {
			throw new PaintMEException(
					"Project properties couldn`t be loaded. " +
							exception.getMessage(), exception);
		}

		return prop.getProperty(propertyName);
	}

	private Properties getProperties() throws IOException {
		Properties prop = new Properties();

		InputStream in = getClass().getResourceAsStream("application.properties");
		prop.load(in);

		return prop;
	}
}
