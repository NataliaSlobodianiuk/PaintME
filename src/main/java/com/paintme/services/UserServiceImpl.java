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
	private String passwordHashPropertyName = "session.user.password";

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
		String passwordHash = this.getProperty(this.passwordHashPropertyName);

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
	public void setSessionUser(String login, String password) throws PaintMEException {
        User user = this.userRepository.findByLogin(login);

        if (user != null) {
            if (!this.isPasswordCorrect(user, password)) {
                throw new PaintMEException(
                        "User with login " + login +
                                " has different password (not " + password + ")");
            } else {
                if (user.getStatus() != UserStatuses.OFFLINE) {
                    throw new PaintMEException(
                            "User with login '" + login +
                                    "' and password '" + password +
                                    "' is already signed in.");
                } else {
                    user.setStatus(UserStatuses.ONLINE);
                    this.userRepository.save(user);

                    this.addProperty(this.loginPropertyName, user.getLogin());
                    this.addProperty(this.passwordHashPropertyName, user.getPasswordHash());
                }
            }
        }
	}

	@Override
	public void removeSessionUser() throws PaintMEException {
	    User sessionUser = this.getSessionUser();

	    if (sessionUser != null)
        {
            if (sessionUser.getStatus() == UserStatuses.OFFLINE){
                throw new PaintMEException(
                        "User with login " + sessionUser.getLogin() +
                                " is already signed out.");
            }
            else
            {
                sessionUser.setStatus(UserStatuses.OFFLINE);
                this.userRepository.save(sessionUser);

                this.deleteProperty(this.loginPropertyName);
                this.deleteProperty(this.passwordHashPropertyName);
            }
        }
        else {
	        throw new PaintMEException(
                "No session user found.");
        }
	}

	private boolean isPasswordCorrect(User user, String password) throws PaintMEException {
        String passwordHash;
        try {
            passwordHash = Hashing.getSecurePassword(password, user.getPasswordSalt(), "SHA-256");
        }
        catch (NoSuchAlgorithmException exception){
            throw new PaintMEException("Unknown algorithm was used for getting user's password hash.");
        }

        return  Objects.equals(user.getPasswordHash(), passwordHash);
    }

	private String getProperty(String propertyName) throws PaintMEException {
		Properties props = this.loadProperties();

		return props.getProperty(propertyName);
	}

	private void deleteProperty(String propertyName) throws  PaintMEException {
        Properties props = this.loadProperties();

        props.remove(propertyName);

        this.storeProperties(props);
    }

    private void updateProperty(String propertyName, String newValue) throws PaintMEException {
	    Properties props = this.loadProperties();

	    props.setProperty(propertyName, newValue);

	    this.storeProperties(props);
    }

    private void addProperty(String propertyName, String value) throws PaintMEException
    {
        Properties props = this.loadProperties();

        props.put(propertyName, value);

        this.storeProperties(props);
    }

    private Properties loadProperties() throws PaintMEException {
        Properties props = new Properties();

        try {
        FileInputStream in = this.getFileForReading();
        props.load(in);
        in.close();
        }
        catch (IOException exception) {
            throw new PaintMEException(
                    "Project properties couldn`t be loaded. " +
                            exception.getMessage(), exception);
        }

        return props;
    }

    private void storeProperties(Properties props) throws PaintMEException {
        try {
            FileOutputStream out = this.getFileForWriting();
            props.store(out, null);
            out.close();
        }
        catch (IOException exception) {
            throw new PaintMEException(
                    "Project properties couldn`t be stored. " +
                            exception.getMessage(), exception);
        }
    }

    private FileOutputStream getFileForWriting() throws  IOException {
        FileOutputStream out;
        try {
            out = new FileOutputStream(
                    "src/main/resources/" + this.propertiesFileName);
        } catch (FileNotFoundException exception) {
            throw new FileNotFoundException(
                    "Property file '" + this.propertiesFileName
                            + "' not found in the classpath");
        }

        return out;
    }

    private FileInputStream getFileForReading() throws  IOException {
        FileInputStream in;
        try {
            in = new FileInputStream(
                    "src/main/resources/" + this.propertiesFileName);
        } catch (FileNotFoundException exception) {
            throw new FileNotFoundException(
                    "Property file '" + this.propertiesFileName
                            + "' not found in the classpath");
        }

        return in;
    }
}
