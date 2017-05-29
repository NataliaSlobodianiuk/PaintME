package com.paintme.services;

import com.paintme.PaintMEException;
import com.paintme.domain.models.User;
import com.paintme.domain.models.statuses.UserStatuses;
import com.paintme.domain.repositories.UserRepository;
import com.paintme.security.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Properties;

@Service
public class UserServiceImpl implements UserService {

	private String propertiesFileName = "session.properties";
	private String loginPropertyName = "session.user.name";
	private String passwordPropertyName = "session.user.password";

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean changePassword(User user, String oldPassword, String newPassword)
			throws PaintMEException {
		try {
			if (!(Objects.equals(Hashing.getSecurePassword(
					oldPassword, user.getPasswordSalt(), "SHA-256"),
					user.getPasswordHash()))) {
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

		String newPasswordHash;
		try {
			newPasswordHash = Hashing.getSecurePassword(
					newPassword, newSalt, "SHA-256");
		} catch (NoSuchAlgorithmException exception) {
			throw new PaintMEException(
					"Unknown algorithm for password encoding chosen. " +
							exception.getMessage(), exception);
		}

		user.setPasswordSalt(newSalt);
		user.setPasswordHash(newPasswordHash);

		this.userRepository.save(user);

		return true;
	}

	@Override
	public User getSessionUser() throws PaintMEException {
		String login = this.getProperty(this.loginPropertyName);
		String passwordHash = this.getProperty(this.passwordPropertyName);

		User user = null;
		if (login != null && passwordHash != null) {
			user = this.userRepository
					.findByLoginAndPasswordHash(login, passwordHash);
			if (user == null) {
				throw new PaintMEException(
						"User with login " + login +
								" and password hash " + passwordHash +
								" doesn't exist.");
			}
		}

		return user;
	}

	@Override
	public boolean uploadUser(User user) throws PaintMEException {
		boolean isUploaded;

		String login = user.getLogin();
		String passwordHash = user.getPasswordHash();

		if (user.getStatus() != UserStatuses.OFFLINE){
			throw new PaintMEException(
					"User with login " + login +
							" and password hash " + passwordHash +
							" is already signed in.");
		}

		if (this.userRepository.findByLoginAndPasswordHash(
				login, passwordHash) != null) {
			this.addProperty(this.loginPropertyName, login);
			this.addProperty(this.passwordPropertyName, passwordHash);

			user.setStatus(UserStatuses.ONLINE);
			userRepository.save(user);

			isUploaded = true;
		}
		else {
			throw new PaintMEException(
					"User with login " + login +
							" and password hash " + passwordHash +
							" doesn't exist.");
		}

		return isUploaded;
	}

	@Override
	public boolean unloadUser(User user) throws PaintMEException {
		boolean isUnloaded;

		String login = user.getLogin();
		String passwordHash = user.getPasswordHash();

		if (user.getStatus() == UserStatuses.OFFLINE){
			throw new PaintMEException(
					"User with login " + login +
							" and password hash " + passwordHash +
							" is already signed out.");
		}

		if (this.userRepository.findByLoginAndPasswordHash(
				login, passwordHash) != null) {

			this.removeProperties(
					this.loginPropertyName,
					this.passwordPropertyName);

			user.setStatus(UserStatuses.OFFLINE);
			userRepository.save(user);

			isUnloaded = true;
		}
		else {
			throw new PaintMEException(
					"User with login " + login +
							" and password hash " + passwordHash +
							" doesn't exist.");
		}

		return isUnloaded;
	}

	public String getProperty(String propertyName) throws PaintMEException {
		Properties props;

		try {
			props = this.getProperties();
		}  catch (IOException exception) {
			throw new PaintMEException(
					"Project properties couldn`t be loaded. " +
							exception.getMessage(), exception);
		}

		return props.getProperty(propertyName);
	}

	public void addProperty(String propertyName, String propertyValue)
			throws PaintMEException{

		Properties props = new Properties();
		props.setProperty(propertyName, propertyValue);

		try {
			this.saveProperties(props, true);
		} catch (IOException exception) {
			throw new PaintMEException(
					"Couldn`t save project properties. " +
							exception.getMessage(), exception);
		}
	}

	public void removeProperties(String ... propertyNames)
			throws PaintMEException{

		Properties props;

		try {
			props = this.getProperties();
		}  catch (IOException exception) {
			throw new PaintMEException(
					"Project properties couldn`t be loaded. " +
							exception.getMessage(), exception);
		}

		for (int i = 0; i < propertyNames.length; i++)
		{
			props.remove(propertyNames[i]);
		}

		try {
			this.saveProperties(props, false);
		} catch (IOException exception) {
			throw new PaintMEException(
					"Couldn`t save project properties. " +
							exception.getMessage(), exception);
		}
	}

	private Properties getProperties() throws IOException {
		Properties prop = new Properties();

		FileInputStream in;
		try {
			in = new FileInputStream(
					"src/main/resources/" + this.propertiesFileName);
		} catch (FileNotFoundException exception) {
			throw new FileNotFoundException(
					"Property file '" + this.propertiesFileName
							+ "' not found in the classpath");
		}

		if (in != null) {
			prop.load(in);
		}
		else {
			throw new FileNotFoundException(
					"Property file '" + this.propertiesFileName
							+ "' not found in the classpath");
		}

		in.close();

		return prop;
	}

	private void saveProperties(Properties props, boolean append)
			throws IOException, PaintMEException {

		FileOutputStream out;
		try {
			out = new FileOutputStream(
					"src/main/resources/" + this.propertiesFileName,
					append);
		} catch (FileNotFoundException exception) {
			throw new FileNotFoundException(
					"Property file '" + this.propertiesFileName
							+ "' not found in the classpath");
		}

		try {
			props.store(out, null);
		} catch (IOException exception) {
			throw new PaintMEException(
					"Couldn't write to project properties: " +
							exception.getMessage(), exception);
		}

		out.close();
	}
}
